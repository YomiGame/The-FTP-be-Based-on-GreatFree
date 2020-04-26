package trycloud.message;

import org.greatfree.message.ServerMessage;


import java.util.ArrayList;


public class ReturnFileUrlRp extends ServerMessage {
    private static final long serialVersionUID = -1275453900394267448L;

    private ArrayList<String> AllFilePathList;

    public ReturnFileUrlRp(String clientSaveUrl) {
        super(LibraryApplicationID.RETURN_FILE_URL_Rp);
        this.AllFilePathList = new FindOutAllFile(clientSaveUrl).GetFilePathList();

    }

    public ArrayList<String> getServerFilePath()
    {
        return this.AllFilePathList;
    }


}
