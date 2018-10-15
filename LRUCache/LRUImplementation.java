package LRUCache;

import java.util.HashMap;
import java.util.Map;

public class LRUImplementation {

    private int actualSize;
    private Map<Integer,Node> map;
    private DoublyLinkedList linkedList;

    public LRUImplementation(){
        this.map=new HashMap<>();
        this.linkedList=new DoublyLinkedList();
    }

    public void put(int id,String data){
        //update the node if already exists
        //if the item is revisited then set it as the head and shift the rest of the items
        if(map.containsKey(id)){
            Node node=this.map.get(id);
            node.setData(data);
            System.out.println("Inside put:"+node);

            //update the node to be the new head(cache feature)
            update(node);
            return;
        }

        //data not present in the cache
        Node node=new Node(id,data);
        //we have to insert the node and set it to be the head node
        if(this.actualSize<Constants.CAPACITY){
            this.actualSize++;
            add(node);
        }else{
            //cache is full....remove the last node and insert the new one
            removeTail();
            add(node);
        }
    }

    //rmeove the last itme from the cache
    private void removeTail() {
        //get the last node from the map
        Node lastNode=this.map.get(this.linkedList.getTailNode().getId());

        //previous to tail node is the "NEW tail node" since we remove the actual tail node
        this.linkedList.setTailNode(linkedList.getTailNode().getPreviousNode());

        //set the next to tail node to be null
        if(this.linkedList.getTailNode()!=null){
            this.linkedList.getTailNode().setNextNode(null);
        }

        lastNode=null;
    }

    //add at the begnining
    private void add(Node node) {
        node.setNextNode(this.linkedList.getHeadNode());
        node.setPreviousNode(null);

        //set ex-head to the new head if it is not the 1st node
        if(linkedList.getHeadNode()!=null){
            linkedList.getHeadNode().setPreviousNode(node);
        }
        //set the new head
        this.linkedList.setHeadNode(node);

        //if there is 1 node in the list then it is the head and tail both
        if(linkedList.getTailNode()==null){
            linkedList.setTailNode(node);
        }

        //update the map with the insertion of new node
        this.map.put(node.getId(),node);
    }


    //will move the given node to the front
    private void update(Node node) {
        Node previousNode=node.getPreviousNode();
        Node nextNode=node.getNextNode();

        //hence it is the middle node in the list
        if(previousNode!=null){
            previousNode.setNextNode(nextNode);
        }else{
            //it is the first node
            this.linkedList.setHeadNode(nextNode);
        }

        //is it is the last node
        if(nextNode!=null){
            //not the last node
            nextNode.setPreviousNode(previousNode);
        }else{
            //last node
            this.linkedList.setTailNode(previousNode);
        }


        //move the given node to the head
        add(node);
    }



    //****************************TESTING*************************************
    public Node get(int id){
        if(!map.containsKey(id)){
            return null;
        }
        Node node=map.get(id);
        //move the recent visited item to the head
        update(node);
        return node;
    }

    public void show(){
        Node actualNode=this.linkedList.getHeadNode();
        while(actualNode!=null){
            System.out.print(actualNode+"<->");
            actualNode=actualNode.getNextNode();
        }
    }

    public static void main(String[] args){
        LRUImplementation cache=new LRUImplementation();
        cache.put(0,"A");
        cache.put(1,"B");
        cache.put(2,"C");
        cache.put(3,"D");
        cache.put(4,"E");
        cache.put(5,"F");
        cache.put(6,"G");

        System.out.println(cache.get(6));
        cache.show();
        System.out.println();

        System.out.println(cache.get(3));
        cache.show();
        System.out.println();

        System.out.println(cache.get(4));
        cache.show();
        System.out.println();
    }
}
class Constants{
    public Constants(){

    }

    public static final int CAPACITY=4;

}
class DoublyLinkedList{
    private Node headNode;
    private Node tailNode;

    public DoublyLinkedList(){

    }

    public Node getHeadNode() {
        return headNode;
    }

    public Node getTailNode() {
        return tailNode;
    }

    public void setHeadNode(Node headNode) {
        this.headNode = headNode;
    }

    public void setTailNode(Node tailNode) {
        this.tailNode = tailNode;
    }
}


class Node{
    private int id;//keys in the hashtable
    private String data;
    private Node previousNode;
    private Node nextNode;

    public Node(){

    }
    public Node(int id,String data){
        this.id=id;
        this.data=data;
    }

    public int getId() {
        return id;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    @Override
    public String toString() {
        return this.data;
    }
}
