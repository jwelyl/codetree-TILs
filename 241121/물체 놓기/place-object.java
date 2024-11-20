import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N; // 정점 개수
    private static int[] putCosts;
    private static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        putCosts = new int[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int v = 1; v <= N; v++)
            putCosts[v] = Integer.parseInt(br.readLine());

        for (int v1 = 1; v1 <= N; v1++) {
            st = new StringTokenizer(br.readLine());
            for (int v2 = 1; v2 <= N; v2++) {
                int cost = Integer.parseInt(st.nextToken());
                if (v1 != v2)
                    graph[v1].add(new Edge(v2, cost));
            }
        }

        System.out.println(prim());
    } // main-end

    private static long prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.cost, e2.cost));
        boolean[] contained = new boolean[N + 1];
        long mstCost = 0;

        // 초기화: 최소 직접 배치 비용을 간선으로 간주
        for (int v = 1; v <= N; v++)
            pq.offer(new Edge(v, putCosts[v]));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (contained[current.vertex]) {
                continue;
            }

            contained[current.vertex] = true;
            mstCost += current.cost;

            // 인접 정점들 추가
            for (Edge edge : graph[current.vertex]) {
                if (!contained[edge.vertex]) {
                    pq.offer(edge);
                }
            }
        }

        return mstCost;
    }

    private static class Edge {
        public int vertex;
        public int cost;

        public Edge(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
} // Main-class-end
