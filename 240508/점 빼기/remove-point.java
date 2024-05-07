import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
  private static final int NONE = Integer.MAX_VALUE;
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static final StringBuilder sb = new StringBuilder();
  private static StringTokenizer tokens;
  private static int n; //  점의 개수
  private static int m; //  질의 개수

  private static final TreeSet<Point> treeSet = new TreeSet<>();

  public static void main(String[] args) throws IOException {
    tokens = new StringTokenizer(br.readLine());

    n = Integer.parseInt(tokens.nextToken());
    m = Integer.parseInt(tokens.nextToken());

    for(int i = 0; i < n; i++) {
      tokens = new StringTokenizer(br.readLine());
      treeSet.add(new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
    }

    for(int i = 0; i < n; i++) {
      int k = Integer.parseInt(br.readLine());

      Point p = treeSet.ceiling(new Point(k, 0)); //  x 좌표가 k인 점보다 x 좌표가 크거나 같은 점 찾기

      if(p == null) //  그런 점이 없을 경우
        sb.append("-1 -1\n");
      else {  //  그런 점이 있을 경우
        sb.append(p.x).append(" ").append(p.y).append("\n");  //  출력하고
        treeSet.remove(p);  //  지우기
      }
    }

    System.out.println(sb);
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
      int ret = Integer.compare(this.x, p.x); //  x 값이 작은 점 먼저

      if(ret == 0)  //  x 값이 같을 경우
        ret = Integer.compare(this.y, p.y); //  y 값이 작은 점 먼저

      return ret;
    }
  }
} //  Main-class-end