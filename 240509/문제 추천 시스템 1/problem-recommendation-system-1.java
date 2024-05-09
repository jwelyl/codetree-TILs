import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static final StringBuilder sb = new StringBuilder();
  private static StringTokenizer tokens;

  private static int n; //  문제 수
  private static int m; //  명령어 수

  private static final TreeSet<Problem> treeSet = new TreeSet<>();
  private static final Map<Integer, Integer> map = new HashMap<>(); //  Key : 문제 번호, Value : 문제 난이도

  public static void main(String[] args) throws IOException {
    n = Integer.parseInt(br.readLine());

    for(int i = 0; i < n; i++) {
      tokens = new StringTokenizer(br.readLine());
      int num = Integer.parseInt(tokens.nextToken());
      int level = Integer.parseInt(tokens.nextToken());

      treeSet.add(new Problem(num, level));
    }

    m = Integer.parseInt(br.readLine());

    for(int i = 0; i < m; i++) {
      tokens = new StringTokenizer(br.readLine());
      String cmd = tokens.nextToken();

      Problem out = null;
      int num;    //  문제 번호
      int level;  //  문제 레벨

      switch(cmd) {
        case "rc":
          int x = Integer.parseInt(tokens.nextToken());

          if(x == 1)
            out = treeSet.first();
          else
            out = treeSet.last();

          sb.append(out.num).append("\n");

          break;
        case "ad":
          num = Integer.parseInt(tokens.nextToken());     //  추가할 문제 번호
          level = Integer.parseInt(tokens.nextToken());   //  추가할 문제의 레벨

          int prevLevel = map.getOrDefault(num, -1);  //  해당 문제가 이미 존재하는 문제일 경우 이전 난이도

          if(prevLevel != -1)  //  해당 문제가 이미 존재할 경우
            treeSet.remove(new Problem(num, prevLevel));  //  이전 문제 제거

          treeSet.add(new Problem(num, level));         //  문제 삽입(이미 존재할 경우 레벨 변경해서 다시 삽입)
          map.put(num, level);
          break;
        case "sv":
          num = Integer.parseInt(tokens.nextToken());     //  삭제할 문제 번호
          level = Integer.parseInt(tokens.nextToken());   //  삭제할 문제의 레벨

          treeSet.remove(new Problem(num, level));
          map.remove(num);
          break;
      }
    }

    System.out.print(sb);
  } // main-end

  private static class Problem implements Comparable<Problem> {
    int num;    //  문제 번호
    int level;  //  문제 레벨

    public Problem(int num, int level) {
      this.num = num;
      this.level = level;
    }

    @Override
    public int compareTo(Problem prob) {
      int ret = Integer.compare(prob.level, this.level);  //  레벨이 높은 문제 먼저

      if(ret == 0)  //  두 문제 레벨이 같을 경우
        ret = Integer.compare(prob.num, this.num);  //  문제 번호가 큰 문제 먼저

      return ret;
    }

    @Override
    public String toString() {
      return "Problem{" +
          "num=" + num +
          ", level=" + level +
          '}';
    }
  }
} //  Main-class-end