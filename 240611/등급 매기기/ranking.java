import java.util.*;
import java.io.*;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokens;
  private static double a, b;

  public static void main(String[] args) throws IOException {
    // tokens = new StringTokenizer(br.readLine());
    // a = Integer.parseInt(tokens.nextToken());
    // b = Integer.parseInt(tokens.nextToken());
    a = Integer.parseInt(br.readLine());

    if(a >= 90)
        System.out.println("A");
    else if(a >= 80)
        System.out.println("B");
    else if(a >= 70)
        System.out.println("C");
    else if(a >= 60)
        System.out.println("D");
    else
        System.out.println("F");
  } //  main-end
} //  Main-class-end