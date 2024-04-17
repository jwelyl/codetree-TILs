import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static char[] parentheses;

    private static int[] right;

    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        parentheses = br.readLine().toCharArray();

        int len = parentheses.length;

        right = new int[len];

        right[len - 1] = 0;
        for(int i = len - 2; i >= 0; i--) {
            if(parentheses[i] == ')' && parentheses[i + 1] == ')')
                right[i] = right[i + 1] + 1;
            else
                right[i] = right[i + 1];
        }

        for(int i = 1; i < len; i++) {
            if(parentheses[i - 1] == '(' && parentheses[i] == '(')
                ans += right[i];
        }

        System.out.println(ans);
    }   //  main-end
}   //  Main-class-end