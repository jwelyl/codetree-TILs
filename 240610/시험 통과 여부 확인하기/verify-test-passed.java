import java.util.*;
import java.io.*;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokens;
  private static int n;

  public static void main(String[] args) throws IOException {
    n = Integer.parseInt(br.readLine());

    if(n >= 80)
        System.out.println("pass");
    else
        System.out.println(80 - n + " more score");
  } //  main-end
} //  Main-class-end