package trycloud.message;

import org.greatfree.message.ServerMessage;

public class ReturnFileNativeRp extends ServerMessage {

    private static final long serialVersionUID = -1276488899874258826L;
    private String FileName;
    private int FileLength;
    public ReturnFileNativeRp (String fileName,int fileLength)
    {
        super(LibraryApplicationID.RETURN_FILE_NATIVE_Rp);
        if(fileName == null||fileLength == 0)
        {
            this.FileName = null;
            this.FileLength = 0;
        }else
        {
            this.FileName = fileName;
            this.FileLength = fileLength;
        }
    }

    public String getFileName()
    {
        return this.FileName;
    }
    public int getFileLength()
    {
        return this.FileLength;
    }
}
