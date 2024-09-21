import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    
    private static int N;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
    
        recursion(N);
        
        System.out.print(sb);
    }    //    main-end
    
    private static void recursion(int nth) {
    	if(nth == 1) {
    		sb.append("1 1 ");
    		return;
    	}
    	
    	sb.append(nth).append(" ");
    	recursion(nth - 1);
    	sb.append(nth).append(" ");
    }
}    //    Main-class-end