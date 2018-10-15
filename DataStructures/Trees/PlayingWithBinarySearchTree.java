package DataStructures.Trees;

public class PlayingWithBinarySearchTree<T extends Comparable<T>> {

    private MyTreeNode<T> root;



    /**********************INSERTION INTO BINARY TREEE************************************/
    public void insert(T data){
        MyTreeNode<T> newTreeNode=new MyTreeNode<>(data);
        if(root==null){
            root=newTreeNode;
            return;
        }
        MyTreeNode<T> currentTreeNode=root;
        boolean flag=true;

        while(flag){
            if(currentTreeNode.getData().compareTo(data)>0){
                if(currentTreeNode.getLeftChild()==null){
                    //insert to the left and stop
                    currentTreeNode.setLeftChild(newTreeNode);
                    flag=false;
                }else{
                    //keep going to the left
                    currentTreeNode=currentTreeNode.getLeftChild();
                }
            }else{
                if(currentTreeNode.getRightChild()==null){
                    //insert to the right and stop
                    currentTreeNode.setRightChild(newTreeNode);
                    flag=false;
                }else{
                    //keep going to the right
                    currentTreeNode=currentTreeNode.getRightChild();

                }
            }
        }

    }
    /**************************DELETION OF A NODE FROM THE TREE IF THE DATA IS GIVEN****************/
    public void deleteNode(T data){

        if(root!=null) {
             root=deleteNode(root, data);
        }
    }

    private MyTreeNode<T> deleteNode(MyTreeNode<T> node, T data) {
         /*3 possible cases of deletion
         * a.) when node to be deleted is the leaf node
         * b.) when node to be deleted has a single child
         * c.) when node to be deleted has 2 children*/

        //doing with recursion
        if (node == null) {
            return node;
        }
        if (node.getData().compareTo(data) > 0) {
            //go to left
            node.setLeftChild(deleteNode(node.getLeftChild(), data));

        } else if (node.getData().compareTo(data) < 0) {
            //go to right
            node.setRightChild(deleteNode(node.getRightChild(), data));
        } else {

            if(node.getLeftChild()==null && node.getRightChild()==null)
            {
                System.out.println("Removing leaf node....");
                return null;
            }

            if (node.getLeftChild() == null) {
                //delete node with single right child node
                MyTreeNode<T> tempTreeNode = node.getRightChild();
                return tempTreeNode;


            }else if (node.getRightChild() == null) {
                //  deleting node with single left node
                MyTreeNode<T> tempTreeNode = node.getLeftChild();
                return tempTreeNode;

            }
                /*deleting the node with 2 children nodes*/

                //find the predecessor-----largest element(right most) in the left subtree
                MyTreeNode<T> predecessorNode = getPredecessorNode(node.getLeftChild());
                //swap the predecessor node with the node to be deleted
                node.setData(predecessorNode.getData());
                //remove the leaf node and check of the predecessor has a left child. If so then set that child has the left child of the current node
                node.setLeftChild(deleteNode(node.getLeftChild(), predecessorNode.getData()));

        }
        return node;
    }

    private MyTreeNode<T> getPredecessorNode(MyTreeNode<T> leftSubtreeNode) {
        //getting the largest element on the left subtree from the deleting node
        //this means we need to get the right most child on the left subtree to get maximum element value

        if(leftSubtreeNode.getRightChild()!=null)
        {
            return getPredecessorNode(leftSubtreeNode.getRightChild());
        }
        return leftSubtreeNode;

    }

    /************************Getting the maximum and Minimum elements from the Binary tree****************/
    public void getMax(){
        if(root!=null){
            getMaxValue(root);
        }
    }
    public void getMin(){
        if(root!=null){
            getMinValue(root);
        }
    }

    private void getMinValue(MyTreeNode<T> node) {
        if(node.getLeftChild()!=null){
            getMinValue(node.getLeftChild());
        }else{
            System.out.println("Min Element:"+node.getData());
        }
    }

    private void getMaxValue(MyTreeNode<T> node) {
        //always go to the right
        if(node.getRightChild()!=null){
            getMaxValue(node.getRightChild());
        }else{
            System.out.println("Max Element:"+node.getData());
        }
    }

    /**************************************Finding DEPTH/HEIGHT of the Binary tree*********************/

    //The height of the binary tree is the number of edges between the ROOT and the farthest LEAF node
    public int heightOfBinaryTree(){
        /*
        Get the max depth of left-subtree
        Get the max depth of right-subtree
        Get the max of both depths */
        if(root!=null){
            int height=getMaxHeight(root);
            System.out.println("Height of the tree:"+height);
            return height;
        }
        return 0;
    }

    private int getMaxHeight(MyTreeNode<T> node) {
        if(node==null){
            return 0;
        }
        int left_subTreeHeight=getMaxHeight(node.getLeftChild());
        int right_subTreeHeight=getMaxHeight(node.getRightChild());
        //get the max of both height +1
        return Math.max(left_subTreeHeight,right_subTreeHeight)+1;
    }


    /***************************************DFS Traversals in a Binary Tree*************************/
    public void Traversal() {
        if(root!=null){
            System.out.println("In-Order traversal!");
            in_order(root);
        }

    }
    /************DFS TRAVERSALS---IN-ORDER,POST ORDER AND PRE ORDER*********/
    private void in_order(MyTreeNode<T> node){
        //traversal is best through recursion
        if(node.getLeftChild()!=null){
            in_order(node.getLeftChild());
        }
        System.out.print(node.getData()+"---->");
        if(node.getRightChild()!=null){
            in_order(node.getRightChild());
        }

    }

    /****************************************************BFS(LEVEL ORDER )TRAVERSALS IN BINARY TREE********************/
    /*USES: CAN BE USED TO PRINT ELEMENTS FROM A GIVEN ELEMENT*/

    public void levelOrderTraversal(){
        int h=heightOfBinaryTree();
        for(int i=1;i<=h;i++){
            printAtGivenLevel(root,i);
        }
    }

    private void printAtGivenLevel(MyTreeNode<T> node, int level) {
        if(node==null){
            return;
        }
        if(level==1){
            System.out.print(node.getData()+" ");
        }else{
            printAtGivenLevel(node.getLeftChild(),level-1);
            printAtGivenLevel(node.getRightChild(),level-1);
        }

    }


    public static void main(String[] args){

        PlayingWithBinarySearchTree obj=new PlayingWithBinarySearchTree<>();
         obj.insert(32);
        obj.insert(10);
        obj.insert(1);
        obj.insert(19);
        obj.insert(16);
        obj.insert(23);
        obj.insert(55);
        obj.insert(79);
        obj.insert(15);
        obj.insert(81);


      /*  obj.deleteNode(15);
        obj.Traversal();
        obj.getMax();
        obj.getMin();
        obj.heightOfBinaryTree();*/
      obj.levelOrderTraversal();
    }
}

class MyTreeNode<T extends Comparable<T>>{

    private MyTreeNode<T> leftChild;
    private MyTreeNode<T> rightChild;
    private T data;

    MyTreeNode(T data){
        this.data=data;
    }

    //getter and setter

    public MyTreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(MyTreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public MyTreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(MyTreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}