import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static final int MAX = 4;
	
	private static final int ALIVE = 0;		//	몬스터 활성 상태
	private static final int EGG   = 1;		//	몬스터 알 상태
	private static final int DEAD  = 2;		//	몬스터 시체 상태
	
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;
	
	private static final List<Monster>[][] aliveMap = new ArrayList[MAX][MAX];	//	aliveMap[i][j] : (i, j) 칸에 존재하는 활성 상태 몬스터 리스트
	private static final List<Monster>[][] eggMap = new ArrayList[MAX][MAX];	//	eggMap[i][j] : (i, j) 칸에 존재하는 알 상태 몬스터 리스트
	private static final List<Monster>[][] deadMap = new ArrayList[MAX][MAX];	//	deadMap[i][j] : (i, j) 칸에 존재하는 시체 상태 몬스터 리스트
	
	private static int time = 0;	//	전역 시간, 1턴 당 1 시간 흐름
	
	private static int M;	//	최초 몬스터 수
	private static int T;	//	턴 수
	
	private static final Packman packman = new Packman(0, 0);	//	팩맨
	
	public static void main(String[] args) throws IOException {
		init();
		
		tokens = new StringTokenizer(br.readLine());
		M = Integer.parseInt(tokens.nextToken());
		T = Integer.parseInt(tokens.nextToken());
		
		tokens = new StringTokenizer(br.readLine());
		
		packman.y = Integer.parseInt(tokens.nextToken()) - 1;
		packman.x = Integer.parseInt(tokens.nextToken()) - 1;	//	팩맨 초기 위치 입력
		
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(tokens.nextToken()) - 1;
			int x = Integer.parseInt(tokens.nextToken()) - 1;	//	몬스터 초기 위치
			int dir = Integer.parseInt(tokens.nextToken());		//	몬스터 초기 방향
			
			Monster m = new Monster(y, x, dir);	//	초기 몬스터는 활성 상태
			aliveMap[y][x].add(m);	//	활성 상태 몬스터를 활성 상태 (y, x) 칸에 넣기 
		}
		
		while(time < T) {
			simulation();
			time++;
		}
		
		System.out.println(aliveCnt());
	}	//	main-end
	
	//	alive, egg, dead map 초기화
	private static void init() {
		for(int r = 0; r < MAX; r++) {
			for(int c = 0; c < MAX; c++) {
				aliveMap[r][c] = new ArrayList<>();
				eggMap[r][c] = new ArrayList<>();
				deadMap[r][c] = new ArrayList<>();
			}
		}
	}
	
	private static void simulation() {
		//	1. 몬스터 복제 시도, 알 낳기
		layEggs();
//		printStatus(time + " layEggs");
		//	2. 활성 상태 몬스터 이동하기
		moveAlives();
//		printStatus(time + " moveAlives");
		//	3. 팩맨 이동
		packmanMoves();
//		printStatus(time + " packmanMoves");
		//	4. 시체 처리
		clearDead();
//		printStatus(time + " clearDead");
		//	5. 몬스터 복제 완성, 알 낳기
		hatchEggs();
//		printStatus(time + " hatchEggs");
	}
	
	//	1. 몬스터 알 생성
	private static void layEggs() {
		for(int r = 0; r < MAX; r++) {
			for(int c = 0; c < MAX; c++) {
				for(Monster parent : aliveMap[r][c]) {	//	(r, c) 칸에 활성 상태의 몬스터 각각에 대하여
					Monster child = new Monster(parent);	//	알 상태로 복제
					eggMap[r][c].add(child);	//	복제된 알을 알 상태 (r, c) 칸에 넣기
				}
			}
		}
	}
	
	//	2. 활성 상태 몬스터 이동
	private static void moveAlives() {
		List<Monster>[][] tmp = new ArrayList[MAX][MAX];	//	tmp[r][c] : (r, c)로 이동한 몬스터들 임시로 모아두는 리스트
		for(int r = 0; r < MAX; r++) {
			for(int c = 0; c < MAX; c++)
				tmp[r][c] = new ArrayList<>();
		}
		
		for(int r = 0; r < MAX; r++) {
			for(int c = 0; c < MAX; c++) {
				List<Monster> mList = aliveMap[r][c];	//	(r, c)칸에 존재하는 활성 상태 몬스터 리스트
				
				if(mList.isEmpty())	//	해당 칸에 활성 상태 몬스터 없을 경우
					continue;
				
				for(Monster m : mList) {
					int cy = m.y;
					int cx = m.x;		//	현재 몬스터 위치
					int cdir = m.dir;	//	현재 몬스터 방향
					
					int mdir = -1;	//	이동할 방향
					
					for(int i = 0; i < 8; i++) {
						int ny = cy + dyM[cdir];
						int nx = cx  +dxM[cdir];	//	이동할 칸
						
						//	현재 방향으로 이동할 칸이 범위 밖이거나, 팩맨이 있거나, 시체가 있으면
						if(!isIn(ny, nx) || (ny == packman.y && nx == packman.x) || !deadMap[ny][nx].isEmpty()) {
							cdir = nextDir(cdir);	//	다음 방향으로
							continue;
						}
						
						mdir = cdir;
						break;
					}
					
					if(mdir == -1)//	결국 어떤 방향으로도 이동할 수 없을 경우
						tmp[cy][cx].add(m);	//	제 자리에 그대로 있기
					else {
						int ny = cy + dyM[mdir];
						int nx = cx + dxM[mdir];	//	(ny, nx)칸으로 이동하면 됨
						m.y = ny;
						m.x = nx;
						m.dir = mdir;				//	몬스터가 바라보는 방향 변경
						
						tmp[ny][nx].add(m);	//	(ny, nx)칸으로 이동
					}
				}
			}
		}
		
		for(int r = 0; r < MAX; r++) {
			for(int c = 0; c < MAX; c++)
				aliveMap[r][c] = tmp[r][c];
		}
	}
	
	//	3. 팩맨 이동
	private static void packmanMoves() {
		int cy = packman.y;
		int cx = packman.x;	//	팩맨의 현재 위치
		
		int[] py = new int[3];
		int[] px = new int[3];	//	팩맨이 이동할 세 칸 좌표
		boolean[][] visited = new boolean[MAX][MAX];
		
		int maxEat = -1;	//	팩맨이 최대로 먹을 수 있는 활성 상태 몬스터 수
		
		for(int d0 = 0; d0 < 4; d0++) {	//	첫 번째 이동 방향
			int ny0 = cy + dyP[d0];
			int nx0 = cx + dxP[d0];	//	첫 번째 이동 방향으로 이동한 칸
			
			if(!isIn(ny0, nx0))	//	범위 벗어날 경우
				continue;
			
			int eat0 = 0;
			
			if(!visited[ny0][nx0]) {
				eat0 += aliveMap[ny0][nx0].size();
				visited[ny0][nx0] = true;
			}
			
			for(int d1 = 0; d1 < 4; d1++) {	//	두 번째 이동 방향
				int ny1 = ny0 + dyP[d1];
				int nx1 = nx0 + dxP[d1];	//	두 번째 이동 방향으로 이동한 칸
				
				if(!isIn(ny1, nx1))	//	범위 벗어날 경우
					continue;
				
				int eat1 = eat0;
				
				if(!visited[ny1][nx1]) {
					eat1 += aliveMap[ny1][nx1].size();
					visited[ny1][nx1] = true;
				}
				
				for(int d2 = 0; d2 < 4; d2++) {
					int ny2 = ny1 + dyP[d2];
					int nx2 = nx1 + dxP[d2];	//	세 번째 이동 방향으로 이동한 칸
					
					if(!isIn(ny2, nx2))	//	범위 벗어날 경우
						continue;
					
					int eat2 = eat1;	//	세 번 이동해서 먹을 수 있는 활성 상태 몬스터 수
					
					if(!visited[ny2][nx2]) {
						eat2 += aliveMap[ny2][nx2].size();
						visited[ny2][nx2] = true;
					}
					
					if(eat2 > maxEat) {	//	최대로 먹는 방법 갱신한 경우
//						System.out.println("maxEat = " + maxEat);
//						System.out.println("d0 = " + d0);
//						System.out.println("d1 = " + d1);
//						System.out.println("d2 = " + d2);
//						System.out.println("eat = " + eat3);
						
						maxEat = eat2;
						py[0] = ny0;
						px[0] = nx0;
						py[1] = ny1;
						px[1] = nx1;
						py[2] = ny2;
						px[2] = nx2;
					}
					
					visited[ny2][nx2] = false;
				}
				
				visited[ny1][nx1] = false;
			}
			
			visited[ny0][nx0] = false;
		}
		
		//	팩맨이 이동한 경로에 있는 활성 상태 몬스터 먹기 
		for(int i = 0; i < 3; i++) {
			int y = py[i];
			int x = px[i];	//	i번째 이동한 칸
			
			List<Monster> mList = aliveMap[y][x];
			for(Monster m : mList) {
				m.status = DEAD;		//	시체 상태로 변경
				m.expired = time + 2;	//	2턴 뒤에 제거됨
				deadMap[y][x].add(m);	//	시체 칸으로 이동
			}
			
			mList.clear();
		}
		
		packman.y = py[2];
		packman.x = px[2];	//	팩맨 위치 변경
	}
	
	//	4. 시체 상태 몬스터 제거
	private static void clearDead() {
//		System.out.println("current time = " + time);
		
		for(int r = 0; r < MAX; r++) {
			for(int c = 0; c < MAX; c++) {
				List<Monster> tmp = new ArrayList<>();
				
				for(Monster m : deadMap[r][c]) {	//	(r, c) 칸에 존재하는 시체들 중에서
					if(m.expired > time)	//	아직 사라질 시간이 되지 않은 경우
						tmp.add(m);	//	보존하기
				}
				
				deadMap[r][c] = tmp;
			}
		}
	}
	
	//	5. 몬스터 알 부화
	private static void hatchEggs() {
		for(int r = 0; r < MAX; r++) {
			for(int c = 0; c < MAX; c++) {
				for(Monster egg : eggMap[r][c]) {	//	(r, c) 칸에 알 상태의 몬스터 각각에 대하여
					egg.status = ALIVE;	//	몬스터 활성 상태로 변경
					aliveMap[r][c].add(egg);	//	부화한 몬스터를 활성 상태 (r, c) 칸에 넣기
				}
				
				eggMap[r][c].clear();	//	(r, c) 칸의 모든 알 부화시키기
			}
		}
	}
	
	//	현재 몬스터 방향 dir로 이동할 수 없을 때 다음 이동 방향
	private static int nextDir(int dir) {
		return (1 <= dir && dir <= 7) ? dir + 1 : 1;
	}
	
	private static int aliveCnt() {
		int sum = 0;
		
		for(int r = 0; r < MAX; r++) {
			for(int c = 0; c < MAX; c++)
				sum += aliveMap[r][c].size();
		}
		
		return sum;
	}
	
