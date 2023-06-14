/**
 * Created by student on 12/21/2017.
 */
import java.util.*;

//This class represents a directed graph using adjacency
//list representation
class DirectedGraph
{
    private int V;   // No. of vertices
    private LinkedList<Edge> adj[]; //Adjacency List
    private ArrayList<Integer> vertices = new ArrayList<Integer>();

    // Constructor
    DirectedGraph(int v)
    {

        V = v;
        // isRoot = new boolean[V];
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i){
            vertices.add(i);
            adj[i] = new LinkedList<>();
            //isRoot[i] = true;
        }
    }

    // Function to add an edge into the graph
    void addEdge(int u, int v,int w) {
        adj[u].add(new Edge(u, v, w));
    }

    // Function to add an edge into the graph
    void addEdge(Edge e) {
        adj[e.src].add(e);
    }

    // getter methods
    public ArrayList<Integer> getVertices() {
        return vertices;
    }

    public int getV() {
        return V;
    }

    LinkedList<Edge> getAdjList(int v){
        return adj[v];
    }

    public void display(){
        for(Integer v: vertices)
            System.out.println(v);
    }
}


// Class to represent edge
class Edge{
    Integer src, dest;
    Integer weight;
    public Edge(Integer src, Integer dest, Integer weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }


    public boolean equals(Object obj){
        if (obj instanceof Edge){
            Edge e = (Edge)obj;
            return this.src == e.src && this.dest == e.dest && this.weight == e.weight;
        }

        return false;
    }

    public String toString(){
        return String.format("s:%d, d:%d w:%d", src, dest, weight);
    }
}

class Vertex{
    int id;
    public int getId() {
        return id;
    }

    public Vertex(int id) {
        this.id = id;
    }

    public boolean equals(Object obj){
        if (obj instanceof Vertex)
            return this.id == ((Vertex)obj).id;

        return false;
    }
}
