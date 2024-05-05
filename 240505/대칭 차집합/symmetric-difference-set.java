import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokens;

  private static int nA;  //  집합 A의 원소 개수
  private static int nB;  //  집합 B의 원소 개수
  private static int nAB; //  집합 A, B의 교집합의 원소 개수

  private static final Set<Integer> setA = new HashSet<>(); //  집합 A
  private static final Set<Integer> setB = new HashSet<>(); //  집합 B

  public static void main(String[] args) throws IOException {
    tokens = new StringTokenizer(br.readLine());

    nA = Integer.parseInt(tokens.nextToken());
    nB = Integer.parseInt(tokens.nextToken());

    tokens = new StringTokenizer(br.readLine());
    for(int i = 0; i < nA; i++)
      setA.add(Integer.parseInt(tokens.nextToken()));

    tokens = new StringTokenizer(br.readLine());
    for(int i = 0; i < nA; i++) {
      if(setA.contains(Integer.parseInt(tokens.nextToken())))
          nAB++;
    }

    System.out.println(nA + nB - 2 * nAB);
  } // main-end
} // Main-class-end