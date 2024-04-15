import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer tokens;

    private static int n;   //  연산 개수

    public static void main(String[] args) throws IOException {
        Node cur = new Node(br.readLine());

        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            tokens = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(tokens.nextToken());

            if(cmd == 1)
                cur.insertPrev(tokens.nextToken());
            else if(cmd == 2)
                cur.insertNext(tokens.nextToken());   
            else if(cmd == 3) {
                Node prev = cur.prev;

                if(prev != null)
                    cur = prev;
            }
            else {
                Node next = cur.next;

                if(next != null)
                    cur = next;
            }

            cur.print();
        }

        System.out.print(sb);
    }   //  main-end

    private static class Node {
        String element;

        Node prev;
        Node next;

        public Node(String element) {
            this.element = element;
            this.prev = null;
            this.next = null;
        }

        public void insertPrev(String element) {
            Node node = new Node(element);
            
            node.next = this;
            node.prev = this.prev;

            if(this.prev != null)
                this.prev.next = node;
            this.prev = node;
        }

        public void insertNext(String element) {
            Node node = new Node(element);
            
            node.prev = this;
            node.next = this.next;

            if(this.next != null)
                this.next.prev = node;
            this.next = node;
        }

        public void print() {
            if(this.prev == null)
                sb.append("(Null) ");
            else
                sb.append(this.prev.element).append(" ");
            
            sb.append(this.element).append(" ");

            if(this.next == null)
                sb.append("(Null) ");
            else
                sb.append(this.next.element).append(" ");
            
            sb.append("\n");
        }
    }    
}   //  Main-class-end