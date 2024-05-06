import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokens;
  private static final StringBuilder sb = new StringBuilder();

  private static int n;   //  점 개수
  private static final TreeSet<Integer> treeSet = new TreeSet<>();

  public static void main(String[] args) throws IOException {
    treeSet.add(0);

    n = Integer.parseInt(br.readLine());

    tokens = new StringTokenizer(br.readLine());

    int point = Integer.parseInt(tokens.nextToken());
    treeSet.add(point);
    sb.append(point).append("\n");

    int low = treeSet.first();
    int high = treeSet.last();

    for(int i = 0; i < n - 1; i++) {
      point = Integer.parseInt(tokens.nextToken());

      treeSet.add(point);

      if(low < point && point < high) {
        if(point - low < high - point) {
          sb.append(point - low).append("\n");
          high = point;
        }
        else {
          sb.append(high - point).append("\n");
          low = point;
        }
      }
      else sb.append(high - low).append("\n");
    }

    System.out.print(sb);
  } // main-end
} // Main-class-end