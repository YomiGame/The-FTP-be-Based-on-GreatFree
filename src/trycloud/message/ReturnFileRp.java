package trycloud.message;

import org.greatfree.message.ServerMessage;


public class ReturnFileRp extends ServerMessage
{
    private static final long serialVersionUID = -1275453900394258734L;

    private byte[] ReturnTable;

    public ReturnFileRp(byte[] returnTable) {
        super(LibraryApplicationID.RETURN_FILE_Rp);
        this.ReturnTable = returnTable;
    }

    public byte[] getTable()
    {
        return this.ReturnTable;
    }

}