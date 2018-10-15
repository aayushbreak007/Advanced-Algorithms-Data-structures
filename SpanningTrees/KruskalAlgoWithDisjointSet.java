package SpanningTrees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalAlgoWithDisjointSet {

    public void spanningTree(List<Vertex> vertexList,List<Edge> edgeList){
        DisjointSet disjointSet=new DisjointSet(vertexList);
        List<Edge> mst=new ArrayList<>();

        //sort the edge list according to the weight
        Collections.sort(edgeList);

        //iterate through all the edges
        for (Edge edge:edgeList) {
            Vertex u=edge.getStartVertex();
            Vertex v=edge.getTargetVertex();


            //use disjoint set to detect CYCLES
            if(disjointSet.find(u.getNode()) != disjointSet.find(v.getNode())){

                //will return the node id----should not be same if not in the same set
                mst.add(edge);
                disjointSet.union(u.getNode(),v.getNode());
            }
        }

        for (Edge edge:mst) {
            System.out.print(edge.getStartVertex()+" "+edge.getTargetVertex()+"---");

        }
    }

    public static void main(String[] args){
        List<Vertex> vertexList = new ArrayList<>();
        vertexList.add(new Vertex("A"));
        vertexList.add(new Vertex("B"));
        vertexList.add(new Vertex("C"));
        vertexList.add(new Vertex("D"));
        vertexList.add(new Vertex("E"));
        vertexList.add(new Vertex("F"));
        vertexList.add(new Vertex("G"));
        vertexList.add(new Vertex("H"));

        List<Edge> edgeList = new ArrayList<>();
        edgeList.add(new Edge(vertexList.get(0), vertexList.get(1), 3));
        edgeList.add(new Edge(vertexList.get(0), vertexList.get(2), 2));
        edgeList.add(new Edge(vertexList.get(0), vertexList.get(3), 5));
        edgeList.add(new Edge(vertexList.get(1), vertexList.get(5), 13));
        edgeList.add(new Edge(vertexList.get(1), vertexList.get(3), 2));
        edgeList.add(new Edge(vertexList.get(2), vertexList.get(4), 5));
        edgeList.add(new Edge(vertexList.get(2), vertexList.get(3), 2));
        edgeList.add(new Edge(vertexList.get(3), vertexList.get(4), 4));
        edgeList.add(new Edge(vertexList.get(3), vertexList.get(5), 6));
        edgeList.add(new Edge(vertexList.get(3), vertexList.get(6), 3));
        edgeList.add(new Edge(vertexList.get(4), vertexList.get(6), 6));
        edgeList.add(new Edge(vertexList.get(5), vertexList.get(6), 2));
        edgeList.add(new Edge(vertexList.get(5), vertexList.get(7), 3));
        edgeList.add(new Edge(vertexList.get(6), vertexList.get(7), 6));

        KruskalAlgoWithDisjointSet kruskalAlgorithm = new KruskalAlgoWithDisjointSet();
        kruskalAlgorithm.spanningTree(vertexList, edgeList);
    }

}

class Vertex{

    private String name;
    private Node node;

    public Vertex(String name){
        this.name=name;
    }

    public Node getNode() {
        return node;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

//WE HAVE TO SORT THE EDGES OF THE GRAPH ACCORDING TO THE WEIGHTS
class Edge implements Comparable<Edge>{//compare and sort the given edge with another edge

    private double weight;
    private Vertex startVertex;
    private Vertex targetVertex;

    public Edge(Vertex startVertex,Vertex targetVertex,double weight){
        this.weight=weight;
        this.startVertex=startVertex;
        this.targetVertex=targetVertex;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public Vertex getStartVertex() {
        return startVertex;

    }

    public void setTargetVertex(Vertex targetVertex) {
        this.targetVertex = targetVertex;
    }

    public Vertex getTargetVertex() {
        return targetVertex;
    }

    @Override
    public int compareTo(Edge otherEdge) {
        //sort according to the weights
        return Double.compare(this.weight,otherEdge.getWeight());

    }
}
class Node{

    private int id;
    private int rank;//depth of the tree
    private Node parent;

    public Node(int rank, int id,Node parent){
        this.id=id;
        this.rank=rank;
        this.parent=parent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}

class DisjointSet{

    private int nodeCount=0;
    private int setCount=0;
    private List<Node> rootNodes;//representatives of the sets

    public DisjointSet(List<Vertex> vertices){
        this.rootNodes=new ArrayList<>();
        //make the disjoint sets as many as the vertices in the graph initially
        makeSets(vertices);
    }

    /*--INITIALIZATION--*/
    private void makeSets(List<Vertex> vertices) {
        for (Vertex v:vertices) {
            //making set for each vertex
            makeSet(v);

        }
    }

    private void makeSet(Vertex vertex) {
        Node n=new Node(0,rootNodes.size(),null);
        vertex.setNode(n);
        this.rootNodes.add(n);//adding reference for each set
        this.setCount++;
        this.nodeCount++;
    }

    /*---UNION-FIND---*/
    public int find(Node n){
        Node currentNode=n;

        //now we are looking for the parent
        while(currentNode.getParent()!=null){
            currentNode=currentNode.getParent();
        }

        //found the root
        Node rootNode=currentNode;
        currentNode=n;

        //-------PATH COMPRESSION------------BOOST UP ALGO----
        while(currentNode!=rootNode){
            Node temp=currentNode.getParent();

            currentNode.setParent(rootNode);//now current node is directly attached to the root node
            currentNode=temp;
        }
        return rootNode.getId();
    }

    public void union(Node node1,Node node2){
        int index1=find(node1);//returns the id
        int index2=find(node2);

        if(index1==index2){//REPRESENTATIVES ARE THE SAME
            //MEANS THAT BOTH THE NODES ARE IN THE SAME SET
            return;
        }
        Node root1=this.rootNodes.get(index1);//returns the root node
        Node root2=this.rootNodes.get(index2);

        //union-by-rank---to avoid tree unbalancing
        /*attach the smaller tree to the root of the larger tree*/
        if(root1.getRank()<root2.getRank()){
            //attach root1 to root2
            root1.setParent(root2);
        }else if(root1.getRank()>root2.getRank()){
            root2.setParent(root1);
        }else{
            root2.setParent(root1);
            root1.setRank(root1.getRank()+1);//increment the rank if equal
        }
        this.setCount--;//merging of the sets
    }

}