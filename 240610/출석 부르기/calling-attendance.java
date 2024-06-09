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

    if(a == 1)
        System.out.println("John");
    else if(a == 2)
        System.out.println("Tom");
    else if(a == 3)
        System.out.println("Paul");
    else
        System.out.println("Vacancy");
  } //  main-end
} //  Main-class-end