package MultithreadingCourse.ForkJoinFramework;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Fork_JoinImplementation {

    public static void main(String[] args){
        ForkJoinPool pool=new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        //we don't want to create threads more than the cores/processors ...so we use the pool which automatically mananges it
        SimpleRecursiveTask simpleRecursiveTask=new SimpleRecursiveTask(120);
        System.out.println(pool.invoke(simpleRecursiveTask));
    }

}
class SimpleRecursiveTask extends RecursiveTask<Integer>{

    private int simulatedWork;
    public SimpleRecursiveTask(int simulatedWork){
        this.simulatedWork=simulatedWork;
    }
    @Override
    protected Integer compute() {

        if(simulatedWork>100){
            System.out.println("Parallel execution and split task....."+ simulatedWork);

            //division of the task
            SimpleRecursiveTask task1=new SimpleRecursiveTask(simulatedWork/2);
            SimpleRecursiveTask task2=new SimpleRecursiveTask(simulatedWork/2);


            //on calling fork it will recurse again to sub-divide task further on
            task1.fork();
            task2.fork();

            int solution=0;
            //finish the task and merge
            solution+=task1.join();
            solution+=task2.join();
            return solution;

        }else{
            System.out.println("No need for parallel execution....."+simulatedWork);
            return 2*simulatedWork;
        }
    }
}