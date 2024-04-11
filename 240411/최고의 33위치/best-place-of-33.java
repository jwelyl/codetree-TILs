import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    
    private static int n;
    private static int[][] table;

    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        n = Integer.parseInt(br.readLine());

        table = new int[n][n];
        for(int i = 0; i < n; i++) {
            tokens = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                table[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        for(int i = 0; i <= n - 3; i++) {
            for(int j = 0; j <= n - 3; j++) {
                int cnt = 0;

                for(int y = i; y <= i + 2; y++) {
                    for(int x = j; x <= j + 2; x++) {
                        if(table[y][x] == 1) {
                            cnt++;
                        }
                    }
                }

                ans = Math.max(ans, cnt);
            }
        }

        System.out.println(ans);
    }
}