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
	private static final List<Person> people = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			people.add(new Person(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(people, (p1, p2) -> Integer.compare(p1.height, p2.height));
		
		for(Person p : people)
			sb.append(p).append("\n");
		
		System.out.print(sb);
	} //	main-end
	
	private static class Person {
		public String name;
		public int height;
		public int weight;
		
		public Person(String name, int height, int weight) {
			this.name = name;
			this.height = height;
			this.weight = weight;
		}
		
		@Override
		public String toString() {
			return new StringBuilder().append(name).append(" ").append(height).append(" ").append(weight).toString();
		}
	}
} //	Main-class-end