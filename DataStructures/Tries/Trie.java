package DataStructures.Tries;

import java.util.ArrayList;
import java.util.List;

public class Trie
{
    private TrieNode rootNode;
    private int indexOfSingleChild;

    public Trie(){
        this.rootNode=new TrieNode("");//the root node is always empty
    }

    public void insert(String key){
        TrieNode tempNode=rootNode;

        for(int i=0;i<key.length();i++){
            char c=key.charAt(i);
            int asciiIndex=c-'a';//we get the actual index starting from 0

            if(tempNode.getChild(asciiIndex)==null){
                TrieNode newNode=new TrieNode(String.valueOf(c));
                tempNode.setChild(asciiIndex,newNode);
                tempNode=newNode;
            }else{
                tempNode=tempNode.getChild(asciiIndex);
            }
        }
        tempNode.setLeaf(true);//reached the end of the character
    }

    public boolean search(String key){

        TrieNode tempNode=rootNode;
        for(int i=0;i<key.length();i++){
            char c=key.charAt(i);
            int asciiIndex=c-'a';

            if(tempNode.getChild(asciiIndex)==null){
                //given key is not present in the trie ds
                return false;
            }
            tempNode=tempNode.getChild(asciiIndex);
        }
        //TO HANDLE ONLY COMPLETE STRINGS

        /*if(!tempNode.isLeaf()){
            return false;
        }*/

        return true;//given key is present in the trie ds if not returned false

    }


    /**************************************************TRIE AUTOCOMPLETE***************************************/
    public List<String> allWordsWithPrefix(String prefix){
        TrieNode node=rootNode;
        List<String> allWords=new ArrayList<>();

        for(int i=0;i<prefix.length();i++){
            char c=prefix.charAt(i);
            int asciiIndex=c-'a';
            node=node.getChild(asciiIndex);

        }
        //after reaching end of the prefix find suggestions
        collect(node,prefix,allWords);
        return allWords;
    }

    private void collect(TrieNode node, String prefix, List<String> allWords) {
        if(node==null){
            return;
        }
        if(node.isLeaf()){
            allWords.add(prefix);//adding all the possible suggestions to the prefix
        }
        //apply DFS to access neighbvour nodes for suggestions
        for(TrieNode childNode:node.getChildren()){
            if(childNode==null){
                continue;
            }
            String childCharacter=childNode.getCharacter();
            //recursion
            collect(childNode,prefix+childCharacter,allWords);
        }
    }

    /*************************************LONGEST COMMON PREFIX*******************************/

    public String longestCommonPrefix(){
        TrieNode node=rootNode;
        String lcp="";
        while(countNumOfChildren(node)==1 && !node.isLeaf()){
            node=node.getChild(indexOfSingleChild);
            lcp=lcp+String.valueOf((char)(indexOfSingleChild+'a'));//converting to ascii (reverse)
        }
        return lcp;
    }

    private int countNumOfChildren(TrieNode node) {
        int numOfChildren=0;
        for(int i=0;i<node.getChildren().length;i++){
            if(node.getChild(i)!=null){
                numOfChildren++;
                indexOfSingleChild=i;
            }
        }
        return numOfChildren;

    }
    /***************************************************************************************/

    public static void main(String[] args){
        Trie trie=new Trie();
        trie.insert("joe");
        trie.insert("aayush");
        trie.insert("aayushi");
        trie.insert("adam");
        trie.insert("adamm");
        trie.insert("aman");
       /* if(trie.search("aay")){
            System.out.println("Word found in the trie data structure...");
        }else{
            System.out.println("Not found...");
        }*/


        //AUTOCOMPLETE
        //  List<String> list=trie.allWordsWithPrefix("");--------------SHOWS SORTED LIST OF WORDS
        List<String> list=trie.allWordsWithPrefix("aa");
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

    }
}
class TrieNode{

    private String character;
    private TrieNode[] children;


    private boolean leaf;
    private boolean visited;//for DFS

    public TrieNode(String character){
        this.character=character;
        children=new TrieNode[Constant.ALPHABET_SIZE];//every single node has 26 children referencs and hence not memory friendly
    }

    public void setChild(int index,TrieNode node){
        this.children[index]=node;
    }
    public TrieNode getChild(int index){
        return children[index];
    }




    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public TrieNode[] getChildren() {
        return children;
    }

    public void setChildren(TrieNode[] children) {
        this.children = children;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }


    @Override
    public String toString() {
        return this.character;
    }
}

class Constant{
    public static final int ALPHABET_SIZE=26;
}