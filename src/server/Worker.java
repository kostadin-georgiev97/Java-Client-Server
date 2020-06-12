package server;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;

public class Worker {

    public <V,E> Future<List<E>> calculate (Function<V,E> f, List<V> data){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<E>> runner = executor.submit(()-> {
            List<E> portion = new ArrayList<>();
            for (V elem : data) {
                portion.add(f.apply(elem));
            }
            return portion;
        });
        return runner;
    }

}
