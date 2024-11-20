import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine()); // Number of vertices (nodes)
        int[] directCost = new int[n]; // Cost to place a problem directly at each vertex

        // Input for direct costs
        for (int i = 0; i < n; i++) {
            directCost[i] = Integer.parseInt(br.readLine());
        }

        // Adjacency matrix to store edge costs
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // Prim's Algorithm using a priority queue
        boolean[] visited = new boolean[n]; // To track visited nodes
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);

        // Add direct costs as virtual edges to the priority queue
        for (int i = 0; i < n; i++) {
            pq.add(new Edge(i, directCost[i]));
        }

        int totalCost = 0;
        int edgesUsed = 0;

        while (!pq.isEmpty() && edgesUsed < n) {
            Edge current = pq.poll();

            if (visited[current.node]) {
                continue;
            }

            visited[current.node] = true;
            totalCost += current.cost;
            edgesUsed++;

            // Add neighbors of the current node to the priority queue
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    pq.add(new Edge(i, graph[current.node][i]));
                }
            }
        }

        System.out.println(totalCost);
    }

    // Helper class to represent edges
    private static class Edge {
        int node; // Destination node
        int cost; // Cost of the edge

        Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
}
