package ShortestPathAlgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DAGAlgorithm {

    /*
    * We sort the vertices of an a-cyclic directed graph with "TOPOLOGICAL ORDERING at each iteration"
    * We follow the BELLMAN-FORD ALGORITHM THEN */
    public void DAG(List<Vertex> vertexList,Vertex sourceVertex,Vertex targetVertex){
        sourceVertex.setDistance(0);
        TopologicalSort topoSort=new TopologicalSort();
        topoSort.makeTopologicalOrder(vertexList);

        Stack<Vertex> stack=topoSort.getOrderingStack();
        for (Vertex actualVertex: stack) {

            //bellman-ford algorithm
            for (Edge edge:actualVertex.getAdjacencyList()) {
                double newDistance=edge.getStartVertex().getDistance()+edge.getWeight();
                if(newDistance<edge.getTargetVertex().getDistance()){

                    edge.getTargetVertex().setDistance(newDistance);
                    edge.getTargetVertex().setPredecessor(edge.getStartVertex());

                }

            }

        }
        if(targetVertex.getDistance()==Double.MAX_VALUE){
            System.out.println("No shortest path there....");
        }else{
            System.out.println("Targer vertex shortest path:"+targetVertex.getDistance());
        }
    }
    public List<Vertex> shortestPathTo(Vertex targetVertex) {
        if (targetVertex.getDistance() == Double.MAX_VALUE) {
            System.out.println("No path from source to the target vertex...");
        }
        List<Vertex> shortestPathToTarget = new ArrayList<>();
        //backtrack
        for (Vertex vertex = targetVertex; vertex != null; vertex = vertex.getPredecessor()) {
            shortestPathToTarget.add(vertex);
        }
        //reverse the shortest path
        Collections.reverse(shortestPathToTarget);
        return shortestPathToTarget;

    }

    public static void main(String[] args){
        Vertex vertex0=new Vertex("A");
        Vertex vertex1=new Vertex("B");
        Vertex vertex2=new Vertex("C");

        List<Vertex> vertexList=new ArrayList<>();
        vertexList.add(vertex0);
        vertexList.add(vertex1);
        vertexList.add(vertex2);

        vertex0.addNeighbour(new Edge(1,vertex0,vertex1));
        vertex0.addNeighbour(new Edge(1,vertex0,vertex2));
        vertex1.addNeighbour(new Edge(1,vertex1,vertex2));

        DAGAlgorithm obj=new DAGAlgorithm();
        obj.DAG(vertexList,vertex0,vertex2);
        System.out.println(obj.shortestPathTo(vertex2));
    }

}

class TopologicalSort{
    private Stack<Vertex> orderingStack;

    public TopologicalSort(){
        this.orderingStack=new Stack<>();
    }

    public void makeTopologicalOrder(List<Vertex> vertexList){
        for (Vertex vertex:vertexList) {
            if(!vertex.isVisited()){
                dfs(vertex);
            }
            
        }
    }

    private void dfs(Vertex vertex) {
        for (Edge edge:vertex.getAdjacencyList()) {
            if(!edge.getTargetVertex().isVisited()){
                edge.getTargetVertex().setVisited(true);
                dfs(edge.getTargetVertex());
            }

        }
        this.orderingStack.push(vertex);
    }

    public Stack<Vertex> getOrderingStack() {
        Collections.reverse(this.orderingStack);
        return this.orderingStack;
    }
}
