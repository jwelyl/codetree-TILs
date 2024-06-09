import java.util.*;
import java.io.*;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokens;
  private static int h, w;
  private static double b;

  public static void main(String[] args) throws IOException {
    tokens = new StringTokenizer(br.readLine());
    h = Integer.parseInt(tokens.nextToken());
    w = Integer.parseInt(tokens.nextToken());

    b = 10_000.0f * w / h / h;

    System.out.printf("%d\n", (int)b);
    if(b >= 25)
        System.out.println("Obesity");
  } //  main-end
} //  Main-class-end