import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int N;	//	정점 개수

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
	
		System.out.println(recursion(N));
	}	//	main-end
	
	private static int recursion(int nth) {
		if(nth == 1 || nth == 2)
			return nth;
		
		return recursion(nth / 3) + recursion(nth - 1);
	}
}	//	Main-class-end