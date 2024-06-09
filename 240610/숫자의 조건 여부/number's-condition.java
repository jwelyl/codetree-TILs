import java.util.*;
import java.io.*;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokens;
  private static int n;

  public static void main(String[] args) throws IOException {
    n = Integer.parseInt(br.readLine());

    if(n >= 113)
        System.out.println("1");
    else
        System.out.println("0");
  } //  main-end
} //  Main-class-end