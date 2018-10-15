package MultithreadingCourse.ForkJoinFramework;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelMaxFinding {

    public static int THRESHOLD=0;
    public static void main(String[] args){

        int[] nums=initializeNums();
        THRESHOLD=nums.length/Runtime.getRuntime().availableProcessors();

        ForkJoinPool pool=new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        MaxFinding maxFinding=new MaxFinding(nums,0,nums.length);

       long start=System.currentTimeMillis();
       System.out.println("Max is :"+pool.invoke(maxFinding));
        System.out.println("Time taken:"+(System.currentTimeMillis()-start)+"ms");


    }

    private static int[] initializeNums() {
        Random random=new Random();
        int[] nums=new int[10000];
        for(int i=0;i<10000;i++){

            nums[i]=random.nextInt(1000);
        }
        return nums;
    }

}

class MaxFinding extends RecursiveTask<Integer>{

    //computing sums in a range
    private int[] nums;
    private int lowIndex;
    private int highIndex;

    public MaxFinding(int[] nums,int lowIndex,int highIndex){
        this.nums=nums;
        this.lowIndex=lowIndex;
        this.highIndex=highIndex;
    }


    @Override
    protected Integer compute() {

        if(highIndex-lowIndex<ParallelMaxFinding.THRESHOLD){
            //WE DON'T WANT TO SPLIT THE TASK IF THE RANGE IS TOO LOW
            return sequentialMaxFind();
        }else{
            //split the task
            int middleIndex=(lowIndex+highIndex)/2;
            //inserting the sub-task into the fork-joiin pool
            MaxFinding task1=new MaxFinding(nums,lowIndex,middleIndex);
            MaxFinding task2=new MaxFinding(nums,middleIndex+1,highIndex);

            invokeAll(task1,task2);//forks the tasks into the fork-join pool

            return Math.max(task1.join(),task2.join());
        }
    }


    private  int sequentialMaxFind(){
        int max=nums[lowIndex];

        for(int i=lowIndex+1;i<highIndex;i++){
            if(nums[i]>max){
                max=nums[i];
            }
        }
        return max;
    }
}
