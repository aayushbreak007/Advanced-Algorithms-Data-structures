package MultithreadingCourse.ConcurrentCollections;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class FirstWorkerb implements Runnable{

    private BlockingQueue<String> blockingQueue;
    public FirstWorkerb(BlockingQueue<String> blockingQueue){
        this.blockingQueue=blockingQueue;
    }
    @Override
    public void run() {

        try {
            blockingQueue.put("B");
            blockingQueue.put("A");
            Thread.sleep(1000);
            blockingQueue.put("G");
            blockingQueue.put("F");
            Thread.sleep(1000);
            blockingQueue.put("E");
            blockingQueue.put("C");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class SecondWorkerb implements Runnable{

    private BlockingQueue<String> blockingQueue;
    public SecondWorkerb(BlockingQueue<String> blockingQueue){
        this.blockingQueue=blockingQueue;
    }
    @Override
    public void run() {

        try {
            Thread.sleep(5000);
            System.out.println(blockingQueue.take());
            Thread.sleep(1000);
            System.out.println(blockingQueue.take());
            Thread.sleep(1000);
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class PriorityBlockingQueueExample {

    public static void main(String[] args){
        BlockingQueue<String> priorityBlockingQueue=new PriorityBlockingQueue<>();

        new Thread(new FirstWorkerb(priorityBlockingQueue)).start();
        new Thread(new SecondWorkerb(priorityBlockingQueue)).start();
    }
}
