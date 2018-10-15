package MaximumFlow.FordFulkersonEdmondKarp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FordFulkersonAlgorithm {

    private boolean[] marked; // marked[v.getId()] = true  s -> v in the residual graph
    private Edge[] edgeTo;  // edgeTo[v] = edges in the augmenting path
    private double valueMaxFlow;

    public FordFulkersonAlgorithm(FlowNetwork flowNetwork, Vertex s, Vertex t) {

        while( hasAugmeningPath(flowNetwork, s, t) ) {

            double minValue = Double.POSITIVE_INFINITY;

            for(Vertex v=t; v != s; v = edgeTo[v.getId()].getOther(v)) {
                minValue = Math.min(minValue, edgeTo[v.getId()].getResidualCapacity(v));
            }

            for(Vertex v = t; v !=s; v=edgeTo[v.getId()].getOther(v)){
                edgeTo[v.getId()].addResidualFlowTo(v, minValue);
            }

            valueMaxFlow = valueMaxFlow + minValue;
        }
    }

    public boolean isInCut(int index) {
        return marked[index];
    }

    public double getMaxFlow() {
        return this.valueMaxFlow;
    }

    private boolean hasAugmeningPath(FlowNetwork flowNetwork, Vertex s, Vertex t) {

        edgeTo = new Edge[flowNetwork.getNumOfVertices()];
        marked = new boolean[flowNetwork.getNumOfVertices()];

        Queue<Vertex> queue = new LinkedList<>();
        queue.add(s);
        marked[s.getId()] = true;

        while( !queue.isEmpty() && !marked[t.getId()]){

            Vertex v = queue.remove();

            for(Edge e : flowNetwork.getAdjacenciesList(v)) {
                Vertex w = e.getOther(v);

                if( e.getResidualCapacity(w) > 0){
                    if( !marked[w.getId()]){
                        edgeTo[w.getId()] = e;
                        marked[w.getId()] = true;
                        queue.add(w);
                    }
                }
            }

        }

        return marked[t.getId()];
    }

    public static void main(String[] args){
        FlowNetwork flowNetwork = new FlowNetwork(4);

        Vertex vertex0 = new Vertex(0, "s");
        Vertex vertex1 = new Vertex(1, "A");
        Vertex vertex2 = new Vertex(2, "B");
        Vertex vertex3 = new Vertex(3, "t");

        List<Vertex> vertexList = new ArrayList<>();
        vertexList.add(vertex0);
        vertexList.add(vertex1);
        vertexList.add(vertex2);
        vertexList.add(vertex3);

        flowNetwork.addEdge(new Edge(vertex0, vertex1, 4));
        flowNetwork.addEdge(new Edge(vertex0, vertex2, 5));

        flowNetwork.addEdge(new Edge(vertex1, vertex3, 7));

        flowNetwork.addEdge(new Edge(vertex2, vertex1, 4));
        flowNetwork.addEdge(new Edge(vertex2, vertex3, 1));


        FordFulkersonAlgorithm fordFulkerson = new FordFulkersonAlgorithm(flowNetwork, vertex0, vertex3);

        System.out.println("Maximum flow is: " + fordFulkerson.getMaxFlow());

        // print min-cut
        System.out.println("Vertices in the min cut set: ");
        for (int v = 0; v < vertexList.size(); v++) {
            if (fordFulkerson.isInCut(v))
                System.out.print(vertexList.get(v)+" - ");
            ;
        }
    }
}


class FlowNetwork{
    private int numOfVertices;
    private int numOfEdges;
    private List<List<Edge>> adjacenciesList;

    public FlowNetwork(int numOfVertices) {
        this.numOfVertices = numOfVertices;
        this.numOfEdges = 0;
        this.adjacenciesList = new ArrayList<>();

        for(int i=0;i<numOfVertices;++i) {
            List<Edge> edgeList = new ArrayList<>();
            adjacenciesList.add(edgeList);
        }
    }

    public int getNumOfVertices() {
        return this.numOfVertices;
    }

    public int getNumOfEdges() {
        return this.numOfEdges;
    }

    //helper method
    public void addEdge(Edge e) {
        Vertex v = e.getStartVertex();
        Vertex w = e.getTargetVertex();
        adjacenciesList.get(v.getId()).add(e);
        adjacenciesList.get(w.getId()).add(e);
        numOfEdges++;
    }

    public List<Edge> getAdjacenciesList(Vertex v) {
        return adjacenciesList.get(v.getId());
    }
}

class Vertex{

    private int id;
    private String name;
    private boolean visited;

    public Vertex(int id,String name){
        this.id=id;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return this.id+" "+this.name;
    }
}
class Edge{

    private Vertex startVertex;
    private Vertex targetVertex;
    private final double capacity;
    private double flow;

    public Edge(Vertex startVertex, Vertex targetVertex, double capacity) {
        this.startVertex = startVertex;
        this.targetVertex = targetVertex;
        this.capacity = capacity;
        this.flow=0.0;//initialize flow to be 0 for every edge in the begenning
    }

    public Edge(Edge edge) {
        //sometimes we need to initialize an edge according to a new edge
        this.startVertex=edge.getStartVertex();
        this.targetVertex=edge.getTargetVertex();
        this.capacity=edge.getCapacity();
        this.flow=edge.getFlow();
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public Vertex getTargetVertex() {
        return targetVertex;
    }

    public void setTargetVertex(Vertex targetVertex) {
        this.targetVertex = targetVertex;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getFlow() {
        return flow;
    }

    public void setFlow(double flow) {
        this.flow = flow;
    }


    //helper methods
    public Vertex getOther(Vertex vertex){
        if(vertex==startVertex){
            return targetVertex;
        }else{
            return startVertex;
        }
    }
    public double getResidualCapacity(Vertex vertex){
        if(vertex==startVertex){
            return flow;//backward edge
        }else{
            return capacity-flow;//forward edge/augmented path
        }
    }
    public void addResidualFlowTo(Vertex vertex,double deltaFlow){
        if(vertex==startVertex){
            flow=flow-deltaFlow;//backward edge
        }else{
            flow=flow+deltaFlow;//forward edge
        }
    }

    @Override
    public String toString() {
        return startVertex+" "+targetVertex+" "+flow+"/"+capacity;
    }
}