import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static char[] parentheses;

    private static int[] left;
    private static int[] right;

    private static final List<Integer> leftList = new ArrayList<>();
    private static final List<Integer> rightList = new ArrayList<>();

    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        parentheses = br.readLine().toCharArray();

        int len = parentheses.length;

        left = new int[len];
        right = new int[len];

        left[0] = parentheses[0] == '(' ? 1 : 0;
        for(int i = 1; i < len; i++) {
            if(parentheses[i] == '(')
                left[i] = left[i - 1] + 1;

            if(left[i] >= 2)
                leftList.add(i);
        }

        right[len - 1] = parentheses[len - 1] == ')' ? 1 : 0;
        for(int i = len - 2; i >= 0; i--) {
            if(parentheses[i] == ')')
                right[i] = right[i + 1] + 1;

            if(right[i] >= 2)
                rightList.add(i);
        }

        for(int i = 0; i < leftList.size(); i++) {
            int leftPos = leftList.get(i);
            for(int j = 0; j < rightList.size(); j++) {
                int rightPos = rightList.get(j);
            
                if(leftPos < rightPos)
                    ans++;
            }
        }

        System.out.println(ans);
    }   //  main-end
}   //  Main-class-end