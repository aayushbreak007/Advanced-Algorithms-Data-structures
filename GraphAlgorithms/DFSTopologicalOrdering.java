package GraphAlgorithms;



import java.util.ArrayList;

import java.util.List;

import java.util.Stack;



public class DFSTopologicalOrdering<T extends Comparable<T>> {



    private Stack<Vertex> stack;

    public DFSTopologicalOrdering(){

        this.stack=new Stack<>();

    }



    public void dfs(Vertex<T> vertex){

        vertex.setVisited(true);



        for (Vertex<T> v:vertex.getNeighboursList()) {

            if(!v.isVisited()){

                v.setVisited(true);

                dfs(v);

            }



        }

        stack.push(vertex);

    }



    public Stack<Vertex> getStack(){

        return this.stack;

    }



    public static void main(String[] args){

        DFSTopologicalOrdering obj=new DFSTopologicalOrdering<>();



        List<Vertex> graph=new ArrayList<>();

        graph.add(new Vertex("0"));

        graph.add(new Vertex("1"));

        graph.add(new Vertex("2"));

        graph.add(new Vertex("3"));

        graph.add(new Vertex("4"));

        graph.add(new Vertex("5"));



        //add neighbours

        graph.get(2).addNeighbour(graph.get(3));

        graph.get(3).addNeighbour(graph.get(1));

        graph.get(4).addNeighbour(graph.get(0));

        graph.get(4).addNeighbour(graph.get(1));

        graph.get(5).addNeighbour(graph.get(0));

        graph.get(5).addNeighbour(graph.get(2));





        for(int i=0;i<graph.size();i++){

            if(!graph.get(i).isVisited()){

                //if not visited then apply topological ordering on the node

                obj.dfs(graph.get(i));

            }

        }



        Stack<Vertex> stack=obj.getStack();



        for(int i=0;i<graph.size();i++){

            System.out.print(stack.pop()+"->");

        }

    }

}
