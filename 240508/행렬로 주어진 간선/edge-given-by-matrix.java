import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static final StringBuilder sb = new StringBuilder();
  private static StringTokenizer tokens;
  private static int n; //  정점의 개수

  private static int[][] dp;  //  그래프 상태

  public static void main(String[] args) throws IOException {
    n = Integer.parseInt(br.readLine());

    dp = new int[n + 1][n + 1];

    for(int i = 1; i <= n; i++) {
      tokens = new StringTokenizer(br.readLine());
      for(int j = 1; j <= n; j++)
        dp[i][j] = Integer.parseInt(tokens.nextToken());
    }

    floydWarshall();

    for(int i = 1; i <= n; i++) {
      for(int j = 1; j <= n; j++)
        sb.append(dp[i][j]).append(" ");
      sb.append("\n");
    }

    System.out.print(sb);
  } // main-end

  private static void floydWarshall() {
    for(int k = 1; k <= n; k++) {
      for(int i = 1; i <= n; i++) {
        for(int j = 1; j <= n; j++) {
          if(dp[i][k] == 1 && dp[k][j] == 1)
            dp[i][j] = 1;
        }
      }
    }
  }
} //  Main-class-end