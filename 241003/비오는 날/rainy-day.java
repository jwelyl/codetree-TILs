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
    
    private static int N;    //    명령 개수
    
    private static final List<Weather> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	String date = st.nextToken();
        	String day = st.nextToken();
        	String weather = st.nextToken();
        	
        	if(weather.equals("Rain"))
        		list.add(new Weather(date, day, weather));
        }
 
        Collections.sort(list);
        
        System.out.print(list.get(0));
    }    //    main-end
   
    private static class Weather implements Comparable<Weather> {
    	public String date;
    	public String day;
    	public String weather;
    	
    	public Weather(String date, String day, String weather) {
    		this.date = date;
    		this.day = day;
    		this.weather = weather;
    	}
    	
    	@Override
    	public int compareTo(Weather other) {
    		return this.date.compareTo(other.date);
    	}
    	
    	@Override
    	public String toString() {
    		return String.format("%s %s %s", this.date, this.day, this.weather);
    	}
    }
}    //    Main-class-end