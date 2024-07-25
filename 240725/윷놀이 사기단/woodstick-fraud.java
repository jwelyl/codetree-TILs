import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int END = -1;	//	도착했을 경우
	
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;
	
	//	board[i] : i번째 칸에 멈췄을 때 얻을 수 있는 점수
	//	0 : 경로 1 시작 칸
	//	5 : 경로 2로 방향 전환 칸 -> 24
	//	10 : 경로 3으로 방향 전환 칸 -> 33
	//	15 : 경로 4로 방향 전환 칸 -> 41
	//	23 : 경로 1 종료 칸
	//	32 : 경로 2 종료 칸
	//	40 : 경로 3 종료 칸
	//	49 : 경로 4 종료 칸
	private static final int[] board = {
		//	경로 1. 0 ~ 23
		0, 2, 4, 6, 8, 0, 12, 14, 16, 18, 0, 22, 24, 26, 28, 0, 28, 27, 26, 25, 30, 35, 40, 0,
		//	경로 2. 24 ~ 32
		10, 13, 16, 19, 25, 30, 35, 40, 0,
		//	경로 3. 33 ~ 40
		20, 22, 24, 25, 30, 35, 40, 0,
		//	경로 4. 41 ~ 49
		30, 28, 27, 26, 25, 30, 35, 40, 0 
	};
	
	private static int maxScore = 0;	//	최대로 얻을 수 있는 점수
	
	private static final int[] yut = new int[10];
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		for(int i = 0; i < 10; i++)
			yut[i] = Integer.parseInt(tokens.nextToken());
		
		int[] pos = new int[4];	//	4개 말의 현재 위치
		boolean[] visited = new boolean[50];
		
		simulation(0, pos, visited, 0);
		
		System.out.println(maxScore);
	}	//	main-end
	
	private static void simulation(int nth, int[] pos, boolean[] visited, int score) {
		if(nth == 10) {	//	10번 모두 던졌을 경우
			maxScore = Math.max(maxScore, score);
			return;
		}
		
		if(isEnd(pos)) {
			maxScore = Math.max(maxScore, score);
			return;
		}
		
		for(int pNum = 0; pNum < 4; pNum++) {	//	pNum번째 말을 이동한다고 가정
			int curPos = pos[pNum];	//	pNum번째 말의 현재 위치
			
			if(curPos == END)	//	pNum번째 말이 이미 도착점에 도달한 말일 경우, 이동 불가
				continue;
				
			int nextPos = nextPos(curPos, yut[nth]);	//	현재 말이 이동할 다음 칸
			int nscore = score;			//	이동 후 점수
			int[] npos = copyPos(pos);	//	이동 후 말들의 위치
			
			if(nextPos != END) {	//	다음 칸이 도착점이 아닐 경우 
				if(visited[nextPos])	//	다음 칸에 이미 다른 말이 있을 경우, pNum번째 말을 던질 수 없음
					continue;
				
				nscore += board[nextPos];	//	pNum번째 말이 이동하여 점수 추가
				npos[pNum] = nextPos;		//	말들 위치 갱신
				visited[curPos] = false;	//	현재 칸에서 이동
				visited[nextPos] = true;	//	다음 칸 방문 처리
				
				simulation(nth + 1, npos, visited, nscore);	//	다음 윳 던지기
				
				visited[nextPos] = false;	//	방문처리 취소
				visited[curPos] = true;		//	원상복귀
			}
			else {	//	다음 칸이 도착점일 경우
				npos[pNum] = END;	//	다음 던지기에서는 pNum번째 말 못 움직이게 처리
				
				visited[curPos] = false;	//	현재 칸에서 이동
				simulation(nth + 1, npos, visited, nscore);
				visited[curPos] = true;	//	원상복귀
			}
		}
	}
	
	private static boolean isEnd(int[] pos) {
		int cnt = 0;
		for(int pNum = 0; pNum < 4; pNum++)
			if(pos[pNum] == END)
				cnt++;
		
		return cnt == 4;
	}
	
	private static int[] copyPos(int[] pos) {
		int[] ret = new int[4];
		for(int i = 0; i < 4; i++)
			ret[i] = pos[i];
		
		return ret;
	}
	
	//	현재 위치가 curPos이고 eyes의 칸이 나왔을 때 다음 위치, 도착점에 도달한 경우 END  반환
	private static int nextPos(int curPos, int eyes) {
		if(0 <= curPos && curPos < 23) {	//	경로 1에 위치할 경우
			if(curPos + eyes == 5)	//	경로 2로 꺾어야 할 경우
				return 24;
			else if(curPos + eyes == 10)	//	경로 3으로 꺾어야 할 경우
				return 33;
			else if(curPos + eyes == 15)	//	경로 4로 꺾어야 할 경우
				return 41;
			else if(curPos + eyes >= 23)		//	도착점에 도달한 경우
				return END;
		}
		else if(24 <= curPos && curPos < 32) {	//	경로 2에 위치할 경우
			if(curPos + eyes >= 32)	//	도착점에 도달한 경우
				return END;
		}
		else if(33 <= curPos && curPos < 40) {	//	경로 3에 위치할 경우
			if(curPos + eyes >= 40)	//	도착점에 도달한 경우
				return END;
		}
		else if(41 <= curPos && curPos < 49) {	//	경로 4에 위치할 경우
			if(curPos + eyes >= 49)	//	도착점에 도달한 경우
				return END;
		}
		
		return curPos + eyes;
	}
}	//	Main-class-end