package trycloud.message;

import org.greatfree.message.container.Request;


public class SendFileNativeRq extends Request
{
    private static final long serialVersionUID = -8868990725345355796L;

    private String FileName;
    private int FileLength;
    public SendFileNativeRq(String fileName,int fileLength)  {
        super(LibraryApplicationID.SEND_FILE_NATIVE_Rq);

        this.FileName = fileName;
        this.FileLength = fileLength;

}

    public String getName()
    {
        return this.FileName;
    }
    public int getLength()
    {
        return this.FileLength;
    }

}
