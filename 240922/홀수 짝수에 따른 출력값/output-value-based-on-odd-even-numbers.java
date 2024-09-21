import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    
    public static void main(String[] args) throws IOException {
       N = Integer.parseInt(br.readLine());

       System.out.println(recursion(N));
    }    //    main-end
    
    private static int recursion(int num) {
    	if(num == 1 || num == 2)
    		return num;
    	
    	return num + recursion(num - 2);
    }
}    //    Main-class-end