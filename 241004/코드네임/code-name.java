import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {    
        List<Agent> agents = new ArrayList<>();
        
        for(int i = 0; i < 5; i++) {
        	st = new StringTokenizer(br.readLine());
        	agents.add(new Agent(st.nextToken(), Integer.parseInt(st.nextToken())));
        }
        
        Collections.sort(agents, new Comparator<Agent>() {
			@Override
			public int compare(Main.Agent o1, Main.Agent o2) {
				return Integer.compare(o1.score, o2.score);
			}
        });
        
        System.out.println(agents.get(0));
    }    //    main-end
    
    private static class Agent {
    	public String codeName;
    	public int score;
    	
    	public Agent(String codeName, int score) {
    		this.codeName = codeName;
    		this.score = score;
    	}
    	
    	@Override
    	public String toString() {
    		return this.codeName + " " + this.score;
    	}
    }
}    //    Main-class-end