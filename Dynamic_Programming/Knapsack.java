package Dynamic_Programming;

public class Knapsack {
    private int numOfItems;
    private int capacityOfKnapsack;
    private int[][] knapsackTable;
    private int totalBenefits;
    private int[] weights;
    private int[] values;

    public Knapsack(int numOfItems,int capacityOfKnapsack,int[] weights,int[] values){
        this.numOfItems=numOfItems;
        this.capacityOfKnapsack=capacityOfKnapsack;
        this.weights=weights;
        this.values=values;
        //columns-TOTAL WEIGHT
        //ROWS: NO. OF ITEMS
        this.knapsackTable=new int[numOfItems+1][capacityOfKnapsack+1];
    }

    public void solve(){
        //O(N*W)
        //first row and first column will always be 0
        for(int i=1;i<numOfItems+1;i++){
            for(int w=1;w<capacityOfKnapsack+1;w++){

                int notTakingItem=knapsackTable[i-1][w];
                int takingItem=0;

                if(weights[i]<=w){

                    //consider s[i-1][W-wi] if wi<=W
                    takingItem=values[i]+knapsackTable[i-1][w-weights[i]];
                }
                knapsackTable[i][w]=Math.max(notTakingItem,takingItem);
            }
        }

        //total benefit will we the last row and last column
        totalBenefits=knapsackTable[numOfItems][capacityOfKnapsack];
    }

    public void showResult(){
        System.out.println("Total benefit:"+totalBenefits);
        //to get the selected items
        for(int n=numOfItems,w=capacityOfKnapsack;n>0;n--){
            //if the column/row value above the total benefit value is not equal to total benefit value then add item
            //of the current row
            if(knapsackTable[n][w]!=0 && knapsackTable[n][w]!=knapsackTable[n-1][w]){
                //take as many steps to the LEFT of the upper row as much as the weight of the current ITEM
                System.out.println("we take item: #"+n);
                w=w-weights[n];
            }
        }
    }

    public static void main(String[] args){
        int numOfitems=3;
        int capacityOfKnapsack=5;

        int[] weightOfItems=new int[numOfitems+1];
        weightOfItems[1]=4;
        weightOfItems[2]=2;
        weightOfItems[3]=3;

        int[] valueOfItems=new int[numOfitems+1];
        valueOfItems[1]=10;
        valueOfItems[2]=4;
        valueOfItems[3]=7;

        Knapsack knapsack=new Knapsack(numOfitems,capacityOfKnapsack,weightOfItems,valueOfItems);
        knapsack.solve();
        knapsack.showResult();
    }


}
