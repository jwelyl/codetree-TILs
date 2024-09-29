import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
//	private static final boolean DEBUG = true;
	private static final boolean DEBUG = false;
	
	private static final int OUT = 0;	//	참가자가 미로에서 나갔을 경우, 좌표 (0, 0)으로 변경
	
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;	//	미로 크기
	private static int M;	//	참가자 수
	private static int K;	//	게임 시간
	
	private static int[][] maze;	//	미로
	
	private static int[][] participants;	//	participants[nth] = nth번째 참가자 위치 정보 {y, x}

	private static int remains = 0;			//	탈출하지 못한 참가자 수
	
	private static int movedDist = 0;		//	게임 끝날때까지 참가자들이 움직인 거리 합
	private static int exitY = 0;
	private static int exitX = 0;			//	출구 좌표
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		maze = new int[N + 1][N + 1];
		participants = new int[M + 1][2];
		remains = M;
		
		for(int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1; c <= N; c++)
				maze[r][c] = Integer.parseInt(st.nextToken());
		}
		
		for(int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			participants[m][0] = Integer.parseInt(st.nextToken());
			participants[m][1] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		exitY = Integer.parseInt(st.nextToken());
		exitX = Integer.parseInt(st.nextToken());
		
		print("init state");
		
		for(int k = 1; k <= K; k++) {
			step1();
			print("\n\nin time = " + k + ", after step1");
			
			if(remains == 0)
				break;
			
			step2();
			print("\n\nin time = " + k + ", after step2");
		}
		
		System.out.println(movedDist);
		System.out.println(exitY + " " + exitX);
	}	//	main-end
	
	private static void step1() {
		for(int m = 1; m <= M; m++) {	//	m번째 참가자
			int cy = participants[m][0];
			int cx = participants[m][1];
			
			if(cy == OUT && cx == OUT)	//	이미 나간 참가자의 경우
				continue;
			
			int curDist = dist(cy, cx, exitY, exitX);	//	(cy, cx)에서 (exitY, exitX)까지의 최단거리
			int dir = -1;	//	최종적으로 움직일 방향
		
			for(int d = 0; d < 4; d++) {
				if(isIn(cy + dy[d], cx + dx[d]) && maze[cy + dy[d]][cx + dx[d]] == 0) {	//	d 방향으로 한 칸 간 칸이 미로 내부이고, 벽이 아닐 경우
					if(dist(cy + dy[d], cx + dx[d], exitY, exitX) < curDist) {	//	d 방향으로 움직였을때 더 가까워 질 경우
						dir = d;
						break;
					}
				}
			}
			
			if(dir != -1) {	//	m번째 참가자가 이동할 수 있을 경우
				participants[m][0] += dy[dir];
				participants[m][1] += dx[dir];	//	해당 방향으로 이동
				movedDist++;
				
				if(participants[m][0] == exitY && participants[m][1] == exitX) {	//	해당 플레이어가 탈출한 경우
					participants[m][0] = OUT;
					participants[m][1] = OUT;
					remains--;
				}
			}
		}
	}
	
	private static void step2() {
		rotate(selectPartialSquare());
	}
	
	//	출구와 한 명 이상의 참가자를 포함하는 최소 정사각형 고르기
	//	{size, luy, lux, rdy, rdx} 반환
	private static int[] selectPartialSquare() {
		int minSize = Integer.MAX_VALUE;	//	최소 정사각형 크기
		int minLuy = Integer.MAX_VALUE;
		int minLux = Integer.MAX_VALUE;	//	최소 정사각형 좌상단 좌표
		int minRdy = Integer.MAX_VALUE;
		int minRdx = Integer.MAX_VALUE;	//	최소 정사각형 우하단 좌표
		
		for(int m = 1; m <= M; m++) {
			int py = participants[m][0];
			int px = participants[m][1];	//	포함시킬 참가자 좌표
			
			if(py == OUT && px == OUT)	//	이미 탈출한 참가자일 경우
				continue;
			
//			if(DEBUG) {
//				System.out.println("m = " + m);
//				System.out.println("py = " + py);
//				System.out.println("px = " + px);
//			}
			
			int size = Math.max(Math.abs(py - exitY), Math.abs(px - exitX)) + 1;	//	해당 플레이어를 포함시키기 위한 최소 정사각형 크기
			
//			if(DEBUG) {
//				System.out.println("size = " + size);
//				System.out.println("minSize = " + minSize);
//			}
			
			if(size > minSize) {	//	이미 더 작은 정사각형 찾은 경우
				if(DEBUG) {
				System.out.println("minSize = " + minSize);
				System.out.println("size = " + size);
				}
				continue;
			}
			
			int luy = 1;
			int lux = 1;
			int rdy = 0;
			int rdx = 0;
			
//			if(DEBUG) {
//				System.out.println("luy = " + luy);
//				System.out.println("lux = " + lux);
//				System.out.println("rdy = " + rdy);
//				System.out.println("rdx = " + rdx);
//				
//				System.out.println("N - size + 1 = " + (N - size + 1));
//			}
			
			OUTER:
			for(luy = 1; luy <= N - size + 1; luy++) {
//				if(DEBUG) {
//					System.out.println("luy = " + luy);
//				}
				
				for(lux = 1; lux <= N - size + 1; lux++) {	//	정사각형 좌상단 좌표
					rdy = luy + size - 1;
					rdx = lux + size - 1;	//	정사각형 우하단 좌표
					
//					System.out.println("(luy, lux, rdy, rdx) = (" + luy + ", " + lux + ", " + rdy + ", " + rdx + ")");
					
					//	해당 정사각형 범위 안에 참가자와 출구가 모두 포함될 경우, 해당 정사각형이 후보임
					if(isIn(py, px, luy, rdy, lux, rdx) && isIn(exitY, exitX, luy, rdy, lux, rdx)) {
//						if(DEBUG) {
//							System.out.println("중간으로");
//							System.out.println("size = " + size);
//							System.out.println("luy = " + luy);
//							System.out.println("lux = " + lux);
//							System.out.println("rdy = " + rdy);
//							System.out.println("rdx = " + rdx);
//						}
						
						break OUTER;
					}
				}
			}
			
//			if(DEBUG) {
//				System.out.println("최종적으로");
//				System.out.println("size = " + size);
//				System.out.println("luy = " + luy);
//				System.out.println("lux = " + lux);
//				System.out.println("rdy = " + rdy);
//				System.out.println("rdx = " + rdx);
//			}
			
			if(size < minSize) {	//	정사각형 크기가 더 작을 경우
				minSize = size;
				minLuy = luy;
				minLux = lux;
				minRdy = rdy;
				minRdx = rdx;
			}
			else {	//	정사각형 크기는 같을 경우
				//	좌상단 y 좌표가 더 작거나, 좌상단 y 좌표가 같을때 좌상단 x 좌표가 더 작을 경우 
				if(luy < minLuy || (luy == minLuy && lux < minLux)) {
					minLuy = luy;
					minLux = lux;
					minRdy = rdy;
					minRdx = rdx;
				}
			}
			
//			if(DEBUG)
//			System.out.println("m = " + m + ", minSize!!! = " + minSize);
		}
		
		if(DEBUG) {
			System.out.println("\npartial square");
			System.out.println("minSize = " + minSize);
			System.out.println("minLuy = " + minLuy);
			System.out.println("minLux = " + minLux);
			System.out.println("minRdy = " + minRdy);
			System.out.println("minRdx = " + minRdx);
			System.out.println();
		}
		return new int[] {minSize, minLuy, minLux, minRdy, minRdx};
	}
	
	//	크기가 size, 좌상단 좌표가 (luy, lux), 우하단 좌표가 (rdy, rdx)인 부분 정사각형을 시계방향으로 90도 회전함
	private static void rotate(int[] partialSquare) {
		int size = partialSquare[0];
		int luy = partialSquare[1];
		int lux = partialSquare[2];
		int rdy = partialSquare[3];
		int rdx = partialSquare[4];
		
		if(DEBUG) {
			System.out.println("in rotate");
			System.out.println("size = " + size);
			System.out.println("luy = " + luy);
			System.out.println("lux = " + lux);
			System.out.println("rdy = " + rdy);
			System.out.println("rdx = " + rdx);
		}
		
		
		int[][][] mapping = new int[N + 1][N + 1][2];	//	mapping[y][x] : 원래 (y, x)칸이 회전 후 위치할 좌표 {y', x'}
		
		int xx = rdx;
		
		for(int y = luy; y <= rdy; y++) {
			int yy = luy;
			for(int x = lux; x <= rdx; x++) {
				mapping[y][x][0] = yy++;
				mapping[y][x][1] = xx;
			}
			
			xx--;
		}
		
		if(DEBUG) {
			System.out.println("mapping");
			for(int y = luy; y <= rdy; y++) {
				for(int x = lux; x <= rdx; x++)
					System.out.print("(" + mapping[y][x][0] + ", " + mapping[y][x][1] + ") ");
				System.out.println();
			}
		}
		
		int[][] nMaze = copyMaze();	//	기존 미로 복사
		
		if(DEBUG) {
			System.out.println("nMaze");
			for(int r = 1; r <= N; r++) {
				for(int c = 1; c <= N; c++)
					System.out.print(nMaze[r][c] + " ");
				System.out.println();
			}
			System.out.println();
		
			System.out.println("\nparticipants");
			for(int m = 1; m <= M; m++)
				System.out.println("participant " + m + " : (" + participants[m][0] + ", " +  participants[m][1] + ")");
			System.out.println("remains = " + remains);
		
		}
		
		boolean[] rotated = new boolean[M + 1];	//	참가자가 회전됐는지 확인
		boolean exitRotated = false;	//	출구가 회전됐는지 확인
		
		for(int cy = luy; cy <= rdy; cy++) {
			for(int cx = lux; cx <= rdx; cx++) {	//	현재 미로칸 (cy, cx)
				int ny = mapping[cy][cx][0];
				int nx = mapping[cy][cx][1];		//	현재 미로칸 (cy, cx)가 회전 결과로 이동할 칸
			
				if(maze[cy][cx] >= 1)	//	현재 미로칸이 벽일 경우
					nMaze[ny][nx] = maze[cy][cx] - 1;	//	벽 내구도 1 감소시켜서 복사
				else {
					nMaze[ny][nx] = maze[cy][cx];
					if(cy == exitY && cx == exitX && !exitRotated) {	//	현재 칸이 출구일 경우
						exitY = ny;
						exitX = nx;	//	출구 변경
						exitRotated = true;
					}
				}
				
				for(int m = 1; m <= M; m++) {	//	(cy, cx)에 있던 참가자 좌표 갱신시키기
					if(participants[m][0] == cy && participants[m][1] == cx && !rotated[m]) {
						if(DEBUG) {
							System.out.println("m = " + m);
							System.out.println("cy = " + cy);
							System.out.println("cx = " + cx);
							
							System.out.println("ny = " + ny);
							System.out.println("nx = " + nx);
						}
						
						rotated[m] = true;
						participants[m][0] = ny;
						participants[m][1] = nx;
					}
				}
			}
		}
		
		maze = nMaze;	//	새로운 미로로 갈아타기
	}
	
	private static int[][] copyMaze() {
		int[][] copied = new int[N + 1][N + 1];
		
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++)
				copied[r][c] = maze[r][c];
		}
		
		return copied;
	}
	
	private static final int[] dy = {-1, 1, 0, 0};
	private static final int[] dx = {0, 0, -1, 1};
	
	private static boolean isIn(int y, int x) {
		return isIn(y, x, 1, N, 1, N);
	}
	
	private static boolean isIn(int y, int x, int fromY, int toY, int fromX, int toX) {
		return (fromY <= y && y <= toY) && (fromX <= x && x <= toX);
	}
	
	private static int dist(int y1, int x1, int y2, int x2) {
		return Math.abs(y1 - y2) + Math.abs(x1 - x2);
	}
	
	private static void print(String msg) {
		if(DEBUG) {
			System.out.println(msg);
			
			System.out.println("\nmaze");
			
			for(int r = 1; r <= N; r++) {
				for(int c = 1; c <= N; c++)
					System.out.print(maze[r][c] + " ");
				System.out.println();
			}
			
			System.out.println("(exitY, exitX) = (" + exitY + ", " + exitX + ")");
		
			System.out.println("\nparticipants");
			for(int m = 1; m <= M; m++)
				System.out.println("participant " + m + " : (" + participants[m][0] + ", " +  participants[m][1] + ")");
			System.out.println("remains = " + remains);
		
			System.out.println("movedDist = " + movedDist);
		}
	}
}	//	Main-class-end