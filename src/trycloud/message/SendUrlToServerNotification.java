package trycloud.message;

import org.greatfree.message.container.Notification;

public class SendUrlToServerNotification extends Notification {
    private static final long serialVersionUID = -8868990728484355805L;
    private String FileUrl;
    public SendUrlToServerNotification(String fileUrl)
    {

        super(LibraryApplicationID.SEND_FILE_URL_NOTIFICATION);
        this.FileUrl = fileUrl;
    }

    public String getFileUrl()
    {
        return this.FileUrl;
    }
}
