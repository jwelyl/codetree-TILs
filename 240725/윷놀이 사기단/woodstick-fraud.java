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
	//	5 : 경로 2로 방향 전환 칸 -> 22
	//	10 : 경로 3으로 방향 전환 칸 -> 31
	//	15 : 경로 4로 방향 전환 칸 -> 39
	//	21 : 경로 1 종료 칸
	//	30 : 경로 2 종료 칸
	//	38 : 경로 3 종료 칸
	//	47 : 경로 4 종료 칸
	private static final int[] board = {
		//	경로 1. 0 ~ 21
		0, 2, 4, 6, 8, 0, 12, 14, 16, 18, 0, 22, 24, 26, 28, 0, 32, 34, 36, 38, 40, 0,
		//	경로 2. 22 ~ 30
		10, 13, 16, 19, 25, 30, 35, 40, 0,
		//	경로 3. 31 ~ 38
		20, 22, 24, 25, 30, 35, 40, 0,
		//	경로 4. 39 ~ 47
		30, 28, 27, 26, 25, 30, 35, 40, 0 
	};	
	
	private static final int[] pos = new int[4];
	private static final boolean[] visited = new boolean[48];
	
	private static int maxScore = 0;	//	최대로 얻을 수 있는 점수
	
	private static final int[] yut = new int[10];
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		for(int i = 0; i < 10; i++)
			yut[i] = Integer.parseInt(tokens.nextToken());
		
		simulation(0, 0);
		
		System.out.println(maxScore);
	}	//	main-end
	
	private static void simulation(int nth, int score) {
		if(nth == 10) {	//	10번 모두 던졌을 경우
			maxScore = Math.max(maxScore, score);
			return;
		}
		
		for(int pNum = 0; pNum < 4; pNum++) {	//	pNum번째 말을 이동한다고 가정
			int curPos = pos[pNum];	//	pNum번째 말의 현재 위치
			
			if(curPos == END)	//	pNum번째 말이 이미 도착점에 도달한 말일 경우, 이동 불가
				continue;
				
			int nextPos = nextPos(curPos, yut[nth]);	//	현재 말이 이동할 다음 칸
			int nscore = score;			//	이동 후 점수
			
			if(nextPos != END) {	//	다음 칸이 도착점이 아닐 경우 
				if(visited[nextPos])	//	다음 칸에 이미 다른 말이 있을 경우, pNum번째 말을 던질 수 없음
					continue;
				
				nscore += board[nextPos];	//	pNum번째 말이 이동하여 점수 추가
				pos[pNum] = nextPos;		//	말들 위치 갱신
				setVisited(curPos, false);	//	현재 칸에서 이동
				setVisited(nextPos, true);	//	다음 칸 방문 처리
				
				simulation(nth + 1, nscore);	//	다음 윳 던지기
				
				setVisited(nextPos,false);	//	방문처리 취소
				setVisited(curPos, true);	//	원상복귀
				pos[pNum] = curPos;
			}
			else {	//	다음 칸이 도착점일 경우
				pos[pNum] = END;	//	다음 던지기에서는 pNum번째 말 못 움직이게 처리
				
				setVisited(curPos, false);	//	현재 칸에서 이동
				simulation(nth + 1, nscore);
				setVisited(curPos, true);	//	원상복귀
				
				pos[pNum] = curPos;
			}
		}
	}
	
	private static void setVisited(int pos, boolean status) {
		//	경로 2, 3, 4 모두 지나는 칸 방문 상태 변경
		if(pos == 26 || pos == 34 || pos == 43) {
			visited[26] = status;
			visited[34] = status;
			visited[43] = status;
		}
		else if(pos == 27 || pos == 35 || pos == 44) {
			visited[27] = status;
			visited[35] = status;
			visited[44] = status;
		}
		else if(pos == 28 || pos == 36 || pos == 45) {
			visited[28] = status;
			visited[36] = status;
			visited[45] = status;
		}
		//	경로 1, 2, 3, 4 모두 지나는 칸 방문 상태 변경
		else if(pos == 20 || pos == 29 || pos == 37 || pos == 46) {
			visited[20] = status;
			visited[29] = status;
			visited[37] = status;
			visited[46] = status;
		}
		else visited[pos] = status;	//	그 외 칸 방문 상태 변경 
	}
	
	//	현재 위치가 curPos이고 eyes의 칸이 나왔을 때 다음 위치, 도착점에 도달한 경우 END  반환
	private static int nextPos(int curPos, int eyes) {
		if(0 <= curPos && curPos < 21) {	//	경로 1에 위치할 경우
			if(curPos + eyes == 5)	//	경로 2로 꺾어야 할 경우
				return 22;
			else if(curPos + eyes == 10)	//	경로 3으로 꺾어야 할 경우
				return 31;
			else if(curPos + eyes == 15)	//	경로 4로 꺾어야 할 경우
				return 39;
			else if(curPos + eyes >= 21)		//	도착점에 도달한 경우
				return END;
		}
		else if(22 <= curPos && curPos < 30) {	//	경로 2에 위치할 경우
			if(curPos + eyes >= 30)	//	도착점에 도달한 경우
				return END;
		}
		else if(31 <= curPos && curPos < 38) {	//	경로 3에 위치할 경우
			if(curPos + eyes >= 38)	//	도착점에 도달한 경우
				return END;
		}
		else if(39 <= curPos && curPos < 47) {	//	경로 4에 위치할 경우
			if(curPos + eyes >= 47)	//	도착점에 도달한 경우
				return END;
		}
		
		return curPos + eyes;
	}
}	//	Main-class-end