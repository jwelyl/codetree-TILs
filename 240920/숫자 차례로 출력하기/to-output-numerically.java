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
 
        recursion1(N);
        sb.append("\n");
        recursion2(N);
        
        System.out.print(sb);
    }    //    main-end
  
    private static void recursion1(int nth) {
    	if(nth == 0)
    		return;
    	
    	recursion1(nth - 1);
    	sb.append(nth).append(" ");
    }
    
    private static void recursion2(int nth) {
    	if(nth == 0) {
    		sb.append("\n");
    		return;
    	}
    	
    	sb.append(nth).append(" ");
    	recursion2(nth - 1);
    }
}    //    Main-class-end