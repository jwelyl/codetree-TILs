import java.util.*;
import java.util.Map.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static final Map<Integer, Integer> map = new HashMap<>();   //  Key : x 좌표, Value : y 좌표
    
    private static int n = 0;       //  점 개수
    private static long ans = 0L;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            tokens = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(tokens.nextToken());
            int y = Integer.parseInt(tokens.nextToken());

            if(!map.containsKey(x)) //  해당 x 좌표의 점이 없을 경우
                map.put(x, y);  //  해당 점을 넣음
            else {  //  해당 x 좌표의 점이 존재할 경우
                int prevY = map.get(x); //  존재하는 점의 y 좌표

                if(prevY > y)   //  새로 입력받은 y 좌표보다 클 경우
                    map.put(x, y);  //  더 작은 y 값으로 대체
            }
        }

        Set<Entry<Integer, Integer>> entrySet = map.entrySet();

        for(Entry<Integer, Integer> entry : entrySet)
            ans += entry.getValue();

        System.out.println(ans);
    }   //  main-end
}   //  Main-class-end