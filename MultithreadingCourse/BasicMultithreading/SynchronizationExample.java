package MultithreadingCourse.BasicMultithreading;

public class SynchronizationExample {
    private static volatile int counter=0;

    //means the thread 2 will wait for thread 1 to fininsh incrementing and vice versa before updating on it's turn while mainting time-slice execution
    public static synchronized void incrementCounter(){
        //declaring the counter as VOLATILE "WILL NOT WORK" since incrementing is not an "ATOMIC Operation"
        ++counter;
    }

    public static void process(){
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100;i++){
                    //   System.out.println("Thread_1");
                    incrementCounter();
                    //++counter;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100;i++){
                    // System.out.println("Thread_2");
                    incrementCounter();
                    // ++counter;
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                if(counter==197){
                    System.out.println("Hey..."+counter);
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        SynchronizationExample.process();
        System.out.println(counter);
    }
}