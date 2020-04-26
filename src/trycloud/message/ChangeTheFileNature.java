package trycloud.message;

import java.io.File;

public class ChangeTheFileNature {
    private String ChangeFileNature;
    public ChangeTheFileNature(String State , String FileUrl,String NewFileName)
    {
        File NeedChangeFile  = new File(FileUrl);
        String NewFilePath = PathToFolderPath(NeedChangeFile) + NewFileName;
        switch(State){
            case "ReName":
                if (NeedChangeFile.renameTo(new File(NewFilePath))&&NewFilePath != null)
                {
                    this.ChangeFileNature = "The File Have Been Change Name" +"\n path: "+ NewFilePath;
                }else
                {
                    this.ChangeFileNature = "The File Change Name Failure";
                }
                break;
            case "DeleteFile":
                if (NeedChangeFile.delete())
                {
                    this.ChangeFileNature = "The File Have Been Delete";
                }else
                {
                    this.ChangeFileNature = "The File Delete Failure";
                }
                break;
        }
    }

    private String PathToFolderPath(File file)
    {
        String NewFileName = file.getParent() + "/";
        return NewFileName;
    }

    public String getFileChangeNature()
    {
        return this.ChangeFileNature;
    }
}

