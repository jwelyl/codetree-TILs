import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static int M;
	private static int K;
	
	private static boolean[] broken;
	private static int[] parents;
	private static int dsCnt;
	
	private static final List<int[]> edges = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int v = 1; v <= N; v++) {
			int cost = Integer.parseInt(st.nextToken());
			edges.add(new int[] {0, v, cost});
		}
		
		broken = new boolean[N + 1];
		parents = new int[N + 1];
		for(int v = 0; v <= N; v++)
			parents[v] = v;
		
		dsCnt = N;
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			if((v1 == N && v2 == 1) ||(v1 == 1 && v2 == N))
				broken[N] = true;
			else
				broken[Math.min(v1, v2)] = true;
		}
		
		for(int v = 1; v <= N - 1; v++) {
			if(!broken[v]) {
//				System.out.println("v1 = " + v);
//				System.out.println("v2 = " + (v + 1));
				int v1 = findDS(v);
				int v2 = findDS(v + 1);
				
				union(v1, v2);
				dsCnt--;
			}
		}
		
		if(!broken[N]) {
//			System.out.println("v1 = " + N);
//			System.out.println("v2 = " + 1);
			union(findDS(N), findDS(1));
			dsCnt--;
		}
		
//		System.out.println(dsCnt);
		
		if(dsCnt == 1)
			System.out.println(1);
		else {
			dsCnt++;
			
			kruskal();
		}
	} //	main-end
	
	private static void kruskal() {
		long mstCost = 0;
		int edgeCnt = dsCnt - 1;
		
		edges.sort((e1, e2) -> Integer.compare(e1[2], e2[2]));
		
		for(int[] edge : edges) {
			int v1 = findDS(edge[0]);
			int v2 = findDS(edge[1]);
			int c = edge[2];
			
			if(v1 != v2) {
				union(v1, v2);
				mstCost += c;
				edgeCnt--;
			}
			
			if(edgeCnt == 0)
				break;
		}
		
		System.out.println(mstCost <= K ? 1 : 0);
	}
	
	private static int findDS(int v) {
		if(v == parents[v])
			return v;
		
		return parents[v] = findDS(parents[v]);
	}
	
	private static void union(int v1, int v2) {
//		System.out.println("union");
		parents[v2] = v1;
	}
} //	Main-class-end