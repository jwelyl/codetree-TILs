import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;			//	재현시 크기 N
	private static int[][] map;		//	map[i][j] : 재현시의 (i, j) 구역의 인구수
	
	private static int maxWardCnt;		//	인구 가장 많은 선거구 인구수
	private static int minWardCnt;		//	인구 가장 적은 선거구 인구수
	private static int minDiff = Integer.MAX_VALUE;	//	가장 인구가 많은 선거구와 적은 선거구의 인구 차이의 최솟값
	
	private static final int[] numOfRegions = new int[5];	//	numOfRegions[i] : i + 1번째 선거구에 속한 인구수
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j <= N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}	//	input-end
		
		simulation();
		
		System.out.println(minDiff);
	} //	main-end
	
	//	매번 x, y, d1, d2가 결정될 때마다 numOfRegions와 ward를 초기화함
	private static void reset() {
		Arrays.fill(numOfRegions, 0);
		maxWardCnt = 0;
		minWardCnt = Integer.MAX_VALUE;
	}
	
	private static void simulation() {
		for(int x = 1; x <= N - 2; x++) {
			for(int y = 2; y <= N - 1; y++) {
				for(int d1 = 1; d1 <= N - 2; d1++) {
					for(int d2 = 1; d2 <= N - 2; d2++) {
						if(x + d1 > N || y - d1 < 1 || x + d1 + d2 > N || y - d1 + d2  > N || x + d2 > N || y + d2 > N)
							continue;
						
						reset();
						setWards(x, y, d1, d2);
						
						for(int i = 0; i < 5; i++) {
							if(numOfRegions[i] > maxWardCnt)
								maxWardCnt = numOfRegions[i];
							if(numOfRegions[i] < minWardCnt)
								minWardCnt = numOfRegions[i];
						}
						
						if(minWardCnt == 0)	//	인구가 0인 선거구가 있을 경우
							continue;
						
						minDiff = Math.min(minDiff, maxWardCnt - minWardCnt);
					}
				}
			}
		}
	}	//	simulation-end
	
	//	x, y, d1, d2가 정해졌을 때 재현시의 모든 구역의 선거구를 결정함
	private static void setWards(int x, int y, int d1, int d2) {
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {				
				//	선거구 5에 해당하는 부분
				//	선분 1. (x, y)와 (x + d1, y - d1) 잇는 선분 r = -c + y + x
				//	선분 2. (x, y)와 (x + d2, y + d2) 잇는 선분 r = c - y + x
				//	선분 3. (x + d1, y - d1)와 (x + d1 + d2, y - d1 + d2) 잇는 선분 r = c - y + x + 2 * d1
				//	선분 4. (x + d2, y + d2)와 (x + d1 + d2, y - d1 + d2) 잇는 선분 r = -c + y + x + 2 * d2
				if((r >= -c + y + x) && (r <= -c + y + x + 2 * d2) && 
						(r >= c - y + x) && (r <= c - y + x + 2 * d1)) 
					numOfRegions[4] += map[r][c];	//	5번 선거구 인구 추가
				//	선거구 1에 해당하는 부분
				else if((1 <= r && r < x + d1) && (1 <= c && c <= y) && (r < -c + y + x)) 
					numOfRegions[0] += map[r][c];
				//	선거구 2에 해당하는 부분
				else if((1 <= r && r <= x + d2) && (y < c && c <= N) && (r < c - y + x)) 
					numOfRegions[1] += map[r][c];
				//	선거구 3에 해당하는 부분
				else if((x + d1 <= r && r <= N) && (1 <= c && c < y - d1 + d2) && (r > c - y +x + 2 * d1)) 
					numOfRegions[2] += map[r][c];
				//	선거구 4에 해당하는 부분
				else if((x + d2 < r && r <= N) && (y - d1 + d2 <= c && c <= N) && (r > -c + y + x + 2 * d2)) 
					numOfRegions[3] += map[r][c];
			}
		}
	}	//	setWards-end
} //	Main-class-end