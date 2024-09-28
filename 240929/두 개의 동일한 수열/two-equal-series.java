import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	
	private static int[] nums1;
	private static int[] nums2;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		nums1 = new int[N];
		nums2 = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i <= N; i++)
			nums1[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i <= N; i++)
			nums2[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		
		for(int i = 0; i < N; i++) {
			if(nums1[i] != nums2[i]) {
				System.out.println("No");
				return;
			}
		}
		
		System.out.println("Yes");
	}	//	main-end
}	//	Main-class-end