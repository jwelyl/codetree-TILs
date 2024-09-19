import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MAX_X = 1_000_000;
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    
    private static int N;    //    홈 개수
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
 
        recursion1(1, N);
        recursion2(N, N);
        
        System.out.print(sb);
    }    //    main-end
  
    private static void recursion1(int cur, int nth) {
    	sb.append(cur).append(" ");
    	
    	if(cur == nth) {
    		sb.append("\n");
    		return;
    	}
    	
    	recursion1(cur + 1, nth);
    }
    
    private static void recursion2(int cur, int nth) {
    	sb.append(cur).append(" ");
    	
    	if(cur == 1) {
    		sb.append("\n");
    		return;
    	}
    	
    	recursion2(cur - 1, nth);
    }
}    //    Main-class-end