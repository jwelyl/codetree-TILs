import java.util.*;
import java.io.*;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokens;

  private static final Map<Integer, Integer> map = new HashMap<>();
  private static int n;
  private static int[] a, b, c, d;

  private static long ans = 0L;

  public static void main(String[] args) throws IOException {
    n = Integer.parseInt(br.readLine());

    a = new int[n];
    b = new int[n];
    c = new int[n];
    d = new int[n];

    tokens = new StringTokenizer(br.readLine());
    for(int i = 0; i < n; i++)
      a[i] = Integer.parseInt(tokens.nextToken());
    tokens = new StringTokenizer(br.readLine());
    for(int i = 0; i < n; i++)
      b[i] = Integer.parseInt(tokens.nextToken());
    tokens = new StringTokenizer(br.readLine());
    for(int i = 0; i < n; i++)
      c[i] = Integer.parseInt(tokens.nextToken());
    tokens = new StringTokenizer(br.readLine());
    for(int i = 0; i < n; i++)
      d[i] = Integer.parseInt(tokens.nextToken());


    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
          int sum = a[i] + b[j];
          int comp = -sum;

          map.put(comp, map.getOrDefault(comp, 0) + 1);
      }
    }

    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        int sum = c[i] + d[j];

        ans += map.getOrDefault(sum, 0);
      }
    }

    System.out.println(ans);
  }   //  main-end
}   //  Main-class-end