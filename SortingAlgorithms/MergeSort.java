package SortingAlgorithms;

import java.util.Random;

import static SortingAlgorithms.BubbleSort.showArray;

public class MergeSort {

    private int[] nums;
    private int[] tempArray;

    public MergeSort(int[] nums){
        this.nums=nums;
        tempArray=new int[nums.length];
    }

    public void mergeSort(int low,int high){
        if(low>=high){
            return;
        }
        int middle=(low+high)/2;

        //recursively divide the array into subarrays(left and right)
        //the subarray is sorted if it only has single element left
        mergeSort(low,middle);//left
        mergeSort(middle+1,high);//right

        //conquer---merge the subarrays into sorted ways
        merge(low,middle,high);
    }

    private void merge(int low, int middle, int high) {
         //now this is where merge sort require O(n) space complexity
        for(int i=low;i<=high;i++){
            tempArray[i]=nums[i];
        }

        int i=low;
        int j=middle+1;
        int k=low;//for the temp array

        while(i<=middle && j<=high){
            if(tempArray[i]<=tempArray[j]){
                nums[k]=tempArray[i];
                i++;
            }else{
                nums[k]=tempArray[j];
                j++;
            }
            k++;
        }
        //copy the rest of the left side sorted of the subarray to the final array
        while(i<=middle){
            nums[k]=tempArray[i];
            i++;
            k++;
        }

        //copy the rest of the right sorted of the subarray to the final array
        while(j<=high){
            nums[k]=tempArray[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args){
        Random random=new Random();
        int[] nums=new int[30];
        for(int i=0;i<nums.length;i++){
            nums[i]=random.nextInt(1000)-500;
        }

        MergeSort mergeSort=new MergeSort(nums);
        mergeSort.mergeSort(0,nums.length-1);
        showArray(nums);
    }
}
