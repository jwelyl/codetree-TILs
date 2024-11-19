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
			int cv = cur[0];
			
			if(contained[cv])	//	이미 MST에 포함된 정점일 경우
				continue;
			
			contained[cv] = true;	//	MST에 해당 정점 포함
			mstCost += cur[1];		//	MST에 해당 정점을 연결하기 위한 비용 추가
			
			for(Edge edge : graph[cv]) {
				int nv = edge.vertex;
				int cost = edge.cost;	//	cv와 연결된 정점들과 연결 비용
				
				if(cost < dist[nv]) {	//	기존에 알려진 nv의 연결 비용보다 적은 비용일 경우
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