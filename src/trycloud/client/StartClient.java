package trycloud.client;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.greatfree.client.StandaloneClient;
import org.greatfree.exceptions.RemoteReadException;

import trycloud.message.*;

import static trycloud.message.Config.*;

class StartClient
{



    public static void main(String[] args) throws ClassNotFoundException, RemoteReadException, IOException, InterruptedException
    {
        ArrayList<String> CheckIPList = new ArrayList<>();
        ArrayList<String> CheckPathList = new ArrayList<>();
        for (int i = 0;i<=(SERVER_CONFIG.length -1); i++)
        {
            InspectIpAndPort SocketTest = new InspectIpAndPort(SERVER_CONFIG[i][0]);
            if(SocketTest.getServerIP() != null)
            {
                CheckIPList.add(SERVER_CONFIG[i][0]);
                CheckPathList.add(SERVER_CONFIG[i][1]);
            }
        }
        if(CheckIPList.size() > 0||CheckPathList.size() > 0)
        {
            StandaloneClient.CS().init();
            IPChoice ServerIp = new IPChoice();
            PathContrler ServerFileUrl = new PathContrler();
            ServerIp.GetAndChangeServerIP(CheckIPList.get(0));
            ServerFileUrl.GetAndChangeServerFilePath(CheckPathList.get(0));
            System.out.println(CLIENT_DEFAULT_IP+ServerIp.GetAndChangeServerIP(null)+CLIENT_DEFAULT_URL+ServerFileUrl.GetAndChangeServerFilePath(null));
            Switch(ServerIp,ServerFileUrl);
        }
        else {
            System.out.println(CLIENT_NO_SERVER);
        }
    }

