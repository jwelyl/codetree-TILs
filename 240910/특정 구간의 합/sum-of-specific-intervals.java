import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    private static int N;
    private static int M;

    private static int[] prefixSum;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        prefixSum = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++)
            prefixSum[i] = prefixSum[i - 1] + Integer.parseInt(st.nextToken());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            sb.append(prefixSum[t] - prefixSum[f - 1]).append("\n");
        }

        System.out.print(sb);
    }   //  main-end
}   //  Main-class-end