import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;

    private static final Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        tokens = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            set.add(Integer.parseInt(tokens.nextToken()));

        System.out.println(set.size());
    }   //  main-end
}   //  Main-class-end