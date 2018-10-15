package MultithreadingCourse.ConcurrentCollections;


import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker2 implements Runnable{

    private int id;
    private CountDownLatch countdownLatch;
    private Random random;

    public  Worker2(int id,CountDownLatch countdownLatch){
        this.id=id;
        this.countdownLatch=countdownLatch;

    }
    @Override
    public void run() {
        doWork();
        //releasing the latch once the work by this thread is done
        countdownLatch.countDown();//this is going to decrement the value of the countdown--latch released

    }

    private void doWork() {
        System.out.println("Thread with id "+this.id+" starts working...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class CountdownLatchExample {

    public static void main(String[] args){
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        CountDownLatch latch=new CountDownLatch(5);//LATCHES FOR 5 THREADS
        for(int i=0;i<5;i++){

            //here with the same thread we can do all the task independently which are automated through LATCHES
            executorService.execute(new Worker2(i+1,latch));


        }
        try {
            latch.await();//wait till the countdown goes to 0
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All the prerequisites are done... ");
        executorService.shutdown();

    }


}