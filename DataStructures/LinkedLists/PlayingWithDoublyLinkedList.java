package DataStructures.LinkedLists;

public class PlayingWithDoublyLinkedList {
    /**
     * A doubly Linked list has 2 pointers from and to each node in it. Both previous and forward, making it a bi-directional flow
     *
     * But 'WHY' and 'WHEN' wto go for a 'DOUBLY linked list'???
     * 1.) easily insertion at the end and front. The first and Last nodes are easily accessible...without traversal
     * 2.) can work as a queue and stack both
     * 3.) node deletion requires no additional pointers and can be done in O(1)
     */

    private static DoublyLinkedListNode root;
    private void insertAtFront(int data){
        DoublyLinkedListNode newNode=new DoublyLinkedListNode(data);
        if(root==null){
            root=newNode;
            return;
        }
        //insert at beg
        newNode.next=root;
        root.prev=newNode;
        root=newNode;
    }

    private DoublyLinkedListNode getNodeAtPos(int index){
        if(root==null){
            return root;
        }
        if(index==0){
            return root;
        }
        DoublyLinkedListNode currentNode=root;
        int count=0;
        while(currentNode!=null){

            count++;
            if(count==index){
                return currentNode;
            }
            currentNode=currentNode.next;

        }
        return currentNode;
    }

    private void insertAtPos(int data,DoublyLinkedListNode givenNode){
        //insertion is better if the node after which the node is to be inserted is given
        DoublyLinkedListNode newNode=new DoublyLinkedListNode(data);
        if(givenNode==null){
            return;
        }
        if(givenNode==root){
            root.next=newNode;
            newNode.prev=root;
            root=newNode;

        }else{
            if(givenNode.next!=null){
                DoublyLinkedListNode nextNode=givenNode.next;
                newNode.next=nextNode;
                nextNode.prev=newNode;
                givenNode.next=newNode;
                newNode.prev=givenNode;}
            else{
                //insertion at the end
                givenNode.next=newNode;
                newNode.prev=givenNode;
            }
        }
    }

    private void reverseDoubly(DoublyLinkedListNode node){
        if(node==null){
            return ;
        }
        if(node.next!=null){
            reverseDoubly(node.next);
        }
        System.out.print(node.data+"<->");
    }



    private void printDoubly(){
        if(root==null){
            return;
        }
        DoublyLinkedListNode currentNode=root;
        while(currentNode!=null){

            System.out.print(currentNode.data+"-><-");
            currentNode=currentNode.next;

        }
    }
    public static void main(String[] args){
        PlayingWithDoublyLinkedList obj=new PlayingWithDoublyLinkedList();
        obj.insertAtFront(10);
        obj.insertAtFront(11);
        obj.insertAtFront(18);
        obj.insertAtFront(90);
        obj.insertAtFront(100);
        obj.insertAtFront(1);


        obj.printDoubly();
        obj.insertAtPos(300,obj.getNodeAtPos(6));
        System.out.println();
        obj.printDoubly();

        System.out.println();
        obj.reverseDoubly(root);


    }

}
class DoublyLinkedListNode{
    int data;
    DoublyLinkedListNode prev;
    DoublyLinkedListNode next;

    public DoublyLinkedListNode(int data){
        this.data=data;
        this.prev=null;
        this.next=null;
    }
}