import java.util.*;
import java.io.*;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokens;
  private static String n, m, l;

  public static void main(String[] args) throws IOException {
    tokens = new StringTokenizer(br.readLine(), "-");
    n = tokens.nextToken();
    m = tokens.nextToken();
    l = tokens.nextToken();

    System.out.println(n + "-" + l + "-" + m);
  } //  main-end
} //  Main-class-end