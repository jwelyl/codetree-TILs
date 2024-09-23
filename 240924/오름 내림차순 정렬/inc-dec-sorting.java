import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	private static int N;
	
	private static final List<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			list.add(Integer.parseInt(st.nextToken()));
		
		Collections.sort(list);
		for(int num : list)
			sb.append(num).append(" ");
		sb.append("\n");
		Collections.sort(list, Collections.reverseOrder());
		for(int num : list)
			sb.append(num).append(" ");
		sb.append("\n");
		
		System.out.print(sb);
	} // main-end
} // Main-class-end