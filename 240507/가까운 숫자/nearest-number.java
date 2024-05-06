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

    int low = treeSet.first();  //  가장 가까운 두 점의 작은 점
    int high = treeSet.last();  //  가장 가까운 두 점의 큰 점

    for(int i = 0; i < n - 1; i++) {
      point = Integer.parseInt(tokens.nextToken());

      treeSet.add(point);

      Integer lowerPoint = treeSet.lower(point);    //  현재 입력받은 점보다 작으면서 가장 큰 점
      Integer higherPoint = treeSet.higher(point);  //  현재 입력받은 점보다 크면서 가장 작은 점

      int minDist = high - low; //  이전에 알려진 가장 가까운 두 점 사이 거리
      int leftMinDist;
      int rightMinDist;

      if(lowerPoint != null && higherPoint != null) {
        leftMinDist = point - lowerPoint;     //  현재 입력받은 점과 그 점보다 작은 가장 가까운 점
        rightMinDist = higherPoint - point;   //  현재 입력받은 점과 그 점보다 큰 가장 가까운 점
      }
      else if(lowerPoint == null) { //  현재 입력받은 점보다 작은 점이 없을 경우
        leftMinDist = Integer.MAX_VALUE;
        rightMinDist = higherPoint - point;
      }
      else {  //  현재 입력받은 점보다 큰 점이 없을 경우
        leftMinDist = point - lowerPoint;
        rightMinDist = Integer.MAX_VALUE;
      }

      if(leftMinDist > rightMinDist) {
        if(minDist > rightMinDist) {  //  이전에 알려진 가장 가까운 두 점 사이 거리보다 가까울 경우
          low = point;
          high = higherPoint;   //  가장 가까운 두 점 갱신
          minDist = rightMinDist;
        }
      }
      else {
        if(minDist > leftMinDist) {
          low = lowerPoint;
          high = point;
          minDist = leftMinDist;
        }
      }

      sb.append(minDist).append("\n");
    }

    System.out.print(sb);
  } // main-end
} // Main-class-end