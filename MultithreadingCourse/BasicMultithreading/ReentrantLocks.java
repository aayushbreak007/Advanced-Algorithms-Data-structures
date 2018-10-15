package MultithreadingCourse.BasicMultithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLocks {

    private static int counter=0;
    private static Lock lock=new ReentrantLock(true);

    private static void incrementCounter(){

        lock.lock();
        //put the operation in try finally block to avoid deadlock
        try {
            for (int i = 0; i < 10000; i++) {
                counter++;
            }
        }finally {
            lock.unlock();//WE CAN UNLOCK IN ANY OTHER PART OF THE CODE (EVEN ANOTHER FUCNTION) ---UNLIKE SYNCHRONISED BLOCK
        }

    }
    public static void main(String[] args){

        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                incrementCounter();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                incrementCounter();
                try {
                    Thread.sleep(500);
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

        System.out.println("Counter is :"+counter);

    }
}