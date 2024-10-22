import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;   //  폭탄 수

    private static final List<Bomb> bombs = new ArrayList<>();
    
    //	폭탄 하나 해체하는데 1의 시간이 걸리므로 pq의 원소 개수는 pq 안에 있는 폭탄을 모두 해체하는데 걸리는 총 시간임
    private static final PriorityQueue<Integer> pq = new PriorityQueue<>();

    private static int ans = 0; //  얻을 수 있는 최대 보상

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            bombs.add(new Bomb(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(bombs);    //  데드라인 작은 순서대로 정렬

        for(Bomb bomb : bombs) {    //  데드라인이 작은 폭탄부터
            int reward = bomb.reward;
            int deadline = bomb.deadline;

            pq.offer(reward);	//	현재 폭탄을 해체했을때의 보상을 pq에 삽입
            
            if(deadline < pq.size())	//	현재 폭탄 해체 데드라인보다 많은 폭탄이 있을 경우
            	pq.poll();	//	해체했을때 보상이 가장 작은 폭탄의 해체를 포기
        }
        
        //	해체할 수 있는 폭탄들 해체 보상 더하
        while(!pq.isEmpty())
        	ans += pq.poll();
        
        System.out.println(ans);
    }   //  main-end

    private static class Bomb implements Comparable<Bomb> {
        public int reward;      //  해체했을때 보상
        public int deadline;    //  해체 데드라인

        public Bomb(int reward, int deadline) {
            this.reward = reward;
            this.deadline= deadline;
        }

        @Override
        public int compareTo(Bomb bomb) {
            // 해체 데드라인이 작은 폭탄 순으로 정렬
            return Integer.compare(this.deadline, bomb.deadline);
        }
    }
}   //  Main-class-end