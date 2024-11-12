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
	
	private static final List<int[]> edges = new ArrayList<>();
	private static int[] parents; 
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N * M + 1];
		for(int v = 0; v <= N * M; v++)
			parents[v] = v;
		
		for(int row = 1; row <= N; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 1; col <= M - 1; col++) {
				int v1 = (row - 1) * M + col;
				int v2 = v1 + 1;
				
				edges.add(new int[] {v1, v2, Integer.parseInt(st.nextToken())});
			}
		}
		
		for(int row = 1; row <= N - 1; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 1; col <= M; col++) {
				int v1 = (row - 1) * M + col;
				int v2 = v1 + M;
				
				edges.add(new int[] {v1, v2, Integer.parseInt(st.nextToken())});
			}
		}
		
		System.out.println(kruskal());
	}	//	main-end
	
	private static int kruskal() {
		int mstCost = 0;
		int edgeCnts = 0;
		
		edges.sort((e1, e2) -> Integer.compare(e1[2], e2[2]));
		
		for(int[] edge : edges) {
			int v1 = findDS(edge[0]);
			int v2 = findDS(edge[1]);
			int c = edge[2];
			
			if(v1 != v2) {
				union(v1, v2);
				mstCost += c;
				edgeCnts++;
			}
			
			if(edgeCnts == N * M - 1)
				break;
		}
		
		return mstCost;
	}

	private static int findDS(int v) {
		if(v == parents[v])
			return v;
		
		return parents[v] = findDS(parents[v]);
	}
	
	private static void union(int v1, int v2) {
		parents[v2] = v1;
	}
}	//	Main-class-end
