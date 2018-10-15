package SortingAlgorithms;

public class QuickSort {

    private int[] nums;

    public QuickSort(int[] nums){
        this.nums=nums;
    }

    public void sort(){
        quickSort(0,nums.length-1);
    }
    public void showArray2(){
        for(int i=0;i<nums.length;i++){
            System.out.print(nums[i]+" ");
        }
    }
    private void quickSort(int low,int high){
        if(low>=high){
            return;
        }
        int pivot=partition(low,high);
        quickSort(low,pivot-1);
        quickSort(pivot+1,high);
    }

    private int partition(int low,int high){

        int pivotIndex=(low+high)/2;//choosing the middle element always a the pivot
        //swap pivot with the last element in the array
        swapNum4(pivotIndex,high);

        int i=low;
        for(int j=low;j<high;j++){
            //pivot is the high
            //so compare each element with the pivot and put put smaller elements than pivot on the left and other on the right
            if(nums[j]<=nums[high]){
                //swap i and j
                swapNum4(i,j);
                i++;
            }
        }
        //swap i with the high to bring pivot as the partitioning element
        swapNum4(i,high);
        return i;//returns the pivot index
    }

    private void swapNum4(int i, int j) {
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

    public static void main(String[] args){
        int[] nums={1,5,3,7,6,7,8,3,2};
        QuickSort quickSort=new QuickSort(nums);
        quickSort.sort();
        quickSort.showArray2();
    }
}
