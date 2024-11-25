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
    
    private static int N;    //    소 마릿수
    private static final Queue<Integer> queue = new LinkedList<>();
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        for(int q = 0; q < N; q++) {
        	st = new StringTokenizer(br.readLine());
        	String cmd = st.nextToken();
        	
        	switch(cmd) {
        	case "push":
        		queue.offer(Integer.parseInt(st.nextToken()));
        		break;
        	case "pop":
        		sb.append(queue.poll()).append("\n");
        		break;
        	case "size":
        		sb.append(queue.size()).append("\n");
        		break;
        	case "empty":
        		sb.append(queue.isEmpty() ? "1\n" : "0\n");
        		break;
    		default:
        		sb.append(queue.peek()).append("\n");	
        	}
        }
        
        System.out.print(sb);
    }    //    main-end
}    //    Main-class-end