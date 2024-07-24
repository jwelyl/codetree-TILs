import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static final int MAX = 4;
	private static final int PMAX = MAX * MAX + 1;
	private static final int EMPTY = 0;		//	도둑말이 없을 경우
	private static final int NONE = -1;
	
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;
	
	private static int maxScore = 0;	//	게임 끝날 때 까지 얻을 수 있는 최대 점수
	
	public static void main(String[] args) throws IOException {
		Piece[] thieves = new Piece[PMAX];				//	thieves[pNum] : pNum번째 도둑말, 죽었을 경우 null
		int[][] map = new int[MAX][MAX];				//	map[y][x] : (y, x) 칸에 존재하는 도둑말 번호, 없을 경우 EMPTY 
		Piece police = new Piece(NONE, 0, 0, NONE);		
		
		for(int r = 0; r < MAX; r++) {
			tokens = new StringTokenizer(br.readLine());
			for(int c = 0; c < MAX; c++) {
				int pNum = Integer.parseInt(tokens.nextToken());
				int dir = Integer.parseInt(tokens.nextToken()) - 1;
				
				thieves[pNum] = new Piece(pNum, r, c, dir);
				map[r][c] = pNum;
			}
		}
		
		int initScore = kill(police, thieves, map);	//	(0, 0)에 존재하는 도둑말 죽이고 시작함
		
		simulation(police, thieves, map, initScore);
		
		System.out.println(maxScore);
	}	//	main-end
	
	private static void simulation(Piece police, Piece[] thieves, int[][] map, int score) {
		Piece[] cThieves = copyThieves(thieves);	//	기존 도둑말 복사
		int[][] cMap = copyMap(map);				//	기존 맵 복사
		
		moveAll(police, cThieves, cMap);			//	모든 도둑말들 이동
		List<int[]> posList = checkEnd(police, cThieves, cMap);	//	현재 술래말이 이동할 수 있는 칸
		
		if(posList.isEmpty()) {	//	술래말이 더 이상 이동할 수 없을 경우
			maxScore = Math.max(maxScore, score);	//	최대 점수 갱신
			return;	//	종료
		}
		
		for(int[] pos : posList) {	//	이동할 수 있는 칸에 대해서
			int ny = pos[0];
			int nx = pos[1];		//	술래말이 이동할 칸
			int nscore = score;		//	이동했을 때의 새 점수
			
			Piece nPolice = copyPolice(police);
			Piece[] nThieves = copyThieves(cThieves);
			int[][] nMap = copyMap(cMap);
			
			nPolice.y = ny;
			nPolice.x = nx;
			
			nscore += kill(nPolice, nThieves, nMap);	//	해당 칸으로 이동해서 도둑말 잡고 점수 얻음
			
			simulation(nPolice, nThieves, nMap, nscore);	//	다음 시뮬레이션
		}
	}
	
	//	police가 있는 칸의 도둑말을 제거하고 점수를 얻음
	private static int kill(Piece police, Piece[] thieves, int[][] map) {
		int y = police.y;
		int x = police.x;
		int pNum = map[y][x];
		
		map[y][x] = EMPTY;					//	map에서 도둑말 제거
		police.dir = thieves[pNum].dir;		//	도둑말의 방향을 술래말의 방향으로 함
		thieves[pNum] = null;				//	(y, x)칸에 있던 도둑말 제거
		
		return pNum;	//	잡은 도둑말의 번호만큼 점수를 얻음
	}
	
	//	기존 술래말을 복사해서 새로운 술래말 만듬
	private static Piece copyPolice(Piece origPolice) {
		return new Piece(origPolice);
	}
	
	//	기존 도둑말 리스트 복사해서 새로운 도둑말 리스트 만듬
	private static Piece[] copyThieves(Piece[] origThieves) {
		Piece[] ret = new Piece[PMAX];
		
		for(int pNum = 1; pNum < PMAX; pNum++) {
			if(origThieves[pNum] != null)
				ret[pNum] = new Piece(origThieves[pNum]);
		}
		
		return ret;
	}
	
	//	기존 맵을 복사해서 새로운 맵을 만듬
	private static int[][] copyMap(int[][] origMap) {
		int[][] ret = new int[MAX][MAX];
		for(int r = 0; r < MAX; r++) {
			for(int c = 0; c < MAX; c++)
				ret[r][c] = origMap[r][c];
		}
		
		return ret;
	}
	
	//	모든 도둑말이 이동함
	private static void moveAll(Piece police, Piece[] thieves, int[][] map) {
		for(int pNum = 1; pNum <= MAX * MAX; pNum++) {	//	번호가 작은 도둑말부터 이동함
			Piece thief = thieves[pNum];
			
			if(thief == null)	//	이미 잡힌 도둑말의 경우
				continue;		//	skip
			
			int cy = thief.y;
			int cx = thief.x;			//	현재 도둑말 위치
			int cdir = thief.dir;		//	현재 도둑말 방향
			
			int ndir = NONE;
			int ny = NONE;
			int nx = NONE;
			
			for(int d = 0; d < 8; d++) {
				int moveDir = (cdir + d) % 8;
				int moveY = cy + dy[moveDir];
				int moveX = cx + dx[moveDir];	//	이동하려는 다음 칸
				
				//	다음 칸이 격자 밖이거나, 술래말이 있을 경우
				if(!isIn(moveY, moveX) || moveY == police.y && moveX == police.x)
					continue;
				else {
					ndir = moveDir;
					ny = moveY;
					nx = moveX;
					break;
				}
			}
			
			if(ndir != NONE && ny != NONE && nx != NONE) {	//	현재 도둑말이 이동할 칸이 있을 경우
				if(map[ny][nx] != EMPTY) {	//	가려는 칸에 다른 도둑말이 있을 경우
					int otherNum = map[ny][nx];				//	다른 도둑말 번호
					Piece other = thieves[map[ny][nx]];		//	다른 도둑말
					
					map[cy][cx] = otherNum;	//	현재 칸에 다른 도둑말 옴
					other.y = cy;
					other.x = cx;	//	현재 칸으로 가려는 칸에 있던 다른 말이 옴			
				}
				else	//	가려는 칸에 다른 도둑말이 없을 경우
					map[cy][cx] = EMPTY;	//	현재 칸 비우기
				
				map[ny][nx] = pNum;	//	가려는 칸에 현재 말이 감
				thief.dir = ndir;
				thief.y = ny;
				thief.x = nx;
			}
		}
	}
	
	//	현재 police 위치에서 이동 가능한지 확인
	//	이동 가능할 경우, 이동 가능한 좌표 리스트로 반환
	//	이동 가능한 좌표가 없을 경우, 빈 리스트 반환
	private static List<int[]> checkEnd(Piece police, Piece[] thieves, int[][] map) {
		List<int[]> posList = new ArrayList<>();
		
		int cy = police.y;
		int cx = police.x;
		int cdir = police.dir;
		
		for(int k = 0; ; k++) {
			int ny = cy + dy[cdir] * k;
			int nx = cx + dx[cdir] * k;
			
			if(!isIn(ny, nx))	//	다음 칸이 격자 밖일 경우, 이 방향으로는 더 이상 확인 불가
				break;
			
			if(map[ny][nx] == EMPTY)	//	다음 칸이 빈 칸일 경우, 그 다음 칸으로 넘어가기
				continue;
			
			posList.add(new int[] {ny, nx});	//	도둑말 있는 칸 넣기
		}
		
		return posList;
	}

	private static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	private static final int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	
	private static boolean isIn(int y, int x) {
		return (0 <= y && y < MAX) && (0 <= x && x < MAX);
	}
	
	private static class Piece {
		public int pNum;	//	말 번호
		public int y;
		public int x;		//	말 좌표
		public int dir;		//	말이 바라보는 방향
		
		public Piece(int pNum, int y, int x, int dir) {
			this.pNum = pNum;
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
		
		public Piece(Piece piece) {
			this(piece.pNum, piece.y, piece.x, piece.dir);
		}
	}
}	//	Main-class-end