package trycloud.message;

import org.greatfree.message.container.Request;

public class DelectFileRq extends Request {
    private static final long serialVersionUID = -8868990748867355796L;
    private String DeleteFileUrl;

    public DelectFileRq(String DeleteFileUrl) {

        super(LibraryApplicationID.DELETE_FILE_Pq);
        this.DeleteFileUrl = DeleteFileUrl;
    }

    public String getNameUrl_DelFile()
    {
        return this.DeleteFileUrl;
    }

}
