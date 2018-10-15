package Backtracking_Algorithms;

public class N_QueensProblem {
    private int[][] chessTable;
    private int numOfQueens;

    public N_QueensProblem(int numOfQueens){
        this.chessTable=new int[numOfQueens][numOfQueens];
        this.numOfQueens=numOfQueens;

    }

    public void solve(){
        //If setQueens from 0th column returns true then we found the solution
        if(setQueens(0)){
            printQueens();
        }else{
            //no feasable solution
            System.out.println("There is no solution.....");
        }
    }

    private boolean setQueens(int columnIndex) {
        if(columnIndex==numOfQueens){
            //basically if we manage to put 4 queens in columns 1,2,3,4 then we found the solution
            //found the solution--placed all the queens
            return true;
        }

        for(int rowIndex=0;rowIndex<numOfQueens;++rowIndex){
            //iterate through all the rows for the given column and check if the QUEEN can be placed or not
            if(isPlaceValid(rowIndex,columnIndex)){
                //we are able to put a QUEEN without being attacked at this position
                chessTable[rowIndex][columnIndex]=1;

                //check for the next Column now if the queen can be placed recursively
                if(setQueens(columnIndex+1)){
                    return true;
                }

                //otherwise failed to put next queen
                /* so BACKTRACK to the previous queen then increment its position in loop*/
                chessTable[rowIndex][columnIndex]=0;
               // ++rowIndex;

            }
        }
        return false;
    }

    private boolean isPlaceValid(int rowIndex, int columnIndex) {
        /*
        * case1: check row
        * case 2: check diagonals*/

        //ROW
        for(int i=0;i<columnIndex;i++){
            //queen the present the same row
            if(chessTable[rowIndex][i]==1){
                return false;
            }
        }

        //DIAGONALS
        for(int i=rowIndex, j=columnIndex;i>=0&&j>=0;--i,--j){
            //queen is present in the first diagonal
            if(chessTable[i][j]==1){
                return false;
            }
        }

        for(int i=rowIndex,j=columnIndex;i<chessTable.length && j>=0;++i,--j){
            //queen is present in the second diagnal
            if(chessTable[i][j]==1){
                return false;
            }
        }
        return true;
    }

    private void printQueens() {
        for(int i=0;i<chessTable.length;i++){
            for(int j=0;j<chessTable.length;j++){
                if(chessTable[i][j]==1){
                    System.out.print(" * ");
                }else{
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }


    public static void main(String[] args){
        N_QueensProblem queensProblem=new N_QueensProblem(20);
        queensProblem.solve();
    }
}
