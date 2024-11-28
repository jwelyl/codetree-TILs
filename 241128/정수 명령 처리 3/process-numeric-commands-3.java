import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    
    private static int N;
    private static final Deque<Integer> deque = new LinkedList<>();
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        for(int q = 0; q < N; q++) {
        	st = new StringTokenizer(br.readLine());
        	String cmd = st.nextToken();
        	
        	switch(cmd) {
        	case "push_front":
        		deque.offerFirst(Integer.parseInt(st.nextToken()));
        		break;
        	case "push_back":
        		deque.offerLast(Integer.parseInt(st.nextToken()));
        		break;
        	case "pop_front":
        		sb.append(deque.pollFirst()).append("\n");
        		break;
        	case "pop_back":
        		sb.append(deque.pollLast()).append("\n");
        		break;
        	case "size":
        		sb.append(deque.size()).append("\n");
        		break;
        	case "empty":
        		sb.append(deque.isEmpty() ? "1\n" : "0\n");
        		break;
        	case "front":
        		sb.append(deque.peekFirst()).append("\n");
        		break;
    		default:
    			sb.append(deque.peekLast()).append("\n");
        	}
        }
        
        System.out.print(sb);
    }    //    main-end
}    //    Main-class-end