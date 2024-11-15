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
	private static int M;

	private static int connected = 0;	//	이미 연결된 간선 개수
	
	private static int[] xpos;
	private static int[] ypos;
	
	private static int[] parents;
	
	private static final List<Edge> edges = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		xpos = new int[N + 1];
		ypos = new int[N + 1];
		parents = new int[N + 1];
		for(int v = 1; v <= N; v++)
			parents[v] = v;
		
		for(int v = 1; v <= N; v++) {
			st = new StringTokenizer(br.readLine());
			xpos[v] = Integer.parseInt(st.nextToken());
			ypos[v] = Integer.parseInt(st.nextToken());
		}
		
		for(int v1 = 1; v1 < N; v1++) {
			for(int v2 = v1 + 1; v2 <= N; v2++) {
				double cost = dist(v1, v2);
				edges.add(new Edge(v1, v2, cost));
			}
		}
		
		for(int e = 0; e < M; e++) {
			st = new StringTokenizer(br.readLine());
			int v1 = findDS(Integer.parseInt(st.nextToken()));
			int v2 = findDS(Integer.parseInt(st.nextToken()));
			
			if(v1 != v2) {
				union(v1, v2);
				connected++;
			}
		}
		
		System.out.println(String.format("%.2f", kruskal()));
	}	//	main-end
	
	private static double dist(int v1, int v2) {
		return Math.sqrt((xpos[v1] - xpos[v2] + 0.0) * (xpos[v1] - xpos[v2]) + (ypos[v1] - ypos[v2] + 0.0) * (ypos[v1] - ypos[v2]));
	}
	
	private static int findDS(int v) {
		if(v == parents[v])
			return v;
		
		return parents[v] = findDS(parents[v]);
	}
	
	private static void union(int v1, int v2) {
		parents[v2] = v1;
	}
	
	private static double kruskal() {
		double mstCost = 0.0;
		int edgeCnt = connected;
		
		edges.sort((e1, e2) -> Double.compare(e1.cost, e2.cost));

		for(Edge edge : edges) {
			int v1 = findDS(edge.v1);
			int v2 = findDS(edge.v2);
			double cost = edge.cost;
			
			if(v1 != v2) {
				union(v1, v2);
				mstCost += cost;
				edgeCnt++;
			}
			
			if(edgeCnt == N - 1)
				break;
		}
		
		return mstCost;
	}
	
	private static class Edge {
		public int v1;
		public int v2;
		public double cost;
		
		public Edge(int v1, int v2, double cost) {
			this.v1 = v1;
			this.v2 = v2;
			this.cost = cost;
		}
		
		@Override
		public String toString() {
			return new StringBuilder().append(String.format("[%d, %d, %f]", v1, v2, cost)).toString();
		}
	}
}	//	Main-class-end