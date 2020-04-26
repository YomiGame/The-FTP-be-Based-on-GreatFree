package trycloud.message;

import org.greatfree.message.container.Request;

public class SendFileRq extends Request {
    private static final long serialVersionUID = -1275453908796259974L;
    private byte[] SendFileTable;
    private int SubScript;
    public SendFileRq(byte[] sendFileTable,int SendFileSub)
    {
        super(LibraryApplicationID.SEND_FILE_Rq);
        this.SendFileTable = sendFileTable;
        this.SubScript = SendFileSub;
    }

    public byte[] getFileTable()
    {
        return this.SendFileTable;
    }
    public int getSub()
    {
        return this.SubScript;
    }
}
