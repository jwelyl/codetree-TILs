import java.util.*;
import java.io.*;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokens;
  private static int a, b, c;

  public static void main(String[] args) throws IOException {
    tokens = new StringTokenizer(br.readLine());
    a = Integer.parseInt(tokens.nextToken());
    b = Integer.parseInt(tokens.nextToken());
    c = Integer.parseInt(tokens.nextToken());

    System.out.printf("%d\n%d\n%d\n", a + b + c, (a + b + c) / 3, (a + b + c) * 2 / 3);
  } //  main-end
} //  Main-class-end