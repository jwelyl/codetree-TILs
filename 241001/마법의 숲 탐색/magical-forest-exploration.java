import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int R;
	private static int C;	//	숲 크기 R * C
	private static int K;	//	정령 수
	
	private static int[][] map;	//	map[r][c] : (r, c)를 차지한 골렘 번호, EXIT일 경우, 출구임
	private static boolean[][] exitMap;	//	exitMap[r][c] : (r, c)칸에 출구가 존재하면 true
	
	private static Golem[] golems;	//	golems[i] : i번째 골렘 객체
	
	private static int sum = 0;	//	각 정렬들이 최종적으로 위치한 행의 총합
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[R + 4][C + 1];
		exitMap = new boolean[R + 4][C + 1];
		
		golems = new Golem[K + 1];
		for(int k = 1; k <= K; k++) {
			st = new StringTokenizer(br.readLine());
			int r = 2;
			int c = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			golems[k] = new Golem(k, r, c, dir);
			
			golems[k].move();
		}
		
		System.out.println(sum);
	}	//	main-end
	
	private static final int[] dr = {-1, 0, 1, 0};
	private static final int[] dc = {0, 1, 0, -1};
	
	private static boolean isIn(int r, int c) {
		return (1 <= r && r <= R + 3) && (1 <= c && c <= C);
	}
	
	private static class Golem {
		public final int num;		//	골렘 번호
		public int r;
		public int c;	//	골렘 위치
		public int dir;	//	골렘 방향
		
		public Golem(int num, int r, int c, int dir) {
			this.num = num;
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
		
		public int[] getExit() {
			return new int[] {this.r + dr[this.dir], this.c + dc[this.dir]};
		}
		
		public boolean isIn() {	//	해당 골렘이 숲 안에 있으면 true
			for(int d = 0; d < 4; d++) {
				int sr = r + dr[d];
				int sc = c + dc[d];
				
				if(!(4 <= sr && sr <= R + 3) || !(1 <= sc && sc <= C))
					return false;
			}
			
			return true;
		}

		public void removeAll() {
			for(int r = 1; r <= R + 3; r++) {
				Arrays.fill(map[r], 0);
				Arrays.fill(exitMap[r], false);
			}
		}
		
		public void draw() {
			int[] exit = this.getExit();
			
			map[this.r][this.c] = this.num;
			
			for(int d = 0; d < 4; d++) {
				map[this.r + dr[d]][this.c + dc[d]] = this.num;
				
				if(this.r + dr[d] == exit[0] && this.c + dc[d] == exit[1])
					exitMap[this.r + dr[d]][this.c + dc[d]] = true;
			}
		}
		
		//	1. 남쪽 방향으로 내려갈 수 있는지 확인
		public boolean checkSouth() {
			if(!Main.isIn(r + 1, c - 1) || map[r + 1][c - 1] != 0)
				return false;
			if(!Main.isIn(r + 2, c) || map[r + 2][c] != 0)
				return false;
			if(!Main.isIn(r + 1, c + 1) || map[r + 1][c + 1] != 0)
				return false;

			return true;
		}
		
		//	1. 남쪽으로 이동하기 (dir 변경 없음)
		public void moveSouth() {
			this.r++;	//	중심 좌표 1 증가
		}
		
		//	2. 서쪽 방향으로 이동 후 내려갈 수 있는지 확인
		public boolean checkWest() {
			if(!Main.isIn(r - 1, c - 1) || map[r - 1][c - 1] != 0)
				return false;
			if(!Main.isIn(r, c - 2) || map[r][c - 2] != 0)
				return false;
			if(!Main.isIn(r + 1, c - 2) || map[r + 1][c - 2] != 0)
				return false;
			if(!Main.isIn(r + 1, c - 1) || map[r + 1][c - 1] != 0)
				return false;
			if(!Main.isIn(r + 2, c - 1) || map[r + 2][c - 1] != 0)
				return false;
			
			return true;
		}
		
		//	1. 서쪽으로 이동하고 내려가기 (dir 변경)
		public void moveWest() {
			this.dir = (this.dir == 0) ? 3 : this.dir - 1;
			
			this.r++;
			this.c--;
		}
		
		//		3. 동쪽 방향으로 이동 후 내려갈 수 있는지 확인
		public boolean checkEast() {
			if(!Main.isIn(r - 1, c + 1) || map[r - 1][c + 1] != 0)
				return false;
			if(!Main.isIn(r, c + 2) || map[r][c + 2] != 0)
				return false;
			if(!Main.isIn(r + 1, c + 2) || map[r + 1][c + 2] != 0)
				return false;
			if(!Main.isIn(r + 1, c + 1) || map[r + 1][c + 1] != 0)
				return false;
			if(!Main.isIn(r + 2, c + 1) || map[r + 2][c + 1] != 0)
				return false;
			
			return true;
		}
		
		//	1. 동쪽으로 이동하고 내려가기 (dir 변경)
		public void moveEast() {
			this.dir = (this.dir + 1) % 4;
			
			this.r++;
			this.c++;
		}
		
		public void move() {
			while(true) {
				if(checkSouth()) {		//	1. 남쪽으로 내려갈 수 있으면
					moveSouth();		//	남쪽으로 내려가기
					continue;
				}
				else if(checkWest()) {	//	2. 남쪽으로 내려갈 수 없고, 서쪽으로 내려갈 수 있으면
					moveWest();			//	서쪽으로 내려가기
					continue;
				}
				else if(checkEast()) {	//	3. 서쪽으로도 내려갈 수 없고, 동쪽으로 내려갈 수 있으면
					moveEast();			//	동쪽으로 내려가기
					continue;
				}
				
				break;	//	남, 서, 동 셋 다 불가능하면 이동 종료
			}
			
			if(!this.isIn())	//	만약 이동을 끝냈는데 골렘 일부가 숲 밖에 있으면
				this.removeAll();	//	숲에 있던 모든 골렘 지우기
			else {	//	이동을 끝내고 골렘이 숲 안에 완전히 있으면
				this.draw();	//	골렘을 map에 그리기
				Main.sum += this.bfs();	//	정령을 이동시켰을 때 최종 행 위치 구하기
			}
		}
		
		private int bfs() {
			Queue<int[]> q = new LinkedList<>();
			boolean[][] visited = new boolean[R + 4][C + 1];
			int maxRow = this.r;
			
			visited[this.r][this.c] = true;
			q.offer(new int[] {this.r, this.c, this.num});
			
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				int cr = cur[0];
				int cc = cur[1];
				int cnum = cur[2];
				
				for(int d = 0; d < 4; d++) {
					int nr = cr + dr[d];
					int nc = cc + dc[d];
					
					if(!Main.isIn(nr, nc) || visited[nr][nc])	//	범위 밖이거나 이미 방문한 칸일 경우
						continue;
					
					if(exitMap[cr][cc]) {	//	현재 칸이 출구일 경우
						if(map[nr][nc] != 0) {	//	다음 칸이 빈 칸만 아니면 갈 수 있음
							maxRow = Math.max(maxRow, nr);
							visited[nr][nc] = true;
							q.offer(new int[] {nr, nc, map[nr][nc]});
						}
					}
					else {	//	현재 칸이 출구가 아닐 경우
						if(map[nr][nc] == cnum) {	//	다음 칸이 같은 골렘 칸이거나, 출구일 경우 갈 수 있음
							maxRow = Math.max(maxRow, nr);
							visited[nr][nc] = true;
							q.offer(new int[] {nr, nc, map[nr][nc]});
						}
					}
				}
			}
			
			return maxRow - 3;
		}
	}	//	Golem-class-end
}	//	Main-class-end