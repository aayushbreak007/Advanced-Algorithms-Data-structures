package SortingAlgorithms;

import static SortingAlgorithms.BubbleSort.showArray;

public class CountingSort {

    private int[] nums;
    public CountingSort(int[] nums){
        this.nums=nums;
    }
   public void countingSort(int min,int max){
        int[] countArray=new int[max-min+1];
        for(int i=0;i<nums.length;i++){//counts the occurances of values
            countArray[nums[i]-min]++;
        }
        int z=0;
        for(int i=min;i<=max;i++){


            //place the index value as many times the count value inside it in the final output array
            while(countArray[i-min]>0){
                //places objects at its correct position and decreses the count by 1
                this.nums[z]=i;
                z++;
                countArray[i-min]--;
            }

        }
   }

   public static void main(String[] args){
       int[] nums={1,5,3,2,2,1,4,5};
       CountingSort countingSort=new CountingSort(nums);
       countingSort.countingSort(1,5);
       showArray(nums);
   }
}
