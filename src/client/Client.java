package client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import app.App;
import app.iApp;

public class Client {

public static void main(String[] args) {
		
		iApp clientApp = new App();
		String serverName = "localhost";
		int port = 3456;
		
		try {
			
			Socket socket = new Socket(serverName, port);

			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			
			out.writeObject(clientApp);
			System.out.println(clientApp.getData());
			
			@SuppressWarnings("unchecked")
			List<Integer> reply = (List<Integer>) in.readObject();
			System.out.println(reply);
			
			in.close();
			out.close();
			
			socket.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
}
