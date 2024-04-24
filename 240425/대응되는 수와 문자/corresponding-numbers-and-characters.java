import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer tokens;
    
    private static int n;   //  숫자 개수
    private static int m;   //  조사 개수

    private static final Map<String, Integer> map1 = new HashMap<>();   //  K : 문자열, V : 숫자
    private static final Map<Integer, String> map2 = new HashMap<>();   //  K : 숫자, V : 문자열

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());

        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());

        for(int i = 1; i <= n; i++) {
            String str = br.readLine();
            map1.put(str, i);
            map2.put(i, str);
        }

        for(int i = 1; i <= m; i++) {
            String str = br.readLine();
            if('0' <= str.charAt(0) && str.charAt(0) <= '9')  //  숫자일 경우
                sb.append(map2.get(Integer.parseInt(str))).append("\n");
            else    //  문자열일 경우
                sb.append(map1.get(str)).append("\n");
        }

        System.out.print(sb);
    }   //  main-end
}   //  Main-class-end