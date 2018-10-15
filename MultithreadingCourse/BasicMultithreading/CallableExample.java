package MultithreadingCourse.BasicMultithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class Processor3 implements Callable<String>{

    private int id;
    public Processor3(int id){
        this.id=id;

    }
    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "ID:"+id;

    }
}

public class CallableExample {

    public static void main(String[] args){
        ExecutorService executorService= Executors.newFixedThreadPool(2);//2 processor3 threads
        List<Future<String>> list_of_ids=new ArrayList<>();

        for(int i=0;i<5;i++){
            Future<String> future=executorService.submit(new Processor3(i+1));
            list_of_ids.add(future);
        }



        //print the returned IDs from the callable function
        for(Future<String> future:list_of_ids){
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();

    }


}