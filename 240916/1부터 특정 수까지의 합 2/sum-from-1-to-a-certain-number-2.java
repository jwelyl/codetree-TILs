import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		System.out.println(recursion(Integer.parseInt(br.readLine())));
	}	//	main-end
	
	private static int recursion(int num) {
		if(num == 1)
			return 1;
		
		return num + recursion(num - 1);
	}
}	//	Main-class-end