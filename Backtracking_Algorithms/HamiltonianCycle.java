package Backtracking_Algorithms;

public class HamiltonianCycle {
    private int numOfVertices;
    private int[] hamiltonianPath;
    private int[][] adjacencyMatrix;

    public HamiltonianCycle(int[][] adjacencyMatrix){
        this.adjacencyMatrix=adjacencyMatrix;
        this.hamiltonianPath=new int[adjacencyMatrix.length];
        this.numOfVertices=adjacencyMatrix.length;




    }

    public void solve(){
        //the first vertex in the hamiltonian path will be added
        this.hamiltonianPath[0]=0;
        //find feasible solution from second vertex since first one is already added
        if(findFeasibleSolution(1)){
            //show the hamiltonian path
            showHamiltonianPath();
        }else{
            System.out.println("No feasible solution....");
        }
    }
    private boolean findFeasibleSolution(int vertexPosition) {

        if(vertexPosition==numOfVertices){
            //we have reached every single vertex
            if(adjacencyMatrix[hamiltonianPath[vertexPosition-1]][hamiltonianPath[0]]==1){
                //this means that the last vertex is connected to the first vertex
                //hence it forms the HAMILTONIAN CYCLE

                return true;
            }
            return false;

        }
        for(int vertexIndex=1;vertexIndex<numOfVertices;vertexIndex++){
            if(isFeasible(vertexIndex,vertexPosition)){
                hamiltonianPath[vertexPosition]=vertexIndex;

                if(findFeasibleSolution(vertexPosition+1)){
                    return true;
                }

                //OTHERWISE BACKTRACK
                //consider next position
            }
        }


        return false;


    }

    private boolean isFeasible(int vertexIndex, int actualPosition) {
        //first check: whether 2 nodes are connected
        if(adjacencyMatrix[hamiltonianPath[actualPosition-1]][vertexIndex]==0){
         //they are not connected (previous is not connected to the current vertex)
         return false;
        }
        //second check: if we have visitied it or not
        for(int i=0;i<actualPosition;i++){
            if(hamiltonianPath[i]==vertexIndex){
                //we have already visited this vertex
                return false;
            }
        }
        return true;
    }

    private void showHamiltonianPath() {
        System.out.println("Hamiltonian cycle..");
        for(int i=0;i<hamiltonianPath.length;i++){
            System.out.print(hamiltonianPath[i]+" ");
        }
        System.out.print(hamiltonianPath[0]);//cycle
    }


    public static void main(String[] args){
        int[][] matrix={
                {0,1,1,1,0,0},
                {1,0,1,0,1,0},
                {1,1,1,1,0,1},
                {1,0,1,0,0,1},
                {0,1,0,0,0,1},
                {0,1,1,1,1,1}
        };
        HamiltonianCycle cycle=new HamiltonianCycle(matrix);
        cycle.solve();

    }
}
