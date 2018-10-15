package SpanningTrees;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PrimsAlgoLazy {

    private List<PVertex> unvisitedVertices;
    private List<PEdge> spanningTree;
    private PriorityQueue<PEdge> edgeHeap;
    private double fullCost;

    public PrimsAlgoLazy(List<PVertex> unvisitedVertices ){
        this.unvisitedVertices=unvisitedVertices;
        this.spanningTree=new ArrayList<>();
        this.edgeHeap=new PriorityQueue<>();
    }

    public void primsAlgorithm(PVertex vertex){
        this.unvisitedVertices.remove(vertex);//already visited
        while(!unvisitedVertices.isEmpty()){
            for(PEdge edge:vertex.getAdjacencyList()){
                if(this.unvisitedVertices.contains(edge.getTargetVertex())){
                    //we make sure that we add the edges which do not form a cycle
                    this.edgeHeap.add(edge);
                }
            }
            PEdge minEdge=this.edgeHeap.remove();
            this.spanningTree.add(minEdge);
            this.fullCost+=minEdge.getWeight();

            //update the vertex with the targetVertex
            vertex=minEdge.getTargetVertex();
            this.unvisitedVertices.remove(vertex);
        }
    }

    public void showMST(){
        System.out.println("The mininmum spanning tree cost:"+this.fullCost);
        for(PEdge edge:spanningTree){
            System.out.println(edge.getStartVertex()+"-"+edge.getTargetVertex());
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

        PrimsAlgoLazy algorithm=new PrimsAlgoLazy(vertexList);
        algorithm.primsAlgorithm(vertex2);
        algorithm.showMST();
    }
}
class PVertex implements Comparable<PVertex>//used for EAGER implementation
{

    private String name;
    private boolean visited;
    private PVertex previousVertex;
    private List<PEdge> adjacencyList;

    //this is used fro prim's algo with EAGER implementation  **************************

    private PEdge minEdge;//shortest edge to the min MST from a non MST vertex
    private double distance=Double.POSITIVE_INFINITY;//TO DETECT WHETHER HEAP NEEDS AN UPDATE OR NOT IN CASE OF LESS WEIGHT EDGE

    //**********************************************************************************

    public PVertex(String name){
        this.name=name;
        this.adjacencyList=new ArrayList<>();
    }

    public PEdge getMinEdge() {
        return minEdge;
    }

    public void setMinEdge(PEdge minEdge) {
        this.minEdge = minEdge;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void addEdge(PEdge edge){
        this.adjacencyList.add(edge);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public PVertex getPreviousVertex() {
        return previousVertex;
    }

    public void setPreviousVertex(PVertex previousVertex) {
        this.previousVertex = previousVertex;
    }

    public List<PEdge> getAdjacencyList() {
        return adjacencyList;
    }

    public void setAdjacencyList(List<PEdge> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }
    @Override
    public String toString() {
        return this.name;
    }


    @Override
    public int compareTo(PVertex otherVertex) {
        return Double.compare(this.distance,otherVertex.getDistance());
    }
}


class PEdge implements Comparable<PEdge>{

    private double weight;
    private PVertex startVertex;
    private PVertex targetVertex;

    public PEdge(PVertex startVertex,PVertex targetVertex,double weight){
        this.startVertex=startVertex;
        this.targetVertex=targetVertex;
        this.weight=weight;
    }

    public double getWeight() {
        return weight;
    }

    public PVertex getStartVertex() {
        return startVertex;
    }

    public PVertex getTargetVertex() {
        return targetVertex;
    }

    public void setStartVertex(PVertex startVertex) {
        this.startVertex = startVertex;
    }

    public void setTargetVertex(PVertex targetVertex) {
        this.targetVertex = targetVertex;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(PEdge otherEdge) {
        return Double.compare(this.weight,otherEdge.getWeight());
}
    }