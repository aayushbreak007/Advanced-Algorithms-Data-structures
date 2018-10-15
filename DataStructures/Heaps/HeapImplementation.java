package DataStructures.Heaps;

public class HeapImplementation {
    //we represent "Heaps" as a 1-D array
    private int[] heap;

    //we want to track the no.of items in the heap
    private int heapSize;//...it will 0 in the beginning ofcourse

    public HeapImplementation(){
        this.heap=new int[10];//10 items in the heap max
    }


    //INSERTION --O( LogN) with heap properties maintained
    public void insert(int item){

        if(isHeapFull()){
            throw new RuntimeException("Heap is full...");
        }

        //insert the item and increment the heap size
        this.heap[heapSize]=item;
        heapSize++;

        //check if heap properties are violated or not
        fixUp(heapSize-1);

    }

    private void fixUp(int index) {

        //get the parent index
        int parentIndex=(index-1)/2;
        //if it is a MAX heap then parent should be greater than the children
        if(index>0 && (heap[index]>heap[parentIndex])){
            //swap
            swap(index,parentIndex);
            //call recursively
            fixUp(parentIndex);
        }
    }

    //REMOVAL---O( LOGN)
    public int remove(){
        int max=this.heap[0];//peeking only

        //swap root with the last element
        swap(0,heapSize-1);
        this.heapSize--;//removing the items finally

        //check if the new root violates the HEAP properties
        fixDown(0);
        return max;
    }

    private void fixDown(int index) {
         int indexLeftToParent=2*index+1;
         int indexRightToParent=2*index+2;

         int largestIndex=index;
         //swap the current root with the largest item either on left or right side recursively
         if(indexLeftToParent<heapSize && heap[indexLeftToParent]>heap[index]){
             largestIndex=indexLeftToParent;
         }
         if(indexRightToParent<heapSize && heap[indexRightToParent]>heap[largestIndex]){
             largestIndex=indexRightToParent;
         }

         if(index!=largestIndex){
             swap(largestIndex,index);
             //recursion
             fixDown(largestIndex);
         }
    }

    public void heapSort(){
        int size=this.heapSize;

        for(int i=0;i<size;i++){
            int max=remove();
            System.out.println(max+" ");
        }
    }



    private void swap(int index, int parentIndex) {
        int temp=heap[index];
        heap[index]=heap[parentIndex];
        heap[parentIndex]=temp;
    }

    private boolean isHeapFull() {
        return this.heap.length==10;
    }
}
