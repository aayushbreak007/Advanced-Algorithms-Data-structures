package Dynamic_Programming;

public class SubsetSum {

    private boolean[][] dpTable;
    private int[] S;
    private int sum;

    public SubsetSum(int[] S,int sum){
        this.sum=sum;
        this.S=S;
        this.dpTable=new boolean[S.length+1][sum+1];
    }

    public void solve(){
        //first row is false
        //first column is true
        for(int i=0;i<S.length+1;i++){
            dpTable[i][0]=true;
        }

        for(int rowIndex=1;rowIndex<S.length+1;rowIndex++){
            for(int colIndex=1;colIndex<sum+1;colIndex++){
                if(colIndex<S[rowIndex-1]){
                    //copy the value from the above cell
                    dpTable[rowIndex][colIndex]=dpTable[rowIndex-1][colIndex];
                }else{

                    if(dpTable[rowIndex-1][colIndex]==true){
                        dpTable[rowIndex][colIndex]=dpTable[rowIndex-1][colIndex];
                    }else{
                        dpTable[rowIndex][colIndex]=dpTable[rowIndex-1][colIndex-S[rowIndex-1]];
                    }
                }
            }
        }
        System.out.println("Solution:"+dpTable[S.length][sum]);
    }

    public void showResult(){
        int colIndex=sum;
        int rowIndex=S.length;

        while(colIndex>0 || rowIndex>0){
            if(dpTable[rowIndex][colIndex]==dpTable[rowIndex-1][colIndex]){
                //this given interger is not part of the solution
                rowIndex--;
            }else{
                System.out.println("We take: "+S[rowIndex-1]);
                colIndex=colIndex-S[rowIndex-1];
                rowIndex--;
            }
        }
    }

    public static void main(String[] args){
        int[] numbers={5,2,3,1};
        int sum=9;

        SubsetSum subsetSum=new SubsetSum(numbers,sum);
        subsetSum.solve();
        subsetSum.showResult();
    }
}
