package SortingAlgorithms;

import static SortingAlgorithms.BubbleSort.showArray;


public class InsertionSort {
    //requires lots of shifts as it compares the previous elements with the current and check for smaller and swaps

    public static void main(String[] args){
        int[] nums={1,4,2,0,-10,-5,10,100};

        //insertion sort
        for(int i=1;i<nums.length;i++){
            int j=i;
            while(j>0 && nums[j]<nums[j-1]){
                //if it is smaller than previous elements then keep swapping
                swapNum2(nums,j,j-1);
                j=j-1;//keep checking for previous elements
            }
        }
        showArray(nums);

    }

    private static void swapNum2(int[] nums, int i, int j) {
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}
