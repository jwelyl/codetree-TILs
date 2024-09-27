import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Comparator;

public class Main {
//	private static final boolean DEBUG = true;
	private static final boolean DEBUG = false;
	
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int Q;	//	명령어 개수
	private static int N;	//	격자 행 크기
	private static int M;	//	격자 열 크기
	private static int P;	//	토끼 수
	
	private static Rabbit[] rabbits;
	private static final PriorityQueue<Rabbit> minPq = new PriorityQueue<>();
	private static final PriorityQueue<Rabbit> maxPq = new PriorityQueue<>(new Comparator<Rabbit>() {
		@Override
		public int compare(Rabbit o1, Rabbit o2) {
			if(o1.cnt == 0)
				return 1;
			else if(o2.cnt == 0)
				return -1;
			
			int ret = Integer.compare(o2.r + o2.c, o1.r + o1.c);
			
			if(ret == 0) {
				ret = Integer.compare(o2.r, o1.r);
				
				if(ret == 0) {
					ret = Integer.compare(o2.c, o1.c);
					
					if(ret == 0)
						ret = Integer.compare(o2.pid, o1.pid);
				}
			}
			
			return ret;
		}	
	});
	
	//	Key : 토끼 고유번호 pid, Value : 고유번호가 pid인 토끼가 rabbits에 저장된 index
	private static final Map<Integer, Integer> pidToIdxMap = new HashMap<>();
	//	Key : 고유번호가 pid인 토끼가 rabbits에 저장된 index, Value : 토끼 고유번호 pid
	private static final Map<Integer, Integer> idxToPidMap = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		Q = Integer.parseInt(br.readLine());
		
