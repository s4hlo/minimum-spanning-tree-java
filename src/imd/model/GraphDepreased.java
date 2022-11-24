package imd.model;

public class GraphDepreased {
	private int size = 5;
	private int[] parent = new int[size];
	private int INF = Integer.MAX_VALUE;

	private int graph[][] = new int[][] {
			{ INF, 5  , 10 , 15 , 2   },
			{ 5  , INF, 21 , 2  , 45  },
			{ 10 , 21 , INF, 53 , 12  },
			{ 15 , 2  , 53 , INF, 13  },
			{ 2  , 45 , 12 , 13 , INF },
	};

	public GraphDepreased() {

	}

	public int[][] getGraph() {
		return graph;
	}

	public void setGraph(int[][] graph) {
		this.graph = graph;
	}

	// ? Disjoint set

	public int find(int i) {
		while (parent[i] != i)
			i = parent[i];
		return i;
	}

	public void union1(int i, int j) {
		int a = find(i);
		int b = find(j);
		parent[a] = b;
	}

	// ? KRUSKAL

	public void kruskalMST(int cost[][]) {
		int mincost = 0; // Cost of min MST.
		for (int i = 0; i < size; i++)
			parent[i] = i;
		int edge_count = 0;
		while (edge_count < size - 1) {
			int min = INF, a = -1, b = -1;
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (find(i) != find(j) && cost[i][j] < min) {
						min = cost[i][j];
						a = i;
						b = j;
					}
				}
			}
			union1(a, b);
			System.out.printf("Edge %d:(%d, %d) cost:%d \n",
					edge_count++, a, b, min);
			mincost += min;
		}
		System.out.printf("\n Minimum cost= %d \n", mincost);
	}
}
