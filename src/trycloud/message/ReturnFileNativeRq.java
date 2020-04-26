package trycloud.message;

import org.greatfree.message.container.Request;

public class ReturnFileNativeRq extends Request {
    private static final long serialVersionUID = -1276488700394258826L;
    private String fileUrl;
    public ReturnFileNativeRq (String fileUrl)
    {
        super(LibraryApplicationID.RETURN_FILE_NATIVE_Rq);
        this.fileUrl = fileUrl;

    }

    public String getFileUrl()
    {
        return this.fileUrl;
    }
}
