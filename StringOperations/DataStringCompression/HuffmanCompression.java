package StringOperations.DataStringCompression;
import java.util.PriorityQueue;

public class HuffmanCompression {
    public HuffmanTree buildTree(int[] charFrequencies){
        //will get the min freq node
        PriorityQueue<HuffmanTree> queue=new PriorityQueue<>();

        //initialization
        for(int i=0;i<charFrequencies.length;i++){
            if(charFrequencies[i]>0){
                //creating leaf nodes
                queue.add(new HuffmanLeaf(charFrequencies[i],(char)i));
            }
        }

        while (queue.size()>1){//more than one tree structure
            //keep merging the trees until we have a SINGLE tree like structure
            HuffmanTree tree1=queue.poll();//returns huffman tree with smallest freq
            HuffmanTree tree2=queue.poll();

            //merge--2 trees into 1
            queue.add(new HuffmanNode(tree1,tree2));

        }
        return queue.poll();//will return constructed merged single huffman tree
    }

    public void printCodes(HuffmanTree tree,StringBuilder prefix){
        if(tree instanceof HuffmanLeaf){
            HuffmanLeaf leaf=(HuffmanLeaf) tree;
            System.out.println(leaf.getValue()+"\t"+leaf.getFrequency()+"\t"+prefix);
        }else{
            HuffmanNode node=(HuffmanNode) tree;

            //RECURSION

            //traverse the left subtree
            prefix.append("0");
            printCodes(node.getLeftTree(),prefix);

            //now startover when you reached the leaf node
            prefix.deleteCharAt(prefix.length()-1);

            //traverse the right subtree
            prefix.append("1");
            printCodes(node.getRightTree(),prefix);
            prefix.deleteCharAt(prefix.length()-1);

        }
    }

    public static void main(String[] args){
        String text="My name is joiiii!";
        int[]  charFrequencies=new int[256];

        for(char c:text.toCharArray()){
            ++charFrequencies[c];//count array
        }

        HuffmanCompression huffmanCompression=new HuffmanCompression();
        HuffmanTree huffmanTree=huffmanCompression.buildTree(charFrequencies);
        huffmanCompression.printCodes(huffmanTree,new StringBuilder());

    }

}


class HuffmanLeaf extends HuffmanTree{

    //this is going to store only the letter
    private  char value;

    //super calls the parent's class constructor
    public HuffmanLeaf(int frequency) {
        super(frequency);
    }
    public HuffmanLeaf(int frequency,char value){
        super(frequency);
        this.value=value;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }
}
class HuffmanNode extends HuffmanTree{

    private HuffmanTree leftTree;
    private HuffmanTree rightTree;

    public HuffmanNode(HuffmanTree rightTree,HuffmanTree leftTree) {
        //when we merge the 2 subtrees with frequencys then the parent subtree will be (leftTree freq + rightTRee freq)
        super(rightTree.getFrequency()+leftTree.getFrequency());
        this.leftTree=leftTree;
        this.rightTree=rightTree;
    }

    public HuffmanTree getLeftTree() {
        return leftTree;
    }

    public HuffmanTree getRightTree() {
        return rightTree;
    }

    public void setLeftTree(HuffmanTree leftTree) {
        this.leftTree = leftTree;
    }

    public void setRightTree(HuffmanTree rightTree) {
        this.rightTree = rightTree;
    }
}

class HuffmanTree implements Comparable<HuffmanTree>{

    //we have to sort the letters values according to their frequencies
    private int frequency;

    public HuffmanTree(int frequency){
        this.frequency=frequency;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public int compareTo(HuffmanTree otherTree) {

        //compare huffman trees according to the frequencies
        return Integer.compare(this.frequency,otherTree.getFrequency());
    }
}
