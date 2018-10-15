package DataStructures.HashTables.HashCollisionsResolutions;

public class LinearProbing {

    private HashItem2[] hashTable;

    public LinearProbing(){
        hashTable=new HashItem2[Constants2.TABLE_SIZE];
    }

    public void put(int key,int value){
        int generatedIndex=hashFunction(key);


        while (hashTable[generatedIndex]!=null){
            //keep finding the next empty slot
            generatedIndex=(generatedIndex+1)%Constants2.TABLE_SIZE;
            System.out.println("collision---hopping to next index: "+generatedIndex);

        }

        hashTable[generatedIndex]=new HashItem2(key,value);
    }

    public int get(int key){
        int generatedIndex= hashFunction(key);

        while(hashTable[generatedIndex]!=null && hashTable[generatedIndex].getKey()!=key){
            //consider next array slot
            generatedIndex=(generatedIndex+1)%Constants2.TABLE_SIZE;
        }
        if(hashTable[generatedIndex]==null){
            //search miss
            return -1;
        }else{
            return hashTable[generatedIndex].getValue();
        }
    }

    public int hashFunction(int key){
        return key%Constants2.TABLE_SIZE;
    }

    public static void main(String[] args){
        LinearProbing obj=new LinearProbing();
        obj.put(1,10);
        System.out.println();
        obj.put(2,1000);
    }
}

class Constants2{
    private Constants2(){

    }
    public static final int TABLE_SIZE=10;
}

class HashItem2{
    private int key;
    private int value;

    public HashItem2(int key,int value){
        this.key=key;
        this.value=value;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setValue(int value) {
        this.value = value;
    }
}