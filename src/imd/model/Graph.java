package imd.model;

import java.util.*;

public class Graph {
    private int treeVerticesMax; // V-> no. of vertices & E->no.of edges
    private Vector<Edge> edges;// collection of all edges

    public Vector<Edge> getEdges() {
        return edges;
    }

    public Graph(int v, int e) {
        treeVerticesMax = v;
        edges = new Vector<Edge>();
    }

    public void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }

    private int find(Subset subsets[], int i) {
        if (subsets[i].getParent() != i) {
            subsets[i].setParent(find(subsets, subsets[i].getParent()));
        }
        return subsets[i].getParent();
    }

    private void Union(Subset subsets[], int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].getRank() < subsets[yroot].getRank())
            subsets[xroot].setParent(yroot);
        else if (subsets[xroot].getRank() > subsets[yroot].getRank())
            subsets[yroot].setParent(xroot);
        else {
            subsets[yroot].setParent(xroot);
            subsets[xroot].setRank(subsets[xroot].getRank() + 1);
        }
    }

    public void KruskalMST() {
        Vector<Edge> results = new Vector<Edge>();
        int e = 0;
        int i = 0;

        Collections.sort(edges);
        System.out.println("-----------");
        for (Edge edge : edges) {
            System.out.print( "(" + edge.getSrc() + " " + edge.getDest() + " " + edge.getWeight() + ")");
        }
        System.out.println("-----------");
        Subset subsets[] = new Subset[treeVerticesMax];

        for (i = 0; i < treeVerticesMax; ++i)
            subsets[i] = new Subset();
        for (int v = 0; v < treeVerticesMax; ++v) {
            subsets[v].setParent(v);
            subsets[v].setRank(0);
        }

        i = 0;
        while (e < treeVerticesMax - 1) {
            Edge next_edge = edges.get(i);
            i++;
            int x = find(subsets, next_edge.getSrc());
            int y = find(subsets, next_edge.getDest());
            if (x != y) {
                results.add(next_edge);
                e++;
                Union(subsets, x, y);
            }
        }


        System.out.println("Following are the edges in "
                + "the constructed MST");
        int minimumCost = 0;

        for (Edge edge : results) {
            System.out.println(edge.getSrc()
                               + " -- " + edge.getDest()
                               + " == " + edge.getWeight());
            System.out.println("-----------------");
            minimumCost += edge.getWeight();
        }

        System.out.println("Minimum Cost Spanning Tree "
                + minimumCost);
    }
}
