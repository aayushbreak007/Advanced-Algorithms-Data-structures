package MultithreadingCourse.BasicMultithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

//for singleton pattern
enum Downloader{
    INSTANCE;
    //now this will run 3 threads simultaneously with permitted THREAD PERMITS and other threads will wait for these 3 threads to finish
    private Semaphore semaphore=new Semaphore(3,true);
    public void downloadData(){
        try {
            semaphore.acquire();//acquires lock
            download();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }

    private void download() {
        System.out.println("Downloading data from the web....");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
public class SemaphoreExample {

    public static void main(String[] args){

        //IF YOU DONT WANT TO CREATE THREADS MANUALLY THEN USE ..."EXECUTER SERVICE"--IT CREATES AUTOMATICALLY
        ExecutorService executorService= Executors.newCachedThreadPool();

        for(int i=0;i<12;i++){
            //now we can create several threads
            executorService.execute(new Runnable() {
                @Override
                public void run() {

                    Downloader.INSTANCE.downloadData();

                }
            });
        }

    }
}