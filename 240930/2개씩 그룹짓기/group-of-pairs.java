import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {	
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	
	private static int[] nums;
	
	private static int minMaxSum = -1;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		nums = new int[2 * N];
		st = new StringTokenizer(br.readLine());

		for(int i = 0; i < 2 * N; i++)
			nums[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(nums);
		
		int start = 0;
		int end = 2 * N - 1;
		while(start < end)
			minMaxSum = Math.max(minMaxSum, nums[start++] + nums[end--]);
		
		System.out.println(minMaxSum);
	}	//	main-end
}	//	Main-class-end