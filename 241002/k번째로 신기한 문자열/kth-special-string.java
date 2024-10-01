import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static int K;
	private static String T;
	
	private static final List<String> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		T = st.nextToken();
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			
			if(str.startsWith(T))
				list.add(str);
		}
		
		Collections.sort(list);
		
		System.out.println(list.get(K - 1));
	}	//	main-end
}	//	Main-class-end