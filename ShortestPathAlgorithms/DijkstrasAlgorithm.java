package ShortestPathAlgorithms;



import java.util.ArrayList;

import java.util.Collections;

import java.util.List;

import java.util.PriorityQueue;



public class DijkstrasAlgorithm {





    public void computePath(Vertex sourceVertex){

        sourceVertex.setDistance(0);//setting the initial vertex to 0

        PriorityQueue<Vertex> priorityQueue=new PriorityQueue<>();

        priorityQueue.add(sourceVertex);



        while(!priorityQueue.isEmpty()){

            Vertex actualVertex=priorityQueue.poll();//returns the smallest distance vertex from the starting vertex

            actualVertex.setVisited(true);



            //consider its neighbours

            for (Edge edge:actualVertex.getAdjacencyList()) {

                Vertex v=edge.getTargetVertex();//this is the neighbour vertex to the actual vertex

                Double newDistance=actualVertex.getDistance()+ edge.getWeight();

                if(newDistance<v.getDistance() && !v.isVisited()){

                    // priorityQueue.remove(v);

                    v.setDistance(newDistance);

                    v.setPredecessor(actualVertex);//to track the shortest path to the target vertex

                    priorityQueue.add(v);

                }



            }

        }

    }



    public List<Vertex> getShortestPathTo(Vertex targetVertex){

        List<Vertex> shortestPathToTarget=new ArrayList<>();



        //backtrack

        for(Vertex vertex=targetVertex; vertex!=null;vertex=vertex.getPredecessor()){

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



        vertex0.addNeighbour(new Edge(1,vertex0,vertex1));

        vertex0.addNeighbour(new Edge(4,vertex0,vertex2));

        vertex1.addNeighbour(new Edge(1,vertex1,vertex2));



        DijkstrasAlgorithm obj=new DijkstrasAlgorithm();

        obj.computePath(vertex0);



        System.out.println(obj.getShortestPathTo(vertex2));

    }

}



class Edge{



    private double weight;

    private Vertex startVertex;

    private Vertex targetVertex;



    public Edge(double weight,Vertex startVertex, Vertex targetVertex){

        this.weight=weight;

        this.startVertex=startVertex;

        this.targetVertex=targetVertex;



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



    public Vertex getTargetVertex() {

        return targetVertex;

    }



    public void setStartVertex(Vertex startVertex) {

        this.startVertex = startVertex;

    }



    public void setTargetVertex(Vertex targetVertex) {

        this.targetVertex = targetVertex;

    }

}





//this will be used by the priority queue to compare the distances and get the MIN HEAP will give the minDistance vertex everytime

class Vertex implements Comparable<Vertex>{//will compare vertices



    private String name;

    private List<Edge> adjacencyList;

    private boolean visited;

    private Vertex predecessor;

    private double distance =Double.MAX_VALUE;



    public Vertex(String name){

        this.name=name;

        adjacencyList=new ArrayList<>();

    }





    //getters and setters



    public String getName() {

        return name;

    }



    public void setName(String name) {

        this.name = name;

    }



    public List<Edge> getAdjacencyList() {

        return adjacencyList;

    }



    public void setAdjacencyList(List<Edge> adjacencyList) {

        this.adjacencyList = adjacencyList;

    }



    public boolean isVisited() {

        return visited;

    }



    public void setVisited(boolean visited) {

        this.visited = visited;

    }



    public Vertex getPredecessor() {

        return predecessor;

    }



    public void setPredecessor(Vertex predecessor) {

        this.predecessor = predecessor;

    }



    public double getDistance() {

        return distance;

    }



    public void setDistance(double distance) {

        this.distance = distance;

    }





    //add edge



    public void addNeighbour(Edge edge){

        this.adjacencyList.add(edge);

    }

    @Override

    public String toString() {

        return this.name;

    }





    //compare vertices according to DISTANCES

    @Override

    public int compareTo(Vertex otherVertex) {

        //compare current vertex distance with the other vertex distance

        return Double.compare(this.distance,otherVertex.getDistance());

    }

}
