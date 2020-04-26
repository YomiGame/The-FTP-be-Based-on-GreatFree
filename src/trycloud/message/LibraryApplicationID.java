package trycloud.message;

public class LibraryApplicationID
{
	//Send FileUrl to Server
	public final static int SEND_FILE_URL_NOTIFICATION = 80001;

    //Used to return file
	public final static int RETURN_FILE_Rq = 80004;
	public final static int RETURN_FILE_Rp = 80005;

	//Used to get sever file url
	public final static int RETURN_FILE_URL_Rq = 80007;
	public final static int RETURN_FILE_URL_Rp = 80008;
	//Rename Sever File
	public final static int RENAME_Rq = 80009;
	public final static int RETURN_RENAME_Rp = 80010;

	//Delete Sever File
	public final static int DELETE_FILE_Rp = 80011;
	public final static int DELETE_FILE_Pq = 80012;
	//Get File Native
	public final static int RETURN_FILE_NATIVE_Rp = 80013;
	public final static int RETURN_FILE_NATIVE_Rq = 80014;
	//Send File Native
	public final static int SEND_FILE_NATIVE_Rp = 80015;
	public final static int SEND_FILE_NATIVE_Rq = 80016;
	//Used to send file
	public final static int SEND_FILE_Rq = 80017;
	public final static int SEND_FILE_Rp = 80018;
	//Used to change file path
	public final static int CHANGE_FILE_Rq = 80019;
	public final static int CHANGE_FILE_Rp = 80020;

}
