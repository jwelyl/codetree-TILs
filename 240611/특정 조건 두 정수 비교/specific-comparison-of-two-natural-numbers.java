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

    System.out.println(a < b ? 1 : 0);
    System.out.println(a == b ? 1 : 0);
  } //  main-end
} //  Main-class-end