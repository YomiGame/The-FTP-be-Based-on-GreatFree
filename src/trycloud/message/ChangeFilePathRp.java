package trycloud.message;

import org.greatfree.message.ServerMessage;

public class ChangeFilePathRp extends ServerMessage {

    private static final long serialVersionUID = -8868990724998355796L;
    private boolean fileTF;
    public  ChangeFilePathRp(String clientFilePath,String serverFilePath)
    {
        super(LibraryApplicationID.CHANGE_FILE_Rp);
        if(clientFilePath == serverFilePath)
        {
            this.fileTF = true;
        }else
        {
            this.fileTF =false;
        }
    }

    public boolean ReturnFileTF()
    {
        return this.fileTF;
    }
}
