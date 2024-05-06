import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokens;
  private static final StringBuilder sb = new StringBuilder();

  private static int n;   //  점의 개수
  private static int m;   //  질의 개수
  private static final TreeSet<Point> treeSet = new TreeSet<>();

  public static void main(String[] args) throws IOException {
    tokens = new StringTokenizer(br.readLine());
    n = Integer.parseInt(tokens.nextToken());
    m = Integer.parseInt(tokens.nextToken());

    for(int i = 1; i <= n; i++) {
      tokens = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(tokens.nextToken());
      int y = Integer.parseInt(tokens.nextToken());

      treeSet.add(new Point(x, y));
    }

    for(int i = 0; i < m; i++){
      tokens = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(tokens.nextToken());
      int y = Integer.parseInt(tokens.nextToken());

      Point p = treeSet.higher(new Point(x, y));

      if(p == null)
        sb.append("-1 -1\n");
      else
        sb.append(p.x).append(" ").append(p.y).append("\n");
    }

    System.out.print(sb);
  } // main-end

  private static class Point implements Comparable<Point> {
    int x;
    int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int compareTo(Point p) {
      int ret = Integer.compare(this.x, p.x);

      if(ret == 0)
          ret = Integer.compare(this.y, p.y);

      return ret;
    }
  }
} // Main-class-end