    private static void Switch(IPChoice ServerIp,PathContrler ServerFileUrl)throws ClassNotFoundException, RemoteReadException, IOException, InterruptedException
    {
        System.out.println("\n"+"Choice the Features"+CLIENT_HINT+CLIENT_CHOICE_ZEN+CLIENT_CHOICE_ONE+CLIENT_CHOICE_TWO+CLIENT_CHOICE_THREE+CLIENT_CHOICE_FOUR+CLIENT_CHOICE_FIFE+CLIENT_CHOICE_LAST);
        Scanner inPut = new Scanner(System.in);
        String numChoice  = inPut.nextLine();

        switch (numChoice) {
            case "0":
                ArrayList<String> IPList = new ArrayList<>();
                ArrayList<String> PathList = new ArrayList<>();
                for (int i = 0;i<=(SERVER_CONFIG.length -1); i++)
                {
                    InspectIpAndPort SocketTest = new InspectIpAndPort(SERVER_CONFIG[i][0]);
                        if(SocketTest.getServerIP() != null)
                        {
                            IPList.add(SERVER_CONFIG[i][0]);
                            PathList.add(SERVER_CONFIG[i][1]);
                        }
                }
                ServerIp.GetAndChangeServerIP(IPList.get(0));//Set Default Server IP
                System.out.println("\n Choice Your Target Server's IP , default IP is : "+ServerIp.GetAndChangeServerIP(null)+"\n");
                for (int a = 0;a<=(IPList.size() - 1); a++)
                {
                    System.out.println(a+" :  "+IPList.get(a)+"\n");
                }
                numChoice = inPut.nextLine();
                try {
                    int InputNum = Integer.parseInt(numChoice);
                    if(InputNum <= (IPList.size() - 1))
                     {
                         ServerIp.GetAndChangeServerIP(IPList.get(InputNum));
                         ServerFileUrl.GetAndChangeServerFilePath(PathList.get(InputNum));
                         System.out.println("Now IP is :"+ServerIp.GetAndChangeServerIP(null));
                         System.out.println("Now file url is :"+ServerFileUrl.GetAndChangeServerFilePath(null));
                         StandaloneClient.CS().syncNotify(ServerIp.GetAndChangeServerIP(null) , 6420 , new SendUrlToServerNotification(PathList.get(InputNum)));
                         Switch(ServerIp,ServerFileUrl);
                     }
                }catch (NumberFormatException e)
                {
                    System.out.println("\n"+"You not choice IP  \n");
                    Switch(ServerIp,ServerFileUrl);
                }

            case "1"://Get From the Server
                ReturnFileUrlRp getSeverFileList = (ReturnFileUrlRp) StandaloneClient.CS().read(ServerIp.GetAndChangeServerIP(null), 6420, new ReturnFileUrlRq(ServerFileUrl.GetAndChangeServerFilePath(null)));
                //Rp data = (Rp) StandaloneClient.CS().read(IP,Ip port,Rq)
                printlnAllPath(getSeverFileList.getServerFilePath());
                System.out.println("Choice the file, please write the Index in the next line");
                numChoice = inPut.nextLine();

                try {
                    int InputNum = Integer.parseInt(numChoice);
                    if (getSeverFileList.getServerFilePath().size() >= InputNum)//Null protect
                    {
                        System.out.println("You choice is " + InputNum + " file path: " + getSeverFileList.getServerFilePath().get(InputNum));
                        String GetServerPath = getSeverFileList.getServerFilePath().get(InputNum);
                        ReturnFileNativeRp fileNative = (ReturnFileNativeRp) StandaloneClient.CS().read(ServerIp.GetAndChangeServerIP(null), 6420, new ReturnFileNativeRq(GetServerPath));
                        if (fileNative.getFileLength() == 0 || fileNative.getFileName() == null) {
                            System.out.println("SeverFile have been Change by other User");
                            Switch(ServerIp,ServerFileUrl);
                        } else {
                            byte[][] FileDataByte = new byte[fileNative.getFileLength()][];
                            for (int i = 0; i < (fileNative.getFileLength()); i++) {
                                ReturnFileRp fileData = (ReturnFileRp) StandaloneClient.CS().read(ServerIp.GetAndChangeServerIP(null), 6420, new ReturnFileRq(i));
                                FileDataByte[i] = fileData.getTable();
                                System.out.println("Have Get File Table  < " + i + " > TableAllLength >>" + (fileNative.getFileLength() - 1));

                                if (i == (fileNative.getFileLength() - 1)) {
                                    System.out.println("The Length = " + (FileDataByte.length - 1));
                                    SaveFile(FileDataByte, fileNative.getFileName());
                                    Switch(ServerIp,ServerFileUrl);
                                }
                            }
                        }
                    } else {
                        System.out.println("\n" + "You not choice file" + "\n");
                        Switch(ServerIp,ServerFileUrl);
                    }
                    Switch(ServerIp,ServerFileUrl);
                }catch (NumberFormatException e)
                {
                    System.out.println("\n"+"You not choice file Because your input is : "+e.getMessage()+"\n");
                    Switch(ServerIp,ServerFileUrl);
                }
            case "2"://Send to the Server
                ArrayList<String> clientFilePathList = new FindOutAllFile(CLIENT_PATH).GetFilePathList();
                printlnAllPath(clientFilePathList);
                numChoice = inPut.nextLine();
                try {
                    int InputNum = Integer.parseInt(numChoice);
                if (clientFilePathList.size() >= InputNum)//Null protect
                {
                    System.out.println("You choice is " + InputNum + " file path: " + clientFilePathList.get(InputNum)+"target IP:"+ServerIp.GetAndChangeServerIP(null));
                    HandleDataToList dateAll = new HandleDataToList(clientFilePathList.get(InputNum));
                    String filename = dateAll.getName();
                    byte[][] fileList = dateAll.getData();
                    SendFileNativeRp fileNation = (SendFileNativeRp) StandaloneClient.CS().read(ServerIp.GetAndChangeServerIP(null), 6420, new SendFileNativeRq(filename,fileList.length));
                    if(fileNation.getLength()>0)
                    {
                        for(int i = 0; i <= (fileNation.getLength()-1); i++) {
                            SendFileRp fileData = (SendFileRp) StandaloneClient.CS().read(ServerIp.GetAndChangeServerIP(null), 6420, new SendFileRq((fileList[i]), i));
                            if (fileData != null) {
                                System.out.println("Server Have Get File Table  < " + fileData.getTableNum() + " > TableAllLength >>" + (fileNation.getLength()));
                            }else
                            {
                                //No Return
                                System.out.println("Server Have Get File Table  < " + 0 + " > TableAllLength >>" + (fileNation.getLength()));
                            }
                        }
                    }
                    //StandaloneClient.CS().syncNotify(SERVER_IP, 6420,new ListNotification(clientFilePathList.get(Integer.parseInt(numChoice))));
                    //StandaloneClient.CS().syncNotify(IP , IP port , Object);
                }else
                {
                    System.out.println("\n"+"You not choice file"+"\n");
                    Switch(ServerIp,ServerFileUrl);
                }
                Switch(ServerIp,ServerFileUrl);}
                catch (NumberFormatException e)
                {
                    System.out.println("\n"+"You not choice file Because your input is : "+e.getMessage()+"\n");
                    Switch(ServerIp,ServerFileUrl);
                }
            case "3"://ReNameSeverFile
                ReturnFileUrlRp getSeverFileData = (ReturnFileUrlRp) StandaloneClient.CS().read(ServerIp.GetAndChangeServerIP(null), 6420, new ReturnFileUrlRq(ServerFileUrl.GetAndChangeServerFilePath(null)));
                //Rp data = (Rp) StandaloneClient.CS().read(IP,Ip port,Rq)
                printlnAllPath(getSeverFileData.getServerFilePath());
                System.out.println("Choice the file, please write the Index in the next line");
                numChoice = inPut.nextLine();
                try {
                    int InputNum = Integer.parseInt(numChoice);
                    if (getSeverFileData.getServerFilePath().size() >= InputNum)//Null protect
                    {
                        System.out.println("You choice is " + InputNum + " file path: " + getSeverFileData.getServerFilePath().get(InputNum));
                        String GetServerPath = getSeverFileData.getServerFilePath().get(InputNum);
                        System.out.println("please write the new name");
                        numChoice = inPut.nextLine();

                        ReturnReNameRp fileNation = (ReturnReNameRp) StandaloneClient.CS().read(ServerIp.GetAndChangeServerIP(null), 6420, new ReNameRq(GetServerPath, numChoice));
                        System.out.println(fileNation.getFileNatureChannge());

                    } else {
                        System.out.println("\n" + "You not choice file" + "\n");
                        Switch(ServerIp,ServerFileUrl);
                    }
                    Switch(ServerIp,ServerFileUrl);
                }catch (NumberFormatException e)
                {
                    System.out.println("\n"+"You not choice file Because your input is : "+e.getMessage()+"\n");
                    Switch(ServerIp,ServerFileUrl);
                }
            case "4"://DeleteSeverFile
                ReturnFileUrlRp getSeverFIlePathData = (ReturnFileUrlRp) StandaloneClient.CS().read(ServerIp.GetAndChangeServerIP(null), 6420, new ReturnFileUrlRq(ServerFileUrl.GetAndChangeServerFilePath(null)));
                //Rp data = (Rp) StandaloneClient.CS().read(IP,Ip port,Rq)
                printlnAllPath(getSeverFIlePathData.getServerFilePath());
                System.out.println("Choice the file, please write the Index in the next line");
                numChoice = inPut.nextLine();
                try {
                    int InputNum = Integer.parseInt(numChoice);
                    if (getSeverFIlePathData.getServerFilePath().size() >= InputNum)//Null protect
                    {
                        System.out.println("You choice is " + InputNum + "file path: " + getSeverFIlePathData.getServerFilePath().get(InputNum));
                        String GetServerPath = getSeverFIlePathData.getServerFilePath().get(InputNum);
                        ReturnDeleteFileRp fileNation = (ReturnDeleteFileRp) StandaloneClient.CS().read(ServerIp.GetAndChangeServerIP(null), 6420, new DelectFileRq(GetServerPath));
                        System.out.println(fileNation.getFileNatureChannge());

                    } else {
                        System.out.println("\n" + "You not choice file" + "\n");
                        Switch(ServerIp,ServerFileUrl);
                    }
                    Switch(ServerIp,ServerFileUrl);
                }catch (NumberFormatException e){
                    System.out.println("\n"+"You not choice file Because your input is : "+e.getMessage()+"\n");
                    Switch(ServerIp,ServerFileUrl);
                }
            case "5"://Change File's save file's url
                System.out.println("Do you want to change your Server Save Path,\n"+SERVER_FILE_ONE+SERVER_FILE_TWO+SERVER_FILE_THREE);
                numChoice = inPut.nextLine();
                switch (numChoice)
                {
                    case "1":
                        System.out.println("please write your file path on the next line \n");
                        numChoice = inPut.nextLine();
                        ChangeFilePathRp filePathTF_1 = (ChangeFilePathRp) StandaloneClient.CS().read(ServerIp.GetAndChangeServerIP(null), 6420, new ChangeFilePathRq(ServerFileUrl.GetAndChangeServerFilePath(numChoice)));
                        if(filePathTF_1.ReturnFileTF())
                        {
                            System.out.println("Change File Save Path Succeed");
                        }else
                        {
                            System.out.println("Change File Save Path Failure");
                        }
                        Switch(ServerIp,ServerFileUrl);
                    case "2":
                        System.out.println("please write your file path on the next line \n");
                        numChoice = inPut.nextLine();
                        String OldFilePath = ServerFileUrl.GetAndChangeServerFilePath(null);
                        String filePath = ServerFileUrl.GetAndChangeServerFilePath(numChoice);
                        System.out.println("Local file path is "+filePath+"\n");

                        ChangeFilePathRp filePathTF_2 = (ChangeFilePathRp) StandaloneClient.CS().read(ServerIp.GetAndChangeServerIP(null), 6420, new ChangeFilePathRq(ServerFileUrl.GetAndChangeServerFilePath(OldFilePath+"/"+numChoice)));
                        if(filePathTF_2.ReturnFileTF())
                        {
                            System.out.println("Change File Save Path Succeed");
                        }else
                        {
                            System.out.println("Change File Save Path Failure");
                        }
                        Switch(ServerIp,ServerFileUrl);
                        default:
                        Switch(ServerIp,ServerFileUrl);
                }


            case "886"://Exits
                StandaloneClient.CS().dispose();
                inPut.close();
                break;
            default://Other
                System.out.println("\n"+"Are you want to exits Program ? "+"\n"+"Yes: please choice 1"+"\n"+"No: please choice other");
                numChoice = inPut.nextLine();

                if (numChoice.equals("1")){
                    StandaloneClient.CS().dispose();
                    inPut.close();
                }else {
                    Switch(ServerIp,ServerFileUrl);
                }

                break;
        }

        }

    private static void SaveFile(byte[][] rp_List,String FileName)//Handle to Save File
    {
        if (rp_List != null) {
            System.out.println("\n" + "< " + Arrays.toString(rp_List) + " > " + FileName + "   the list have return" + "\n");
            byte[] allData = new ByteToAllByte(rp_List).getAllByte();
            try {
                new HandleDataToFile(allData, FileName, CLIENT_PATH);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Save File Error");
            }

        }else {

            System.out.println("Not file from server");
        }
    }

    private static void printlnAllPath(ArrayList<String> allFilePathList)//Handle Print All path in List
    {
        for(int i = 0 ; i<allFilePathList.size() ; i++ ) {
            String filePath = allFilePathList.get(i);
            System.out.println("\n"+i+".  "+filePath);
        }
    }



}
