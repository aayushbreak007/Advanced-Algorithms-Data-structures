package SortingAlgorithms;



public class BubbleSort {
    public static void main(String[] args){
        int[] nums={12,7,-5,-77,102};
        showArray(nums);

        //bubbleSort--bubbles out the largest item to the right in case of ascending order
        for(int i=0;i<nums.length-1;i++){
            for(int j=0;j<nums.length-1-i;j++){
                //because on every iteration the largets element will be the last element and we have to consider fewer items on every iteration
                if(nums[j]>nums[j+1]){//ascending
                    swapNum(nums,j,j+1);
                }
            }
        }
        System.out.println();
        showArray(nums);
    }

    public static void swapNum(int[] nums, int j, int i) {
        int temp=nums[j];
        nums[j]=nums[j+1];
        nums[j+1]=temp;
    }

    public static void showArray(int[] nums) {
        for(int i=0;i<nums.length;i++){
            System.out.print(nums[i]+" ");
        }
    }
}
