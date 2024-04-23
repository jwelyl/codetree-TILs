import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;   //  보석 개수
    private static int m;   //  가방 무게

    private static final List<Gem> gemList = new ArrayList<>();

    private static double maxValue = 0.0;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());

        for(int i = 0; i < n; i++) {
            tokens = new StringTokenizer(br.readLine());

            gemList.add(new Gem(Integer.parseInt(tokens.nextToken()), Double.parseDouble(tokens.nextToken())));
        }

        Collections.sort(gemList);

        for(Gem gem : gemList) {
            int w = gem.w;
            double v = gem.v;
            double partial = v / w;

            if(w < m) {
                maxValue += v;
                m -= w;
            } else {
                maxValue += (v / w) * m;
                break;
            }
        }

        System.out.printf("%.3f\n", maxValue);
    }   //  main-end

    private static class Gem implements Comparable<Gem> {
        int w;
        double v;

        public Gem(int w, double v) {
            this.w = w;
            this.v = v;
        }

        @Override
        public int compareTo(Gem gem) {
            return Double.compare(gem.v / gem.w, this.v / this.w);
        }
    }
}   //  Main-class-end