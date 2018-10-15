package Dynamic_Programming;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    //MEMOIZE TABLE
    private Map<Integer,Integer> memoizeTable;
    public Fibonacci(){
        this.memoizeTable=new HashMap<>();
        this.memoizeTable.put(0,0);//when key is 0 value is 0 for fibonacci numbers
        this.memoizeTable.put(1,1);//when the key is 1 the value is 1
    }

    //O(N)
    public int fibonacciDP(int n){
        //solves in Linear time
        if(memoizeTable.containsKey(n)){
            return memoizeTable.get(n);//o(1)
        }
        //recursively add values to the keys in the memoize table
        //BINET FORMULA
        memoizeTable.put(n-1,fibonacciDP(n-1));
        memoizeTable.put(n-2,fibonacciDP(n-2));

        int calculatedNumber=memoizeTable.get(n-1)+memoizeTable.get(n-2);
        //add the calculated value also in to the memoize table
        memoizeTable.put(n,calculatedNumber);
        return calculatedNumber;
    }

    //O(2^N)
    public int naiveFibonacci(int n){
        //IT HAS EXPONENTIAL RUNNING TIME
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        return naiveFibonacci(n-1)+naiveFibonacci(n-2);//starts from ofcourse
    }
    public static void main(String[] args){
        Fibonacci fibonacci=new Fibonacci();
        System.out.println(fibonacci.fibonacciDP(1000));

    }
}
