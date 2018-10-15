package MultithreadingCourse.BasicMultithreading;


import java.util.ArrayList;
import java.util.List;

class Processor{

    private List<Integer> list=new ArrayList<>();
    private final int LIMIT=5;
    private final int BOTTOM=0;
    public void produce() throws InterruptedException{

        synchronized (this)//applying "CLASS INTRINSIC LOCK"
        {
            System.out.println("We are in the produce method...");
            wait();//will give the "CLASS INTRINSIC LOCK" to another thread till it's waiting--since they are synchrnoised on the same class lock
            System.out.println("Again producer method....");
        }

    }

    public void consume()throws InterruptedException{

        Thread.sleep(1000);
        synchronized (this){
            System.out.println("Consumer method....");

            notify();//non-deterministic WAKING UP OF THE WAITING THREAD---CAN WAKE UP ANY WAITING THREAD
            //notify says---I'm done and releases the CLASS INTRINSIC LOCK

            notifyAll();//notifies all synchrnised methods
            Thread.sleep(3000);//wait to complete the task

        }

    }

}
public class WaitAndNotifyExample {

    public static void main(String[] args){

        Processor processor=new Processor();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();


        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}