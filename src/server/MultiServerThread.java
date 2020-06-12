package server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;

import app.iApp;

public class MultiServerThread extends Thread {

	private Socket socket;
	List<Worker> workers;
	
	public MultiServerThread(Socket socket, int numWorkers) {
		this.socket = socket;
		
		workers = new ArrayList<>();
		for(int i = 0; i < numWorkers; i++) {
			Worker worker = new Worker();
			workers.add(worker);
		}
	}
	
	public void run() {
		
		try {
			
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			
			iApp app = (iApp) in.readObject();
			List<Integer> result = this.calculate(app.getFunction(), app.getData());
			out.writeObject(result);
			
			out.close();
			in.close();
			
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	         
	}
	
	public<V,E> List<E> calculate (Function<V,E> f, List<V> data) {
        List<E> result = new ArrayList<>();
        int amountEach = data.size()/workers.size(); //list of workers
        int limit = amountEach;
        int lowerBound = 0;
        List<Future<List<E>>> allFutures = new LinkedList<>();
        
        for (int i = 0; i < workers.size(); i++){
            Future<List<E>> futures = workers.get(i).calculate(f, data.subList(lowerBound, limit));
            allFutures.add(futures);
            limit = limit + amountEach;
            lowerBound = lowerBound + amountEach;
        }

        for (Future<List<E>> future : allFutures) {
            try {
                result.addAll(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
	
}
