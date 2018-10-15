package MultithreadingCourse.ParallelAlgorithms;

import java.util.Random;

public class ParallelSummationImplementation {

    public static void main(String[] args){
        Random random=new Random();
        int[] nums=new int[100000000];
        for(int i=0;i<nums.length;i++){
            nums[i]=random.nextInt(100);
        }
        int numOfProcessors=Runtime.getRuntime().availableProcessors();//4 processors

        long start=System.currentTimeMillis();
        ParallelSum parallelSum=new ParallelSum(numOfProcessors);
        System.out.println("Sum is:"+parallelSum.sum(nums));
        System.out.println("Parallel sum takes:"+(System.currentTimeMillis()-start)+" ms");
    }

}

class ParallelSum{
    private ParallelWorker[] sums;
    private int numOfThreads;

    public ParallelSum(int numOfThreads){
        this.numOfThreads=numOfThreads;
        this.sums=new ParallelWorker[numOfThreads];
    }

    public int sum(int[] nums){

        //dividing the 1-D array into as many portions as number of threads
        int steps=(int)Math.ceil(nums.length*1.0/numOfThreads);
        for(int i=0;i<numOfThreads;i++){
            sums[i]=new ParallelWorker(nums,i*steps,(i+1*steps));
            sums[i].start();
        }

        //join parallel workers
        for(ParallelWorker worker:sums){
            try {
                worker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int total=0;
        for(ParallelWorker worker:sums){
            total+=worker.getPartialSum();
        }
        return total;
    }
}

class ParallelWorker extends Thread{
    private int[] nums;
    private int low;
    private int high;
    private int partialSum;//partial array sum b/w low and high index

    public ParallelWorker(int[] nums,int low,int high){
        this.nums=nums;
        this.low=low;
        this.high=high;

    }

    public int getPartialSum() {
        return partialSum;
    }

    @Override
    public void run() {
        partialSum=0;
        for(int i=low;i<high;i++){
            partialSum=partialSum+nums[i];
        }
    }
}
