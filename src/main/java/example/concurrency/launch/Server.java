package example.concurrency.launch;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import example.concurrency.runnable.ClientHandler;

public class Server {

	public final static int PORT = 9876;

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = new ServerSocket(Server.PORT);

		try {
			
			while (true) {
				Socket client = serverSocket.accept();
				System.out.println("New connection recieved!");
				new Thread(new ClientHandler(client)).start();
			}
			
		} finally {
			serverSocket.close();
		}

	}

}
