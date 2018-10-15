package MultithreadingCourse.ConcurrentCollections;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayedWorker implements Delayed{

    private long duration;
    private String message;
    public DelayedWorker(long duration,String message){
        this.duration=System.currentTimeMillis()+duration;
        this.message=message;
    }
    @Override
    public long getDelay(TimeUnit unit) {


        return unit.convert(duration-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed otherDelayed) {
        if(this.duration<((DelayedWorker)otherDelayed).getDuration()){
            return -1;//
        }
        if(this.duration>((DelayedWorker)otherDelayed).getDuration()){
            return +1;//
        }
        return 0;
    }

    //getter and setters

    public long getDuration() {
        return duration;
    }

    public String getMessage() {
        return message;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return this.message;
    }
}
public class DelayQueuesExample {

    //USEFUL FOR SERVER BASED APPS
    public static void main(String[] args){
        BlockingQueue<DelayedWorker> queue=new DelayQueue<>();

        //adding the message and its delay time
        try {
            queue.put(new DelayedWorker(1000,"This is the first message.."));
            queue.put(new DelayedWorker(10000,"This is the second message.."));//wait 10 seconds to take out the second message
            queue.put(new DelayedWorker(4000,"This is the third message.."));//wait 4 seconds to take out the third message
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while(!queue.isEmpty()){
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
