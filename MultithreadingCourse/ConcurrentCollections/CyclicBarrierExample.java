package MultithreadingCourse.ConcurrentCollections;


import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker3 implements Runnable{

    private int id;
    private Random random;
    private CyclicBarrier cyclicBarrier;

    public Worker3(int id,CyclicBarrier cyclicBarrier){
        this.id=id;
        this.random=new Random();
        this.cyclicBarrier=cyclicBarrier;
    }
    @Override
    public void run() {

        doWork();
    }

    private void doWork() {
        System.out.println("Thread with ID "+id+" starts the task..");
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread with ID "+id+" finished..");

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return ""+this.id;
    }
}
public class CyclicBarrierExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {

            //THIS WILL RUN WHEN ALL THE TASKS ARE FINISHED
            @Override
            public void run() {
                System.out.println("All the tasks are finished...");
            }
        });

        for (int i = 0; i < 5; i++) {
            executorService.execute(new Worker3(i+1,barrier));

        }

        executorService.shutdown();
    }
}
