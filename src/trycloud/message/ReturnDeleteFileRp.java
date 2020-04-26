package trycloud.message;

import org.greatfree.message.ServerMessage;


public class ReturnDeleteFileRp extends ServerMessage {
    private static final long serialVersionUID = -1275454879296487448L;

    private String FileChangeNotice;

    public ReturnDeleteFileRp(String FileNatureString) {
        super(LibraryApplicationID.DELETE_FILE_Rp);
        this.FileChangeNotice = FileNatureString;

    }

    public String getFileNatureChannge()
    {
        return this.FileChangeNotice;
    }


}
