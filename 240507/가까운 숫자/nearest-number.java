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
    treeSet.add(0);  // 0 좌표 추가

    n = Integer.parseInt(br.readLine());
    tokens = new StringTokenizer(br.readLine());

    // 첫 번째 점 추가
    int point = Integer.parseInt(tokens.nextToken());
    treeSet.add(point);
    sb.append(point).append("\n");  // 첫 번째 점과 0 사이의 거리 출력

    for (int i = 1; i < n; i++) {
      point = Integer.parseInt(tokens.nextToken());
      treeSet.add(point);

      // 새 점 기준 이전과 이후 점 찾기
      Integer lower = treeSet.lower(point);
      Integer higher = treeSet.higher(point);

      // 최소 간격 계산
      int minDist = Integer.MAX_VALUE;
      if (lower != null) {
        minDist = point - lower;
      }
      if (higher != null && higher - point < minDist) {
        minDist = higher - point;
      }

      sb.append(minDist).append("\n");
    }

    System.out.print(sb);
  } // main-end
} // Main-class-end