import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final StringBuilder sb = new StringBuilder();

	private static int N;
	
	private static final List<String> words = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
	
		for(int i = 0; i < N; i++)
			words.add(br.readLine());
		
		Collections.sort(words);

		for(int i = 0; i < N; i++)
			sb.append(words.get(i)).append("\n");
		
		System.out.print(sb);
	}	//	main-end
}	//	Main-class-end