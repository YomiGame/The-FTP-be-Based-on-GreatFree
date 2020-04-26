package trycloud.message;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;


public class InspectIpAndPort {
    private String serverIP;
    public InspectIpAndPort(String InServerIP){
        Socket SocketConnect = new Socket();
        try{
            SocketConnect.connect(new InetSocketAddress(InServerIP, 6420),100);//建立连接
            if(SocketConnect.isConnected())
            {
                this.serverIP = InServerIP;
            }
        }catch (IOException e) {
            System.out.println("your Server :"+InServerIP+" is not online");
            this.serverIP = null;
        }
        }

    public String getServerIP()
        {
            return this.serverIP;
        }
}
