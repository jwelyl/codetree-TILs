import java.util.*;
import java.io.*;
import java.util.Map.Entry;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  private static int n;   //  문자열 개수
  private static int maxCnt;

  private static final Map<String, Integer> map = new HashMap<>();   //  K : 문자열, V : 수열의 수의 등장 횟수

  public static void main(String[] args) throws IOException {
    n = Integer.parseInt(br.readLine());

    for(int i = 0; i < n; i++) {
      String str = br.readLine();

      map.put(str, map.getOrDefault(str, 0) + 1);
      maxCnt = Math.max(maxCnt, map.get(str));
    }

    System.out.println(maxCnt);
  }   //  main-end
}   //  Main-class-end