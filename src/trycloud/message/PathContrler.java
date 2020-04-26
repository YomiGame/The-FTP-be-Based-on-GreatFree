package trycloud.message;

import static trycloud.message.Config.SERVER_CONFIG;


public class PathContrler {
    private String SeverFilePath;
    public PathContrler()
    {
        this.SeverFilePath = SERVER_CONFIG[0][1];//default
    }

    ///if Change then FilePath is not null
    public String GetAndChangeServerFilePath(String FilePath)
    {
        if (FilePath != null)
        {
            this.SeverFilePath = FilePath;
        }
        return this.SeverFilePath;
    }
}
