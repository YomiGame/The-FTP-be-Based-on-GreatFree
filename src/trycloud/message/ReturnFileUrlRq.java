package trycloud.message;

import org.greatfree.message.container.Request;

public class ReturnFileUrlRq extends Request {
    private static final long serialVersionUID = -1275453900394259974L;

    private String FileUrl;

    public ReturnFileUrlRq(String FileUrl)
    {
        super(LibraryApplicationID.RETURN_FILE_URL_Rq);
        this.FileUrl = FileUrl;
    }

    public String getFileUrl()
    {
        return this.FileUrl;
    }
}
