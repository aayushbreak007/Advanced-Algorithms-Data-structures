package DataStructures.LinkedLists;

public class PlayingWithLinkedList<T extends Comparable<T>> {

    private Node<T> root;
    private int sizeOfList;


    //O(1)
    public void insertAtBeginning(T data){
        Node newNode=new Node(data);
        this.sizeOfList++;

        if(this.root==null){
            //newNode is the first node...make it root
            this.root=newNode;
        }else{

            /*Note: insertion at the beginning is preferred for LinkedList and not insertion at end due to its
              purpose of usage.Complexity is 0(N) for insertion at the end while it is O(1) at beg
             */
            newNode.setNextNode(this.root);
            this.root=newNode;
        }
    }

    //0(1)
    public void removalAtBeginning(){
        if(this.root==null){
            System.out.println("List is Empty!!");
            return;

        }
        this.root=this.root.getNextNode();//making next node as the root node
        this.sizeOfList--;
    }

    //O(N)
    public void traverseList(){
        if(this.root==null) {
            return;
        }
        Node currentNode=this.root;
        while(currentNode!=null){
            System.out.print(currentNode.getData()+"->");
            currentNode=currentNode.getNextNode();
        }

    }
    //***************************************************CUSTOM-METHODS**************************************************

    public void insertionAtPos(T data,int index){
        Node newNode=new Node(data);
        if(this.root==null){
            this.root=newNode;
            return;
        }
        Node currentNode=this.root;
        if(index==1){
            insertAtBeginning(data);
        }else{
            int currPos=0;
            while(currentNode.getNextNode()!=null){
                currPos++;
                if(currPos==index-1){//holding on to the previous node
                    Node nextNode=currentNode.getNextNode();
                    currentNode.setNextNode(newNode);
                    newNode.setNextNode(nextNode);
                    this.sizeOfList++;
                    return;
                }
                currentNode=currentNode.getNextNode();
            }
            //if it is insertion at the end
            currentNode.setNextNode(newNode);
        }
    }


    public void removalAtPos(int index){
        if(this.root==null){
            return;
        }
        Node currentNode = this.root;
        if(index==1){
            //removal at the beginning
            removalAtBeginning();

        }else {
            int currPos = 0;

            while (currentNode.getNextNode() != null) {
                currPos++;
                if (currPos == index - 1) {
                    Node nextNode=currentNode.getNextNode();
                    currentNode.setNextNode(nextNode.getNextNode());
                    this.sizeOfList--;
                    return;
                }
                currentNode=currentNode.getNextNode();
            }


        }
    }


    public static void main(String[] args){
        PlayingWithLinkedList obj=new PlayingWithLinkedList();
        obj.insertAtBeginning(10);
        obj.insertAtBeginning(11);
        obj.insertAtBeginning(12);
        obj.insertAtBeginning(100);
        obj.traverseList();

        obj.insertionAtPos(13,3);
        System.out.println();
        obj.traverseList();

        obj.removalAtPos(5);
        System.out.println();
        obj.traverseList();
    }

}
class Node <T extends Comparable<T>>{
    private T data;
    private Node<T> nextNode;

    public Node(T data){
        this.data=data;
    }

    //getter and setters
    public Node<T> getNextNode(){
        return nextNode;
    }
    public void setNextNode(Node<T> nextNode){
        this.nextNode=nextNode;
    }
    public T getData(){
        return data;
    }
    public void setData(T data){
        this.data=data;
    }

    //overriding to string method
    @Override
    public String toString(){
        return this.data.toString();
    }
}