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
	
	private static int minPutV = -1;
	private static int minPutCost = Integer.MAX_VALUE;
	private static long sum = 0;
	
	private static List<Edge>[] graph;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		for(int v = 1; v <= N; v++) {
			int putCost = Integer.parseInt(br.readLine());
			if(minPutCost > putCost) {
				minPutCost = putCost;
				minPutV = v;
			}

			sum += putCost;
		}
		
		graph = new ArrayList[N + 1];
		for(int v = 0; v <= N; v++)
			graph[v] = new ArrayList<>();
		
		for(int v1 = 1; v1 <= N; v1++) {
			st = new StringTokenizer(br.readLine());
			for(int v2 = 1; v2 <= N; v2++) {
				int c = Integer.parseInt(st.nextToken());
				
				if(v1 != v2)
					graph[v1].add(new Edge(v2, c));
			}
		}
		
		System.out.println(prim());
	} // main-end
	
	private static int prim() {
		PriorityQueue<long[]> pq = new PriorityQueue<>((v1, v2) -> Long.compare(v1[1], v2[1]));
		int mstCost = minPutCost;
		long[] dist = new long[N + 1];
		Arrays.fill(dist, 100_000L * 300 * 300 + 1);
		boolean[] contained = new boolean[N + 1];
		
		dist[minPutV] = 0;
		pq.offer(new long[] {minPutV, dist[minPutV]});
		
		while(!pq.isEmpty()) {
			long[] cur = pq.poll();
			int cv = (int)cur[0];
			
			if(contained[cv])	//	이미 MST에 포함된 정점일 경우
				continue;
			
			contained[cv] = true;	//	MST에 해당 정점 포함
			mstCost += cur[1];		//	MST에 해당 정점을 연결하기 위한 비용 추가
			
			for(Edge edge : graph[cv]) {
				int nv = edge.vertex;
				int cost = edge.cost;	//	cv와 연결된 정점들과 연결 비용
				
				if(cost < dist[nv]) {	//	기존에 알려진 nv의 연결 비용보다 적은 비용일 경우
					dist[nv] = cost;
					pq.offer(new long[] {nv, dist[nv]});
				}
			}
		}
		
		return Math.min(sum, mstCost);
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