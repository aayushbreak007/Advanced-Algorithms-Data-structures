package GraphAlgorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class BreadthFirstSearch<T extends Comparable<T>> {



    public void bfs(Vertex root){

        Queue<Vertex> queue=new LinkedList<>();

        queue.add(root);

        root.setVisited(true);



        while(!queue.isEmpty()){

            Vertex<T> actualVertex=queue.remove();

            System.out.print(actualVertex.getData()+" ");



            for (Vertex<T> v:actualVertex.getNeighboursList()) {

                if(!v.isVisited()){

                    v.setVisited(true);

                    queue.add(v);

                }

            }


        }

    }



    public static void main(String[] args){

        BreadthFirstSearch<Integer> obj=new BreadthFirstSearch<>();

        Vertex vertex1=new Vertex(1);

        Vertex vertex2=new Vertex(2);

        Vertex vertex3=new Vertex(3);

        Vertex vertex4=new Vertex(4);

        Vertex vertex5=new Vertex(5);



        //adding neighbours

        vertex1.addNeighbour(vertex2);

        vertex1.addNeighbour(vertex4);

        vertex4.addNeighbour(vertex5);

        vertex2.addNeighbour(vertex3);



        obj.bfs(vertex1);



    }

}

class Vertex<T extends Comparable<T>>{



    private T data;

    private boolean visited;

    private boolean isBeingVisited;

    private List<Vertex<T>> neighboursList;



    public Vertex(T data){

        this.data=data;

        this.neighboursList=new ArrayList<>();

    }



    public void setData(T data) {

        this.data = data;

    }



    public T getData() {

        return data;

    }



    public boolean isVisited() {

        return visited;

    }



    public boolean isBeingVisited() {

        return isBeingVisited;

    }



    public void setBeingVisited(boolean beingVisited) {

        this.isBeingVisited = beingVisited;

    }



    public void setVisited(boolean visited) {

        this.visited = visited;

    }



    public List<Vertex<T>> getNeighboursList() {

        return neighboursList;

    }



    public void setNeighboursList(List<Vertex<T>> neighboursList) {

        this.neighboursList = neighboursList;

    }



    public void addNeighbour(Vertex vertex){

        this.neighboursList.add(vertex);

    }



    @Override

    public String toString() {

        return this.data.toString();

    }

}