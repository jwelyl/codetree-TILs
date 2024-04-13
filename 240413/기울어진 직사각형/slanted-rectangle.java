import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;       //  격자 크기
    private static int[][] map; //  격자

    private static int ans = 0; //  기울어진 직사각형에 포함된 숫자 합 최댓갑

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        for(int i = 0; i < n; i++) {
            tokens = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(tokens.nextToken());
        }

        for(int y = 0; y < n; y++) {
            for(int x = 0; x < n; x++) {
                //  시작점을 (y, x)로 함
                for(int k1 = 1; k1 <= n; k1++) {
                    for(int k2 = 1; k2 <= n; k2++) {
                        //  1, 3 방향으로 대각선으로 k1 이동
                        //  2, 4 방향으로 대각선으로 k2 이동
                        int sum = 0;

                        int py1 = y + dy[0] * k1;
                        int px1 = x + dx[0] * k1;   //  (y, x)에서 1 방향으로 k1만큼 이동한 좌표

                        if(!isIn(py1, px1)) //  (y, x)에서 1 방향으로 k1만큼 이동할 수 없을 경우
                            continue;

                        for(int k = 1; k <= k1; k++) {
                            sum += map[y + dy[0] * k][x + dx[0] * k];
                        }

                        int py2 = py1 + dy[1] * k2;
                        int px2 = px1 + dx[1] * k2; //  (py1, px1)에서 2 방향으로 k2만큼 이동한 좌표

                        if(!isIn(py2, px2)) //  (py1, px1)에서 2 방향으로 k2만큼 이동할 수 없을 경우
                            continue;        
                        
                        for(int k = 1; k <= k2; k++) {
                            sum += map[py1 + dy[1] * k][px1 + dx[1] * k];
                        }

                        int py3 = py2 + dy[2] * k1;
                        int px3 = px2 + dx[2] * k1; //  (py2, px2)에서 3 방향으로 k1만큼 이동한 좌표

                        if(!isIn(py3, px3)) //  (py2, px2)에서 3 방향으로 k1만큼 이동할 수 없을 경우
                            continue;

                        for(int k = 1; k <= k1; k++) {
                            sum += map[py2 + dy[2] * k][px2 + dx[2] * k];
                        }

                        for(int k = 1; k <= k2; k++) {
                            sum += map[py3 + dy[3] * k][px3 + dx[3] * k];
                        }

                        ans = Math.max(ans, sum);
                    }
                }
            }
        }

        System.out.println(ans);
    }   //  main-end

    private static final int[] dy = {-1, -1, 1, 1};
    private static final int[] dx = {1, -1, -1, 1};

    private static boolean isIn(int y, int x) {
        return (0 <= y && y < n) && (0 <= x && x < n);
    }
}   //  Main-class-end