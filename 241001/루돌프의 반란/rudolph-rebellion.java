import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	private static int N;	//	게임판 크기
	private static int M;	//	게임 턴 수
	private static int P;	//	산타 수
	private static int C;	//	루돌프 힘
	private static int D;	//	산타의 힘
	
	private static int ry = 0;
	private static int rx = 0;	//	루돌프 위치
	
	private static int[][] santaMap;	//	santaMap[r][c] : (r, c)칸에 존재한느 산타 번호
	
	private static Santa[] santas;
	private static int deadCnt = 0;	//	죽은 산타 수
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		ry = Integer.parseInt(st.nextToken());
		rx = Integer.parseInt(st.nextToken());
		
		santaMap = new int[N + 1][N + 1];
		santas = new Santa[P + 1];
		
		for(int p = 1; p <= P; p++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			int sx = Integer.parseInt(st.nextToken());
			
			santaMap[sy][sx] = num;
			santas[num] = new Santa(sy, sx);
		}
		
		for(int time = 1; time <= M; time++) {
			rudolphMove(time);
			if(isEnd())
				break;
			santaMove(time);
			if(isEnd())
				break;
			addScores();
		}
		
		for(int p = 1; p <= P; p++)
			sb.append(santas[p].score).append(" ");
		
		System.out.print(sb);
	}	//	main-end
	
	private static void rudolphMove(int time) {
		int minDist = Integer.MAX_VALUE;	//	가장 가까운 산타까지 거리
		int maxR = 0;
		int maxC = 0;		//	산타 좌표
		Santa santa = null;	//	가장 가까운 산타
		
		for(int p = 1; p <= P; p++) {
			santa = santas[p];	//	p번째 산타
			
			if(!santa.isAlive())	//	이미 죽은 산타면 skip
				continue;
			
			int r = santa.y;
			int c = santa.x;	//	산타 좌표
			
			int dist = dist(ry, rx, r, c);
			
			if(dist < minDist) {	//	1. 해당 산타가 가장 가까울 경우
				minDist = dist;
				maxR = r;
				maxC = c;
			}
			else if(dist == minDist) {	//	1을 만족하는 산타가 여럿일 경우
				if(r > maxR) {	//	2. 행이 더 큰 산타 선택
					maxR = r;
					maxC = c;
				}
				else if(r == maxR && c > maxC)	//	1, 2를 만족하는 산타가 여럿일 경우, 열이 더 큰 산타 선택
					maxC = c;
			}
		}
		
		minDist = Integer.MAX_VALUE;
		int nry = 0;
		int nrx = 0;	//	루돌프가 이동할 칸
		int dir = 0;
		
		for(int d = 0; d < 8; d++) {
			int ny = ry + dy[d];
			int nx = rx + dx[d];
			
			if(!isIn(ny, nx))	//	범위를 벗어난 경우
				continue;
			
			int dist = dist(ny, nx, maxR, maxC);
			
			if(dist < minDist) {
				minDist = dist;
				nry = ny;
				nrx = nx;
				dir = d;
			}
		}
		
		int num = santaMap[nry][nrx];
		
		if(num != 0 && santas[num].isAlive())
			collision(num, C, dir, time);	//	루돌프가 이동해서 num 산타와 충돌
		
		ry = nry;
		rx = nrx;	//	루돌프 위치 갱신
	}
	
	private static void santaMove(int time) {
		for(int p = 1; p <= P; p++) {
			Santa santa = santas[p];
			
			//	이미 죽거나 기절한 산타는 이동하지 않음
			if(!santa.isAlive() || santa.stunned >= time)
				continue;
			
			int cy = santa.y;
			int cx = santa.x;
			
			int minDist = dist(cy, cx, ry, rx);	//	현재 루돌프까지의 거리
			int minY = -1;
			int minX = -1;	//	산타가 이동할 칸
			int dir = -1;	//	산타가 이동할 방향
			
			for(int d = 0; d < 4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				
				if(!isIn(ny, nx) || (santaMap[ny][nx] != 0 && santas[santaMap[ny][nx]].isAlive()))	//	범위를 벗어나거나 다른 산타가 있을 경우
					continue;
				
				int dist = dist(ny, nx, ry, rx);
				
				if(dist < minDist) {	//	거리가 더 가까워질 경우
					minDist = dist;
					minY = ny;
					minX = nx;
					dir = d;
				}
			}
			
			if(minY != -1 && minX != -1) {	//	산타가 이동할 수 있을 경우
				santa.y = minY;
				santa.x = minX;							//	산타 좌표 재설정
				santaMap[cy][cx] = 0;					//	산타 원래 있던 칸에서 지우기
				santaMap[santa.y][santa.x] = p;			//	산타 새로 이동할 칸에 그리기
				
				if(santa.y == ry && santa.x == rx)	//	루돌프가 있는 칸일 경우
					collision(p, D, reverseDir(dir), time);	//	p 산타가 이동해서 루돌프와 충돌
			}
		}
	}
	
	private static void collision(int num, int add, int dir, int time) {
		santas[num].score += add;		//	점수 추가
		santas[num].stunned = time + 1;	//	time에 루돌프와 충돌하여 time + 1까지 기절 상태
	
		int cy = santas[num].y;
		int cx = santas[num].x;			//	산타 현재 위치
		int ny = cy + dy[dir] * add;
		int nx = cx + dx[dir] * add;	//	산타가 날아갈 위치
		
		santas[num].y = ny;
		santas[num].x = nx;	//	해당 방향으로 날아감
		
		santaMap[cy][cx] = 0;	//	기존에 있던 칸에서 산타 지우기
		if(!isIn(ny, nx)) {	//	범위를 벗어날 경우
			santas[num].dead = true;	//	해당 산타 죽이기
			return;
		}
		
		int nnum = santaMap[ny][nx];	//	(ny, nx)칸 번호
		santaMap[ny][nx] = num;	//	해당 칸에 현재 산타가 자리잡음
		
		if(nnum != 0)	//	해당 칸에 산타가 있을 경우
			interaction(nnum, dir);	//	상호작용 발생
 	}
	
	private static void interaction(int num, int dir) {
		Santa santa = santas[num];	//	dir 방향으로 한 칸 밀려나게 된 산타
		int cy = santa.y;
		int cx = santa.x;	//	해당 산타 원래 위치
		
		int ny = cy + dy[dir];
		int nx = cx + dx[dir];	//	해당 산타가 밀려날 칸
		
		santa.y = ny;
		santa.x = nx;
		
		if(!isIn(ny, nx)) {	//	밀려난 칸이 범위 밖일 경우
			santa.dead = true;	//	해당 산타는 죽음
			return;
		}
		
		int nnum = santaMap[ny][nx];	//	밀려난 칸에 원래 있던 산타
		
		santaMap[ny][nx] = num;	//	해당 산타는 밀려난 칸으로 이동
		
		if(nnum != 0)	//	밀려난 칸에 원래 산타가 있을 경우
			interaction(nnum, dir);	//	그 산타도 밀려남
	}
	
	private static void addScores() {
		for(int p = 1; p <= P; p++) {
			if(santas[p].isAlive())
				santas[p].score++;
		}
	}
	
	private static boolean isEnd() {
		return deadCnt == P;
	}
	
	private static int reverseDir(int dir) {
		if(dir == 0 || dir == 2)
			return 2 - dir;
		return 4 - dir;
	}
	
//	private static final int[] dy = {-1, 1, 0, 0, -1, 1, 1, -1};
//	private static final int[] dx = {0, 0, -1, 1, 1, 1, -1, -1};
	
	private static final int[] dy = {-1, 0, 1, 0, -1, 1, 1, -1};
	private static final int[] dx = {0, 1, 0, -1, 1, 1, -1, -1};
	
	private static boolean isIn(int y, int x) {
		return (1 <= y && y <= N) && (1 <= x && x <= N);
	}
	
	private static int dist(int y1, int x1, int y2, int x2) {
		return (y1 - y2) * (y1 - y2) + (x1 - x2) * (x1 - x2);
	}
	
	private static class Santa {
		public int y;
		public int x;			//	산타 위치
		public int score;		//	해당 산타 점수
		public boolean dead;	//	산타가 죽었으면 true
		public int stunned;		//	기절 유효 시간 (k에 기절했다면 k + 1까지 유효)
		
		public Santa(int y, int x) {
			this.y = y;
			this.x = x;
			this.score = 0;
			this.dead = false;
			this.stunned = -1;
		}
		
		public boolean isAlive() {
			return !this.dead;
		}
	}
}	//	Main-class-end