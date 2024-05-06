import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokens;
  private static final StringBuilder sb = new StringBuilder();

  private static int n;   //  서로 다른 숫자 개수
  private static int m;   //  찾으려는 숫자 개수
  private static final TreeSet<Integer> treeSet = new TreeSet<>();

  public static void main(String[] args) throws IOException {
    tokens = new StringTokenizer(br.readLine());
    n = Integer.parseInt(tokens.nextToken());
    m = Integer.parseInt(tokens.nextToken());

    tokens = new StringTokenizer(br.readLine());
    for(int i = 0; i < n; i++)
      treeSet.add(Integer.parseInt(tokens.nextToken()));

    for(int i = 0; i < m; i++) {
      int num = Integer.parseInt(br.readLine());
      Integer ceil = treeSet.ceiling(num);

      sb.append(ceil == null ? -1 : ceil).append("\n");
    }

    System.out.print(sb);
  } // main-end
} // Main-class-end