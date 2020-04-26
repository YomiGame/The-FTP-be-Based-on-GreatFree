package trycloud.message;

public class Config {
    public static String DEFAULT_IP = "0.0.0.0";//"/home/pi/Desktop/temp";//"/home/yomi/Desktop/GreatFreeFile/";
    //public final static String SERVER_IP = "169.254.231.40";//"106.52.232.79";//
    public final static String CLIENT_PATH = "F:\\AASM\\";


    public static String[][] SERVER_CONFIG= {
        {"169.254.231.40","/home/pi/Desktop/temp"},
        {"106.52.232.79","/home/yomi/Desktop/GreatFreeFile"}
    };
    public final static String CLIENT_DEFAULT_IP = "\n"+"Your default ip is : ";
    public final static String CLIENT_DEFAULT_URL = "\n"+"Your default URL is : ";
    public final static String CLIENT_NO_SERVER = "\n"+"Your not Server can be connected";
    public final static String CLIENT_HINT = "\n"+"Please countersign your computer's port 6420 is open";
    public final static String CLIENT_CHOICE_ZEN = "\n"+"0: Choice Server's IP";
    public final static String CLIENT_CHOICE_ONE = "\n"+"1: Get the File from the Sever";
    public final static String CLIENT_CHOICE_TWO = "\n"+"2: Send the File to the Sever";
    public final static String CLIENT_CHOICE_THREE = "\n"+"3: Change Sever's File Name";
    public final static String CLIENT_CHOICE_FOUR = "\n"+"4: Delete File in the Sever";
    public final static String CLIENT_CHOICE_FIFE = "\n"+"5: Change or create new ServerFile Path";
    public final static String CLIENT_CHOICE_LAST = "\n"+"886 : Quit";
    //change server file path
    public final static String SERVER_FILE_ONE = "    1: new File Path\n";
    public final static String SERVER_FILE_TWO = "    2: local File's child path or new File\n";
    public final static String SERVER_FILE_THREE = "    other:nothing\n";

}


