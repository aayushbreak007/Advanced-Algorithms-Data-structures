package DataStructures.Trees;

public class SplayTrees<T extends Comparable<T>> {
    private SplayNode<T> root;
    /************************************INSERT INTO SPLAY TREE***********************************/
    public void insert(T data){
        //DIFFERENT INSERTION USING parentNode
        SplayNode<T> currentNode=this.root;
        SplayNode<T> parentNode=null;

        while(currentNode!=null){

            parentNode=currentNode;//used this loop to get the parent

            if(data.compareTo(currentNode.getData())<0){
                //go to left
                currentNode=currentNode.getLeftNode();
            }else{
                //go to right
                currentNode=currentNode.getRightNode();
            }
        }
        currentNode=new SplayNode<>(data);
        currentNode.setParentNode(parentNode);

        if(parentNode==null){
            this.root=currentNode;
        }else if(parentNode.getData().compareTo(currentNode.getData())<0){
            //set to right
            parentNode.setRightNode(currentNode);
        }else{
            //set to left
            parentNode.setLeftNode(currentNode);
        }
        splay(currentNode);
    }


    /*****************************************SEARCH INTO SPLAY TREE************************************/
    public SplayNode<T> find(T data){

        //iteration
        SplayNode<T> currentNode=this.root;
        while(currentNode!=null){
            if(data.compareTo(currentNode.getData())<0){
                currentNode=currentNode.getLeftNode();
            }else if(data.compareTo(currentNode.getData())>0){
                currentNode=currentNode.getRightNode();
            }else{
                splay(currentNode);
                return currentNode;
            }
        }
        splay(currentNode);
        return null;
    }
    /************************************************ROTATIONS******************************************/

    private void leftRotation(SplayNode<T> node){
        SplayNode<T> tempNode=node.getRightNode();
        if(tempNode!=null){
            SplayNode<T> t=tempNode.getLeftNode();//if any
            node.setRightNode(t);


            if(tempNode.getLeftNode()!=null){
                tempNode.getLeftNode().setParentNode(node);
            }
            tempNode.setParentNode(node.getParentNode());
        }


        if(node.getParentNode()==null){
            this.root=tempNode;
        }else if(node==node.getParentNode().getLeftNode()){
            node.getParentNode().setLeftNode(tempNode);
        }else{
            node.getParentNode().setRightNode(tempNode);
        }
        if(tempNode!=null){
            tempNode.setLeftNode(node);
        }
        node.setParentNode(tempNode);




    }

    private void rightRotation(SplayNode<T> node){
        SplayNode<T> tempNode=node.getLeftNode();
        if(tempNode!=null){
            SplayNode<T> t=tempNode.getRightNode();//if any
            node.setLeftNode(t);

            if(tempNode.getRightNode()!=null){
                tempNode.getRightNode().setParentNode(node);
            }
            tempNode.setParentNode(node.getParentNode());
        }

        if(node.getParentNode()==null){
            this.root=tempNode;
        }else if(node==node.getParentNode().getLeftNode()){
            node.getParentNode().setLeftNode(tempNode);
        }else{
            node.getParentNode().setRightNode(tempNode);
        }
        if(tempNode!=null){
            tempNode.setRightNode(node);
        }
        node.setParentNode(tempNode);
    }

    /**********************************************SPLAYING*********************************************/

    private void splay(SplayNode<T> node) {

        while(node.getParentNode()!=null){
            //ZIG-SITUATION
            if(node.getParentNode().getParentNode()==null){
                if(node.getParentNode().getLeftNode()==node){
                    //rotate right
                    rightRotation(node.getParentNode());
                }else{
                    //left rotate
                    leftRotation(node.getParentNode());
                }
            }

            //ZIG-ZIG SITUATION
            else if(node.getParentNode().getLeftNode()==node && node.getParentNode().getParentNode().getLeftNode()==node.getParentNode()){
                //rotate parent to X to the right
                rightRotation(node.getParentNode().getParentNode());
                //rotate X to the right
                rightRotation(node.getParentNode());
            }else if(node.getParentNode().getRightNode()==node && node.getParentNode().getParentNode().getRightNode()==node.getParentNode()){
                //rotate parent to X to the left
                leftRotation(node.getParentNode().getParentNode());
                //rotate X to the left
                leftRotation(node.getParentNode());
            }


            //ZIG-ZAG SITUATION
            else if(node.getParentNode().getRightNode()==node && node.getParentNode().getParentNode().getLeftNode()==node.getParentNode()){
                //rotate X to the left
                leftRotation(node.getParentNode());
                //rotate X to the right
                rightRotation(node.getParentNode());
            }else {
                //rotate X to the right
                rightRotation(node.getParentNode());
                //rotate X to the left
                leftRotation(node.getParentNode());
            }
        }


    }

    /********************************************PRINT ROOT**************************************/
    public void printRoot(){
        System.out.println(root.getData());
    }

    /************************************IN-ORDER TRAVERSAL**************************************/
    public void Traversal(){
        if(root!=null){
            in_orderTraversal(root);
        }
    }
    private void in_orderTraversal(SplayNode<T> splayNode) {
        if(splayNode.getLeftNode()!=null){
            in_orderTraversal(splayNode.getLeftNode());
        }
        System.out.print(splayNode.getData()+"->");
        if(splayNode.getRightNode()!=null){
            in_orderTraversal(splayNode.getRightNode());
        }
    }

    public static void main(String[] args){
        SplayTrees<Integer> splayTrees=new SplayTrees<>();
        splayTrees.insert(10);
        splayTrees.insert(-5);
        splayTrees.insert(0);
        splayTrees.insert(20);
        splayTrees.insert(30);


        splayTrees.find(-5);
        splayTrees.find(0);
        splayTrees.printRoot();





    }
}
class SplayNode<T extends Comparable<T>>{
    private T data;
    private SplayNode<T> leftNode;
    private SplayNode<T> rightNode;
    private SplayNode<T> parentNode;

    public SplayNode(T data){
        this.data=data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setLeftNode(SplayNode<T> leftNode) {
        this.leftNode = leftNode;
    }

    public SplayNode<T> getLeftNode() {
        return leftNode;
    }

    public void setRightNode(SplayNode<T> rightNode) {
        this.rightNode = rightNode;
    }

    public SplayNode<T> getRightNode() {
        return rightNode;
    }

    public SplayNode<T> getParentNode() {
        return parentNode;
    }

    public void setParentNode(SplayNode<T> parentNode) {
        this.parentNode = parentNode;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}