package DataStructures.Trees;

public class AVLTree<T extends Comparable<T>> {
    private AVLNode<T> root;


    //*********************************DELETION IN THE AVL TREE***************************************
    private AVLNode<T> deleteNode(AVLNode<T> node, T data) {
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
            node.setLeftNode(deleteNode(node.getLeftNode(), data));

        } else if (node.getData().compareTo(data) < 0) {
            //go to right
            node.setRightNode(deleteNode(node.getRightNode(), data));
        } else {

            //FOUND THE NODE TO BE REMOVED
            if(node.getLeftNode()==null && node.getRightNode()==null)
            {
                System.out.println("Removing leaf node....");
                return null;
            }

            if (node.getLeftNode() == null) {
                //delete node with single right child node
                AVLNode<T> tempTreeNode = node.getRightNode();
                return tempTreeNode;


            }else if (node.getRightNode() == null) {
                //  deleting node with single left node
                AVLNode<T> tempTreeNode = node.getLeftNode();
                return tempTreeNode;

            }
                /*deleting the node with 2 children nodes*/

            //find the predecessor-----largest element(right most) in the left subtree
            AVLNode<T> predecessorNode = getPredecessorNode(node.getLeftNode());
            //swap the predecessor node with the node to be deleted
            node.setData(predecessorNode.getData());
            //remove the leaf node and check of the predecessor has a left child. If so then set that child has the left child of the current node
            node.setLeftNode(deleteNode(node.getLeftNode(), predecessorNode.getData()));

        }

