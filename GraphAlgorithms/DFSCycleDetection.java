package GraphAlgorithms;



public class DFSCycleDetection<T extends Comparable<T>> {



    //set 2 boolean flags

    /**

     * isBeingVisited --when current node goes to it's neighbours node

     * isVisited----when there are no more edges to the isBeingVisited node

     */



    public void dfsCycle(Vertex<T> vertex){



        vertex.setBeingVisited(true);



        for (Vertex<T> v:vertex.getNeighboursList()) {

            if(v.isBeingVisited())

            {

                System.out.println("Backward edge present to vertex "+v+" from vertex "+vertex);

                return;

            }

            if(!v.isVisited()){

                v.setVisited(true);

                dfsCycle(v);

            }



        }

        vertex.setBeingVisited(false);

        vertex.setVisited(true);

    }



    public static void main(String[] args){

        DFSCycleDetection obj=new DFSCycleDetection();

        Vertex vertex1=new Vertex(1);

        Vertex vertex2=new Vertex(2);

        Vertex vertex3=new Vertex(3);

        Vertex vertex4=new Vertex(4);

        Vertex vertex5=new Vertex(5);

        Vertex vertex8=new Vertex(8);

        Vertex vertex9=new Vertex(9);



        //adding neighbours

        vertex1.addNeighbour(vertex2);

        vertex2.addNeighbour(vertex4);

        vertex2.addNeighbour(vertex5);

        vertex5.addNeighbour(vertex1);

        vertex4.addNeighbour(vertex8);

        vertex4.addNeighbour(vertex9);



        obj.dfsCycle(vertex1);

    }



}
