package example.concurrency.runnable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

	private final Socket client;
	private final BufferedReader input;
	private final BufferedWriter output;
	private final String clientIdentifier;

	public ClientHandler(Socket client) throws IOException {
		this.client = client;
		input = new BufferedReader(new InputStreamReader(client.getInputStream()));
		output = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		clientIdentifier = "Client " + client.getRemoteSocketAddress();
	}

	@Override
	public void run() {
		try {
			
			while(!client.isClosed())
			{
				String message = input.readLine();
				if(message == null)
					return;
				System.out.println(clientIdentifier + " said: " + message);
				
				output.write("Thank you " + clientIdentifier + ", your message [" + message + "] has been recieved!\n");
				output.flush();
			}
			
			
		} catch (Exception e) {
		
			//e.printStackTrace();
		
		} finally {
			
			System.out.println(clientIdentifier + " has disconnected");
			
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
