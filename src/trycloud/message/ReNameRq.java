package trycloud.message;

import org.greatfree.message.container.Request;

public class ReNameRq extends Request {

    private static final long serialVersionUID = -8868990725887355796L;
    private String RefileName;
    private String NewName;

    public ReNameRq(String ReNameFile,String NewName) {
        super(LibraryApplicationID.RENAME_Rq);
        this.RefileName = ReNameFile;
        this.NewName = NewName;
    }

    public String getNameUrl_ReName()
    {
        return this.RefileName;
    }
    public String getNewNameUrl_reName(){return this.NewName;}
}
