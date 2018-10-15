package StronglyConnectedComponents.Kosaraju;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class KosarajuAlgorithm {

    private int[] id;
    private int count;
    private boolean[] marked;

    public KosarajuAlgorithm(Graph graph){
        DepthFirstOrder dfs=new DepthFirstOrder(graph.getTransposedGraph());
        marked=new boolean[graph.getVertexList().size()];
        id=new int[graph.getVertexList().size()];

        //second dfs--
        for(Vertex vertex:dfs.getStack()){
            if(!marked[vertex.getId()]){
                dfs(vertex);
                count++;
            }
        }
    }

    private void dfs(Vertex vertex) {
        marked[vertex.getId()]=true;
      //  id[vertex.getId()]=count;
        vertex.setComponentId(count);

        for(Vertex v:vertex.getAdjacencyList()){
            if(!marked[v.getId()]){
                dfs(v);
            }
        }
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args){
        List<Vertex> vertexList = new ArrayList<>();

        vertexList.add(new Vertex(0, "a"));
        vertexList.add(new Vertex(1, "b"));
        vertexList.add(new Vertex(2, "c"));
        vertexList.add(new Vertex(3, "d"));
        vertexList.add(new Vertex(4, "e"));
        vertexList.add(new Vertex(5, "f"));
        vertexList.add(new Vertex(6, "g"));
        vertexList.add(new Vertex(7, "h"));


        List<Edge> edgeList = new ArrayList<Edge>();

        edgeList.add(new Edge(1, vertexList.get(0), vertexList.get(1)));

        edgeList.add(new Edge(1, vertexList.get(1), vertexList.get(2)));
        edgeList.add(new Edge(1, vertexList.get(1), vertexList.get(4)));
        edgeList.add(new Edge(1, vertexList.get(1), vertexList.get(5)));

        edgeList.add(new Edge(1, vertexList.get(2), vertexList.get(3)));
        edgeList.add(new Edge(1, vertexList.get(2), vertexList.get(6)));

        edgeList.add(new Edge(1, vertexList.get(3), vertexList.get(2)));
        edgeList.add(new Edge(1, vertexList.get(3), vertexList.get(7)));

        edgeList.add(new Edge(1, vertexList.get(4), vertexList.get(0)));
        edgeList.add(new Edge(1, vertexList.get(4), vertexList.get(5)));

        edgeList.add(new Edge(1, vertexList.get(5), vertexList.get(6)));

        edgeList.add(new Edge(1, vertexList.get(6), vertexList.get(5)));

        edgeList.add(new Edge(1, vertexList.get(7), vertexList.get(3)));
        edgeList.add(new Edge(1, vertexList.get(7), vertexList.get(6)));



        Graph graph = new Graph(vertexList,edgeList);

//		for(Vertex v : graph.getTransposeGraph().getVertexList()){
//			for(Vertex u : v.getAdjaenciesList()){
//				System.out.println(v+"->"+u);
//			}
//		}

        KosarajuAlgorithm kosarajuAlgorithm = new KosarajuAlgorithm(graph);

        System.out.println(kosarajuAlgorithm.getCount());

        for(Vertex vertex : vertexList){
            System.out.println(vertex+" - "+vertex.getComponentId());
        }
    }
}

class DepthFirstOrder{
    private Stack<Vertex> stack;

    public DepthFirstOrder(Graph graph){
        stack=new Stack<>();

        //first DFS--FOR TOPOLOGICAL ORDERING
        for(Vertex vertex:graph.getVertexList()){

            if(!vertex.isVisited()){
                dfs(vertex);
            }
        }
    }

    private void dfs(Vertex vertex) {
        vertex.setVisited(true);
        for(Vertex v: vertex.getAdjacencyList()){
            if(!v.isVisited()){
                dfs(v);
            }
        }
        stack.push(vertex);//topological ordering
    }

    //to get the stack

    public Stack<Vertex> getStack() {
        return this.stack;
    }
}

class Graph{
    private List<Vertex> vertexList;
    private List<Edge> edgeList;

    public Graph(){

    }
    public Graph(List<Vertex> vertexList, List<Edge> edgeList) {
        this.vertexList = vertexList;
        this.edgeList = edgeList;
    }

    public List<Vertex> getVertexList() {
        return vertexList;
    }

    public void setVertexList(List<Vertex> vertexList) {
        this.vertexList = vertexList;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(List<Edge> edgeList) {
        this.edgeList = edgeList;
    }

    //TRANSPOSING THE GRAPH
    public Graph getTransposedGraph(){
        Graph transposedGraph=new Graph();

        List<Vertex> transposedVertextList=new ArrayList<>();
        for(Vertex v:this.vertexList){
            transposedVertextList.add(v);
        }
        for(Edge edge:this.edgeList){
            transposedVertextList.get(transposedVertextList.indexOf(edge.getTargetVertex())).addNeighbour(edge.getStartVertex());

        }
        transposedGraph.setVertexList(transposedVertextList);
        return transposedGraph;
    }

}
class Vertex{

    private int id;
    private String name;
    private boolean visited;
    private List<Vertex> adjacencyList;
    private int componentId;//same id for all vertices belonging to the same scc

    public Vertex(int id, String name) {
        this.id = id;
        this.name = name;
        this.adjacencyList=new ArrayList<>();
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

    public List<Vertex> getAdjacencyList() {
        return adjacencyList;
    }

    public void setAdjacencyList(List<Vertex> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }


    //add this
    public void addNeighbour(Vertex vertex){
        this.adjacencyList.add(vertex);
    }
    @Override
    public String toString() {
        return this.name;
    }
}
class Edge{
    private double weight;//not required
    private Vertex startVertex;
    private Vertex targetVertex;

    public Edge(double weight, Vertex startVertex, Vertex targetVertex) {
        this.weight = weight;
        this.startVertex = startVertex;
        this.targetVertex = targetVertex;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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
}

