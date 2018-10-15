package GraphAlgorithms;



import java.io.File;

import java.util.Scanner;



public class DFSMazeProblem {



    //THIS IS THE REASON WHY DEPTH FIRST SEARCH WAS INTRODUCED

    private int[][] mazeMap;

    private boolean[][] visited;

    private int startPositionCol;

    private int startPositionRow;



    public DFSMazeProblem(int[][] mazeMap,int startPositionRow,int startPositionCol){



        this.mazeMap=mazeMap;

        this.visited=new boolean[mazeMap.length][mazeMap.length];

        this.startPositionRow=startPositionRow;

        this.startPositionCol=startPositionCol;

    }



    public void findWayOut(){

        try{

            dfs(startPositionRow,startPositionCol);

            System.out.println("No solution found...");

        }catch (RuntimeException ex){

            System.out.println("Route found to the exit...");

        }

    }



    private void dfs(int rowIndex, int colIndex) {



        System.out.println("Visiting i="+rowIndex+", j="+colIndex);





        if(this.mazeMap[rowIndex][colIndex]==3){

            throw new RuntimeException();//we break the iteration with exception if found an exit

        }



        int endOfMap=this.mazeMap.length-1;



        if(visited[rowIndex][colIndex]){

            //skip it

            return;

        }else if(rowIndex<0 || rowIndex>=endOfMap){//out of map

            return;

        }else if(colIndex<0 || colIndex>=endOfMap){//out of map

            return;

        }else if(this.mazeMap[rowIndex][colIndex]==1){//it is a wall

            return;



        }else{

            //this is dfs algo

            this.visited[rowIndex][colIndex]=true;

            dfs(rowIndex+1,colIndex);//go down

            dfs(rowIndex,colIndex+1);//go right

            dfs(rowIndex,colIndex-1);//go left

            dfs(rowIndex-1,colIndex);//go up



        }





    }



    public static void main(String[] args){

        FileReader fileReader=new FileReader("C:\\Users\\PAAYUSH\\Desktop\\map.txt",7,7);

        fileReader.parseFile();

        DFSMazeProblem mazeSolver=new DFSMazeProblem(fileReader.getMap(),fileReader.getStartPositionRow(),fileReader.getStartPositionCol());

        mazeSolver.findWayOut();

    }



}

class FileReader{

    private int[][] map;

    private String fileName;

    private int numOfRows;

    private int numOfColumns;

    private int startPositionCol;//start point --2 column index

    private int startPositionRow;//start point --3 row index



    public FileReader(String fileName,int numOfRows, int numOfColumns){

        this.fileName=fileName;

        this.numOfRows=numOfRows;

        this.numOfColumns=numOfColumns;

        this.map=new int[numOfRows][numOfColumns];



    }



    public void parseFile(){

        try{

            Scanner scanner=new Scanner(new File(this.fileName));



            for(int i=0;i<this.numOfRows;i++){

                for(int j=0;j<this.numOfColumns;j++){



                    map[i][j]=scanner.nextInt();   //reading integers from the file



                    if(map[i][j]==2) {

                        startPositionRow = i;

                        startPositionCol = j;

                    }

                }



            }

            scanner.close();



        }catch (Exception ex){

            ex.printStackTrace();

        }

    }



    //getter





    public int getStartPositionCol() {

        return startPositionCol;

    }



    public int getStartPositionRow() {

        return startPositionRow;

    }



    public int[][] getMap() {

        return this.map;

    }

}
