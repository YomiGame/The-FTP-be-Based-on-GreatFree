package trycloud.server;

import java.io.IOException;

import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.server.container.ServerContainer;
import org.greatfree.server.container.ServerTask;
import org.greatfree.util.TerminateSignal;

class MyServer
{
	private ServerContainer server;

	private MyServer()
	{
	}
	
	private static MyServer instance = new MyServer();
	
	public static MyServer CS()
	{
		if (instance == null)
		{
			instance = new MyServer();
			return instance;
		}
		else
		{
			return instance;
		}
	}

	public void stop(long timeout) throws ClassNotFoundException, IOException, InterruptedException, RemoteReadException
	{
		TerminateSignal.SIGNAL().setTerminated();
		this.server.stop(timeout);
	}

	public void start(int port, ServerTask task) throws IOException, ClassNotFoundException, RemoteReadException
	{
		this.server = new ServerContainer(port, task);
		this.server.start();
	}
}
