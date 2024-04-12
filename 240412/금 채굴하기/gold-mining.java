import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;   //  금광 크기
    private static int m;   //  금 하나 가격

    private static int[][] map; //  금광

    private static int profit = 0;    //  금 채굴 시 이익 (음수일 경우 손해)
    private static int ans = 0;     //  손해 보지 않고 얻을 수 있는 최대 금 개수
    
    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());

        map = new int[n][n];

        for(int i = 0; i < n; i++) {
            tokens = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(tokens.nextToken());
        }

        for(int y = 0; y < n; y++) {
            for(int x = 0; x < n; x++) {
                //   k = 0일 때,
                int cnt = (map[y][x] == 1 ? 1 : 0);     //  (y, x)가 1이면 1, 아니면 0
                profit = cnt * m - 1;    //  (y, x)가 1이면 m - 1, 아니면 -1

                if(profit >= 0)  //  손해는 아닐 경우
                    ans = Math.max(ans, cnt);

                for(int k = 1; k <= n; k++) {
                    cnt = bfs(y, x, k); //  (y, x)를 중심으로 마름모 꼴로 채굴했을 때의 금 개수
                    profit = cnt * m - k * k - (k + 1) * (k + 1); //  (y, x)를 중심으로 마름모 꼴로 채굴했을 때의 이익

                    if(profit >= 0) //  손해는 아닐 경우
                        ans = Math.max(ans, cnt);
                }
            }
        }
    
        System.out.println(ans);
    }   // main-end

    private static int bfs(int y, int x, int k) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        
        int cnt = (map[y][x] == 1 ? 1 : 0);

        visited[y][x] = true;
        q.offer(new int[] {y, x, 0});

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];            //   현재 위치
            int cdist = cur[2];         //   현재 위치까지 움직인 칸 수

            if(cdist == k)  //  더 이상 갈 수 없을 경우
                continue;

            for(int d = 0; d < 4; d++) {
                int ny = cy + dy[d];
                int nx = cx + dx[d];    //  다음 위치
                int ndist = cdist + 1;  //  다음 위치까지 움직여야 하는 칸 수

                if(!isIn(ny, nx) || visited[ny][nx])   //  범위 벗어나거나, 이미 방문한 칸일 경우
                    continue;

                visited[ny][nx] = true; //  방문 처리

                if(map[ny][nx] == 1)    //  금이 있을 경우
                    cnt++;

                q.offer(new int[] {ny, nx, ndist});
            }
        }   //  while-end

        return cnt;
    }   //  bfs-end

    private static int[] dy = {0, 1, 0, -1};
    private static int[] dx = {1, 0, -1, 0};

    private static boolean isIn(int y, int x) {
        return (0 <= y && y < n) && (0 <= x && x < n);
    }
}   //  Main-class-end