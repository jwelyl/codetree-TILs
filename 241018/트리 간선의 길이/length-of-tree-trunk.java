import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static List<Edge>[] tree;
	
	private static int[] dist;
	private static int root = 1;
	private static int maxV = 0;	//	ROOT에서 시작했을 때 가장 먼 정점
	private static int maxDist = 0;	//	ROOT에서 시작했을 때 가장 먼 정점까지의 거리
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		tree = new ArrayList[N + 1];
		dist = new int[N + 1];
		
		for(int i = 0; i <= N; i++)
			tree[i] = new ArrayList<>();
	
		for(int m = 0; m < N - 1; m++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			tree[v1].add(new Edge(v2, c));
			tree[v2].add(new Edge(v1, c));
		}
		
		dfs(root, 0, new boolean[N + 1]);
		root = maxV;
		dfs(root, 0, new boolean[N + 1]);
		
		System.out.println(maxDist);
	}	//	main-end
	
	private static void dfs(int v, int d, boolean[] visited) {
		dist[v] = d;
		visited[v] = true;
		
		if(dist[v] > maxDist) {
			maxDist = dist[v];
			maxV = v;
		}
		
		for(Edge edge : tree[v]) {
			if(!visited[edge.vertex])
				dfs(edge.vertex, d + edge.cost, visited);
		}
	}
	
	private static class Edge {
		public int vertex;
		public int cost;
		
		public Edge(int vertex, int cost) {
			this.vertex = vertex;
			this.cost = cost;
		}
	}
}	//	Main-class-end