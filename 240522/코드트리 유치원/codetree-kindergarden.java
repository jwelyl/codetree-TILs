import java.util.*;
import java.io.*;

public class Main {
  private static final int HEAD = 0;
  private static final int TAIL = Integer.MAX_VALUE;

  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static final StringBuilder sb = new StringBuilder();
  private static StringTokenizer tokens;

  private static final Map<Integer, Node> nodeMap = new HashMap<>();
  private static final Node head = new Node(HEAD);
  private static final Node tail = new Node(TAIL);

  private static int q;   //  행동 수
  private static int nextNum = 2; //  다음에 서야 하는 학생 번호

  public static void main(String[] args) throws IOException {
    init();

    q = Integer.parseInt(br.readLine());

    for(int i = 0; i < q; i++) {
      tokens = new StringTokenizer(br.readLine());

      int cmd = Integer.parseInt(tokens.nextToken());
      int a = Integer.parseInt(tokens.nextToken());
      int b = cmd != 3 ? Integer.parseInt(tokens.nextToken()) : 0;

      switch(cmd) {
        case 1:
          pushBack(a, b);
          break;
        case 2:
          pushFront(a, b);
          break;
        case 3:
          print(a);
          break;
      }
    }

    System.out.print(sb);
  }   //  main-end

  private static void init() {
    Node first = new Node(1);

    nodeMap.put(HEAD, head);
    nodeMap.put(TAIL, tail);
    nodeMap.put(1, first);

    head.next = first;
    first.prev = head;  //  1번 노드와 head 연결

    tail.prev = first;
    first.next = tail;  //  1번 노드와 tail 연결
  }

  //  pos 뒤에 cnt개의 노드 삽입
  private static void pushBack(int pos, int cnt) {
    Node posNode = nodeMap.get(pos);    //  삽입할 위치 노드
    Node back = posNode.next;           //  삽입할 위치 노드 뒤의 노드

    Node first = new Node(nextNum);
    nodeMap.put(nextNum++, first);

    Node last = first;

    //  삽입할 노드들을 연결 리스트로 만듬
    for(int i = 0; i < cnt - 1; i++) {  //  first 노드 제외 cnt - 1개 더 삽입해야 함
      Node input = new Node(nextNum);
      nodeMap.put(nextNum++, input);

      last.next = input;
      input.prev = last;
      last = input;
    }

    posNode.next = first;
    first.prev = posNode;   //  삽입할 노드 리스트의 첫번째 노드와 삽입할 위치 노드 연결
    last.next = back;
    back.prev = last;       //  삽입할 노드 리스트의 마지막 노드와 삽입할 위치 노드의 뒤의 노드 연결
  }

  //  pos 앞에 cnt개의 노드 삽입
  private static void pushFront(int pos, int cnt) {
    Node posNode = nodeMap.get(pos);    //  삽입할 위치 노드
    Node front = posNode.prev;           //  삽입할 위치 노드 앞의 노드

    Node first = new Node(nextNum);
    nodeMap.put(nextNum++, first);

    Node last = first;

    //  삽입할 노드들을 연결 리스트로 만듬
    for(int i = 0; i < cnt - 1; i++) {  //  first 노드 제외 cnt - 1개 더 삽입해야 함
      Node input = new Node(nextNum);
      nodeMap.put(nextNum++, input);

      last.next = input;
      input.prev = last;
      last = input;
    }

    posNode.prev = last;
    last.next = posNode;      //  삽입할 노드 리스트의 마지막 노드와 삽입할 위치 노드 연결
    first.prev = front;
    front.next = first;       //  삽입할 노드 리스트의 첫번째 노드와 삽입할 위치 노드의 의 노드 연결
  }

  //  pos 위치 앞, 뒤 노드 번호 출력
  private static void print(int pos) {
    Node posNode = nodeMap.get(pos);
    Node prev = posNode.prev;
    Node next = posNode.next;
    
    sb.append(prev == head || next == tail ? -1 : String.format("%d %d", prev.num, next.num)).append("\n");
  }

  private static class Node {
    int num;
    Node prev;
    Node next;

    public Node(int num) {
      this.num = num;
      this.prev = null;
      this.next = null;
    }
  }
}   //  Main-class-end