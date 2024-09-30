import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int WALL = -1;
	
//	private static final boolean DEBUG = true;
	private static final boolean DEBUG = false;
	
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;	//	맵 크기
	private static int M;	//	시간
	private static int K;	//	제초제 뿌릴 범위
	private static int C;	//	제초제 지속 시간
	
	private static int[][] map;			//	나무, 벽 배치 상태
	private static int[][] killed;		//	killed[r][c] : (r, c)칸에 놓인 제초제가 만기되는 시간
	
	private static long ans = 0L;	//	M년 동안 박멸한 나무 수
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		killed = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++)
				map[r][c] = Integer.parseInt(st.nextToken());
		}
		
		for(int year = 1; year <= M; year++) {
			if(DEBUG)
				System.out.println("year = " + year + "\n");
			
			print("init");
			step1();
			print("after step1");
			step2(year);
			print("after step2");
			step3(year);
			print("after step3");
		}
		
		System.out.println(ans);
	}	//	main-end
	
	//	1. 나무 성장
	private static void step1() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c] > 0) {	//	(r, c)칸에 나무가 있을 경우
					int neighbors = 0;	//	이웃한 칸 중 나무가 있는 칸의 개수
					
					for(int d = 4; d < 8; d++) {
						int nr = r + dy[d];
						int nc = c + dx[d];
						
						if(isIn(nr, nc) && map[nr][nc] > 0)	//	주변 칸에 나무가 있을 경우
							neighbors++;
					}
				
					map[r][c] += neighbors;
				}
			}
		}
	}
	
	//	2. 나무 번식
	private static void step2(int year) {
		int[][] add = new int[N][N];	//	add[r][c] : (r, c)칸에 추가될 나무 수
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c] > 0) {	//	(r, c)에 나무가 있을 경우
					int canCnt = 0;	//	(r, c) 주변 4칸 중 번식 가능한 칸의 개수
					
					int[] canDir = new int[4];
					
					for(int d = 4; d < 8; d++) {
						int nr = r + dy[d];
						int nc = c + dx[d];	//	(r, c) 주변 칸
						
						if(isIn(nr, nc) && map[nr][nc] == 0 && killed[nr][nc] < year)	//	(nr, nc)에 번식이 가능할 경우
							canDir[canCnt++] = d;	//	d 방향으로 번식 가능, 번식 가능한 칸의 개수 1 증가
					}
					
					for(int d = 0; d < canCnt; d++) {
						int dir = canDir[d];	//	dir 방향으로 나무 번식 가능
						
						add[r + dy[dir]][c + dx[dir]] += map[r][c] / canCnt;	//	해당 칸에 나무 번식
					}
				}
			}
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++)
				map[r][c] += add[r][c];	//	나무 증가분 반영
		}
	}
	
	//	제초제 뿌리기
	private static void step3(int year) {
		int maxKill = -1;	//	가장 많은 나무를 죽일 수 있는 칸에 제초제 뿌렸을때, 그 때 죽인 나무 수
		int maxR = -1;
		int maxC = -1;		//	그때 제초제 뿌린 칸 수
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				int kill = 0;	//	(r, c)에 제초제 뿌렸을 때 죽이는 나무 수
				
				if(map[r][c] > 0) { 	//	나무가 하나라도 있을 경우
					kill = map[r][c];	//	해당 칸 나무 죽임
					
					for(int d = 0; d < 4; d++) {
						for(int k = 1; k <= K; k++) {
							int nr = r + dy[d] * k;
							int nc = c + dx[d] * k;	//	제초제 뿌릴 칸
							
							if(!isIn(nr, nc))	//	범위를 벗어날 경우, 해당 방향으로는 더 이상 뿌릴 수 없음
								break;
							
							//	다음 칸에 나무가 없거나, 벽일 경우, 제초제만 뿌리고 더 퍼지지 않음
							if(map[nr][nc] == 0 || map[nr][nc] == WALL)
								break;	//	해당 방향으로는 더 이상 퍼지지 않음
							
							kill += map[nr][nc];	//	(nr, nc)에 있는 나무 죽임
						}
					}
				}
				
				if(maxKill < kill) {
					maxKill = kill;
					maxR = r;
					maxC = c;
				}
			}
		}
		
		if(DEBUG) {
			System.out.println("maxR = " + maxR);
			System.out.println("maxC = " + maxC);
			System.out.println("maxKill = " + maxKill);
		}
		
		//	(maxR, maxC)를 중심으로 K 범위만큼 나무 죽이기
		if(map[maxR][maxC] > 0) {	//	해당 칸이 나무일 경우 
			ans += map[maxR][maxC];
			map[maxR][maxC] = 0;
		}
		killed[maxR][maxC] = year + C;
		
		for(int d = 0; d < 4; d++) {
			for(int k = 1; k <= K; k++) {
				int nr = maxR + dy[d] * k;
				int nc = maxC + dx[d] * k;	//	제초제 뿌릴 칸
				
				if(!isIn(nr, nc))	//	범위를 벗어날 경우, 해당 방향으로는 더 이상 뿌릴 수 없음
					break;
				
				killed[nr][nc] = year + C;	//	(nr, nc)에 뿌려진 제초제는 year + C까지 유효
				
				//	다음 칸에 나무가 없거나, 벽일 경우, 제초제만 뿌리고 더 퍼지지 않음
				if(map[nr][nc] == 0 || map[nr][nc] == WALL)
					break;	//	해당 방향으로는 더 이상 퍼지지 않음
				
				ans += map[nr][nc];	//	(nr, nc)에 있는 나무 죽임
				map[nr][nc] = 0;
			}
		}
	}
	
	private static void print(String msg) {
		if(DEBUG) {
			System.out.println(msg);
			
			System.out.println("\nmap");
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++)
					System.out.print(map[r][c] + " ");
				System.out.println();
			}
			
			System.out.println("\nkilled");
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++)
					System.out.print(killed[r][c] + " ");
				System.out.println();
			}
		}
	}
	
	private static final int[] dy = {-1, 1, 1, -1, 0, 1, 0, -1};
	private static final int[] dx = {1, 1, -1, -1, 1, 0, -1, 0};
	
	private static boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < N);
	}
}	//	Main-class-end

