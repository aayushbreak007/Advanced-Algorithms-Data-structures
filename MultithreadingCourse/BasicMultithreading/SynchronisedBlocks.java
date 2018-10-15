package MultithreadingCourse.BasicMultithreading;

public class SynchronisedBlocks {
    private static int count1=0;
    private static int count2=0;

    private static Object lock1=new Object();
    private static Object lock2=new Object();


    public static void add(){
        //now thread 2 can call the second function while thread one calls the first one because of different locks
        //no THREAD WAITING
        synchronized (lock1) {
            count1++;
        }
    }
    public static void addAgain(){
        synchronized (lock2) {
            count2++;
        }
    }

    public static void compute(){
        for(int i=0;i<100;i++){
            add();
            addAgain();
        }
    }
    public static void main(String[] args){

        /*Now wheneever THREAD 1 calls first function ...but Thread2 CANNOT call second function ...due to "CLASS INTRINSIC LOCKS"*/

        /*SOLUTION: to use "LOCKS" on the synchronised blocks*/
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                compute();
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                compute();
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

        System.out.println("Count 1:"+count1+"-"+"Count 2:"+count2);
    }
}