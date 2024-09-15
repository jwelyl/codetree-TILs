import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static int ans = 0;
	
	public static void main(String[] args) throws IOException {
		recursion(Integer.parseInt(br.readLine()));
	}	//	main-end
	
	private static void recursion(int num) {
		if(num == 1) {
			System.out.println(ans);
			return;
		}
		
		ans++;
		
		if(num % 2 == 0)
			recursion(num / 2);
		else
			recursion(num * 3 + 1);
	}
}	//	Main-class-end