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

    // ? Disjoint sets funcs
    private int find(Vector<Subset> subsets, int i) {
        if (subsets.get(i).getParent() != i) {
            subsets.get(i).setParent(find(subsets, subsets.get(i).getParent()));
        }
        return subsets.get(i).getParent();
    }

    private void union(Vector<Subset> subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets.get(xroot).getRank() < subsets.get(yroot).getRank()) {
            subsets.get(xroot).setParent(yroot);
        } else if (subsets.get(xroot).getRank() > subsets.get(yroot).getRank()) {
            subsets.get(yroot).setParent(xroot);
        } else {
            subsets.get(yroot).setParent(xroot);
            subsets.get(xroot).setRank(subsets.get(xroot).getRank() + 1);
        }
    }

    private void swap(Vector<Edge> nums, int l, int i) {
        Edge temp = nums.get(l);
        nums.set(l, nums.get(i));
        nums.set(i, temp);
    }

    private void permutations(Vector<Vector<Edge>> res,
            Vector<Edge> nums, int l, int h) {
        if (l == h) {
            Vector<Edge> tmp = new Vector<Edge>(nums);
            res.add(tmp);
            return;
        }
        for (int i = l; i <= h; i++) {
            swap(nums, l, i);
            permutations(res, nums, l + 1, h);
            swap(nums, l, i);
        }
    }

    public Vector<Vector<Edge>> permute(Vector<Edge> nums) {
        Vector<Vector<Edge>> res = new Vector<Vector<Edge>>();
        int x = nums.size() - 1;
        permutations(res, nums, 0, x);
        return res;
    }

    public Vector<Vector<Edge>> generatePermutations() {
        return permute(edges);

    }

    public void KruskalMST(Vector<Edge> graph) {
        // ! vetor com a arvore geradora minima
        Vector<Edge> results = new Vector<Edge>();
        int counter = 0;

        // Collections.sort(edges);

        // * Temp Debug */
        for (Edge edge : graph) {
            System.out.print("(" + edge.getSrc() + " " + edge.getDest() + " " + edge.getWeight() + ")");
        }

        Vector<Subset> subsetss = new Vector<Subset>();

        for (int v = 0; v < treeVerticesMax; ++v) {
            subsetss.add(new Subset(v, 0));
        }

        int counter2 = 0;
        while (counter < treeVerticesMax - 1) {
            Edge next_edge = graph.get(counter2);
            counter2++;
            int x = find(subsetss, next_edge.getSrc());
            int y = find(subsetss, next_edge.getDest());
            if (x != y) {
                results.add(next_edge);
                counter++;
                union(subsetss, x, y);
            }
        }

        System.out.println("aresta da arvore");
        int minimumCost = 0;
        for (Edge edge : results) {
            System.out.println(edge.getSrc()
                    + " -- " + edge.getDest()
                    + " == " + edge.getWeight());
            System.out.println("-----------------");
            minimumCost += edge.getWeight();
        }

        System.out.println("Custo da arvore " + minimumCost);
    }
}
