import java.util.*;
import java.io.*;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokens;
  private static int a, b;

  public static void main(String[] args) throws IOException {
    tokens = new StringTokenizer(br.readLine());
    a = Integer.parseInt(tokens.nextToken());
    b = Integer.parseInt(tokens.nextToken());
    // a = Integer.parseInt(br.readLine());

    System.out.println(a % 2 == 0 ? "even" : "odd");
    System.out.println(b % 2 == 0 ? "even" : "odd");
  } //  main-end
} //  Main-class-end