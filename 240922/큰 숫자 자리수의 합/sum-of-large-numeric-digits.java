import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    
    private static int N = 1;
    
    public static void main(String[] args) throws IOException {
       st = new StringTokenizer(br.readLine());
       for(int i = 0; i < 3; i++)
    	   N *= Integer.parseInt(st.nextToken());
       
       System.out.println(recursion(N));
    }    //    main-end
    
    private static int recursion(int num) {
    	if(num < 10)
    		return num;
    	
    	return num % 10 + recursion(num / 10);
    }
}    //    Main-class-end