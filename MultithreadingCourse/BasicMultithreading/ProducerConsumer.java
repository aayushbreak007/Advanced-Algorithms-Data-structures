package MultithreadingCourse.BasicMultithreading;

import java.util.ArrayList;
import java.util.List;

class Processor2{

    private List<Integer> list=new ArrayList<>();
    private final int LIMIT=5;
    private final int BOTTOM=0;
    private final Object lock=new Object();

    private int value=0;
    public void produce() throws InterruptedException{

        synchronized (lock)
        {
            while(true){
                if(list.size()==LIMIT){
                    //stop adding items;
                    System.out.println("Waiting for removing items from the list....");
                    lock.wait();
                }else{
                    System.out.println("Adding:"+value);
                    list.add(value);
                    value++;
                    lock.notify();//MY TASK IS DONE--TELL THIS TO OTHER THREAD
                }

                Thread.sleep(1000);
            }
        }

    }

    public void consume()throws InterruptedException{

        synchronized (lock){
            while (true){
                if(list.size()==BOTTOM){
                    System.out.println("Waiting for adding items to the list....");
                    lock.wait();
                }else{
                    //now remove items from the list
                    System.out.println("Remove:"+list.remove(--value));
                    lock.notify();

                    //NOTE: if you write code after notify() it will not be able to wake and call the code below
                }

                Thread.sleep(1000);
            }
        }
    }

}


public class ProducerConsumer {
    public static void main(String[] args){

        Processor2 processor2=new Processor2();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor2.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor2.consume();
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