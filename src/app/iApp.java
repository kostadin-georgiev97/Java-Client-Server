package app;
import java.io.Serializable;
import java.util.List;

public interface iApp extends Serializable {

	public SerializableFunction<Integer, Integer> getFunction();

	public void setFunction(SerializableFunction<Integer, Integer> function);

	public List<Integer> getData();
	
	public void makeData(int numDataElements);
	
}
