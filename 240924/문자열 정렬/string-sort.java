import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static char[] str;

	public static void main(String[] args) throws IOException {
		str = br.readLine().toCharArray();
		
		Arrays.sort(str);
		
		System.out.println(new String(str));
	} // main-end
} // Main-class-end