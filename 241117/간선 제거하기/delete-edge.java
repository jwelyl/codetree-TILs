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
	private static int[] parents;
	
	private static int edgeCostSum = 0;
	
	private static final List<int[]> edges = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		// N = Integer.parseInt(st.nextToken());
		N = -1;
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N + 1];
		for(int v = 1; v <= N; v++)
			parents[v] = v;
		
		for(int e = 0; e < M; e++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			edgeCostSum += c;
			edges.add(new int[] {v1, v2, c});
		}
		
		System.out.println(edgeCostSum - kruskal());
	}	//	main-end
	
	private static int findDS(int v) {
		if(v == parents[v])
			return v;
		
		return parents[v] = findDS(parents[v]);
	}
	
	private static void union(int v1, int v2) {
		parents[v2] = v1;
	}
	
	private static int kruskal() {
		int mstCost = 0;
		int edgeCnt = 0;
		
		edges.sort((edge1, edge2) -> Integer.compare(edge1[2], edge2[2]));
		
		for(int[] edge : edges) {
			int v1 = edge[0];
			int v2 = edge[1];
			int c = edge[2];
			
			v1 = findDS(v1);
			v2 = findDS(v2);
			
			if(v1 != v2) {
				union(v1, v2);
				edgeCnt++;
				mstCost += c;
			}
			
			if(edgeCnt == N - 1)
				break;
		}
		
		return mstCost;
	}
}	//	Main-class-end