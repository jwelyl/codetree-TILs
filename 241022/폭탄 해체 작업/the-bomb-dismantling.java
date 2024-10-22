import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Main {
    private static final int MAX_DL = 10_001;   //  최대 데드라인 = 10,000

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;   //  폭탄 수

    //  occupied[time] : time에 해체해야 할 폭탄이 있을 경우 true
    private static final boolean[] occupied = new boolean[MAX_DL];
    private static final List<Bomb> bombs = new ArrayList<>();

    private static int ans = 0; //  얻을 수 있는 최대 보상

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            bombs.add(new Bomb(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(bombs);    //  보상 큰 순서대로 정렬

        for(Bomb bomb : bombs) {    //  보상이 큰 폭탄부터 해체하기
            int reward = bomb.reward;
            int deadline = bomb.deadline;

            //  해당 폭탄을 최대한 데드라인에 가깝게 해체하기
            for(int time = deadline; time >= 1; time--) {
                if(!occupied[time]) {   //  time에 해체할 수 있을 경우
                    occupied[time] = true;
                    ans += reward;
                    break;
                }
            }
        }

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
            //  해체 시 보상이 큰 순서대로 정렬
            return Integer.compare(bomb.reward, this.reward);
        }
    }
}   //  Main-class-end