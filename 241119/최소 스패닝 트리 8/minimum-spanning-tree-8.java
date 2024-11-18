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
	private static int M; // 간선 개수
	
	private static List<Edge>[] graph;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N + 1];
		for(int v = 0; v <= N; v++)
			graph[v] = new ArrayList<>();
		
		for(int e = 0; e < M; e++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int c =  Integer.parseInt(st.nextToken());
			
			graph[v1].add(new Edge(v2, c));
			graph[v2].add(new Edge(v1, c));
		}
		
		System.out.println(prim());
	} // main-end
	
	private static int prim() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((v1, v2) -> Integer.compare(v1[1], v2[1]));
		int mstCost = 0;
		int[] dist = new int[N + 1];
		Arrays.fill(dist, 1_000 * 1_000 + 1);
		boolean[] contained = new boolean[N + 1];
		
		dist[1] = 0;
		pq.offer(new int[] {1, dist[1]});
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
//			System.out.println("[" + cur[0] + ", " + cur[1] + "]");
			
			int cv = cur[0];
			
			if(!contained[cv]) {
				mstCost += cur[1];
				contained[cv] = true;
			}
			
			for(Edge edge : graph[cv]) {
				int nv = edge.vertex;
				int cost = edge.cost;
				
				if(cost < dist[nv] && !contained[nv]) {
					dist[nv] = cost;
					pq.offer(new int[] {nv, dist[nv]});
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