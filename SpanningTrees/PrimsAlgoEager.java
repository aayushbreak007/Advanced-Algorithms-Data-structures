package SpanningTrees;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PrimsAlgoEager {

    private List<PVertex> vertexList;
    private PriorityQueue<PVertex> heap;//we store list of vertices and not the edges

    public PrimsAlgoEager(List<PVertex> vertexList){
        this.vertexList=vertexList;
        this.heap=new PriorityQueue<>();
    }

    public void spanningTree(){
        for(PVertex vertex:vertexList){
            if(!vertex.isVisited()){
                greedyPrim(vertex);
            }
        }
    }

    private void greedyPrim(PVertex vertex) {
        vertex.setDistance(0);//start position
        heap.add(vertex);

        while(!heap.isEmpty()){
            PVertex v=heap.remove();//remove the vertex with minimum distance
            scanVertices(v);
        }
    }

    private void scanVertices(PVertex v) {
        //visit all the neighbours
        v.setVisited(true);
        for(PEdge edge:v.getAdjacencyList()){
            PVertex w=edge.getTargetVertex();
            if(w.isVisited()){
                continue;
            }
            //if shorted path to the vertex is available then update the heap
            if(edge.getWeight()<w.getDistance()){
                w.setDistance(edge.getWeight());
                w.setMinEdge(edge);
            }

            //updating heap contents
            if(this.heap.contains(w)){
                this.heap.remove(w);
            }
            //otherwise
            this.heap.add(w);
        }
    }

    public void show(){
        for(PVertex vertex:vertexList){
            if(vertex.getMinEdge()!=null){
                PEdge e=vertex.getMinEdge();
                System.out.println("Edge: "+e.getStartVertex()+"-"+e.getTargetVertex());
            }
        }
    }

    public static void main(String[] args){
        List<PVertex> vertexList=new ArrayList<>();

        PVertex vertex0=new PVertex("A");
        PVertex vertex1=new PVertex("B");
        PVertex vertex2=new PVertex("C");

        vertexList.add(vertex0);
        vertexList.add(vertex1);
        vertexList.add(vertex2);

        //for undirected edges
        vertex0.addEdge(new PEdge(vertex0,vertex1,100));
        vertex0.addEdge(new PEdge(vertex0,vertex2,10));
        vertex0.addEdge(new PEdge(vertex1,vertex2,1));

        vertex1.addEdge(new PEdge(vertex1,vertex0,100));
        vertex2.addEdge(new PEdge(vertex2,vertex0,10));
        vertex2.addEdge(new PEdge(vertex2,vertex1,1));

        PrimsAlgoEager algorithm=new PrimsAlgoEager(vertexList);
        algorithm.spanningTree();
        algorithm.show();
    }
}
