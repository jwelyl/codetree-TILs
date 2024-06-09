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
    a = Int.parseInt(br.readLine());

    if(a >= 3000)
        System.out.println("book");
    else if(a >= 1000)
        System.out.println("mask");
    else
        System.out.println("no");
  } //  main-end
} //  Main-class-end