import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {    
        Class class1 = new Class();
        class1.id = "codetree";
        class1.level = 10;
        
        st = new StringTokenizer(br.readLine());
        Class class2 = new Class();
        class2.id = st.nextToken();
        class2.level = Integer.parseInt(st.nextToken());
        
        System.out.println(class1 + "\n" + class2);
    }    //    main-end
    
    private static class Class {
    	public String id;
    	public int level;
    	
    	@Override
    	public String toString() {
    		return "user " + this.id + " " + this.level;
    	}
    }
}    //    Main-class-end