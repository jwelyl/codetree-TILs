import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;
    private static int tMax;
    private static int[] d;
    
    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        tMax = Integer.parseInt(tokens.nextToken());

        d = new int[n + 1];
        for(int i = 1; i <= n; i++)
            d[i] = Integer.parseInt(br.readLine());

        System.out.println(parametricSearch());
    }   //  main-end

    private static int parametricSearch() {
        int start = 0;
        int end = n;
        int ret = Integer.MAX_VALUE;

        while(start <= end) {
            int mid = (start + end) / 2;    //  한 번에 mid명 올림

            if(isOk(mid)) { //  한 번에 mid명씩 올렸을 때 모든 사람이 무대를 끝내는데 tMax 시간 이하면
                ret = mid;
                end = mid - 1;  //  한 번에 올리는 사람 수를 더 적게 해도 가능한지 체크
            }
            else
                start = mid + 1;    //  한 번에 더 많은 사람을 올려야 함
        }

        return ret;
    }

    //  k명씩 무대에 올렸을 때 tMax 시간 안에 모두 무대를 마칠 수 있으면 true
    private static boolean isOk(int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();  //  무대 끝나는 시간이 빠른 순서대로

        int curTime = 0;
        int idx = 1;    //  올라갈 사람 번호

        for(; idx <= k; idx++)
            pq.offer(curTime + d[idx]);   //  k명의 사람 무대에 올림

        while(!pq.isEmpty()) {
            curTime = pq.poll();    //  무대 위의 사람 중 가장 먼저 무대 마친 사람이 내려온 시간

            if(idx <= n)  //  아직 무대에 올라야 할 사람이 남은 경우
                pq.offer(curTime + d[idx++]);
        }

        return curTime <= tMax; //  마지막으로 무대 마친 사람이 tMax가 지나기 전에 내려올 수 있으면 true
    }
}   //  Main-class-end