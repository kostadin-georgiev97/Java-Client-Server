package app;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App implements iApp {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8744139808079314546L;
	private SerializableFunction<Integer, Integer> function;
    private List<Integer> data;
    
    /**
     * Constructs an App with a function and data set.
     */
    public App() {
    	setFunction((x) -> x*2);
    	makeData(100);
    }

	public SerializableFunction<Integer, Integer> getFunction() {
		return function;
	}

	public void setFunction(SerializableFunction<Integer, Integer> function) {
		this.function =  function;
	}

	public List<Integer> getData() {
		return data;
	}
	
	public void makeData(int numDataElements) {
    	List<Integer> data = new ArrayList<Integer>();
    	
        for (int i = 0; i < numDataElements; i++){
            Random rand = new Random();
            data.add(rand.nextInt(101));
        }
        this.data = data;
    }
	
}
