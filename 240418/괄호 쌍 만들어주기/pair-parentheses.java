import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static char[] parentheses;

    private static int[] left;
    private static int[] right;

    private static int leftCnt = 0;
    private static int rightCnt = 0;

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
                leftCnt++;
        }

        right[len - 1] = parentheses[len - 1] == ')' ? 1 : 0;
        for(int i = len - 2; i >= 0; i--) {
            if(parentheses[i] == ')')
                right[i] = right[i + 1] + 1;

            if(right[i] >= 2)
                rightCnt++;
        }

        // for(int i = 0; i < len; i++)
        //     System.out.print(left[i] + " ");
        // System.out.println();
        // for(int i = 0; i < len; i++)
        //     System.out.print(right[i] + " ");
        // System.out.println();

        System.out.println(leftCnt * rightCnt);
    }   //  main-end
}   //  Main-class-end