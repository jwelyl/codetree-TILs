import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static int N;
    private static int ans;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
    
        recursion(N);
    }    //    main-end
    
    private static void recursion(int num) {
    	if(num == 1) {
    		System.out.println(ans);
    		return;
    	}
    	
    	ans++;
    	recursion(num % 2 == 0 ? num / 2 : num / 3);
    }
}    //    Main-class-end