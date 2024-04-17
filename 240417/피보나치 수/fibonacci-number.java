import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final int[] fibo = new int[46];

    private static int n = 0;

    public static void main(String[] args) throws IOException {
        fibo[1] = 1;
        fibo[2] = 1;

        for(int i = 3; i <= 45; i++)
            fibo[i] = fibo[i - 2] + fibo[i - 1];

        n = Integer.parseInt(br.readLine());

        System.out.println(fibo[n]);
    }   //  main-end
}   //  Main-class-end