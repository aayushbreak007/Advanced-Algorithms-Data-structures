package StronglyConnectedComponents.Tarjan;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TarjanAlgorithm {

    private Stack<Vertex> stack;
    private List<Vertex> vertexList;
    private List<List<Vertex>> connectedComponentsList;
    private int time=0;//will keep track of the order we keep visiting vertices
    public static int count=0;

    public TarjanAlgorithm(List<Vertex> vertexList) {
        this.vertexList = vertexList;
        this.stack=new Stack<>();
        this.connectedComponentsList=new ArrayList<>();
    }

    public void DepthFirst(){
        for(Vertex vertex:vertexList){
            if(!vertex.isVisited()){
                dfs(vertex);
            }
        }
    }

    private void dfs(Vertex vertex) {
        vertex.setLowLink(time++);
        vertex.setVisited(true);
        this.stack.add(vertex);
        boolean isComponentRoot=true;

        //visit it's neighbours
        for(Vertex v:vertex.getNeighbourList()){
            if(!v.isVisited()){
                dfs(v);
            }
            //on recursive callbacks---backtracking --update lowlinks

            /*set min-low link on backtrack*/
            if( vertex.getLowLink() > v.getLowLink() ){
                vertex.setLowLink(v.getLowLink());
                isComponentRoot = false;
            }

        }

        //out of the loop we get to the component root
        if(isComponentRoot){
            //found the SCC
            count++;
            //NOW POP() all the nodes from the stack related to this scc of same lowLink values
            List<Vertex> component=new ArrayList<>();

            while(true){
                Vertex actualVertex=stack.pop();
                component.add(actualVertex);

                //reset the set link
                actualVertex.setLowLink(Integer.MAX_VALUE);
                if(actualVertex.getName().equals(vertex.getName())){
                    break;
                }
            }
            connectedComponentsList.add(component);

        }

    }
    public void printComponents() {
        System.out.println(connectedComponentsList);
    }
    public static void main(String[] args){

        List<Vertex> vertexList = new ArrayList<>();

        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        Vertex v6 = new Vertex("6");
        Vertex v7 = new Vertex("7");

        v1.addNeighbour(v5);
        v2.addNeighbour(v1);
        v3.addNeighbour(v2);
        v3.addNeighbour(v4);
        v4.addNeighbour(v3);
        v5.addNeighbour(v2);
        v6.addNeighbour(v2);
        v6.addNeighbour(v5);
        v6.addNeighbour(v7);
        v7.addNeighbour(v6);
        v7.addNeighbour(v3);

        vertexList.add(v1);
        vertexList.add(v2);
        vertexList.add(v6);
        vertexList.add(v4);
        vertexList.add(v5);
        vertexList.add(v3);
        vertexList.add(v7);

        TarjanAlgorithm tarjanAlgorithm = new TarjanAlgorithm(vertexList);
        tarjanAlgorithm.DepthFirst();
        System.out.println("Number of Strongly connected components:"+count);
        tarjanAlgorithm.printComponents();
    }


}
class Vertex{

    private String name;
    private List<Vertex> neighbourList;
    private boolean visited;
    private int lowLink;

    public Vertex(String name) {
        this.name = name;
        this.neighbourList = new ArrayList<>();
    }

    public void addNeighbour(Vertex vertex) {
        this.neighbourList.add(vertex);
    }

    public int getLowLink() {
        return lowLink;
    }

    public void setLowLink(int lowLink) {
        this.lowLink = lowLink;
    }

    public String getName() {
        return name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<Vertex> getNeighbourList() {
        return neighbourList;
    }

    @Override
    public String toString() {
        return this.name;
    }
}