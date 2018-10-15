package Dynamic_Programming;

public class CoinChange {

    //O(2^N)
    public int naiveCoinChange(int M,int[] v,int index){
        if (M < 0 ) {
            return 0;
        }
        if(M==0){
            return 1;
        }
        if(v.length==index){
            //no coins
            return 0;
        }

        //return include + don't include
        return naiveCoinChange(M-v[index],v,index)+naiveCoinChange(M,v,index+1);
    }

    public void coinChangeDP(int[] v,int M){
        int[][] dpTable=new int[v.length+1][M+1];

        for(int i=0;i<=v.length;i++){
            dpTable[i][0]=1;//first column will always be 1
        }
        for(int i=0;i<=M;i++){
            dpTable[0][i]=0;//first row will always be 0
        }

        //O(V*M)
        for(int i=1;i<=v.length;i++){
            for(int j=1;j<=M;j++){

                if(v[i-1]<=j){
                    //if the value is less than the `current M
                    //WE TAKE THIS COIN
                    //dp[i-1][j]+dp[i][j-v[i-1]]
                    dpTable[i][j]=dpTable[i-1][j]+dpTable[i][j-v[i-1]];
                }else{
                    //dp[i-1][j]
                    //WE DO NOT TAKE THE COIN
                    dpTable[i][j]=dpTable[i-1][j];
                }
            }
        }
        //last row last column is the solution
        System.out.println("Solution:"+dpTable[v.length][M]);
    }

    public static void main(String[] args){
        int[] v={1,2,3};
        int M=4;

        CoinChange coinChange=new CoinChange();
        coinChange.coinChangeDP(v,M);
    }

}
