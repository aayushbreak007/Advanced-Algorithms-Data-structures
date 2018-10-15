package MultithreadingCourse.ConcurrentCollections;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class FirstWorker implements Runnable{

    private BlockingQueue<Integer>blockingQueue;
    public FirstWorker(BlockingQueue<Integer> blockingQueue){
        this.blockingQueue=blockingQueue;
    }
    @Override
    public void run() {

        int counter=0;
        while(true){
            try {
                blockingQueue.put(counter);
                System.out.println("Putting items to the queue...."+counter);

                counter++;
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }
    }
}

class SecondWorker implements Runnable{

    private BlockingQueue<Integer>blockingQueue;
    public SecondWorker(BlockingQueue<Integer> blockingQueue){
        this.blockingQueue=blockingQueue;
    }
    @Override
    public void run() {


        while(true){
            try {
                int number=blockingQueue.take();
                System.out.println("Taking item from the queue..."+number);
                Thread.sleep(5000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }
    }
}

public class BlockingQueuesExample {

    //IMPLEMENTS PRODUCER/CONSUMER EFFECTIVELY WITHOUT LOW-LEVEL WAIT()/NOTIFY() OPERATIONS
    public static void main(String[] args){
        BlockingQueue<Integer> blockingQueue=new ArrayBlockingQueue<Integer>(10);
        FirstWorker firstWorker=new FirstWorker(blockingQueue);
        SecondWorker secondWorker=new SecondWorker(blockingQueue);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();
    }
}
