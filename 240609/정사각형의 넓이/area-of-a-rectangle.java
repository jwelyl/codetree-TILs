import java.util.*;
import java.io.*;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static int n;

  public static void main(String[] args) throws IOException {
    n = Integer.parseInt(br.readLine());

    System.out.printf("%d\n", n * n);
    if(n < 5)
        System.out.println("tiny");
  } //  main-end
} //  Main-class-end