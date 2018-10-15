package DataStructures.LinkedLists;


public class MergeSortLinkedList {

    private static MyLinkedListNode root;

    private  void insert(int data){
        MyLinkedListNode newNode=new MyLinkedListNode(data);
        if(this.root==null){
            this.root=newNode;
            return;
        }
        //insert in the beg
        newNode.next=this.root;
        root=newNode;

    }
    private void printLL(MyLinkedListNode startNode){
        if(startNode==null){
            return;
        }
        MyLinkedListNode currentNode=startNode;
        while(currentNode!=null){
            System.out.print(currentNode.data+"->");
            currentNode=currentNode.next;
        }
    }

    public static void main(String[] args) {

        MergeSortLinkedList mergeSortLinkedList=new MergeSortLinkedList();
        mergeSortLinkedList.insert(20);
        mergeSortLinkedList.insert(1);
        mergeSortLinkedList.insert(54);
        mergeSortLinkedList.insert(23);
        mergeSortLinkedList.insert(89);
        mergeSortLinkedList.insert(12);
        mergeSortLinkedList.insert(9);
        mergeSortLinkedList.insert(34);
        mergeSortLinkedList.insert(100);
        mergeSortLinkedList.insert(67);

        mergeSortLinkedList.printLL(root);


        MyLinkedListNode head=applyMergeSort(root);

        System.out.println();
        mergeSortLinkedList.printLL(head);

    }

    private static MyLinkedListNode applyMergeSort(MyLinkedListNode head) {
        if(head==null || head.next==null){
            return head;
        }
        //1.)First get the middle node of linked list
        MyLinkedListNode middleNode=getMiddle(head);
        MyLinkedListNode nextToMiddleNode=middleNode.next;
        middleNode.next=null;//separate into 2 halves

        MyLinkedListNode left=applyMergeSort(head);
        MyLinkedListNode right=applyMergeSort(nextToMiddleNode);

        //merge the 2 halves in a sorted fashion
        MyLinkedListNode sortedHead=mergeHalves(left,right);
        return sortedHead;

    }

    private static MyLinkedListNode mergeHalves(MyLinkedListNode left, MyLinkedListNode right) {
        if(left==null){
            return right;
        }
        if(right==null){
            return left;
        }
        MyLinkedListNode result=null;
        if(left.data<=right.data){
            result=left;
            result.next=mergeHalves(left.next,right);
        }else{
            result=right;
            result.next=mergeHalves(left,right.next);
        }
        return result;
    }


    private static MyLinkedListNode getMiddle(MyLinkedListNode root) {
        //make 2 pointers...slow and fast. (Increment slow by 1 and fast by 2...the end position of the slow pointer will be the middle node)
        MyLinkedListNode slowPointer=root;
        MyLinkedListNode fastPointer=root.next;

        while(fastPointer!=null){
            fastPointer=fastPointer.next;
            if(fastPointer!=null){
                slowPointer=slowPointer.next;
                fastPointer=fastPointer.next;
            }
        }
        return slowPointer;
    }

}

class MyLinkedListNode{
    int data;
    MyLinkedListNode next;

    public MyLinkedListNode(int data){
        this.data=data;
        this.next=null;
    }
}

