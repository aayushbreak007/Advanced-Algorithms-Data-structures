package Recursion;

public class BinarySearch {
    public static int[]array={1,2,6,7,10};
    public static int binarySearch(int x){
        return search(0,array.length-1,x);
    }

    private static int search(int start, int end, int x) {
        if(end<start){
            System.out.println("Item not found...");
            return -1;
        }
        int mid=(start+end)/2;
        if(x==array[mid]){
            return mid;
        }else if(x<array[mid]){
            return search(start,mid-1,x);
        }else{
            return search(mid+1,end,x);
        }
    }
    public static void main(String[] args){

        System.out.println(binarySearch(3));
    }
}
