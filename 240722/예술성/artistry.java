import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int NONE = 0;	//	미방문
	private static final int MAX = 29 * 29 + 1;
	
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;
	
	private static int N;			//	보드 격자 크기
	private static int[][] board;	//	보드
	private static int[][] group;	//	group[i][j] : (i, j)칸이 속한 그룹 번호
	
	private static final int[] groupNum = new int[MAX];	//	groupNum[i] : i번째 그룹 수
	private static final int[] groupCnt = new int[MAX];	//	groupCnt[i] : i번째 그룹의 원소 개수
	private static final int[] groupY = new int[MAX];	//	groupY[i] : i번째 그룹의 y 좌표
	private static final int[] groupX = new int[MAX];	//	groupX[i] : i번째 그룹의 x 좌표
	private static final int[][] neighbors = new int[MAX][MAX];	//	neighbors[i][j] : i번째 그룹과 j번째 그룹이 맞닿은 변의 개수
	
	private static int sum = 0;	//	초기 예술 점수, 1~3 회전 후 예술 점수 합
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		group = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
				board[i][j] = Integer.parseInt(tokens.nextToken());
		}
		
		sum = calcArtScore();
		
		for(int r = 1; r <= 3; r++) {
			rotate();	//	r번째 회전
			sum += calcArtScore();	//	r번째 회전 후 예술성 점수
		}
		
		System.out.println(sum);
	}	//	main-end
	
	private static void reset() {
		Arrays.fill(groupNum, NONE);
		Arrays.fill(groupCnt, NONE);
		Arrays.fill(groupY, NONE);
		Arrays.fill(groupX, NONE);
		for(int i = 0; i < N; i++)
			Arrays.fill(group[i], NONE);
		for(int i = 0; i < MAX; i++)
			Arrays.fill(neighbors[i], NONE);
	}
	
	private static int calcArtScore() {
		reset();
		
		int score = 0;
		int gNum = 0;	//	그룹 번호
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {	//	각 칸별로 그룹 정하기
				int num = board[i][j];
				
				if(group[i][j] == NONE) {	//	아직 그룹 정해지지 않은 경우
					gNum++;					//	그룹 번호 1 증가
					groupNum[gNum] = num;	//	gNum번째 그룹은 num으로 이루어져 있음
					groupY[gNum] = i;
					groupX[gNum] = j;	//	gNum번쨰 그룹 시작 좌표
					bfs1(num, gNum);
				}
			}
		}

		for(int g = 1; g <= gNum; g++)	//	각 그룹 별로 타 그룹과 맞닿은 변 개수 계산
			bfs2(g);
		
		for(int g1 = 1; g1 <= gNum - 1; g1++) {
			for(int g2 = g1 + 1; g2 <= gNum; g2++) {
				//	g1 그룹, g2 그룹 예술성 점수
				int score12 = (groupCnt[g1] + groupCnt[g2]) * groupNum[g1] * groupNum[g2] * neighbors[g1][g2];
				score += score12;
			}
		}
		
		return score;
	}
	
	private static void bfs1(int num, int gNum) {
		Queue<int[]> q = new LinkedList<>();
		groupCnt[gNum]++;	//	gNum번째 그룹에 속한 칸 수 1 증가

		group[groupY[gNum]][groupX[gNum]] = gNum;
		q.offer(new int[] {groupY[gNum], groupX[gNum]});
		
		while(!q.isEmpty()) {
			int[] out = q.poll();
			int cy = out[0];
			int cx = out[1];
			
			for(int d = 0; d < 4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				
				if(!isIn(ny, nx) || board[ny][nx] != num || group[ny][nx] != NONE)
					continue;
				
				group[ny][nx] = gNum;
				groupCnt[gNum]++;
				
				q.offer(new int[] {ny, nx});
			}
		}	//	while-end
	}	//	bfs1-end
	
	
	private static void bfs2(int gNum) {
		int sy = groupY[gNum];
		int sx = groupX[gNum];	//	gNum번째 그룹 시작점
		
		boolean[][] visited = new boolean[N][N];	//	방문 배열
		
		Queue<int[]> q = new LinkedList<>();
		
		visited[sy][sx] = true;
		q.offer(new int[] {sy, sx});
		
		while(!q.isEmpty()) {
			int[] out = q.poll();
			int cy = out[0];
			int cx = out[1];
			
			for(int d = 0; d < 4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				
				if(!isIn(ny, nx))
					continue;
				
				if(group[ny][nx] > gNum)
					neighbors[gNum][group[ny][nx]]++;	//	gNum 그룹과 group[ny][nx]가 맞닿은 변 개수 1 증가
				else if(group[ny][nx] == gNum && !visited[ny][nx]) {	//	같은 그룹이고 방문하지 않았을 경우
					visited[ny][nx] = true;
					q.offer(new int[] {ny, nx});
				}
			}
		}	//	while-end
	}	//	bfs2-end
	
	//	배열 회전
	private static void rotate() {
		rotateCross();
		rotatePart(0, 0);
		rotatePart(0, N / 2 + 1);
		rotatePart(N / 2 + 1, 0);
		rotatePart(N / 2 + 1, N / 2 + 1);
	}
	
	//	중앙 십자가 왼쪽으로 90도 회전
	private static void rotateCross() {
		for(int cnt = 0; cnt < N / 2; cnt++) {
			int y0 = N / 2;
			int x0 = 0 + dx[0] * cnt;
			int y1 = 0 + dy[1] * cnt;
			int x1 = N / 2;
			int y2 = N / 2;
			int x2 = N - 1 + dx[2] * cnt;
			int y3 = N - 1 + dy[3] * cnt;
			int x3 = N / 2;
			
			int tmp = board[y0][x0];
			board[y0][x0] = board[y1][x1];
			board[y1][x1] = board[y2][x2];
			board[y2][x2] = board[y3][x3];
			board[y3][x3] = tmp;
		}
	}
	
	//	(y, x)를 왼쪽 위 꼭짓점으로 하는 N / 2 * N / 2 정사각형 오른쪽으로 90도 회
	private static void rotatePart(int y, int x) {
		int size = N / 2;
		int[][] tmp = new int[size][size];
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++)
				tmp[i][j] = board[i + y][j + x];
		}
		
		for(int j = x + size - 1; j >= x; j--) {
			for(int i = y; i < y + size; i++)
				board[i][j] = tmp[x + size - 1 - j][i - y];
		}
	}
	
	private static final int[] dy = {0, 1, 0, -1};
	private static final int[] dx = {1, 0, -1, 0};
	
	private static boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < N);
	}
}	//	Main-class-end