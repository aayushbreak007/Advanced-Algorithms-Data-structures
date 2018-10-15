package MultithreadingCourse.BasicMultithreading;


class Runner1 implements Runnable{

    @Override
    public void run() {
        //assigning the task to a distinct thread
        for(int i=0;i<10;i++){
            System.out.println("Runner1:"+i);
        }
    }
}
class Runner2 implements Runnable{
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println("Runner2:"+i);
        }
    }
}
public class ThreadExample1 {
    public static void main(String[] args){


        Thread t2=new Thread(new Runner2());
        Thread t1=new Thread(new Runner1());

        t1.start();
        t2.start();


    }
}