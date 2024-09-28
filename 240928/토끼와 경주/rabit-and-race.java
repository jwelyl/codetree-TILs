import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	private static final boolean DEBUG = false;
//	private static final boolean DEBUG = true;
	
	private static final int INIT = 100;		//	경주 시작 준비
	private static final int RACE = 200;		//	경주 진행
	private static final int MODIFY = 300;		//	이동거리 변경
	private static final int RESULT = 400;		//	최고의 토끼 선정
	
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int Q;	//	명령어 개수
	
	//	INIT
	private static int N;	//	격자 행 개수
	private static int M;	//	격자 열 개수
	private static int P;	//	토끼 마릿수
	
	//	RACE
	private static int K;	//	경주 당 턴 수
	private static int S;	//	추가 점수
	
	//	MODIFY
	private static int PID;	//	이동거리 변경할 토끼 고유번호
	private static int L;	//	해당 토끼 현재 이동거리에 곱할 값
	
	private static Rabbit[] rabbitArr;	// rabbitArr[i] : i번째 토끼
	private static final PriorityQueue<Rabbit> pq = new PriorityQueue<>();
	
	//	Key : PID, Value : pid 값이 PID인 토끼가 rabbitArr에 저장된 인덱스 idx
	private static final Map<Integer, Integer> pidToIdxMap = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		Q = Integer.parseInt(br.readLine());
		
		for(int q = 1; q <= Q; q++)
			processCommand();
	}	//	main-end
	
	private static void processCommand() throws IOException {
		st = new StringTokenizer(br.readLine());
		
		int cmd = Integer.parseInt(st.nextToken());
		
		switch(cmd) {
		case INIT:
			init();
			print("after init");
			break;
		case RACE:
			race();
			print("after race");
			break;
		case MODIFY:
			modify();
			print("after modify");
			break;
		case RESULT:
			result();
			break;
		}
	}
	
	private static void init() {
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		rabbitArr = new Rabbit[P + 1];
		for(int idx = 1; idx <= P; idx++) {
			int pid = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			Rabbit rabbit = new Rabbit(pid,d, idx);
			
			pidToIdxMap.put(pid, idx);	//	고유번호가 pid인 토끼 rabbit은 rabbitArr의 index idx에 저장
			rabbitArr[idx] = rabbit;		//	rabbitArr에 저장
			pq.offer(rabbit);			//	우선순위 큐에도 저장
		}
	}
	
	private static void race() {
		K = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		//	exceptions[k][0/1] : {k번째 턴에 점수 합에서 제외되는 토끼 idx / 제외해야 할 점수} 
		int[][] exceptions = new int[K + 1][2];
		int sum = 0;	//	매 턴에 추가되는 점수 합
		Set<Integer> selectedIdxSet = new HashSet<>();	//	선택되어 뛴 적 있는 토끼 index
		
		for(int k = 1; k <= K; k++) {
			Rabbit rabbit = pq.poll();	//	이번 턴에 뛸 토끼
			int curDist = rabbit.d;		//	이번 턴에 뛸 토끼가 뛰어야 하는 거리
			int curIdx = rabbit.idx;	//	이번 턴에 뛸 토끼의 index
			int curRow = rabbit.r;
			int curCol = rabbit.c;	//	이번 턴에 뛸 토끼의 초기 위치
			
			if(DEBUG) {
				System.out.println("selected rabbit = " + rabbit);
			}
			
			int lastRow = 0;
			int lastCol = 0;	//	이번 턴에 뛸 토끼가 최종적으로 위치할 행, 열
			
			for(int dir = 0; dir < 4; dir++) {
				int[] nextPos = getNextPos(curRow, curCol, curDist, dir);
				int nextRow = nextPos[0];
				int nextCol = nextPos[1];	//	dir 방향으로 점프했을 때의 최종 위치
				
				if(nextRow + nextCol > lastRow + lastCol) {
					lastRow = nextRow;
					lastCol = nextCol;
				}
				else if((nextRow + nextCol == lastRow + lastCol) && (nextRow > lastRow)) {
					lastRow = nextRow;
					lastCol = nextCol;
				}
			}
			
			rabbit.r = lastRow;
			rabbit.c = lastCol;	//	이번 턴에서 뛴 토끼 위치 최종적으로 확정
			rabbit.jumpCnt++;	//	이번 턴에서 뛴 토끼 점프 횟수 1 증가
			
			pq.offer(rabbit);	//	다시 우선순위큐 삽입
			
			sum += (lastRow + lastCol);	//	이번 턴에서 rabbit을 제외한 토끼들이 얻을 점수
			exceptions[k][0] = curIdx;	//	이번 턴에서 점수 추가에 제외되는 rabbit의 index
			exceptions[k][1] = lastRow + lastCol;	//	이번 턴에서 점수 추가에 제외되는 rabbit의 제외 점수
			
			selectedIdxSet.add(curIdx);
			
			print("after " + k + "th jump");
		}
		
		//	일단 모든 토끼에게 각 턴에서 발생한 모든 점수를 더함
		for(int idx = 1; idx <= P; idx++)
			rabbitArr[idx].score += sum;
		
		for(int k = 1; k <= K; k++) {
			int[] exception = exceptions[k];
			int idx = exception[0];		//	제외될 토끼 idx
			int score = exception[1];	//	제외할 점수
			
			rabbitArr[idx].score -= score;
		}
		
		addExtraScore(S, selectedIdxSet);
	}
	
	private static int[] getNextPos(int curRow, int curCol, int jumpDist, int dir) {
		int nextRow = 0;
		int nextCol = 0;
		int len = (dir == 0 || dir == 1) ? M - 1 : N - 1;
		
		if(dir == 0) {	//	오른쪽으로 이동할 경우
			nextRow = curRow;	//	행 변화는 없음
			
			if(curCol + jumpDist <= M)	//	오른쪽으로만 jumpDist만큼 갔을 때 격자를 벗어나지 않는 경우
				nextCol = curCol + jumpDist;
			else {	//	오른쪽으로만 jumpDist만큼 갔을 때 격자를 벗어나는 경우
				jumpDist -= (M - curCol);	//	오른쪽 끝까지 가고 남은 이동 수
				
				int q = jumpDist / len;
				int r = jumpDist % len;
				
				if(q % 2 == 0)	//	jumpDist 중 q * len만큼 뛰어서 오른쪽 끝에 도착한 경우 
					nextCol = M - r;	//	오른쪽 끝에서 r만큼 왼쪽으로 뛰면 됨
				else			//	jumpDist 중 q * len만큼 뛰어서 왼쪽 끝에 도착한 경우 
					nextCol = 1 + r;	//	왼쪽 끝에서 r만큼 오른쪽으로 뛰면 됨
			}
		}
		else if(dir == 1) {	//	왼쪽으로 이동할 경우
			nextRow = curRow;	//	행 변화는 없음
			
			if(curCol - jumpDist >= 1)	//	왼쪽으로만 jumpDist만큼 갔을 때 격자를 벗어나지 않는 경우
				nextCol = curCol - jumpDist;
			else {	//	왼쪽으로만 jumpDist만큼 갔을 때 격자를 벗어나는 경우
				jumpDist -= (curCol - 1);	//	왼쪽 끝까지 가고 남은 이동 수
				
				int q = jumpDist / len;
				int r = jumpDist % len;
				
				if(q % 2 == 0)	//	jumpDist 중 q * len만큼 뛰어서 왼쪽 끝에 도착한 경우 
					nextCol = 1 + r;	//	왼쪽 끝에서 r만큼 오른쪽으로 뛰면 됨
				else			//	jumpDist 중 q * len만큼 뛰어서 오른쪽 끝에 도착한 경우 
					nextCol = M - r;	//	오른쪽 끝에서 r만큼 왼쪽으로 뛰면 됨
			}
		}
		else if(dir == 2) {	//	아래쪽으로 이동할 경우
			nextCol = curCol;	//	열 변화는 없음
			
			if(curRow + jumpDist <= N)	//	아래쪽으로만 jumpDist만큼 갔을 때 격자를 벗어나지 않는 경우
				nextRow = curRow + jumpDist;
			else {	//	아래쪽으로만 jumpDist만큼 갔을 때 격자를 벗어나는 경우
				jumpDist -= (N - curRow);	//	아래쪽 끝까지 가고 남은 이동 수
				
				int q = jumpDist / len;
				int r = jumpDist % len;
				
				if(q % 2 == 0)	//	jumpDist 중 q * len만큼 뛰어서 아래쪽 끝에 도착한 경우 
					nextRow = N - r;	//	아래쪽 끝에서 r만큼 위쪽으로 뛰면 됨
				else			//	jumpDist 중 q * len만큼 뛰어서 위쪽 끝에 도착한 경우 
					nextRow = 1 + r;	//	위쪽 끝에서 r만큼 아래쪽으로 뛰면 됨
			}
		}
		else {	//	위쪽으로 이동할 경우
			nextCol = curCol;	//	열 변화는 없음
			
			if(curRow - jumpDist >= N)	//	위쪽으로만 jumpDist만큼 갔을 때 격자를 벗어나지 않는 경우
				nextRow = curRow - jumpDist;
			else {	//	위쪽으로만 jumpDist만큼 갔을 때 격자를 벗어나는 경우
				jumpDist -= (curRow - 1);	//	위쪽 끝까지 가고 남은 이동 수
				
				int q = jumpDist / len;
				int r = jumpDist % len;
				
				if(q % 2 == 0)	//	jumpDist 중 q * len만큼 뛰어서 위쪽 끝에 도착한 경우 
					nextRow = 1 + r;	//	위쪽 끝에서 r만큼 아래쪽으로 뛰면 됨
				else			//	jumpDist 중 q * len만큼 뛰어서 아래쪽 끝에 도착한 경우 
					nextRow = N - r;	//	아래쪽 끝에서 r만큼 위쪽으로 뛰면 됨
			}
		}
		
		return new int[] {nextRow, nextCol};
	}
	
	private static void addExtraScore(int add, Set<Integer> selectedIdxSet) {
		//		K 턴 모두 끝나고 추가 점수 S 받을 토끼 찾기
		int sIdx = 0;	//	추가 점수 받을 토끼의 index
		int sRow = 0;
		int sCol = 0;	//	추가 점수 받을 토끼의 행, 열 위치
		int sPid = 0;	//	추가 점수 받을 토끼의 고유번호
		
		for(int idx = 1; idx <= P; idx++) {
			if(!selectedIdxSet.contains(idx))	//	해당 index 토끼가 지난 K턴 동안 한번도 선택 받은 적 없을 경우
				continue;
			
			int r = rabbitArr[idx].r;
			int c = rabbitArr[idx].c;
			int pid = rabbitArr[idx].pid;
			
			if(r + c > sRow + sCol) {	//	1. 행 + 열 값 큰 토끼
				sIdx = idx;
				sRow = r;
				sCol = c;
				sPid = pid;
			}
			else if(r + c == sRow + sCol) {	//	1을 만족하는 토끼가 여럿일 경우
				if(r > sRow) {	//	2. 행 값 큰 토끼
					sIdx = idx;
					sRow = r;
					sCol = c;
					sPid = pid;
				}
				else if(r == sRow && sPid > pid) {	//	1, 2를 만족하는 토끼가 여럿일 경우, 3. pid 값 큰 토끼
					sIdx = idx;
					sPid = pid;
				}
			}
		}
		
		rabbitArr[sIdx].score += add;
	}
	
	private static void modify() {
		PID = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
	
		int idx = pidToIdxMap.get(PID);	//	고유번호가 PID인 토끼의 rabbitArr에서의 위치
		rabbitArr[idx].d *= L;	//	해당 토끼 이동해야 하는 거리 L배
	}
	
	private static void result() {
		long maxScore = 0;
		
		for(int idx = 1; idx <= P; idx++) {
			Rabbit rabbit = rabbitArr[idx];
			
			if(rabbit.score > maxScore)
				maxScore = rabbit.score;
		}
		
		System.out.println(maxScore);
	}
	
	private static class Rabbit implements Comparable<Rabbit> {
		public int pid;		//	토끼 고유번호
		public int d;		//	토끼가 뛰어야 하는 칸의 수
		public int idx;
		public int r;
		public int c;		//	토끼의 현재 위치
		
		public int jumpCnt;	//	토끼가 현재까지 점프한 횟수
		public long score;	//	해당 토끼의 점수
		
		public Rabbit(int pid, int d, int idx) {
			this.pid = pid;
			this.d = d;
			this.idx = idx;
			this.r = 1;
			this.c = 1;	//	모든 토끼는 초기에 (1, 1)에 위치
			
			this.jumpCnt = 0;
			this.score = 0;
		}
		
		@Override
		public int compareTo(Rabbit other) {
			//	1. 총 점프횟수가 적은 토끼
			int ret = Integer.compare(this.jumpCnt, other.jumpCnt);
		
			if(ret == 0) {	//	1을 만족하는 토끼가 여럿일 경우
				//	2. 위치한 행 + 열 번호가 작은 토끼
				ret = Integer.compare(this.r + this.c, other.r + other.c);
				
				if(ret == 0) {	//	1, 2를 만족하는 토끼가 여럿일 경우
					//	3. 위치한 행 번호가 작은 토끼
					ret = Integer.compare(this.r, other.r);
					
					if(ret == 0)	//	1, 2, 3을 만족하는 토끼가 여럿일 경우
						//	4. 고유번호가 작은 토끼
						ret = Integer.compare(this.pid, other.pid);
				}
				
			}
			
			return ret;
		}

		@Override
		public String toString() {
			return "Rabbit [pid=" + pid + ", d=" + d + ", idx=" + idx + ", r=" + r + ", c=" + c + ", jumpCnt=" + jumpCnt
					+ ", score=" + score + "]";
		}
	}
	
	private static void print(String msg) {
		if(DEBUG) {
			System.out.println(msg);
			
			System.out.print("rabbitArr : ");
			for(int idx = 1; idx <= P; idx++)
				System.out.print(rabbitArr[idx] + " ");
			
			System.out.println("\npq : " + pq);
		}
	}
}	//	Main-class-end