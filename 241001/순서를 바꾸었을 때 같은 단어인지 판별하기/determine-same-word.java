import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		char[] word1 = br.readLine().toCharArray();
		char[] word2 = br.readLine().toCharArray();
		
		Arrays.sort(word1);
		Arrays.sort(word2);
		
		System.out.println(new String(word1).equals(new String(word2)) ? "Yes" : "No");
	}	//	main-end
}	//	Main-class-end