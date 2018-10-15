package ShortestPathAlgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class  BellmanFordAlgorithm {

    private List<Edge>edgeList;
    private List<Vertex> vertexList;

    public BellmanFordAlgorithm(List<Edge> edgeList,List<Vertex> vertexList){
        this.edgeList=edgeList;
        this.vertexList=vertexList;
    }
    public void BellmanFord(Vertex sourceVertex){
        sourceVertex.setDistance(0);
        //going to have V-1 iterations and relax all edges in each iteration
        for(int i=0;i<vertexList.size()-1;i++){
            for (Edge edge:edgeList) {
                if(edge.getStartVertex().getDistance()==Double.MAX_VALUE) {
                    continue;//skip
                }
                double newDistance=edge.getStartVertex().getDistance()+edge.getWeight();
                if(newDistance<edge.getTargetVertex().getDistance()){
                    edge.getTargetVertex().setDistance(newDistance);
                    edge.getTargetVertex().setPredecessor(edge.getStartVertex());
                }
            }

            //iterate again to detect negative cycle
            for (Edge edge:edgeList) {
                if(edge.getStartVertex().getDistance()!=Double.MAX_VALUE){
                    if(hasCycle(edge)){
                        System.out.println("Negative cycle detected...");
                        return;
                    }
                }

            }
        }


    }

    private boolean hasCycle(Edge edge) {

        return edge.getStartVertex().getDistance()+edge.getWeight()<edge.getTargetVertex().getDistance();
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
        List<Vertex> vertexList=new ArrayList<>();
        vertexList.add(new Vertex("A"));
        vertexList.add(new Vertex("B"));
        vertexList.add(new Vertex("C"));

        List<Edge> edgeList=new ArrayList<>();

        edgeList.add(new Edge(1,vertexList.get(0),vertexList.get(1)));
        edgeList.add(new Edge(-1,vertexList.get(0),vertexList.get(2)));
        edgeList.add(new Edge(1,vertexList.get(1),vertexList.get(2)));

        BellmanFordAlgorithm obj= new BellmanFordAlgorithm(edgeList,vertexList);
        obj.BellmanFord(vertexList.get(0));
        System.out.println(obj.shortestPathTo(vertexList.get(2)));

    }

}