//	private static void printStatus(String after) {
//		System.out.println("After " + after);
//		System.out.println("packman = (" + packman.y + ", " + packman.x + ")");
//		System.out.println("-----------aliveMap-----------");
//		for(int r = 0; r < MAX; r++) {
//			for(int c = 0; c < MAX; c++)
//				System.out.print(aliveMap[r][c].size() + " ");
//			System.out.println();
//		}
//		System.out.println("-----------eggMap-----------");
//		for(int r = 0; r < MAX; r++) {
//			for(int c = 0; c < MAX; c++)
//				System.out.print(eggMap[r][c].size() + " ");
//			System.out.println();
//		}
//		System.out.println("-----------deadMap-----------");
//		for(int r = 0; r < MAX; r++) {
//			for(int c = 0; c < MAX; c++)
//				System.out.print(deadMap[r][c].size() + " ");
//			System.out.println();
//		}
//	}
	
	private static final int[] dyM = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	private static final int[] dxM = {0, 0, -1, -1, -1, 0, 1, 1, 1};	//	몬스터 이동 방향
	
	private static final int[] dyP = {-1, 0, 1, 0};
	private static final int[] dxP = {0, -1, 0, 1};		//	팩맨 이동 방향
	
	private static boolean isIn(int y, int x) {	//	범위 벗어나는 지 체크
		return (0 <= y && y < MAX) && (0 <= x && x < MAX);
	}
	
	private static class Packman {
		public int y;
		public int x;
		
		public Packman(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	private static class Monster {
		public int y;
		public int x;			//	몬스터 위치
		public int dir;			//	몬스터가 바라보는 방향
		public int status;		//	몬스터 상태
		public int expired;		//	몬스터 상태가 시체일 경우 사라지는 시간
		
		public Monster(int y, int x, int dir) {	//	게임 시작 시 주어지는 몬스터
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.status = ALIVE;
		}
		
		public Monster(Monster m) {	//	몬스터 m을 복제함
			this(m.y, m.x, m.dir);	//	m의 위치와 초기 방향을 그대로 가짐
			this.status = EGG;		//	상태는 알인 상태
		}
	}
}	//	Main-class-end