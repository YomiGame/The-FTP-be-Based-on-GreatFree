package trycloud.server;

import org.greatfree.message.ServerMessage;
import org.greatfree.message.container.Notification;
import org.greatfree.message.container.Request;
import org.greatfree.server.container.ServerTask;

import trycloud.message.*;


import java.io.*;


class LibraryTask implements ServerTask , Serializable
{
    private String FileURLResources;
    private byte[][] ResourceByte;
    private String FileName;
    private int FileLength;

    private String SeverFilePath = "";

    @Override
    public void processNotification(Notification arg0)
    {
        if (arg0.getApplicationID() == LibraryApplicationID.SEND_FILE_URL_NOTIFICATION)
        {
            SendUrlToServerNotification FileUrlData = (SendUrlToServerNotification) arg0;
            this.SeverFilePath = FileUrlData.getFileUrl();
        }
    }

    @Override
    public ServerMessage processRequest(Request arg0)
    {
        PathContrler pathCtrl = new PathContrler();
        if(this.SeverFilePath.equals(pathCtrl.GetAndChangeServerFilePath(null)) || this.SeverFilePath.equals(""))
        {
            this.SeverFilePath = pathCtrl.GetAndChangeServerFilePath(null);
        }

        switch (arg0.getApplicationID())
        {

            //Send File to Client
            case LibraryApplicationID.RETURN_FILE_Rq:
                ReturnFileRq FileLength = (ReturnFileRq) arg0;
                int tableLength = FileLength.getTableLength();
                //System.out.println("Server Send File table < "+tableLength+" >");
                return new ReturnFileRp(this.ResourceByte[tableLength]);

            case LibraryApplicationID.RETURN_FILE_NATIVE_Rq:
                ReturnFileNativeRq fileUrl = (ReturnFileNativeRq) arg0;
                this.FileURLResources = fileUrl.getFileUrl();
                try {
                    HandleDataToList DataList = new HandleDataToList(this.FileURLResources);
                    this.ResourceByte = DataList.getData();
                    System.out.println("You want to Get File " + DataList.getName());
                    return new ReturnFileNativeRp(DataList.getName(),this.ResourceByte.length);
                } catch (IOException e) {
                    System.out.println("Server Get File Failure");
                    e.printStackTrace();
                    return new ReturnFileNativeRp(null,0);// NUll Protect
                }

            case LibraryApplicationID.RETURN_FILE_URL_Rq:
                ReturnFileUrlRq qFilePath = (ReturnFileUrlRq) arg0;
                System.out.println("You want to get file path " + qFilePath.getFileUrl());
                return new ReturnFileUrlRp(qFilePath.getFileUrl());

            case LibraryApplicationID.RENAME_Rq:
                ReNameRq RenameData = (ReNameRq)arg0;
                return new ReturnReNameRp(new ChangeTheFileNature("ReName",RenameData.getNameUrl_ReName(),RenameData.getNewNameUrl_reName()).getFileChangeNature());

            case LibraryApplicationID.DELETE_FILE_Pq:
                DelectFileRq DelFileData = (DelectFileRq)arg0;
                return new ReturnDeleteFileRp(new ChangeTheFileNature("DeleteFile",DelFileData.getNameUrl_DelFile(),null).getFileChangeNature());

            //Get File From Client
            case LibraryApplicationID.SEND_FILE_NATIVE_Rq:
                SendFileNativeRq sendFileData = (SendFileNativeRq)arg0;
                String fileName = sendFileData.getName();
                int fileLength = sendFileData.getLength();
                this.FileName = fileName;
                this.FileLength = fileLength;
                this.ResourceByte = new byte[fileLength][];
                return new SendFileNativeRp(fileLength);

            case LibraryApplicationID.SEND_FILE_Rq:
                SendFileRq FileData = (SendFileRq)arg0;
                this.ResourceByte[FileData.getSub()] = FileData.getFileTable();
                System.out.println("\n"+"< "+FileData.getSub()+" >  Have Get All < "+(this.FileLength-1)+" > \n");
                if(FileData.getSub() < (this.FileLength-1)) {
                    return new SendFileRp(FileData.getSub() + 1);
                }else
                {
                    System.out.println("\n"+"< "+this.FileName+" >  the File All Get"+"\n");
                    byte[] allData = new ByteToAllByte(this.ResourceByte).getAllByte();
                    try {
                        System.out.println("File will Save as "+this.SeverFilePath);
                        new HandleDataToFile(allData,this.FileName,this.SeverFilePath);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Save File Failure");
                    }
                }
                break;
            case LibraryApplicationID.CHANGE_FILE_Rq:
                ChangeFilePathRq FilePathData = (ChangeFilePathRq)arg0;
                String FilePath = pathCtrl.GetAndChangeServerFilePath(FilePathData.GetNewFilePath());
                this.SeverFilePath = FilePath;
                System.out.println("Change Server File Path Succeed   "+this.SeverFilePath);
                return new ChangeFilePathRp(FilePath,this.SeverFilePath);
        }
        return null;
    }
}

