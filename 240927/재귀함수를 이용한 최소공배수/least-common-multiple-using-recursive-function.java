import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    
    private static int N;    		//  자연수 개수
    private static int[] nums;		//	자연수 집합
    
    public static void main(String[] args) throws IOException {
    	N = Integer.parseInt(br.readLine());
   
    	nums = new int[N];
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < N; i++)
    		nums[i] = Integer.parseInt(st.nextToken());
    	
    	System.out.println(lcm(N - 1));
    }    //    main-end
    
    private static int lcm(int idx) {
    	if(idx == 0)
    		return nums[0];
    	else {
    		int lcm = 0;
    		int num1 = lcm(idx - 1);
    		int num2 = nums[idx];
    		
    		for(int num = Math.max(num1, num2); num <= num1 * num2; num++) {
    			if(num % num1 == 0 && num % num2 == 0) {
    				lcm = num;
    				break;
    			}
    		}
    		
    		return lcm;
    	}
    }
}    //    Main-class-end