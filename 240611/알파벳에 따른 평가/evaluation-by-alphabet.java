import java.util.*;
import java.io.*;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokens;
  private static char a, b;

  public static void main(String[] args) throws IOException {
    // tokens = new StringTokenizer(br.readLine());
    // a = Integer.parseInt(tokens.nextToken());
    // b = Integer.parseInt(tokens.nextToken());
    a = Ibr.readLine().charAt(0);

    if(a == 'S')
        System.out.println("Superior");
    else if(a == 'A')
        System.out.println("Excellent");
    else if(a == 'B')
        System.out.println("Good");
    else if(a == 'C')
        System.out.println("Usually");
    else if(a == 'D')
        System.out.println("Effort");
    else
        System.out.println("Failure");
  } //  main-end
} //  Main-class-end