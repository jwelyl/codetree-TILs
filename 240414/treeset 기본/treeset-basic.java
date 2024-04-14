import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer tokens;

    private static final TreeSet<Integer> set = new TreeSet<>();

    private static int n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            tokens = new StringTokenizer(br.readLine());

            String cmd = tokens.nextToken();

            if(cmd.equals("add")) {
                int key = Integer.parseInt(tokens.nextToken());

                set.add(key);
            }
            else if(cmd.equals("remove")) {
                int key = Integer.parseInt(tokens.nextToken());

                set.remove(key);
            }
            else if(cmd.equals("find")) {
                int key = Integer.parseInt(tokens.nextToken());

                sb.append(set.contains(key)).append("\n");
            }
            else if(cmd.equals("lower_bound")) {
                int x = Integer.parseInt(tokens.nextToken());

                Integer lowerBound = set.ceiling(x);

                if(lowerBound == null)
                    sb.append("None\n");
                else
                    sb.append(lowerBound).append("\n");
            }
            else if(cmd.equals("upper_bound")) {
                int x = Integer.parseInt(tokens.nextToken());

                Integer upperBound = set.higher(x);

                if(upperBound == null)
                    sb.append("None\n");
                else
                    sb.append(upperBound).append("\n");
            }
            else if(cmd.equals("largest")) {
                if(set.isEmpty())
                    sb.append("None\n");
                else
                    sb.append(set.last()).append("\n");
            }
            else if(cmd.equals("smallest")) {
                if(set.isEmpty())
                    sb.append("None\n");
                else
                    sb.append(set.first()).append("\n");
            }
            
        }
    
        System.out.print(sb);
    }
}