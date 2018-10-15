package DataStructures.Arrays;

public class DuplicatesINArray {

    public void solve(int[] ar){
        for(int i=0;i<ar.length;i++){
            if(ar[Math.abs(ar[i])]>0){
                //flip the value
                ar[Math.abs(ar[i])]=-ar[Math.abs(ar[i])];

            }else{
                System.out.println(Math.abs(ar[i])+" is a repeatition");
            }
        }
    }
    public static void main(String[] args){
        int[] ar={4,-1,-1,2,3};
        DuplicatesINArray obj=new DuplicatesINArray();
        obj.solve(ar);

    }
}
