import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		System.out.println(new Secret(st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken())));
	}	//	main-end
	
	private static class Secret {
		public String code;
		public String point;
		public int time;
		
		public Secret(String code, String point, int time) {
			this.code = code;
			this.point = point;
			this.time = time;
		}
		
		@Override
		public String toString() {
			return String.format("secret code : %s\nmeeting pont : %s\ntime : %d", this.code, this.point, this.time);
		}
	}
}	//	Main-class-end