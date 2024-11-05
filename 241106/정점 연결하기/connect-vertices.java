import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N;
	private static int M;
	private static int K;
	
	private static int[] parents;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		parents = new int[N + 1];
		for(int v = 1; v <= N; v++)
			parents[v] = v;
		
		for(int m = 0; m < N - 2; m++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
	
			union(v1, v2);
		}

		for(int v = 1; v <= N; v++) {
			if(v == parents[v])
				sb.append(v).append(" ");
		}
		
		System.out.println(sb);
	}	//	main-end
	
	private static int find(int v) {
		if(v == parents[v])
			return v;
		
		return parents[v] = find(parents[v]);
	}
	
	private static void union(int v1, int v2) {
		v1 = find(v1);
		v2 = find(v2);
		
		if(v1 < v2)
			parents[v2] = v1;
		else if(v1 > v2)
			parents[v1] = v2;
	}
}	//	Main-class-end