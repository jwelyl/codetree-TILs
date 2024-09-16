import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	
	private static int[] nums;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			nums[i] = Integer.parseInt(st.nextToken());
		
		System.out.println(max(0, N - 1));
	}	//	main-end
	
	private static int max(int start, int end) {
		if(start == end)
			return nums[start];
		
		return Math.max(max(start, (start + end) / 2), max((start + end) / 2 + 1, end));
	}
}	//	Main-class-end