package trycloud.message;

import org.greatfree.message.container.Request;

public class ChangeFilePathRq extends Request {

    private static final long serialVersionUID = -8868880725688755796L;

    private String NewFilePath;

    public ChangeFilePathRq(String newFilePath)
    {
        super(LibraryApplicationID.CHANGE_FILE_Rq);
        this.NewFilePath = newFilePath;
    }

    public String GetNewFilePath()
    {
        return this.NewFilePath;
    }
}
