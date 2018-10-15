package MaximumFlow.BipartiteMatching;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BipartiteMatchingApp {

    public static void main(String[] args){
        int N = 5;
        double inf = Double.POSITIVE_INFINITY;

        FlowNetwork flowNetwork = new FlowNetwork(2*N+2);

        List<Vertex> vertexList = new ArrayList<>();

        vertexList.add(new Vertex(0, "s"));

        //all of the applicantas ABCDE can have a job in each company if capacity is 1
        vertexList.add(new Vertex(1, "A"));
        vertexList.add(new Vertex(2, "B"));
        vertexList.add(new Vertex(3, "C"));
        vertexList.add(new Vertex(4, "D"));
        vertexList.add(new Vertex(5, "E"));

        vertexList.add(new Vertex(6, "1"));
        vertexList.add(new Vertex(7, "2"));
        vertexList.add(new Vertex(8, "3"));
        vertexList.add(new Vertex(9, "4"));
        vertexList.add(new Vertex(10, "5"));

        vertexList.add(new Vertex(11, "t"));

        for(int i=0;i<N;i++){
            flowNetwork.addEdge(new Edge(vertexList.get(0), vertexList.get(i+1), 1));
            flowNetwork.addEdge(new Edge(vertexList.get(i+1+N),vertexList.get(11), 1));
        }

        flowNetwork.addEdge(new Edge(vertexList.get(1), vertexList.get(6), inf));
        flowNetwork.addEdge(new Edge(vertexList.get(2), vertexList.get(6), inf));

        flowNetwork.addEdge(new Edge(vertexList.get(1), vertexList.get(7), inf));
        flowNetwork.addEdge(new Edge(vertexList.get(3), vertexList.get(7), inf));

        flowNetwork.addEdge(new Edge(vertexList.get(3), vertexList.get(8), inf));
        flowNetwork.addEdge(new Edge(vertexList.get(5), vertexList.get(8), inf));

        flowNetwork.addEdge(new Edge(vertexList.get(1), vertexList.get(9), inf));
        flowNetwork.addEdge(new Edge(vertexList.get(4), vertexList.get(9), inf));

        flowNetwork.addEdge(new Edge(vertexList.get(4), vertexList.get(10), inf));

        FordFulkersonAlgorithm fordFulkersonAlgorithm = new FordFulkersonAlgorithm(flowNetwork, vertexList.get(0),vertexList.get(11));
        System.out.println("Maximum number of pairs: "+fordFulkersonAlgorithm.getMaxFlow());

        for (int v = 0; v < N; v++) {
            for (Edge e : flowNetwork.getAdjacenciesList(vertexList.get(v+1))) {
                if (e.getStartVertex().equals(v) && e.getFlow() > 0)
                    System.out.println(e.getStartVertex() + "-" + e.getTargetVertex());
            }
        }
    }

}

class FordFulkersonAlgorithm{
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
