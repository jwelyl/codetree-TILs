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
    a = Double.parseDouble(br.readLine());

    if(a >= 1.0)
        System.out.println("High");
    else if(a >= 0.5)
        System.out.println("Middle");
    else
        System.out.println("Low");
  } //  main-end
} //  Main-class-end