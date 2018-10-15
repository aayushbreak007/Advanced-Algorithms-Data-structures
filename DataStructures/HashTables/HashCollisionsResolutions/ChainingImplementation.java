package DataStructures.HashTables.HashCollisionsResolutions;

public class ChainingImplementation {

    //If the hash-functions assigns 2 or more keys to the same array bucket index then it forms a linked-list of keys
    //in the same bucket

    private HashItem[] hashTable;
    public ChainingImplementation(){
        hashTable=new HashItem[Constants.TABLE_SIZE];
    }

    public void put(int key,int value){
        int hashIndex=hash(key);
        if(hashTable[hashIndex]==null){
            //found an empty slot bucket
            System.out.println("NO collision simple insertion....");
            hashTable[hashIndex]=new HashItem(key,value);
        }else{
            //collision
            System.out.println("Collision when inserting with key "+key);
            HashItem hashItem=hashTable[hashIndex];//got the already stored item
            while(hashItem.getNextHashItem()!=null){
                hashItem=hashItem.getNextHashItem();
                System.out.println("Considering the next item in the list "+hashItem.getValue());
            }

            //now set the nexthashitem to the new hash item in the LINKED LIST OF HASH ITEMS IN A BUCKET
            System.out.println("Finally found the place to insert....");
            hashItem.setNextHashItem(new HashItem(key,value));
        }
    }

    public int get(int key){
        int generatedArrayIndex=hash(key);
        if(hashTable[generatedArrayIndex]==null){
            //search miss
            return -1;
        }
        else{
            HashItem hashItem=hashTable[generatedArrayIndex];
            while(hashItem!=null && hashItem.getKey()!=key){

                //traversing through the linked list
                hashItem=hashItem.getNextHashItem();
            }
            if(hashItem==null){
                return -1;//item not found
            }else{
                return hashItem.getValue();
            }
        }
    }

    //hash function
    private int hash(int key){
        return key % Constants.TABLE_SIZE;
      //  return 1; //for testing
    }


    public static void main(String[] args){

        ChainingImplementation obj=new ChainingImplementation();
        obj.put(1,10);
        System.out.println();
        obj.put(2,100);
        System.out.println();
        obj.put(3,1000);
    }

}
class Constants{
    private Constants(){

    }
    public static final int TABLE_SIZE=10;
}

class HashItem{
    private int key;
    private int value;
    private HashItem nextHashItem;

    public HashItem(int key,int value){
        this.key=key;
        this.value=value;
    }

    public HashItem getNextHashItem() {
        return nextHashItem;
    }

    public void setNextHashItem(HashItem nextHashItem) {
        this.nextHashItem = nextHashItem;
    }

    public int getValue() {
        return value;
    }

    public int getKey() {
        return key;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
