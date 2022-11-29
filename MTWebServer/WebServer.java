import java.io.* ;
import java.net.* ;
import java.util.* ;

public final class WebServer
{
	public static void main(String argv[]) throws Exception
	{
		// Set the port number.
		int port = 6789;

		// Establish the listen socket.
      	ServerSocket hearSocket = new ServerSocket(port);

		// Process HTTP service requests in an infinite loop.
		while (true) 
		{
			
			//Get request
			Socket connectionSocket = hearSocket.accept();
			
			HttpRequest newReq = new HttpRequest(connectionSocket);
			
			Thread thrd = new Thread(newReq);

			//run thread
			thrd.start();

			
		}

		//hearSocket.close();
	}
}

final class HttpRequest implements Runnable
{
	final static String CRLF = "\r\n";
	Socket socket;

	// Constructor
	public HttpRequest(Socket socket) throws Exception 
	{
		this.socket = socket;
	}

	// Implement the run() method of the Runnable interface.
	public void run()
	{
		try
		{
			processRequest();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	//INPUT: 	No input params received
	//PROCESS:	Get input data from reader and parse it to 
	//			send appropriate information based on existing files and file requested using HTTP standard
	//OUTPUT:	No output is returned, data is sent out through data stream
	private void processRequest() throws Exception
	{

		//declare our streams
		DataOutputStream O_S = new DataOutputStream (
			socket.getOutputStream());
		BufferedReader B_R = new BufferedReader (new InputStreamReader(
			socket.getInputStream()));

		String requestLine = B_R.readLine();

		//Debug code
		/* 
		System.out.println();
		System.out.println(requestLine);
		*/

		String headerLine = null;
		while((headerLine = B_R.readLine()).length() != 0)
		{
			System.out.println(headerLine);
		}


		//tokenization and retrieval of file token
		StringTokenizer tokens = new StringTokenizer(requestLine);
		
		tokens.nextToken();
		String fileName = tokens.nextToken();

		//formatting for current directory
		fileName = "." + fileName;

		/*Debugging code 
		System.out.println("FILE NAME: " + fileName);
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		*/

		//Open the requested file.
		FileInputStream F_I_S = null;
		boolean fileExists = true;

		//we try to open the file input stream
		try
		{
			F_I_S = new FileInputStream(fileName);
		}
		catch (FileNotFoundException e)
		{
			fileExists = false;
		}

		//sections for HTTP message
		String statusLine = null;
		String contentTypeLine = null;
		String entityBody = null;


		//Hey Doofus, remember, you need to add the protocol to the status line for it to be interpreted properly.
		if (fileExists)
		{
			statusLine = "HTTP/1.0 200 OK\n";
			contentTypeLine = "Content-type: " + contentType(fileName) + "\n"; //Create ContentType function
		}
		else
		{
			statusLine = "HTTP/1.0 404 Not Found\n";
			contentTypeLine = "Content-type: text/html\n";
			entityBody = 	"<html>\n" +
								"\t<head>\n\t\t<title>404 File Not Found</title>\n\t</head>\n" +
								"\t<body>\n\t\t<h1>Sorry, not sorry lol </h1>\n\t</body>\n"+
							"</html>";
		}

		//send data out through output stream to connection instantiator (client's browser)
		O_S.writeBytes(statusLine);
		O_S.writeBytes(contentTypeLine);
		O_S.writeBytes(CRLF);

		//if the file doesn't exist we close file stream and send bytes of 
		if(fileExists)
		{
			sendBytes(F_I_S, O_S); 
			F_I_S.close();
		}
		else
		{
			O_S.writeBytes(entityBody);
		}

		//reclaim resources
		F_I_S.close();
		O_S.close();
		B_R.close();
		socket.close();
	}


	//INPUT:	Get input stream and output stream as parameters
	//PROCESS:	send data from file stream to output stream
	//OUTPUT:	no output is returned, data is sent to client
	private static void sendBytes(FileInputStream F_I_S, OutputStream O_S) throws Exception
	{
		//data buffer
		byte[] buffer = new byte[1024];
		int bytes = 0;

		while((bytes = F_I_S.read(buffer)) != -1)
		{
			O_S.write(buffer, 0, bytes);
		}
	}

	//INPUT:	Get fileName string
	//PROCESS:	match the fileName to the appropriate file type
	//OUTPUT:	return the content Type message from file suffix
	private static String contentType(String fileName)
	{
		if(fileName.endsWith(".htm") || fileName.endsWith(".html"))
		{
			return "text/html";
		}
		else if(fileName.endsWith(".gif"))
		{
			return "image/gif";
		}
		else if(fileName.endsWith(".jpg") || fileName.endsWith(".jpeg"))
		{
			return "image/jpeg";
		}
		return "application/octet-stream";
	}

}
