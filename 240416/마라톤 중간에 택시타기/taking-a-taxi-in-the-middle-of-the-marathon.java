import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;   //  체크 포인트 개수

    private static int[] posX;  //  체크 포인트 x 좌표
    private static int[] posY;  //  체크 포인트 y 좌표

    private static int[] leftX;     //  left x값 저장 배열
    private static int[] leftY;     //  left y값 저장 배열
    private static int[] rightX;    //  right x값 저장 배열
    private static int[] rightY;    //  right y값 저장 배열

    private static int ans = Integer.MAX_VALUE; //  완주 최소 거리

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        posX = new int[n + 1];
        posY = new int[n + 1];

        leftX = new int[n + 1];
        leftY = new int[n + 1];
        rightX = new int[n + 1];
        rightY = new int[n + 1];

        for(int i = 0; i < n; i++) {
            tokens = new StringTokenizer(br.readLine());
            posX[i + 1] = Integer.parseInt(tokens.nextToken());
            posY[i + 1] = Integer.parseInt(tokens.nextToken());
        }

        for(int i = 1; i < n; i++) {
            leftX[i + 1] = leftX[i] + Math.abs(posX[i + 1] - posX[i]);
            leftY[i + 1] = leftY[i] + Math.abs(posY[i + 1] - posY[i]);
        }

        for(int i = n - 1; i >= 1; i--) {
            rightX[i] = rightX[i + 1] + Math.abs(posX[i] - posX[i + 1]);
            rightY[i] = rightY[i + 1] + Math.abs(posY[i] - posY[i + 1]);
        }

        // System.out.print("leftX = ");
        // for(int i = 1; i <= n; i++)
        //     System.out.print(leftX[i] + " ");
        // System.out.println();
        // System.out.print("rightX = ");
        // for(int i = 1; i <= n; i++)
        //     System.out.print(rightX[i] + " ");
        // System.out.println();

        // System.out.print("leftY = ");
        // for(int i = 1; i <= n; i++)
        //     System.out.print(leftY[i] + " ");
        // System.out.println();
        // System.out.print("rightY = ");
        // for(int i = 1; i <= n; i++)
        //     System.out.print(rightY[i] + " ");
        // System.out.println();


        for(int i = 2; i < n; i++)
            ans = Math.min(ans, leftX[i - 1] + Math.abs(posX[i + 1] - posX[i - 1]) + rightX[i + 1] + leftY[i - 1] + Math.abs(posY[i + 1] - posY[i - 1]) + rightY[i + 1]);
        
        System.out.println(ans);
    }   //  main-end
}   //  Main-class-end