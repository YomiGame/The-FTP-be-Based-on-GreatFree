package trycloud.message;

import static trycloud.message.Config.DEFAULT_IP;

public class IPChoice {
    private String ServerIP;
    public IPChoice()
    {
        this.ServerIP = DEFAULT_IP;
    }

    ///if Change then IP is not null
    public String GetAndChangeServerIP(String IP)
    {
        if (IP != null)
        {
            this.ServerIP = IP;
        }
        return this.ServerIP;
    }
}
