package SortingAlgorithms;

import static SortingAlgorithms.BubbleSort.showArray;

public class ShellSort {
    private int[] array;
    public ShellSort(int[] array){
        this.array=array;
    }
    public void shellSort(){
        for(int gap=array.length/2;gap>0;gap/=2){

            for(int i=gap;i<array.length;i++){
                int j=i;

                //insertion sort with gaps is shell sort
                while(j>gap && array[j]<array[j-gap]){
                    swapNum3(array,j,j-gap);
                    j=j-gap;
                }
            }
        }
        showArray(array);
    }

    private void swapNum3(int[] array, int j, int i) {
        int temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }
    public static void main(String[] args){
        int[] nums={-1,0,3,6,2,1,0,5,6,7,8,-50,100};
        ShellSort shellSort=new ShellSort(nums);
        shellSort.shellSort();
    }
}
