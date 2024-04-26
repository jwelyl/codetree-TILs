import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static final StringBuilder sb = new StringBuilder();

    private static int q = 0;
    private static int set = 0;

    public static void main(String[] args) throws IOException {
        q = Integer.parseInt(br.readLine());

        for(int i = 0; i < q; i++) {
            tokens = new StringTokenizer(br.readLine());
            String opt = tokens.nextToken();
            int x = -1;
            int key = -1;

            switch(opt) {
            case "add":
                x = Integer.parseInt(tokens.nextToken()) - 1;
                
                key = 1 << x;
                set = set | key;
                
                break;
            case "delete":
                x = Integer.parseInt(tokens.nextToken()) - 1;

                key = 1 << 30 - 1;
                key = key ^ (1 << x);

                set = set & key;

                break;
            case "print":
                x = Integer.parseInt(tokens.nextToken()) - 1;

                key = 1 << x;

                sb.append((set & key) == 0 ? 0 : 1).append("\n"); 
                break;
            case "toggle":
                x = Integer.parseInt(tokens.nextToken()) - 1;

                key = 1 << x;
                set = set ^ key;

                break;
            default:
                set = 0;
            }
        }

        System.out.print(sb);
    }   //  main-end
}   //  Main-class-end