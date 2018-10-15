package MultithreadingCourse.BasicMultithreading;


class Runner1b extends Thread{

    @Override
    public void run() {
        //assigning the task to a distinct thread
        for(int i=0;i<100;i++){
            System.out.println("Runner1:"+i);
          /*  try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }
}
class Runner2b extends Thread{
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            System.out.println("Runner2:"+i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
public class ThreadExample2 {

    //THIS IS MULTI-THREADING(TIME-SLICING) WHICH IS DIFFERENT FROM PARALLEL COMPUTING

    public static void main(String[] args) throws InterruptedException {


        //difference
        Runner1b t1=new Runner1b();
        Runner2b t2=new Runner2b();

        t1.start();
        t2.start();

        t1.join();//it waits for the thread to DIE so that MAIN thread waits for it's completion before executing
        t2.join();

        //THIS statement will be printed first because there's a MAIN thread running (APPLICATION THREAD)
        System.out.println("Finished the task.....");


    }
}