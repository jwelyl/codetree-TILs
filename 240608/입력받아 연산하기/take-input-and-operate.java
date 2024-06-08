import java.util.*;
import java.io.*;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static int a, b;

  public static void main(String[] args) throws IOException {
    a = Integer.parseInt(br.readLine());
    b = Integer.parseInt(br.readLine());
    
    System.out.printf("%d %d", a + 87, b % 10);
  } //  main-end
} //  Main-class-end