import java.util.*;
import java.io.*;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  private static double a, b;

  public static void main(String[] args) throws IOException {
    System.out.println(br.readLine());
    a = Double.parseDouble(br.readLine());
    b = Double.parseDouble(br.readLine());

    System.out.printf("%.2f\n%.2f\n", a, b);
  } //  main-end
} //  Main-class-end