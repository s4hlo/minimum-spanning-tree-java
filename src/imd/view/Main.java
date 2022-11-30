package imd.view;

import java.util.Vector;

import imd.model.Edge;
import imd.model.Graph;

public class Main {
    public static void main(String[] args) {
        int V = 4; // Number of vertices in graph
        int E = 5; // Number of edges in graph

        Graph graph = new Graph(V, E);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);

        // Function call
        Vector<Vector<Edge>> res = graph.generatePermutations();
        int counterPerms = 0;

        for (Vector<Edge> perm : res) {
            counterPerms++;
            System.out.println();
            System.out.println(">permutacao " + counterPerms);
            graph.KruskalMST(perm);
        }
    }
}