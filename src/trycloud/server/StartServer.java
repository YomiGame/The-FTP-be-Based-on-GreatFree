package trycloud.server;

import java.io.IOException;
import java.util.Scanner;

import org.greatfree.exceptions.RemoteReadException;

class StartServer
{

	public static void main(String[] args)
	{
		System.out.println("Server is starting ...");
		try
		{
			MyServer.CS().start(6420, new LibraryTask());
		}
		catch (ClassNotFoundException | IOException | RemoteReadException e)
		{
			e.printStackTrace();
		}
		System.out.println("Server is started ...");

		Scanner in = new Scanner(System.in);
		System.out.println("Press enter to exit ...");
		in.nextLine();
		
		try
		{
			MyServer.CS().stop(1000);
		}
		catch (ClassNotFoundException | IOException | InterruptedException | RemoteReadException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		in.close();
	}

}
