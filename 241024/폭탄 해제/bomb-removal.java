import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {    
        st = new StringTokenizer(br.readLine());
        System.out.print(new Bomb(st.nextToken(), st.nextToken().charAt(0), Integer.parseInt(st.nextToken())));
    }    //    main-end
    
    private static class Bomb {
    	public String code;
    	public char color;
    	public int second;
    	
    	public Bomb(String codeName, char color, int second) {
    		this.code = codeName;
    		this.color = color;
    		this.second = second;
    	}
    	
    	@Override
    	public String toString() {
    		StringBuilder sb = new StringBuilder();
    		sb.append("code : ").append(code).append("\n");
    		sb.append("color : ").append(color).append("\n");
    		sb.append("second : ").append(second).append("\n");
    		return sb.toString();
    	}
    }
}    //    Main-class-end