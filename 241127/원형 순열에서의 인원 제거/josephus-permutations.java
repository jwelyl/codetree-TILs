import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    
    private static int N;
    private static int K;
    
    private static final Queue<Integer> queue = new LinkedList<>();
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        for(int num = 1; num <= N; num++)
        	queue.offer(num);
        
        while(!queue.isEmpty()) {
        	int order = 1;
        	
        	while(true) {
        		int out = queue.poll();
        		
        		if(order == K) {
        			sb.append(out).append(" ");
        			break;
        		}
        		
        		order++;
        		queue.offer(out);
        	}
        }
        
        System.out.println(sb);
    }    //    main-end
}    //    Main-class-end