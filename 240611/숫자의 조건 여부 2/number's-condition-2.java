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

    if(a == 5)
        System.out.println('A');
    if(a % 2 == 0)
        System.out.println('B');
  } //  main-end
} //  Main-class-end