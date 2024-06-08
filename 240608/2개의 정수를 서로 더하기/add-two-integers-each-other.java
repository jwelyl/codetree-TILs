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

    a += b;
    b += a;
    
    System.out.printf("%d %d", a, b);
  } //  main-end
} //  Main-class-end