package trycloud.message;

import org.greatfree.message.ServerMessage;


public class SendFileNativeRp extends ServerMessage
{
    private static final long serialVersionUID = -8868990723784355796L;

    private int GetLength;
    public SendFileNativeRp(int getLength)  {
        super(LibraryApplicationID.SEND_FILE_NATIVE_Rp);

        this.GetLength = getLength;

}

    public int getLength()
    {
        return this.GetLength;
    }

}
