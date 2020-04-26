package trycloud.message;

import org.greatfree.message.ServerMessage;

public class SendFileRp extends ServerMessage {
    private static final long serialVersionUID = -1275453908796876474L;
    private int TableNum;
    public SendFileRp(int tableNum)
    {
        super(LibraryApplicationID.SEND_FILE_Rp);
        this.TableNum = tableNum;

    }

    public int getTableNum()
    {
        return this.TableNum;

    }
}
