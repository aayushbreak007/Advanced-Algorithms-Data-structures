package GraphAlgorithms;



import java.util.ArrayList;

import java.util.List;

import java.util.Stack;



public class DepthFirstSearch<T extends Comparable<T>> {



    private Stack<DVertex> stack;

    public DepthFirstSearch(){

        this.stack=new Stack<>();

    }

    public void dfs(List<DVertex> vertexList){

        for (DVertex v:vertexList) {

            if(!v.isVisited()){

                v.setVisited(true);

                dfsRecursive(v);

            }

        }

    }



    private void dfsRecursive(DVertex<T> v){

        System.out.print(v.getData()+" ");

        for (DVertex<T> vertex:v.getNeighbourList()) {

            if(!vertex.isVisited()){

                vertex.setVisited(true);

                dfsRecursive(vertex);

            }

        }

    }

    private void dfsWithStack(DVertex v) {

        this.stack.push(v);

        v.setVisited(true);



        while(!stack.isEmpty()){

            DVertex<T> actualVertex=this.stack.pop();

            System.out.print(actualVertex.getData()+" ");



            for (DVertex<T> vertex:actualVertex.getNeighbourList()) {

                if(!vertex.isVisited()){

                    vertex.setVisited(true);

                    this.stack.push(vertex);

                }

            }

        }



    }

    public static void main(String[] args){

        DVertex vertex1=new DVertex(1);

        DVertex vertex2=new DVertex(2);

        DVertex vertex3=new DVertex(3);

        DVertex vertex4=new DVertex(4);

        DVertex vertex5=new DVertex(5);



        List<DVertex> list=new ArrayList<>();



        //adding neighbours

        vertex1.addNeighbour(vertex2);

        vertex1.addNeighbour(vertex3);

        vertex3.addNeighbour(vertex4);

        vertex4.addNeighbour(vertex5);



        //add to the list

        list.add(vertex1);

        list.add(vertex2);

        list.add(vertex3);

        list.add(vertex4);

        list.add(vertex5);



        DepthFirstSearch<Integer> obj=new DepthFirstSearch<>();

        obj.dfs(list);

    }

}



class DVertex<T extends Comparable<T>>{

    private T data;

    private boolean visited;

    private List<DVertex> neighbourList;



    public DVertex(T data){

        this.data=data;

        neighbourList=new ArrayList<>();

    }



    public T getData() {

        return data;

    }



    public void setData(T data) {

        this.data = data;

    }



    public boolean isVisited() {

        return visited;

    }



    public void setVisited(boolean visited) {

        this.visited = visited;

    }



    public List<DVertex> getNeighbourList() {

        return neighbourList;

    }



    public void setNeighbourList(List<DVertex> neighbourList) {

        this.neighbourList = neighbourList;

    }

    public void addNeighbour(DVertex dVertex){

        this.neighbourList.add(dVertex);

    }



    @Override

    public String toString() {

        return super.toString();

    }

}
