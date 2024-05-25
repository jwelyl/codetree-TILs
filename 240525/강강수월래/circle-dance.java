import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer tokens;

    //  Key : 노드 번호, Value : 해당 노드
    private static final Map<Integer, Node> nodeMap = new HashMap<>();

    private static int n;   //  학생 수
    private static int m;   //  원의 수
    private static int q;   //  동작 수

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());

        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());
        q = Integer.parseInt(tokens.nextToken());

        for(int i = 0; i < m; i++) {
            tokens = new StringTokenizer(br.readLine());

            int cnt = Integer.parseInt(tokens.nextToken()); //  i번째 CLL에 있는 학생 수
            Node first = null;  //  첫 번째 노드

            if(cnt == 1) {
                int num = Integer.parseInt(tokens.nextToken());

                first = new Node(num);
                first.prev = first;
                first.next = first;

                nodeMap.put(num, first);
            }
            else if(cnt > 1) {
                int num = Integer.parseInt(tokens.nextToken());

                first = new Node(num);
                nodeMap.put(num, first);

                Node last = first;  //  DLL의 마지막 노드

                for(int j = 0; j < cnt - 1; j++) {
                    num = Integer.parseInt(tokens.nextToken());
                    Node cur = new Node(num);
                    nodeMap.put(num, cur);

                    last.next = cur;
                    cur.prev = last;    //  마지막 노드와 현재 노드 연결

                    last = cur; //  마지막 노드 갱신
                }

                first.prev = last;
                last.next = first;    //  첫 번째 노드의 이전 노드를 마지막 노드로, 마지막 노드의 다음 노드를 첫 번째 노드로 CLL 구성
            }
        }

        for(int i = 0; i < q; i++) {
            tokens = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(tokens.nextToken());
            int a = Integer.parseInt(tokens.nextToken());
            int b = (cmd == 3) ? 0 : Integer.parseInt(tokens.nextToken());

            switch(cmd) {
                case 1:
                    operation1(a, b);
                    break;
                case 2:
                    operation2(a, b);
                    break;
                case 3:
                    operation3(a);
                    break;
            }
        }
    }   //  main-end

    //  서로 다른 CLL에 존재하는 a라는 수를 가진 노드와 b라는 수를 가진 노드를 연결해서 두 CLL을 합치기
    private static void operation1(int a, int b) {
        Node nodeA = nodeMap.get(a);
        Node nodeB = nodeMap.get(b);

        Node nextA = nodeA.next;    //  nodeA의 다음 노드
        Node prevB = nodeB.prev;    //  nodeB의 이전 노드

        nodeA.next = nodeB;
        nodeB.prev = nodeA;

        nextA.prev = prevB;
        prevB.next = nextA;
    }

    //  a라는 수를 가진 노드부터 시작해서 b라는 수를 가진 노드 전까지 시계 방향으로 탐색하여 분리해냄
    private static void operation2(int a, int b) {
        Node nodeA = nodeMap.get(a);
        Node nodeB = nodeMap.get(b);

        Node prevA = nodeA.prev;    //  nodeA의 이전 노드
        Node prevB = nodeB.prev;    //  nodeB의 이전 노드

        nodeA.prev = prevB;
        prevB.next = nodeA; //  nodeA와 nodeB의 이전 노드를 연결해서 CLL로 만듬

        prevA.next = nodeB;
        nodeB.prev = prevA; //  nodeB와 nodeA의 이전 노드를 연결해서 CLL로 만듬
    }

    //  a라는 수를 가진 노드가 포함된 CLL에서 번호가 가장 작은 노드를 찾음
    //  해당 노드부터 시계 반대 방향으로 자기 번호 출력하기
    private static void operation3(int a) {
        Node nodeA = nodeMap.get(a);        //  a라는 번호 가진 노드
        int minNum = a;                     //  해당 CLL의 최소 번호
        Node minNode = nodeA;               //  최소 번호를 가지는 노드
        Node cur = nodeA.next;

        while(cur.num != a) {   //  nodeA 다음 노드부터 시계 방향으로 한 바퀴 순회하기
            if(cur.num < minNum) {
                minNum = cur.num;
                minNode = cur;      //  최소 번호를 가지는 노드 갱신
            }
        
            cur = cur.next;
        }

        sb.append(minNum).append(" ");
        
        cur = minNode.prev;

        while(cur.num != minNum) {  //  minNode 이전부터 시계 반대 방향으로 한 바퀴 순회하기
            sb.append(cur.num).append(" ");
            cur = cur.prev; 
        }

        System.out.print(sb);
    }

    private static class Node {
        int num;
        Node prev;  //  시계 반대 방향으로 이전 노드
        Node next;  //  시계 방향으로 다음 노드

        public Node(int num) {
            this.num = num;
            this.prev = null;
            this.next = null;
        }
    }
}   //  Main-class-end