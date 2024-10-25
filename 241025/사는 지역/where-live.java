import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    
    private static int N;
    private static String lastName = "";
    private static Person lastPerson = null;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
    	
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	Person p = new Person(st.nextToken(), st.nextToken(), st.nextToken());
        	
        	if(p.name.compareTo(lastName) > 0) {
        		lastName = p.name;
        		lastPerson = p;
        	}
        }
        
        System.out.println(lastPerson);
    }    //    main-end
    
    private static class Person {
    	public String name;
    	public String address;
    	public String area;
    	
    	public Person(String name, String address, String area) {
    		this.name = name;
    		this.address = address;
    		this.area = area;
    	}
    	
    	@Override
    	public String toString() {
    		StringBuilder sb = new StringBuilder();
    		sb.append("name ").append(name).append("\n");
    		sb.append("addr ").append(address).append("\n");
    		sb.append("city ").append(area);
    		
    		return sb.toString();
    	}
    }
}    //    Main-class-end