import java.util.*;
import java.io.*;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokens;
  private static int a, b;

  public static void main(String[] args) throws IOException {
    // tokens = new StringTokenizer(br.readLine());
    // a = Integer.parseInt(tokens.nextToken());
    // b = Integer.parseInt(tokens.nextToken());
    a = Integer.parseInt(br.readLine());

    if(a % 2 == 1)
        a += 3;
    if(a % 3 == 0)
        a /= 3;

    System.out.println(a);
  } //  main-end
} //  Main-class-end