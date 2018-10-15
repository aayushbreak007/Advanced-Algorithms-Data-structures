package SelectionAlgorithm;

import java.util.Random;

public class QuickSelectMedianOfMedians {
    //APPLY HOARE'S ALGORITHM
    private int[] nums;
    public QuickSelectMedianOfMedians(int[] nums){
        this.nums=nums;
    }

    public int select(int k){
        return select(0,nums.length-1,k-1);
    }

    private int select(int firstIndex, int lastIndex, int k) {
        int pivotIndex=partition(firstIndex,lastIndex);
        //compare indexes and not elements
        if(pivotIndex>k){
            //go to the larger elemets side i.e left
            return select(firstIndex,pivotIndex-1,k);
        }else if(pivotIndex<k){
            //go to the smaller elements side i.e right
            return select(pivotIndex+1,lastIndex,k);
        }
        //if pivot==k then return pivot index or k index
        return nums[k];
    }

    private int partition(int firstIndex, int lastIndex) {
        int pivotIndex=new Random().nextInt(lastIndex-firstIndex+1)+firstIndex;//in between 2 indexes
        //swap pivot element to the last element in the array
        swap(pivotIndex,lastIndex);

        //now iterate over the array and partition the array into smaller and larger elements to the pivot
        for(int i=firstIndex;i<lastIndex;i++){
            if(nums[i]>nums[lastIndex]){
                //swap i with firstindex elements i.e x
                swap(i,firstIndex);
                firstIndex++;
            }
        }
        //swap the firstindex with the last index that the pivot element
        swap(firstIndex,lastIndex);
        return firstIndex;//this will be the pivot element
    }


    private void swap(int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

    public static void main(String[] args){
        int[] ar={1,-2,5,7,6,8};
        QuickSelectMedianOfMedians obj=new QuickSelectMedianOfMedians(ar);
        System.out.println(obj.select(1));//prints the kth largest element
    }
}
