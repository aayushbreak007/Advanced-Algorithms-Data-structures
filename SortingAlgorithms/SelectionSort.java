package SortingAlgorithms;

import static SortingAlgorithms.BubbleSort.showArray;

public class SelectionSort {
    public static void main(String[] args){
        int[] nums={3,6,0,-5,5,7,2,1};

        for(int i=0;i<nums.length-1;i++){
            int index=i;
            for(int j=i+1;j<nums.length;j++){
                if(nums[j]<nums[index]){
                    index=j;//keep storing the smallest element in the array
                }
            }
            if(index!=i){//do not awap the smallest item if it is left most element
                //swap the smallest element with the leftmost element
                int temp=nums[i];
                nums[i]=nums[index];
                nums[index]=temp;
            }
        }
        showArray(nums);

    }
}