/*
18 1 3 8
-1 0 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 0 0 
-1 -1 -1 -1 -1 0 -1 -1 0 -1 0 -1 -1 -1 -1 -1 -1 -1 
-1 -1 -1 -1 -1 -1 -1 -1 -1 -1 0 -1 -1 -1 -1 -1 -1 -1 
-1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
-1 -1 0 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
-1 -1 -1 -1 -1 -1 -1 -1 0 -1 0 -1 -1 -1 -1 -1 -1 -1 
-1 0 -1 -1 -1 -1 -1 0 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
-1 -1 -1 0 0 -1 -1 0 -1 -1 -1 -1 -1 -1 -1 -1 -1 0 
-1 0 0 -1 -1 -1 -1 -1 -1 -1 0 -1 -1 -1 -1 -1 -1 -1 
-1 -1 0 -1 -1 0 0 -1 -1 -1 -1 -1 -1 -1 0 -1 -1 -1 
-1 -1 -1 -1 -1 -1 -1 -1 0 -1 -1 -1 -1 -1 -1 -1 -1 19 
-1 -1 -1 -1 -1 68 -1 0 -1 -1 0 -1 0 -1 -1 -1 47 0 
-1 -1 -1 -1 -1 -1 0 -1 0 0 -1 -1 -1 -1 -1 0 -1 -1 
-1 -1 -1 0 -1 0 -1 -1 0 -1 -1 -1 -1 0 0 -1 -1 -1 
-1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
-1 -1 -1 0 -1 -1 0 0 0 -1 -1 -1 -1 -1 -1 -1 -1 -1 
-1 -1 -1 -1 -1 -1 -1 -1 0 -1 -1 -1 -1 -1 95 -1 -1 0 
-1 -1 -1 -1 -1 -1 0 0 -1 -1 0 -1 -1 0 -1 0 -1 -1

5 1 3 4
-1 0 -1 -1 5 
-1 -1 -1 -1 -1 
0 0 -1 -1 -1 
-1 -1 -1 -1 0 
-1 -1 -1 -1 0


5 2 3 4
-1 0 -1 -1 5 
-1 -1 -1 -1 -1 
0 0 -1 -1 -1 
-1 -1 -1 -1 0 
-1 -1 -1 -1 0
 
*/