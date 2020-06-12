package server;
import java.io.IOException;
import java.net.ServerSocket;

public class MultiServer {

	public static void main(String[] args) {
		
		int port = 3456, numWorkers = 5;
		
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			
			while(true) {
				new MultiServerThread(serverSocket.accept(), numWorkers).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
