import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokens;
  private static int n;   //  사람 수
  private static int m;   //  비어있는 의자 수
  private static final TreeSet<Integer> treeSet = new TreeSet<>();  //  앉을 수 있는 자리

  private static int ans = 0; //  최대로 앉을 수 있는 사람 수

  public static void main(String[] args) throws IOException {
    tokens = new StringTokenizer(br.readLine());

    n = Integer.parseInt(tokens.nextToken());
    m = Integer.parseInt(tokens.nextToken());

    for(int i = 1; i <= m; i++)
      treeSet.add(i);

    tokens = new StringTokenizer(br.readLine());
    for(int i = 0; i < n; i++) {
      int pos = Integer.parseInt(tokens.nextToken()); //  i+1번째 사람이 앉고 싶어하는 자리 (1 이상, pos 이하 자리)
      Integer leftPos = treeSet.floor(pos); //  pos보다 작거나 같은 자리 중 앉을 수 있는 자리

      if(leftPos == null)  //  pos 이하 자리 중 앉을 수 있는 자리가 없을 경우, 앉을 수 없음
        break;
      else {  //  pos 이하 자리 중 앉을 수 있는 자리가 있을 경우
        ans++;  //  i+1번째 사람은 앉을 수 있음
        treeSet.remove(leftPos);  //  해당 자리를 앉을 수 없게 표시
      }
    }

    System.out.println(ans);
  } // main-end
} // Main-class-end