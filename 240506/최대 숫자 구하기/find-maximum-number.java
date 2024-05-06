import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokens;
  private static final StringBuilder sb = new StringBuilder();

  private static int n;   //  제거할 공의 수
  private static int m;   //  공의 개수
  private static final TreeSet<Integer> treeSet = new TreeSet<>();

  public static void main(String[] args) throws IOException {
    tokens = new StringTokenizer(br.readLine());
    n = Integer.parseInt(tokens.nextToken());
    m = Integer.parseInt(tokens.nextToken());

    for(int i = 1; i <= m; i++)
      treeSet.add(i);

    tokens = new StringTokenizer(br.readLine());
    for(int i = 0; i < n; i++) {
      treeSet.remove(Integer.parseInt(tokens.nextToken()));
      sb.append(treeSet.last()).append("\n");
    }

    System.out.print(sb);
  } // main-end
} // Main-class-end