        //check for violation in AVL properties on removal of the node
        return settleDeletion(node);
    }

    private AVLNode<T> settleDeletion(AVLNode<T> node) {
        int balance=getBalance(node);
        if(balance>1){

            //left heavy
            if(getBalance(node.getLeftNode())<0){
                //left-right heavy
                node.setLeftNode(leftRotation(node.getLeftNode()));
            }
            return rightRotation(node);
        }
        if(balance<-1){
            //right -heavy
            if(getBalance(node.getRightNode())>0){
                //right-left heavy
                node.setRightNode(leftRotation(node.getRightNode()));
            }
            return leftRotation(node);
        }
        return node;

    }


    private AVLNode<T> getPredecessorNode(AVLNode<T> leftSubtreeNode) {
        //getting the largest element on the left subtree from the deleting node
        //this means we need to get the right most child on the left subtree to get maximum element value

        if(leftSubtreeNode.getRightNode()!=null)
        {
            return getPredecessorNode(leftSubtreeNode.getRightNode());
        }
        return leftSubtreeNode;

    }
    //********************************INSERTION METHOD INTO THE AVL TREE**************************8
    public void insert(T data){
        root=insert(root,data);
    }

    private AVLNode<T> insert(AVLNode<T> node, T data) {
        //use recursion
        AVLNode<T> newNode=new AVLNode<T>(data);
        if(node==null){
            return newNode;
        }
        if(data.compareTo(node.getData())<0){
            //goto left
            node.setLeftNode(insert(node.getLeftNode(),data));
        }else{
            //goto right
            node.setRightNode(insert(node.getRightNode(),data));
        }

        //after inserting the node....update the height parameter of the node
        node.setHeight(Math.max(getHeight(node.getLeftNode()),getHeight(node.getRightNode()))+1);

        //this is where we check if any rotations are required to maintain tree balance after insertion
        node=settleViolations(data,node);
        return node;

    }

    private AVLNode<T> settleViolations(T data, AVLNode<T> node) {
        //get the balance only
        int balance=getBalance(node);
        //if it is balanced then diff is not going to be b/w -1 to 1
        /*1.) doubly-left heavy */
        if(balance>1 && data.compareTo(node.getLeftNode().getData())<0){
            //this is to ensure if the grandchild is to the left or not to make it doubly left -heavy situation`
            return rightRotation(node);//this node is the grandparent

        }
        /*2.) doubly-right heavy*/
        if(balance<-1 && data.compareTo(node.getRightNode().getData())>0){
            //this is to ensure if the grandchild is to the right to make doubly-right heavy situation
            return leftRotation(node);
        }
        /*3.) LEFT-RIGHT HEAVY SITUATION*/
        if(balance>1 && data.compareTo(node.getLeftNode().getData())>0){
            //here the grandchild is the right child of the left parent
            //MAKE LEFT ROTATION ON THE PARENT NODE
            //MAKE RIGHT ROTATION ON THE GRANDPARENT NODE
            node.setLeftNode(leftRotation(node.getLeftNode()));//rotating parent to the left
            return rightRotation(node);//right rotation of the grandparent

        }

        /*4.) RIGHT-LEFT HEAVY SITUATION*/
        if(balance<-1 && data.compareTo(node.getRightNode().getData())<0){
            //rotate parent to the right
            //rotate grandparent to the left
            node.setRightNode(rightRotation(node.getRightNode()));
            return leftRotation(node);
        }
        return node;

    }


    //***********************SETTING UP HEIGHT AND BALANCE FOR THE AVL TREE*********************************/
    private int getHeight(AVLNode<T> node){
        if(node==null){
            return -1;
        }
        return node.getHeight();
    }
    private int getBalance(AVLNode<T> node){
        if(node==null){
            return 0;
        }
        //calculating the difference between the 2 heights of the subtrees
        return getHeight(node.getLeftNode())-getHeight(node.getRightNode());
    }

    /***************************************IMPLEMENTING ROTATIONS*****************************************/

    /*1.) Right Rotation---Doubly left heavy rotation*/
    private AVLNode<T> rightRotation(AVLNode<T> node){
        System.out.println("Rotating "+node.getData()+" to the right..!");
        AVLNode<T> tempLeftNode=node.getLeftNode();
        AVLNode<T> t=tempLeftNode.getRightNode();//this is the right child of the root's left child if any

        tempLeftNode.setRightNode(node);
        node.setLeftNode(t);

        //update heights of original root and new root
        node.setHeight(Math.max(getHeight(node.getLeftNode()),getHeight(node.getRightNode()))+1);
        tempLeftNode.setHeight(Math.max(getHeight(tempLeftNode.getLeftNode()),getHeight(tempLeftNode.getRightNode()))+1);
        return tempLeftNode;//return the new root node
    }

    /*2.) Left Rotation---Doubly right heavy rotation*/
    private AVLNode<T> leftRotation(AVLNode<T> node){
        System.out.println("Rotating "+node.getData()+" to the left....");
        AVLNode<T> tempRightNode=node.getRightNode();
        AVLNode<T> t=tempRightNode.getLeftNode();//if any
        tempRightNode.setLeftNode(node);
        node.setRightNode(t);

        //update the heights
        tempRightNode.setHeight(Math.max(getHeight(tempRightNode.getLeftNode()),getHeight(tempRightNode.getRightNode()))+1);
        node.setHeight(Math.max(getHeight(node.getLeftNode()),getHeight(node.getRightNode()))+1);
        return tempRightNode;
    }

    /****************************************TRAVERSAL*****************************************************/
    public void traversal(){

        //Numerical ordering
        if(root==null){
            return;
        }
        in_orderTraversal(root);
    }

    private void in_orderTraversal(AVLNode<T> node) {
        if(node.getLeftNode()!=null){
            in_orderTraversal(node.getLeftNode());
        }
        System.out.print(node.getData()+" ");

        if(node.getRightNode()!=null) {
            in_orderTraversal(node.getRightNode());
        }
    }


    public static void main(String[] args){
        AVLTree<Integer> obj=new AVLTree<>();
        obj.insert(1);
        obj.insert(4);
        obj.insert(3);
        obj.traversal();

    }

}


class AVLNode<T extends Comparable<T>>{

    private T data;
    private AVLNode<T> leftNode;
    private AVLNode<T> rightNode;
    private int height;

    public AVLNode(T data){
        this.data=data;
    }

    public AVLNode<T> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(AVLNode<T> leftNode) {
        this.leftNode = leftNode;
    }

    public AVLNode<T> getRightNode() {
        return rightNode;
    }

    public void setRightNode(AVLNode<T> rightNode) {
        this.rightNode = rightNode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}