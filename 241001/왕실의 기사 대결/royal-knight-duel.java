import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	private static final boolean DEBUG = false;
//	private static final boolean DEBUG = true;
	
	private static final int EMPTY = 0;
	private static final int TRAP = 1;
	private static final int WALL = 2;
	
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int L;	//	체스판 크기 L * L
	private static int N;	//	기사 수
	private static int Q;	//	쿼리 수
	
	private static int[][] map;				//	맵 정보
	private static int[][] knightMap;		//	knightMap[r][c] : (r, c)칸을 점유한 기사 번호
	
	private static Knight[] knights;	//	knights[i] : i번째 기사 객체
	private static int[] initialHp;		//	initialHp[i] : i번째 기사의 최초 체력
	
	private static int ans = 0;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		map = new int[L + 2][L + 2];
		knightMap = new int[L + 2][L + 2];
		for(int r = 0; r <= L + 1; r++)
			Arrays.fill(map[r], WALL);
		
		for(int r = 1; r <= L; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1; c <= L; c++)
				map[r][c] = Integer.parseInt(st.nextToken());
		}
		
		knights = new Knight[N + 1];
		initialHp = new int[N + 1];
		for(int num = 1; num <= N; num++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
		
			knights[num] = new Knight(r, c, h, w, k);
			initialHp[num] = k;
		}
		
		drawKnight();
		print("after init\n");
		
		for(int q = 1; q <= Q; q++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());	//	움직일 기사 번호
			int dir = Integer.parseInt(st.nextToken());	//	움직일 방향
			
			Set<Integer> movedKnights = new HashSet<>();	//	움직여야 하는 기사 번호 집합
			if(knights[num].isAlive()) {	//	해당 기사가 아직 살아있을 경우
				if(checkMove(num, dir, movedKnights)) {		//	해당 기사를 이동시킬 수 있을 경우
					if(DEBUG)
						System.out.println("movedKnights = " + movedKnights);
					
					for(int movedKnightNum : movedKnights)
						move(movedKnightNum, dir, num == movedKnightNum);	//	이동시킴
					drawKnight();
				}
			}
			
			print("after " + q + "th query");
		}
		
		for(int num = 1; num <= N; num++) {
			if(DEBUG)
			System.out.println(num + "th knight = " + knights[num]);
			
			if(knights[num].isAlive())	//	살아있는 기사의 경우
				ans += initialHp[num] - knights[num].hp;	//	받은 데미지를 더함
		}
		
		System.out.println(ans);
	}	//	main-end
	
	private static void move(int num, int dir, boolean moved) {
		Knight knight = knights[num];
		int row = knight.row;
		int col = knight.col;
		final int h = knight.h;
		final int w = knight.w;
		
		int nRow = row + dy[dir];
		int nCol = col + dx[dir];
		
		int minus = 0;	//	num번째 기사가 이동했을 때 새로 차지하는 h * w칸에 존재하는 함정 개수
		
		for(int r = nRow; r < nRow + h; r++) {
			for(int c = nCol; c < nCol + w; c++) {
				if(map[r][c] == TRAP)
					minus++;
			}
		}
		
		if(DEBUG) {
			System.out.println("knight " + num);
			System.out.println("minums = " + minus);
		}
		
		knight.row = nRow;
		knight.col = nCol;
		if(!moved)	//	직접 이동한게 아니라 밀린 경우
			knight.hp -= minus;
	}
	
	private static boolean checkMove(int num, int dir, Set<Integer> movedKnightSet) {
		if(DEBUG)
		System.out.println(String.format("\n\ncheckMove(%d, %d)", num, dir));
		
		Knight knight = knights[num];
		int row = knight.row;
		int col = knight.col;
		final int h = knight.h;
		final int w = knight.w;
		
		if(DEBUG) {
			System.out.println("knight = " + knight);
		}
		
		movedKnightSet.add(num);	//	이동시켜야 할 기사 번호에 추가
		
		int[][] nextPosArr = null;	//	num번째 기사가 움직이면서 새로 차지하게 될 칸들의 배열
		
		if(dir == 0) {	//	위쪽으로 이동할 경우
			nextPosArr = new int[w][2];
			for(int i = 0; i < w; i++) {
				nextPosArr[i][0] = row - 1;
				nextPosArr[i][1] = col + i;
			}
			
		}
		else if(dir == 1) {
			nextPosArr = new int[h][2];
			for(int i = 0; i < h; i++) {
				nextPosArr[i][0] = row + i;
				nextPosArr[i][1] = col + w;
			}
				
		}
		else if(dir == 2) {	//	아래쪽으로 이동할 경우
			if(DEBUG) {
				System.out.println("w = " + w);
			}
			
			nextPosArr = new int[w][2];
			for(int i = 0; i < w; i++) {
				nextPosArr[i][0] = row + h;
				nextPosArr[i][1] = col + i;
			}
		}
		else {
			nextPosArr = new int[h][2];
			for(int i = 0; i < h; i++) {
				nextPosArr[i][0] = row + i;
				nextPosArr[i][1] = col - 1;
			}
		}
		
		if(DEBUG) {
			System.out.print("nextPosArr : ");
			for(int i = 0; i < nextPosArr.length; i++)
				System.out.print("[" + nextPosArr[i][0] + ", " + nextPosArr[i][1] + "] ");
			System.out.println();
		}
		
		for(int i = 0; i < nextPosArr.length; i++) {
			int y = nextPosArr[i][0];
			int x = nextPosArr[i][1];	//	현재 기사를 이동시켰을 때 차지하게 될 칸 (y, x)
			
			if(map[y][x] == WALL)	//	벽에 가로막힐 경우
				return false;	//	기사를 밀 수 없음
			
			if(knightMap[y][x] != 0) {	//	다른 기사가 있을 경우
				if(DEBUG) {
					System.out.println("other kinght = " + knightMap[y][x]);
				}
				
				if(!checkMove(knightMap[y][x], dir, movedKnightSet))	//	다른 기사를 밀 수 없을 경우
					return false;
			}
		}
		
		if(DEBUG)
			System.out.println("true");
		return true;
	}
	
	private static void drawKnight() {
		for(int r = 1; r <= L; r++)
			for(int c = 1; c <= L; c++)
				knightMap[r][c] = 0;
		
		for(int num = 1; num <= N; num++)
			drawKnight(num);
	}
	
	//	num번째 기사를 기사 배치도에 배치함
	private static void drawKnight(int num) {
		if(knights[num].isAlive()) {	//	해당 기사가 살아있을 경우
			int row = knights[num].row;
			int col = knights[num].col;
			int h = knights[num].h;
			int w = knights[num].w;
			
			for(int r = row; r < row + h; r++) {
				for(int c = col; c < col + w; c++)
					knightMap[r][c] = num;
			}
		}
	}
	
	private static void print(String msg) {
		if(DEBUG) {
			System.out.println(msg);
			
			System.out.println("knightMap");
			for(int r = 1; r <= L; r++) {
				for(int c = 1; c <= L; c++)
					System.out.print(knightMap[r][c] + " ");
				System.out.println();
			}
			
			System.out.println("\nknights");
			for(int num = 1; num <= N; num++)
				System.out.print(knights[num] + " ");
			System.out.println();
		}
	}
	
	private static final int[] dy = {-1, 0, 1, 0};
	private static final int[] dx = {0, 1, 0, -1};
	
	private static class Knight {
		public int row;
		public int col;
		public final int h;
		public final int w;
		public int hp;
		
		public Knight(int r, int c, int h, int w, int k) {
			this.row = r;
			this.col = c;
			this.h = h;
			this.w = w;
			this.hp = k;
		}
		
		public boolean isAlive() {
			return this.hp > 0;
		}

		@Override
		public String toString() {
			return "Knight [row=" + row + ", col=" + col + ", h=" + h + ", w=" + w + ", hp=" + hp + "]";
		}
	}
}	//	Main-class-end

/*
4 3 2
0 0 1 0
0 0 1 0
1 1 0 1
0 0 2 0
1 2 2 1 5
2 1 2 1 1
3 2 1 2 3
1 2
2 1
 */