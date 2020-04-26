package trycloud.message;

import org.greatfree.message.ServerMessage;


public class ReturnReNameRp extends ServerMessage {
    private static final long serialVersionUID = -1275453900396487448L;

    private String FileChanngeNotice;

    public ReturnReNameRp(String FileNatureString) {
        super(LibraryApplicationID.RETURN_RENAME_Rp);
        this.FileChanngeNotice = FileNatureString;

    }

    public String getFileNatureChannge()
    {
        return this.FileChanngeNotice;
    }


}
