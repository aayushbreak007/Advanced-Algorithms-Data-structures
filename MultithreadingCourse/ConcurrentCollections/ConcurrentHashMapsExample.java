package MultithreadingCourse.ConcurrentCollections;

import java.util.concurrent.ConcurrentHashMap;

class FirstWorkerc implements Runnable{

    private ConcurrentHashMap<String,Integer> map;

    public FirstWorkerc(ConcurrentHashMap<String,Integer> map){
        this.map=map;
    }
    @Override
    public void run() {

        try {
            map.put("B", 1);
            map.put("H", 2);
            Thread.sleep(1000);
            map.put("F", 3);

            map.put("A", 4);
            Thread.sleep(1000);
            map.put("E", 5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class SecondWorkerc implements Runnable{

    private ConcurrentHashMap<String,Integer> map;

    public SecondWorkerc(ConcurrentHashMap<String,Integer> map){
        this.map=map;
    }
    @Override
    public void run() {

        try {
            Thread.sleep(5000);
            System.out.println(map.get("A"));
            Thread.sleep(1000);
            System.out.println(map.get("E"));
            System.out.println(map.get("F"));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
public class ConcurrentHashMapsExample {

    public static void main(String[] args){
        ConcurrentHashMap<String,Integer>concurrentHashMap=new ConcurrentHashMap<>();

        new Thread(new FirstWorkerc(concurrentHashMap)).start();
        new Thread(new SecondWorkerc(concurrentHashMap)).start();
    }
}
