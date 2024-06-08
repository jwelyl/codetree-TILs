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

    a += 8;
    b *= 3;
    
    System.out.println(a);
    System.out.println(b);
    System.out.println(a * b);
  } //  main-end
} //  Main-class-end