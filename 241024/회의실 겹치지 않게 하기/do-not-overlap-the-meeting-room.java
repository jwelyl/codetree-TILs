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
    private static final List<int[]> meetings = new ArrayList<>();
    
    private static int endTime = 0;
    private static int ans = 0;	//	열 수 있는 회의 최대 개수

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            meetings.add(new int[] {start, end});
        }

        //  회의가 가장 빨리 끝나는 순서대로 정렬
        //	회의 끝나는 시간이 같을 경우 더 빨리 시작하는 회의 선택
        Collections.sort(meetings, (m1, m2) -> Integer.compare(m1[1], m2[1]) == 0 ? Integer.compare(m1[0], m2[0]) : Integer.compare(m1[1], m2[1]));
        
        for(int[] m : meetings) {
        	int s = m[0];
        	int e = m[1];
        	
        	if(s >= endTime) {
        		ans++;
        		endTime = e;
        	}
        }
        
        System.out.println(N - ans);
    }   //  main-end
}   //  Main-class-end