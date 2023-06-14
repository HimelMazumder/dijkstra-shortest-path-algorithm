import java.util.*;

public class ShortestPath {
    // queue to store current distance of vertices (that have not found shortest distance yet) in ascending order;
    PriorityQueue<Vertex> pq;

    DirectedGraph dg;
    ArrayList<Integer> vertices = new ArrayList<Integer>();
    int[] dist;
    int[] parent;


    public ShortestPath(DirectedGraph dg) {
        this.dg = dg;
        pq = new PriorityQueue<Vertex>(new Comparator<Vertex>() {

            @Override
            public int compare(Vertex v0, Vertex v1) {
                return dist[v0.id] - dist[v1.id];
            }
        });

        dist = new int[dg.getV()];
        parent = new int[dg.getV()];
        parent[0] = -1;
        dist[0] = 0;
        for (int i = 1; i < dg.getV(); i++) {
            // Assign Max Int value to all element of dist array and -1 to parent.

            dist[i] = Integer.MAX_VALUE;


        }


        // Insert all the vertices into priority queue pq
        this.vertices = dg.getVertices();
        for (int i = 0; i < dg.getV(); i++) {
            pq.add(new Vertex(vertices.get(i)));
        }


    }

    public void generatePath(int src) {

        // loop until queue is not empty
        while (!pq.isEmpty()) {
            // get the min distance vertex v (which will be the first one) from queue
            Integer v = pq.remove().id;

            // Find all the edges going out from v. Hints: adjacency list
            LinkedList<Edge> list = dg.getAdjList(v);

            //Loop through each neighbor vertex/edge and relax the edge if needed
            for (Edge edge : list) {
                // calculate the distance of the  w via vertex v
                // which will be d[v] + weight(v,w)

                if (dist[edge.dest] > dist[edge.src] + edge.weight) {
                    dist[edge.dest] = dist[edge.src] + edge.weight;
                    parent[edge.dest] = edge.src;


                }


                // if new distance is less than previously calculated distance relax.
                // relaxing is updating the distance via new path and parent info


            }
        }

        System.out.println("Distance Array: " + Arrays.toString(dist));
        System.out.println("Parent Array: " + Arrays.toString(parent));
    }

    // Method to generate the path to each vertex.
    public void showPath() {
        // Loop through the parent array an generate path for each vertex.
        for (int i = 0; i < parent.length; i++) {
            String path = null;
            int k = 0; // to store the destination vertex
            // loop through the parent list to find the path
            System.out.printf("Path to vertex %d from source %d : ->0", i, k);
            int temp = i;

            /*
             *shortestPathTracker and shortestPathTrackerCounter both are being used to display shortest path starting from source
             *avoiding displaying the back tracking path
             */

            int[] shortestPathTracker = new int[6];
            int shortestPathTrackerCounter = 0;

            while (temp != 0) {
                shortestPathTracker[shortestPathTrackerCounter] = temp;
                temp = parent[temp];
                shortestPathTrackerCounter++;
            }

            for(int j = shortestPathTrackerCounter-1; j >= 0; j--) {
                System.out.printf("->%d",shortestPathTracker[j]);
            }


            System.out.printf("\n");
        }
    }


    public static void main(String[] args) {

        DirectedGraph dg = new DirectedGraph(5);
        dg.addEdge(0, 1, 9);
        dg.addEdge(0, 2, 2);
        dg.addEdge(0, 4, 5);
        dg.addEdge(2, 3, 3);
        dg.addEdge(3, 1, 1);
        dg.addEdge(3, 4, 1);

        ShortestPath sp = new ShortestPath(dg);
        sp.generatePath(0);
        sp.showPath();
        /************************Following output will be generated***********/
//		Distance Array: [0, 6, 2, 5, 5]
//		Parent Array: [-1, 3, 0, 2, 0]
//		Path to vertex 0 from source 0 : ->0
//		Path to vertex 1 from source 0 : ->0->2->3->1
//		Path to vertex 2 from source 0 : ->0->2
//		Path to vertex 3 from source 0 : ->0->2->3
//		Path to vertex 4 from source 0 : ->0->4
    }

}