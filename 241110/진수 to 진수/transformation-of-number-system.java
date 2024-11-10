import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int A;
	private static int B;
	private static String N;
	private static int decimal = 0;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		N = br.readLine();
		
		int mult = 1;
		for(int i = N.length() - 1; i >= 0; i--) {
			int digit = N.charAt(i) - '0';
			decimal += mult * digit;
			mult *= A;
		}
		
		System.out.println(Integer.toString(decimal, B));
	} //	main-end
} //	Main-class-end