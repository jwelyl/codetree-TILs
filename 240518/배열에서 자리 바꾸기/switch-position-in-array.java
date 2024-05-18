import java.util.*;
import java.io.*;

public class Main {
  private static final int HEAD = -1;                 //  head 더미 노드
  private static final int TAIL = Integer.MAX_VALUE;  //  tail 더미 노드

  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringBuilder sb = new StringBuilder();
  private static StringTokenizer tokens;

  private static int n;   //  노드 개수
  private static int q;   //  쿼리 개수

  private static final Map<Integer, Node> nodeMap = new HashMap<>();  //  Key : 노드 번호, Value : 노드

  public static void main(String[] args) throws IOException {
    n = Integer.parseInt(br.readLine());
    q = Integer.parseInt(br.readLine());

    init();

    for(int i = 0; i < q; i++) {
      tokens = new StringTokenizer(br.readLine());
      swap(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
    }

    print();
  }   //  main-end

  private static void init() {
    Node head = new Node(HEAD);
    Node tail = new Node(TAIL);
    head.next = tail;
    tail.prev = head;

    nodeMap.put(HEAD, head);
    nodeMap.put(TAIL, tail);

    //  1번 노드부터, n번 노드까지 차례로 연결 리스트 가장 뒤에 추가
    for(int i = 1; i <= n; i++) {
      Node input = new Node(i);
      input.next = tail;
      input.prev = tail.prev;
      tail.prev.next = input;
      tail.prev = input;
      nodeMap.put(i, input);
    }
  }

  //  [from1, to1]과 [from2, to2]의 위치 변경
  private static void swap(int from1, int to1, int from2, int to2) {
    Node f1 = nodeMap.get(from1);
    Node t1 = nodeMap.get(to1);

    Node prev1 = f1.prev;
    Node next1 = t1.next;

    Node f2 = nodeMap.get(from2);
    Node t2 = nodeMap.get(to2);

    Node prev2 = f2.prev;
    Node next2 = t2.next;

    if(next1 == f2) { //  첫 번째 부분 연결 리스트 바로 다음이 두 번째 부분 연결 리스트일 경우
      t1.next = next2;
      next2.prev = t1;
      f2.prev = prev1;
      prev1.next = f2;
      f1.prev = t2;
      t2.next = f1;
    }
    else if(next2 == f1) {  //  두 번째 부분 연결 리스트 바로 다음이 첫 번째 부분 연결 리스트일 경우
      t2.next = next1;
      next1.prev = t2;
      f1.prev = prev2;
      prev2.next = f1;
      f2.prev = t1;
      t1.next = f2;
    }
    else {  //  두 부분 연결 리스트가 인접하지 않을 경우
      prev1.next = f2;
      f2.prev = prev1;
      next1.prev = t2;
      t2.next = next1;

      prev2.next = f1;
      f1.prev = prev2;
      next2.prev = t1;
      t1.next = next2;
    }
  }

  private static void print() {
    Node cur = nodeMap.get(HEAD);

    while(cur.next.num != TAIL) {
      sb.append(cur.next.num).append(" ");
      cur = cur.next;
    }

    System.out.println(sb);
  }

  private static class Node {
    final int num;    //  노드 번호
    Node prev;        //  이전 노드
    Node next;        //  다음 노드

    public Node(int num) {
      this.num = num;
      this.prev = null;
      this.next = null;
    }
  }
}   //  Main-class-end