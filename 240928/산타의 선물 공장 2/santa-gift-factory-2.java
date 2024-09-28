import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int DUMMY = -1;		//	HEAD, TAIL DUMMY node
	
	private static final int INIT = 100;		//	공장 설립
	private static final int TRANSFER = 200;	//	물건 모두 옮기기
	private static final int SWAP = 300;		//	앞 물건만 교체하기
	private static final int DIVIDE = 400;		//	물건 나누기
	private static final int PRESENT = 500;		//	선물 정보 얻기
	private static final int BELT = 600;		//	벨트 정보 얻기
	
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	private static int Q;	//	명령어 개수
	
	private static int N;	//	벨트 개수
	private static int M;	//	선물 개수
	
	private static Node[] heads;	//	heads[i] : i번째 벨트의 head
	private static Node[] tails;	//	tails[i] : i번째 벨트의 tail
	private static int[] cnts;		//	cnts[i] : i번째 벨트에 존재하는 선물 개수
	
	private static Node[] presents;	//	presents[i] : pNum = i인 선물의 Node 객체
	
	public static void main(String[] args) throws IOException {
		Q = Integer.parseInt(br.readLine());
		
		for(int q = 1; q <= Q; q++)
			processCommand();
		
		System.out.print(sb);
	}	//	main-end
	
	private static void processCommand() throws IOException {
		st = new StringTokenizer(br.readLine());
		
		int cmd = Integer.parseInt(st.nextToken());
		
		switch(cmd) {
		case INIT:
			init();
			break;
		case TRANSFER:
			transfer();
			break;
		case SWAP:
			swap();
			break;
		case DIVIDE:
			divide();
			break;
		case PRESENT:
			present();
			break;
		case BELT:
			belt();
			break;
		}
	}
	
	private static void init() {
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		initBelt();
		
		presents = new Node[M + 1];
		
		for(int pNum = 1; pNum <= M; pNum++) {
			Node node = new Node(pNum);
			int bNum = Integer.parseInt(st.nextToken());
			
			presents[pNum] = node;
			pushBack(bNum, node);
		}
	}
	
	private static void initBelt() {
		cnts = new int[N + 1];
		heads = new Node[N + 1];
		tails = new Node[N + 1];
		
		for(int bNum = 1; bNum <= N; bNum++) {
			heads[bNum] = new Node(DUMMY);
			tails[bNum] = new Node(DUMMY);
			
			heads[bNum].next = tails[bNum];
			tails[bNum].prev = heads[bNum];
		}
	}
	
	//	bNum번째 벨트의 가장 마지막에 node를 삽입
	private static void pushBack(int bNum, Node node) {
		Node last = tails[bNum].prev;
		
		last.next = node;
		tails[bNum].prev = node;
		node.prev = last;
		node.next = tails[bNum];
		
		cnts[bNum]++;
	}
	
	//	bNum번째 벨트의 가장 처음에 node를 삽입
	private static void pushFront(int bNum, Node node) {
		Node first = heads[bNum].next;
		
		first.prev = node;
		heads[bNum].next = node;
		node.prev = heads[bNum];
		node.next = first;
		
		cnts[bNum]++;
	}
	
	private static void transfer() {
		int mSrc = Integer.parseInt(st.nextToken());
		int mDst = Integer.parseInt(st.nextToken());
		
		if(cnts[mSrc] != 0) {
			Node srcFront = heads[mSrc].next;
			Node srcBack = tails[mSrc].prev;
			
			Node dstFront = heads[mDst].next;
			
			heads[mDst].next = srcFront;
			srcFront.prev = heads[mDst];
			
			dstFront.prev = srcBack;
			srcBack.next = dstFront;
			
			heads[mSrc].next = tails[mSrc];
			tails[mSrc].prev = heads[mSrc];
			
			cnts[mDst] += cnts[mSrc];
			cnts[mSrc] = 0;
		}
		
		sb.append(cnts[mDst]).append("\n");
	}
	
	private static Node popFront(int bNum) {
		if(cnts[bNum] == 0)
			return null;
		
		Node front = heads[bNum].next;
		Node nFront = front.next;
		
		heads[bNum].next = nFront;
		nFront.prev = heads[bNum];
		cnts[bNum]--;
		
		return front;
	}
	
	private static void swap() {
		int mSrc = Integer.parseInt(st.nextToken());
		int mDst = Integer.parseInt(st.nextToken());
		
		Node srcFront = popFront(mSrc);
		Node dstFront = popFront(mDst);
		
		if(srcFront != null)
			pushFront(mDst, srcFront);
		if(dstFront != null)
			pushFront(mSrc, dstFront);
		
		sb.append(cnts[mDst]).append("\n");
	}
	
	private static void divide() {
		int mSrc = Integer.parseInt(st.nextToken());
		int mDst = Integer.parseInt(st.nextToken());
		
		int cnt = cnts[mSrc] / 2;	//	mSrc 벨트에서 옮길 개수
		
		Node first = heads[mSrc].next;
		Node last = heads[mSrc];
		
		for(int i = 0; i < cnt; i++)
			last = last.next;
		
		Node nLast = last.next;
		heads[mSrc].next = nLast;
		nLast.prev = heads[mSrc];
		
		if(last.pNum != DUMMY) {	//	옮길 선물이 있는 경우
			Node dstFront = heads[mDst].next;
			
			heads[mDst].next = first;
			first.prev = heads[mDst];
			
			last.next = dstFront;
			dstFront.prev = last;
		}
		
		cnts[mSrc] -= cnt;
		cnts[mDst] += cnt;
		
		sb.append(cnts[mDst]).append("\n");
	}
	
	private static void present() {
		int pNum = Integer.parseInt(st.nextToken());
		
		Node node = presents[pNum];
		
		Node prev = node.prev;
		Node next = node.next;
		
		int a = prev.pNum;
		int b = next.pNum;
		
		sb.append(a + 2 * b).append("\n");
	}
	
	private static void belt() {
		int bNum = Integer.parseInt(st.nextToken());
		
		Node front = heads[bNum].next;
		Node back = tails[bNum].prev;
		
		int a = front.pNum;
		int b = back.pNum;
		int c = cnts[bNum];
		
		sb.append(a + 2 * b + 3 * c).append("\n");
	}
	
	private static class Node {
		public final int pNum;	//	선물 번호 (1 ~ M)
		public Node prev;
		public Node next;
		
		public Node(int pNum) {
			this.pNum = pNum;
			this.prev = null;
			this.next = null;
		}
	}
}	//	Main-class-end