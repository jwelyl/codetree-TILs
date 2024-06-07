import java.util.*;
import java.io.*;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokens;
  private static int n, m, l;

  public static void main(String[] args) throws IOException {
    tokens = new StringTokenizer(br.readLine(), ".");
    n = Integer.parseInt(tokens.nextToken());
    m = Integer.parseInt(tokens.nextToken());
    l = Integer.parseInt(tokens.nextToken());

    System.out.println(m + "-" + l + "-" + n);
  } //  main-end
} //  Main-class-end