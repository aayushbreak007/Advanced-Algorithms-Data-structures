package MultithreadingCourse.BasicMultithreading;


class Worker implements Runnable{


    /*CPU "Caches" the variable for the current "THREAD" and will not effect if we Change the "IsTerminated" value ,if it is not "VOLATILE"*/
    private volatile boolean isTerminated=false;

    @Override
    public void run() {

        while(!isTerminated){
            System.out.println("Hello from worker class...");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isTerminated() {
        return isTerminated;
    }

    public void setTerminated(boolean terminated) {
        this.isTerminated = terminated;
    }

}
public class VolatileExample {

    public static void main(String[] args) {
        Worker worker = new Worker();
        Thread t1 = new Thread(worker);
        t1.start();

        //The thread will run for 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.setTerminated(true);//VALUE SET IS "INEFFECTIVE" Since variable is not VOLATILE and is being read from the "CACHE"
        System.out.println("Finished....");
    }


}