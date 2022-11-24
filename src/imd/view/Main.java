package imd.view;

import imd.model.Graph;

public class Main {
    public static void main(String[] args) {
        // System.out.println("hello");
        // Graph test = new Graph();
        // // test.kruskalMST(test.getGraph());
        // test.kruskalMST(test.getGraph());
        /*
         * Let us create following weighted graph
         * 10
         * 0--------1
         * | \ |
         * 6| 5\ |15
         * | \ |
         * 2--------3
         * 4
         */
        int V = 4; // Number of vertices in graph
        int E = 5; // Number of edges in graph
        Graph graph = new Graph(V, E);


        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);

        // Function call
        graph.KruskalMST();
    }
}