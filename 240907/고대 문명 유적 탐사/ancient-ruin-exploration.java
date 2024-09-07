import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
//	private static final boolean DEBUG = true;
	
	private static final boolean DEBUG = false;
	
	private static final int NONE = 0;
	
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	private static int K;	//	탐사 반복 횟수
	private static int M;	//	벽면 적힌 유물 조각 개수
	
	private static int[][] relics = new int[5][5];	//	5 * 5 유물 조각 모음
	private static int[] walls;	//	벽면에 적힌 유물 조각 모음
	private static int wallIdx = 0;	//	벽면에 적힌 유물 조각 가리키는 index
	
	private static int ans = 0;	//	한 턴에서 얻은 점수
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int r = 0; r < 5; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < 5; c++)
				relics[r][c] = Integer.parseInt(st.nextToken());
		}
		
		walls = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++)
			walls[i] = Integer.parseInt(st.nextToken());
		
		for(int k = 1; k <= K; k++) {
			ans = 0;		//	매 턴마다 얻는 점수 초기화
			wallIdx = 0;	//	벽면에 적힌 유물 중 사용할 유물 index 초기화
			
//			System.out.println(k + "번째 턴");
			if(!simulation(k)) {
				if(DEBUG)
					System.out.println(k + "번째 턴에서 중간 종료");
				break;
			}
			
			sb.append(ans).append(" ");
		}
		
		System.out.println(sb);
	}	//	main-end
	
	private static boolean simulation(int k) {	//	k번째 턴에 유물을 얻지 못하면 시뮬레이션 종료
		if(DEBUG)
			System.out.println(k + "번 턴!!!!");
		
		int maxValue = 0;		//	가장 많이 획득할 수 있는 유물 가치
		int minRotCnt = 4;		//	그때 가장 적게 회전한 횟수
		int minCol = 5;
		int minRow = 5;	//	그 때 가장 작은 회전 중심 좌표
		int[][] maxRelics = null;	//	그 때의 배열 상태
		
		int[][] copiedRelics = null;
		
		//	회전시킬 3 * 3 배열 중심지 선택
		for(int rotCnt = 1; rotCnt <= 3; rotCnt++) {
			for(int c = 1; c <= 3; c++) {
				for(int r = 1; r <= 3; r++) {
					
					if(DEBUG) {
						System.out.println("rotCnt, r, c 정해진 후 원본");
						for(int i = 0; i < 5; i++) {
							for(int j = 0; j < 5; j++) 
								System.out.print(relics[i][j] + " ");
							System.out.println();
						}
					}
					
					copiedRelics = copy(relics, 5);	//	기존 5 * 5 배열 복제
					
					int[][] partialRelics = copy(copiedRelics, r, c);	//	회전시킬 부분 배열 복제
					int[][] rotated = turn90(partialRelics, rotCnt);	//	rotCnt번 회전시킨 부분 배열
					
					for(int rr = r - 1; rr <= r + 1; rr++) {
						for(int cc = c - 1; cc <= c + 1; cc++)
							copiedRelics[rr][cc] = rotated[rr - r + 1][cc - c + 1];
					}
					
					if(DEBUG) {
						System.out.println("rotCnt, r, c 정해진 후 복제본");
						
						System.out.println("rotCnt = " + rotCnt);
						System.out.println("r = " + r);
						System.out.println("c = " + c);
					
					}
					
					if(DEBUG) {
						for(int i = 0; i < 5; i++) {
							for(int j = 0; j < 5; j++)
								System.out.print(copiedRelics[i][j] + " ");
							System.out.println();
						}
					}
					
					
					int value = bfs(copiedRelics);
					
					if(DEBUG)
					System.out.println("그 때의 value = " + value);
					
					if(maxValue < value) {
						maxValue = value;
						minRotCnt = rotCnt;
						minCol = c;
						minRow = r;
						maxRelics = copiedRelics;
					}
//					else if(maxValue == value) {
//						if(rotCnt < minRotCnt) {
//							minRotCnt = rotCnt;
//							minCol = c;
//							minRow = r;
//						}
//						else if(rotCnt == minRotCnt && col < minCol)
//					}
				}
			}
		}
		
		if(DEBUG) {
		System.out.println("1차 탐사 ");
		for(int r = 0; r < 5; r++) {
			for(int c = 0; c < 5; c++)
				System.out.print(maxRelics[r][c] + " ");
			System.out.println();
		}
		}
		
		if(maxValue == 0)	//	어떤 탐사 방법으로도 얻을 수 있는 유물이 없을 경우
			return false;
		
		ans += maxValue;	//	유물 1차 획득으로 maxValue만큼 얻음
		
		//	유물 연쇄 획득
		while(true) {
			int add = 0;	//	이번 연쇄 획득으로 얻을 유물 개수
			
			for(int c = 0; c < 5; c++) {	//	유물 빈칸 채우기
				for(int r = 4; r >= 0; r--) {
					if(maxRelics[r][c] == NONE)
						maxRelics[r][c] = walls[wallIdx++];
				}
			}
			
			if(DEBUG) {
			System.out.println("빈 칸 채운 후 ");
			for(int r = 0; r < 5; r++) {
				for(int c = 0; c < 5; c++)
					System.out.print(maxRelics[r][c] + " ");
				System.out.println();
			}
			}
			
			add = bfs(maxRelics);
			
			if(DEBUG) {
			System.out.println("빈 칸 채운 후 연쇄 획득 후");
			for(int r = 0; r < 5; r++) {
				for(int c = 0; c < 5; c++)
					System.out.print(maxRelics[r][c] + " ");
				System.out.println();
			}
			}
			
			if(add == 0)	//	더 이상 연쇄 획득으로 얻을 유물이 없을 경우 종료
				break;
			
			ans += add;
		}
		
		relics = maxRelics;	//	다음 턴을 위해 배열 변경
		
		return true;
	}
	
	private static int[][] copy(int[][] orig, int r, int c) {
		int[][] ret = new int[3][3];
		
		for(int rr = r - 1; rr <= r + 1; rr++) {
			for(int cc = c - 1; cc <= c + 1; cc++)
				ret[rr - r + 1][cc - c + 1] = orig[rr][cc];
		}
		
		return ret;
	}
	
	//	size * size 배열 복제
	private static int[][] copy(int[][] orig, int size) {
		int[][] ret = new int[size][size];
		
		for(int r = 0; r < size; r++) {
			for(int c = 0; c < size; c++)
				ret[r][c] = orig[r][c];
		}
		
		return ret;
	}
	
	//	3 * 3 배열을 90도 회전
	private static int[][] turn90(int[][] orig) {
		int[][] ret = new int[3][3];
		
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				ret[c][2 - r] = orig[r][c];
			}
		}
		
		return ret;
	}
	
	//	3 * 3 배열을 90도로 cnt번 회전 (cnt = 1, 2, 3)
	private static int[][] turn90(int[][] orig, int cnt) {
		int[][] ret = turn90(orig);
		
		for(int i = 0; i < cnt - 1; i++)
			ret = turn90(ret);
		
		return ret;
	}
	
	private static int bfs(int[][] arr) {
		int ret = 0;
		boolean[][] visited = new boolean[5][5];
		
		for(int r = 0; r < 5; r++) {
			for(int c = 0; c < 5; c++) {
				if(arr[r][c] != NONE && !visited[r][c]) {
					List<int[]> posList = bfs(r, c, arr, visited);
					
					if(posList.size() >= 3) {
						ret += posList.size();
						for(int[] pos : posList)
							arr[pos[0]][pos[1]] = NONE;
					}
				}
			}
		}
		
		return ret;
	}
	
	private static final int[] dy = {0, 1, 0, -1};
	private static final int[] dx = {1, 0, -1, 0};
	
	private static boolean isIn(int y, int x) {
		return (0 <= y && y < 5) && (0 <= x && x < 5);
	}
	
	private static List<int[]> bfs(int r, int c, int[][] arr, boolean[][] visited) {
		Queue<int[]> q = new LinkedList<>();
		List<int[]> posList = new ArrayList<>();
		
		visited[r][c] = true;
		q.offer(new int[] {r, c});
		posList.add(new int[] {r, c});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cy = cur[0];
			int cx = cur[1];
			
			for(int d = 0; d < 4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				
				if(isIn(ny, nx) && arr[r][c] == arr[ny][nx] && !visited[ny][nx]) {
					visited[ny][nx] = true;
					q.offer(new int[] {ny, nx});
					posList.add(new int[] {ny, nx});
				}
			}
		}
		
		return posList;
	}
}	//	Main-class-end