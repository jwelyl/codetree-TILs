import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static final StringBuilder sb = new StringBuilder();
  private static StringTokenizer tokens;

  private static int n; //  0부터 n까지의 수
  private static int m; //  m개의 수 제거
  private static final TreeSet<Integer> deletedNums = new TreeSet<>(); //  지워진 수 모음
  private static TreeSet<Segment> segments = new TreeSet<>();  // (길이, 시작 숫자, 끝 숫자)

  public static void main(String[] args) throws IOException {
    tokens = new StringTokenizer(br.readLine());
    n = Integer.parseInt(tokens.nextToken());
    m = Integer.parseInt(tokens.nextToken());

    deletedNums.add(-1);
    deletedNums.add(n + 1); //  null 나오지 않게 처리
    segments.add(new Segment(n + 1, -1, n + 1));

    tokens = new StringTokenizer(br.readLine());
    for(int i = 0; i < m; i++) {
      int delNum = Integer.parseInt(tokens.nextToken());  //  지울 수

      deletedNums.add(delNum);

      int left = deletedNums.lower(delNum);  //  지워진 수들 중 이번에 지워진 수보다 작으면서 가장 큰 수
      int right = deletedNums.higher(delNum); //  지워진 수들 중 이번에 지워진 수보다 크면서 가장 작은 수
      
      segments.remove(new Segment(right - left - 1, left, right));
      segments.add(new Segment(delNum - left - 1, left, delNum));
      segments.add(new Segment(right - delNum - 1, delNum, right));

      // y가 추가된 후로 구간 중 가장 긴 구간을 찾아 출력합니다.
      System.out.println(segments.first().length);
    }
  } // main-end

  private static class Segment implements Comparable<Segment> {
    int length;
    int start;
    int end;

    public Segment(int length, int start, int end) {
      this.length = length;
      this.start = start;
      this.end = end;
    }

    @Override
    public int compareTo(Segment t) {
      if(this.length != t.length)
        return t.length - this.length;  // length 기준 내림차순 정렬
      else if(this.start != t.start)
        return this.start - t.start;      // start 기준 오름차순 정렬
      else
        return this.end - t.end;      // end 기준 오름차순 정렬
    }
  }
} // Main-class-end