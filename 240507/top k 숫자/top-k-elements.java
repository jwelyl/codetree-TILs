import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokens;
  private static final StringBuilder sb = new StringBuilder();

  private static int n;   //  수 개수
  private static int k;   //  상위 k개 수
  private static final TreeSet<Integer> treeSet = new TreeSet<>(Collections.reverseOrder());

  public static void main(String[] args) throws IOException {
    tokens = new StringTokenizer(br.readLine());

    n = Integer.parseInt(tokens.nextToken());
    k = Integer.parseInt(tokens.nextToken());

    tokens = new StringTokenizer(br.readLine());
    for(int i = 0; i < n; i++)
      treeSet.add(Integer.parseInt(tokens.nextToken()));

    for(int i = 0; i < k; i++) {
      int top = treeSet.first();
      treeSet.remove(top);

      sb.append(top).append(" ");
    }

    System.out.println(sb);
  } // main-end
} // Main-class-end