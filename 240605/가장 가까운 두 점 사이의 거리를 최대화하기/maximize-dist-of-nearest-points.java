import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;
    private static final List<Segment> segments = new ArrayList<>();

    private static int left = Integer.MAX_VALUE;
    private static int right = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            tokens = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(tokens.nextToken());
            int end = Integer.parseInt(tokens.nextToken());

            left = Math.min(left, start);
            right = Math.max(right, end);

            segments.add(new Segment(start, end));
        }

        Collections.sort(segments); //  선분 정렬

        System.out.println(parametricSearch());
    }   //  main-end

    private static int parametricSearch() {
        int start = 0;
        int end = right - left;
        int ret = 0;

        while(start <= end) {
            int mid = (start + end) / 2;    //  각 선분 위의 한 개씩 위치한 점들 사이의 거리를 최소 mid가 되게 놓을 수 있나 체크

            if(canPut(mid)) {   //  최소 거리 mid를 유지하며 놓을 수 있을 경우
                ret = mid;
                start = mid + 1;    //  최소 거리를 증가시켰을 때 놓을 수 있나 확인
            }
            else end = mid - 1; //  최소 거리를 더 줄여야 함
        }

        return ret;
    }

    //  각 점들 사이의 거리를 최소 dist로 유지할 수 있으면 true
    private static boolean canPut(int dist) {
        int pos = segments.get(0).start;    //  가능한 선분의 가장 왼쪽에 놓는 것이 좋음

        for(int i = 1; i < n; i++) {
            Segment segment = segments.get(i);
            int start = segment.start;  //  다음 선분 시작점
            int end = segment.end;      //  다음 선분 끝점

            int nextPos = pos + dist;   //  최소 간격 유지하며 다음 점을 놓을 수 있는 가장 작은 좌표
            if(nextPos > end)   //  다음 점을 놓을 수 있는 가장 작은 좌표가 다음 선분보다 오른쪽에 있을 경우
                return false;   //  최소 거리 dist를 유지하며 점을 놓을 수 없음

            if(nextPos < start)    //  가장 작은 좌표가 다음 선분 이전에 있을 경우
                nextPos = start;   //  다음 선분의 시작점에 놓기

            pos = nextPos; 
        }

        return true;
    }

    private static class Segment implements Comparable<Segment> {
        int start;
        int end;

        public Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Segment segment) {
            int ret = Integer.compare(this.start, segment.start);   //  시작점이 작은 선분이 앞으로 오도록 정렬

            if(ret == 0)
                ret = Integer.compare(this.end, segment.end);   //  시작점이 같을 경우 끝 점이 작은 선분이 앞으로 오도록 정렬

            return ret;
        }
    }
}   //  Main-class-end