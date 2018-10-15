package SortingAlgorithms;

import java.util.Arrays;

public class RadixSort {

    //O(K*N)
    private static int getMaxNumber(int[] ar, int n) {
        int max=ar[0];
        for(int i=0;i<ar.length;i++){
            if(ar[i]>max){
                max=ar[i];
            }
        }
        return max;
    }
    private static void countSort(int[] ar, int n, int exp) {
        int output[]=new int[n];
        int buckets[]=new int[10];//0-9 buckets for the number
        Arrays.fill(buckets,0);

        //store count of occurances the "CURRENT DIGIT" of the Current number in the respective BUCKET NUMBER
        for(int i=0;i<n;i++){
            buckets[(ar[i]/exp)%10]++;
        }

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (int i = 1; i < 10; i++) {
            buckets[i] += buckets[i - 1];
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--)
        {
            output[buckets[ (ar[i]/exp)%10 ] - 1] = ar[i];
            buckets[ (ar[i]/exp)%10 ]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to "CURRENT DIGIT"
        for (int i = 0; i < n; i++)
            ar[i] = output[i];
    }
    private static void radix(int[] ar, int n) {
        //finding the max number in the array to know the max no. of digits
        int m=getMaxNumber(ar,n);

        //do counting sort ACCORDING TO EVERY DIGIT FROM RIGHT TO LEFT
        for(int exp=1;m/exp>0;exp*=10){
            countSort(ar,n,exp);//COUNTING SORT
        }
    }




    private static void printArray(int[] ar, int n) {
        for (int i=0; i<n; i++)
            System.out.print(ar[i]+" ");
    }
    public static void main(String[] args){
        int[] ar={170,45,75,90,802,24,2,66};
        int n=ar.length;
        radix(ar,n);
        printArray(ar,n);
    }




}
