import java.util.*;
import java.io.*;

public class Main {
    private static final String HEAD = "_HEAD";
    private static final String TAIL = "_TAIL";

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer tokens;

    private static int n;   //  도시 개수
    private static int q;   //  행동 개수

    private static final Node head = new Node(HEAD); //  head dummy node
    private static final Node tail = new Node(TAIL); //  tail dummy node
    private static Node cur = null; // 핀셋 꽂힌 도시
    private static int size = 0;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        q = Integer.parseInt(tokens.nextToken());

        head.next = tail;
        tail.prev = head;
        size = n;

        tokens = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            Node node = new Node(tokens.nextToken());
            Node pos = tail.prev;   //  pos 뒤에 node를 삽입

            pos.next = node;
            node.prev = pos;    //  pos와 node를 연결
            node.next = tail;
            tail.prev = node;   //  node와 tail을 연결
        }

        cur = head.next;

        for(int i = 0; i < q; i++) {
            tokens = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(tokens.nextToken());

            switch(cmd) {
                case 1:
                    operation1();
                    break;
                case 2:
                    operation2();
                    break;
                case 3:
                    operation3();
                    break;
                case 4:
                    String city = tokens.nextToken();
                    operation4(city);
                    break;
            }

            Node left = cur.prev == head ? tail.prev : cur.prev;
            Node right = cur.next == tail ? head.next : cur.next;

            if(left == right || size == 1)
                sb.append("-1\n");
            else
                sb.append(left.city).append(" ").append(right.city).append("\n");
        }

        System.out.print(sb);
    }   //  main-end

    private static void operation1() { 
        if(size == 1)   //  오른쪽 도시가 없을 경우
            return;

        Node right = cur.next == tail ? head.next : cur.next;   //  cur이 가장 마지막 도시면 right는 첫번째 도시
        cur = right;
    }

    private static void operation2() {
        if(size == 1)   //  왼쪽 도시가 없을 경우
            return;

        Node left = cur.prev == head ? tail.prev : cur.prev;    //  cur이 가장 처음 도시면 left는 마지막 도시
        cur = left;
    }

    private static void operation3() {
        if(size == 1)   //  오른쪽 도시가 없을 경우
            return;

        Node right = cur.next == tail ? head.next : cur.next;   //  cur이 가장 마지막 도시면 right는 첫번째 도시
        Node front = right.prev;
        Node back = right.next;

        front.next = back;
        back.prev = front;

        size--;
    }

    private static void operation4(String city) {
        Node insert = new Node(city);

        Node behind = cur.next; //  cur 노드 뒤의 노드
        
        cur.next = insert;
        insert.prev = cur;  //  cur 노드와 insert 노드 연결

        behind.prev = insert;
        insert.next = behind;   //  behind 노드와 insert 노드 연결

        size++;
    }

    private static class Node {
        String city;
        Node prev;
        Node next;

        public Node(String city) {
            this.city = city;
            this.prev = null;
            this.next = null;
        }
    }
}   //  Main-class-end