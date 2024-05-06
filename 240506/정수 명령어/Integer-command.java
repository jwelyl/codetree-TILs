import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokens;

  private static int tc;  //  테스트 케이스 개수
  private static int k;   //  테스트 케이스 별 연산 개수
  private static final TreeSet<Integer> treeSet = new TreeSet<>();

  public static void main(String[] args) throws IOException {
    tc = Integer.parseInt(br.readLine());

    for(int t = 0; t < tc; t++) {
      k = Integer.parseInt(br.readLine());

      treeSet.clear();

      for(int i = 0; i < k; i++) {
        tokens = new StringTokenizer(br.readLine());

        char opt = tokens.nextToken().charAt(0);
        int n = Integer.parseInt(tokens.nextToken());

        switch(opt) {
          case 'I':
            treeSet.add(n);
            break;
          case 'D':
            if(!treeSet.isEmpty()) {
              if (n == 1)
                treeSet.remove(treeSet.last());
              else
                treeSet.remove(treeSet.first());
            }
        }
      }

      System.out.println(treeSet.isEmpty() ? "EMPTY" : String.format("%d %d", treeSet.last(), treeSet.first()));
    }
  } // main-end
} // Main-class-end