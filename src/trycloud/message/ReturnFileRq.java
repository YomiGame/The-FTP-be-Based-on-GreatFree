package trycloud.message;

import org.greatfree.message.container.Request;

public class ReturnFileRq extends Request
{
    private static final long serialVersionUID = -1275453900394258826L;

    private int TheTableLength;

    public ReturnFileRq(int theTableLength)
    {
        super(LibraryApplicationID.RETURN_FILE_Rq);
        this.TheTableLength = theTableLength;
    }

    public int getTableLength()
    {
        return this.TheTableLength;
    }
}