		for(int q = 0; q < Q; q++)
			processCmd();
	}	//	main-end
	
	private static void processCmd() throws IOException {
		st = new StringTokenizer(br.readLine());
		int cmd = Integer.parseInt(st.nextToken());
		
		if(cmd == 100) {
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			
			rabbits = new Rabbit[P];
			
			for(int i = 0; i < P; i++) {
				int pid = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				rabbits[i] = new Rabbit(pid, d);
				minPq.offer(rabbits[i]);
				maxPq.offer(rabbits[i]);
				pidToIdxMap.put(pid, i);
				idxToPidMap.put(i, pid);
			}
		}
		else if(cmd == 200) {
			int k = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < k; i++) {
				Rabbit rabbit = minPq.peek();	//	가장 우선순위가 높은 토끼
				int cpid = rabbit.pid;
				int cidx = pidToIdxMap.get(cpid);
				int cr = rabbit.r;
				int cc = rabbit.c;	//	현재 토끼 위치
				int cd = rabbit.d;	//	현재 토끼가 이동해야 하는 거리
				
				if(DEBUG) {
				System.out.println("k = " + i);
				System.out.println("selected : " + rabbit);
				}
				
				int r = 0;
				int c = 0;

				for(int dir = 0; dir < 4; dir++) {	//	4방향 탐색
					int[] next = nextPos(cr, cc, dir, cd);
					int ny = next[0];
					int nx = next[1];
					
					if(DEBUG) {
					System.out.println("dir = " + dir);
					System.out.println("ny = " + ny);
					System.out.println("nx = " + nx);
					}
					
					if(ny + nx > r + c) {
						r = ny;
						c = nx;
					}
					else if(ny + nx == r + c) {
						if(ny > r) {
							r = ny;
							c = nx;
						}
					}
				}
				
				if(DEBUG) {
				System.out.println("r = " + r);
				System.out.println("c = " + c);
				}
				
				rabbit.r = r;
				rabbit.c = c;	//	토끼 위치 변경
				rabbit.cnt++;	//	토끼 점프횟수 1 증가
				
				for(int p = 0; p < P; p++) {
					if(p != cidx)
						rabbits[p].s += (r + c);
				}
				
				minPq.remove(rabbit);
				minPq.offer(rabbit);
				maxPq.remove(rabbit);
				maxPq.offer(rabbit);
				
				print(i);
			}
			
			maxPq.peek().s += s;
		}
		else if(cmd == 300) {
			int pid = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			
			int idx = pidToIdxMap.get(pid);
			rabbits[idx].d *= l;
		}
		else {
			int maxScore = 0;
			
			for(Rabbit r : rabbits)
				maxScore = Math.max(maxScore, r.s);
			
			System.out.println(maxScore);
		}
		
		print(cmd);
	}
	
	//	(cy, cx) 칸에서 dir 방향으로 len칸 움직였을 때 최종 위치
	private static int[] nextPos(int cy, int cx, int dir, int len) {
		int ny = 0;
		int nx = 0;
		int end = 0;
		
		if(dir == 0 || dir == 2) {	//	좌우로 움직일 경우
			ny = cy;
			end = M;
			
			if(dir == 0) {	//	오른쪽으로 이동할 경우
				if(cx + len > end) {
					len -= (end - cx);
					
					int q = len / (end - 1);
					int r = len % (end - 1);
					
					if(q % 2 == 0)
						nx = end - r;
					else
						nx = 1 + r;
				}
				else nx = cx + len;
			}
			else {	//	왼쪽으로 이동할 경우
				if(cx - len < 1) {
					len -= (cx - 1);
					
					int q = len / (end - 1);
					int r = len % (end - 1);
					
					if(q % 2 == 0)
						nx = 1 + r;
					else
						nx = end - r;
				}
				else nx = cx - len;
			}
		}
		else {	//	상하로 움직일 경우
			nx = cx;
			end = N;
			
			if(dir == 1) {	//	아래쪽으로 이동할 경우
				if(cy + len > end) {
					len -= (end - cy);
					
					int q = len / (end - 1);
					int r = len % (end - 1);
					
					if(q % 2 == 0)
						ny = end - r;
					else
						ny = 1 + r;
					
					if(DEBUG)
					System.out.println("ny!! = " + ny);
				}
				else ny = cy + len;
			}
			else {	//	위쪽으로 이동할 경우
				if(cy - len < 1) {
					len -= (cy - 1);
					
					int q = len / (end - 1);
					int r = len % (end - 1);
					
					if(q % 2 == 0)
						ny = 1 + r;
					else
						ny = end - r;
					}
				else ny = cy - len;
			}
		}
		
		return new int[] {ny, nx};
	}
	
	private static class Rabbit implements Comparable<Rabbit> {
		public final int pid;	//	토끼 고유 번호
		public int d;			//	토끼가 점프로 이동해야 하는 거리
		public int r;			//	토끼 행 위치
		public int c;			//	토끼 열 위치
		public int cnt;			//	토끼 점프 횟수
		public int s;		//	토끼 점수
		
		public Rabbit(int pid, int d) {
			this.pid = pid;
			this.d = d;
			this.r = 1;
			this.c = 1;
			this.cnt = 0;
			this.s = 0;
		}
		
		@Override
		public String toString() {
			return "Rabbit [pid=" + pid + ", d=" + d + ", r=" + r + ", c=" + c + ", cnt=" + cnt + ", s=" + s + "]";
		}

		@Override
		public int compareTo(Rabbit other) {
			int ret = Integer.compare(this.cnt, other.cnt);	//	1. 총 점프 횟수 적은 토끼 우선
			
			if(ret == 0) {	//	1을 만족하는 토끼가 여럿일 경우
				ret = Integer.compare(this.r + this.c, other.r + other.c);	//	2. 행 + 열 값 작은 토끼 우선
				
				if(ret == 0) {	//	1, 2를 만족하는 토끼가 여럿일 경우
					ret = Integer.compare(this.r, other.r);	//	3. 행 값 작은 토끼 우선
					
					if(ret == 0) {	//	1, 2, 3을 만족하는 토끼가 여럿일 경우
						ret = Integer.compare(this.c, other.c);	//	4. 열 값 작은 토끼 우선
						
						if(ret == 0)	//	1, 2, 3, 4를 만족하는 토끼가 여럿일 경우
							ret = Integer.compare(this.pid, other.pid);	//	5. 고유번호 작은 토끼 우선
					}
				}
			}
			
			return ret;
		}
	}
	
	private static void print(int cmd) {
		if(!DEBUG)
			return;
		
		System.out.println("after " + cmd);
		
		System.out.print("rabbits : ");
		for(int i = 0; i < P; i++)
			System.out.print(rabbits[i] + " ");
		System.out.println();
		System.out.println("minPq : " + minPq);
		System.out.println("maxPq : " + maxPq);
	}
}	//	Main-class-end