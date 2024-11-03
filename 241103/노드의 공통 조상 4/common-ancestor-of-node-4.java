import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static final int ROOT = 1;
	
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	private static int N;
	private static int[] depth;
	private static int[] parents;
	
	private static List<Integer>[] tree;
	
	private static int Q;
	
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        
        depth = new int[N + 1];
        parents = new int[N + 1];
        
        tree = new ArrayList[N + 1];
        for(int v = 0; v <= N; v++)
        	tree[v] = new ArrayList<>();
        
        for(int m = 0; m < N - 1; m++) {
        	st = new StringTokenizer(br.readLine());
        	int v1 = Integer.parseInt(st.nextToken());
        	int v2 = Integer.parseInt(st.nextToken());
        	
        	tree[v1].add(v2);
        	tree[v2].add(v1);
        }
        
        dfs(ROOT, 1);	//	루트의 높이를 1로 설정, 다른 노드들 높이 결정
        
        Q = Integer.parseInt(br.readLine());
        
        for(int q = 0; q < Q; q++) {
        	st = new StringTokenizer(br.readLine());
        	int v1 = Integer.parseInt(st.nextToken());
        	int v2 = Integer.parseInt(st.nextToken());
        	
        	sb.append(lca(v1, v2)).append("\n");
        }
        
        System.out.print(sb);
    }   //  main-end
    
    private static void dfs(int v, int d) {
    	depth[v] = d;
    	
    	for(int next : tree[v]) {
    		if(depth[next] == 0) {
    			parents[next] = v;
    			dfs(next, d + 1);
    		}
    	}
    }
    
    private static int lca(int v1, int v2) {
    	int x = depth[v1] < depth[v2] ? v1 : v2;	//	깊이가 얕은 노드
    	int y = depth[v1] >= depth[v2] ? v1 : v2;	//	깊이가 깊은 노드
    	
    	while(depth[x] < depth[y])	//	x, y 깊이가 같아질때까지
    		y = parents[y];	//	y가 올라옴
    	
    	while(x != y) {	//	높이를 맞춘 후 둘이 같아질때까지
    		x = parents[x];
    		y = parents[y];	//	둘이 높이 하나씩 올라옴
    	}
    	
    	return x;
    }
}   //  Main-class-end