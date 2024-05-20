import java.util.*;
import java.io.*;

public class Main {
  private static final int HEAD = 0;
  private static final int TAIL = Integer.MAX_VALUE;

  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static final StringBuilder sb = new StringBuilder();
  private static StringTokenizer tokens;

  private static Map<Integer, Node> nodeMap = new HashMap<>();    //  Key : 노드 번호, Value : 노드

  private static Node head = new Node(HEAD);
  private static Node tail = new Node(TAIL);

  private static int n;   //  노드 개수
  private static int m;   //  노드 제거 횟수

  public static void main(String[] args) throws IOException {
    init();
    query();

    System.out.print(sb);
  }   //  main-end

  private static void init() throws IOException {
    head.next = tail;
    tail.prev = head;

    nodeMap.put(HEAD, head);
    nodeMap.put(TAIL, tail);

    tokens = new StringTokenizer(br.readLine());
    n = Integer.parseInt(tokens.nextToken());
    m = Integer.parseInt(tokens.nextToken());

    tokens = new StringTokenizer(br.readLine());
    for(int i = 1; i <= n; i++) {
      int num = Integer.parseInt(tokens.nextToken());
      Node node = new Node(num);
      nodeMap.put(num, node);

      Node front = tail.prev; //  현재 가장 마지막 노드
      front.next = node;
      node.prev = front;  //  node와 마지막 노드를 연결

      node.next = tail;
      tail.prev = node;   //  node와 tail을 연결
    }
  }

  private static void query() throws IOException {
    for(int i = 0; i < m; i++)
      delete(Integer.parseInt(br.readLine()));
  }

  private static void delete(int num) {
    Node delNode = nodeMap.get(num);    //  제거할 노드
    Node left = delNode.next.num == TAIL ? head.next : delNode.next;   //  제거할 노드가 가장 뒤의 노드일 경우 가장 처음 노드가 왼쪽 노드
    Node right = delNode.prev.num == HEAD ? tail.prev : delNode.prev;    //  제거할 노드가 가장 앞의 노드일 경우 가장 마지막 노드가 오른쪽 노드

    right.next = left;
    left.prev = right;

    sb.append(right.num).append(" ").append(left.num).append("\n");
  }

  private static class Node {
    int num;    //  기사 번호
    Node prev;
    Node next;

    public Node(int num) {
      this.num = num;
      this.prev = null;
      this.next = null;
    }
  }
}   //  Main-class-end