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
	
	private static Deque<Integer> dq = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			String cmd = st.nextToken();
			int num = -1;
			
			if(cmd.equals("push_front")) {
				num = Integer.parseInt(st.nextToken());
				dq.offerFirst(num);
			}
			else if(cmd.equals("push_back")) {
				num = Integer.parseInt(st.nextToken());
				dq.offerLast(num);
			}
			else if(cmd.equals("pop_front"))
				sb.append(dq.pollFirst()).append("\n");
			else if(cmd.equals("pop_back"))
				sb.append(dq.pollLast()).append("\n");
			else if(cmd.equals("size"))
				sb.append(dq.size()).append("\n");
			else if(cmd.equals("empty"))
				sb.append(dq.isEmpty() ? 1 : 0).append("\n");
			else if(cmd.equals("front"))
				sb.append(dq.peekFirst()).append("\n");
			else if(cmd.equals("back"))
				sb.append(dq.peekLast()).append("\n");
		}
		
		System.out.print(sb);
	}
}	//	Main-end