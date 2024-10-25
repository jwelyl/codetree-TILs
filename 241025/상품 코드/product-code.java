import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        System.out.println(new Product("codetree", 50));
        System.out.println(new Product(st.nextToken(), Integer.parseInt(st.nextToken())));
    }    //    main-end
    
    private static class Product {
    	public String name;
    	public int code;
    	
    	public Product(String name, int code) {
    		this.name = name;
    		this.code = code;
    	}
    	
    	@Override
    	public String toString() {
    		return String.format("product %d is %s", code, name);
    	}
    }
}    //    Main-class-end