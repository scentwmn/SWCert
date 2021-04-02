package pro.graphalgorithm;

// A Java program for Floyd Warshall All Pairs Shortest
// Path algorithm.

public class FloydWarshall
{
	final static int INF = 99999, V = 4;

	void floydWarshall(int graph[][])
	{
		int dist[][] = new int[V][V];

		/* Initialize the solution matrix
		same as input graph matrix.
		Or we can say the initial values
		of shortest distances
		are based on shortest paths
		considering no intermediate
		vertex. */
		for (int inx = 0; inx < V; inx++)
			for (int jnx = 0; jnx < V; jnx++)
				dist[inx][jnx] = graph[inx][jnx];

		/* Add all vertices one by one
		to the set of intermediate
		vertices.
		---> Before start of an iteration,
			we have shortest
			distances between all pairs
			of vertices such that
			the shortest distances consider
			only the vertices in
			set {0, 1, 2, .. k-1} as
			intermediate vertices.
		----> After the end of an iteration,
				vertex no. k is added
				to the set of intermediate
				vertices and the set
				becomes {0, 1, 2, .. k} */
		for (int inx = 0; inx < V; inx++)
		{
			// Pick all vertices as source one by one
			for (int jnx = 0; jnx < V; jnx++)
			{
				// Pick all vertices as destination for the
				// above picked source
				for (int knx = 0; knx < V; knx++)
				{
					// If vertex k is on the shortest path from
					// i to j, then update the value of dist[i][j]
					if (dist[jnx][inx] + dist[inx][knx] < dist[jnx][knx])
						dist[jnx][knx] = dist[jnx][inx] + dist[inx][knx];
				}
			}
		}

		// Print the shortest distance matrix
		printSolution(dist);
	}

	void printSolution(int dist[][])
	{
		System.out.println("The following matrix shows the shortest "+
						"distances between every pair of vertices");
		for (int i=0; i<V; ++i)
		{
			for (int j=0; j<V; ++j)
			{
				if (dist[i][j]==INF)
					System.out.print("INF ");
				else
					System.out.print(dist[i][j]+" ");
			}
			System.out.println();
		}
	}

	// Driver program to test above function
	public static void main (String[] args)
	{
		/* Let us create the following weighted graph
		10
		(0)------->(3)
		|		 /|\
		5 |		 |
		|		 | 1
		\|/		 |
		(1)------->(2)
		3		 */
		int graph[][] = { {0, 5, INF, 10},
						{INF, 0, 3, INF},
						{INF, INF, 0, 1},
						{INF, INF, INF, 0}
						};
		FloydWarshall a = new FloydWarshall();

		// Print the solution
		a.floydWarshall(graph);
	}
}

// Contributed by Aakash Hasija
