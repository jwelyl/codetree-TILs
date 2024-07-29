import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;	//	배지 크기 N * N
	private static int M;	//	초기 바이러스 개수
	private static int K;	//	사이클 수
	
	private static Deque<Integer>[][] virusMap;	//	virusMap[r][c] : (r, c)칸에 존재하는 바이러스 나이 큐, 오름차순으로 저장된다.
	private static Deque<Integer>[][] deadMap;	//	deadMap[r][c] : (r, c)칸에 존재하는 죽은 바이러스들의 나이 큐
	private static Deque<Integer>[][] newMap;	//	newMap[r][c] : (r, c)칸에 새로 번식할 바이러스들 임시로 모아둘 큐
	private static int[][] foodMap;				//	foodMap[r][c] : (r, c)칸에 존재하는 양분 양
	private static int[][] addMap;				//	addMap[r][c] : 매 사이클 끝날 때 마다 (r, c)에 추가되는 양분 양
	
	private static int ans = 0;	//	K 사이클 후 남아 있는 바이러스 수
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		virusMap = new LinkedList[N][N];
		deadMap = new LinkedList[N][N];
		newMap = new LinkedList[N][N];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				virusMap[r][c] = new LinkedList<>();
				deadMap[r][c] = new LinkedList<>();
				newMap[r][c] = new LinkedList<>();
			}
		}
		
		foodMap = new int[N][N];
		for(int r = 0; r < N; r++)
			Arrays.fill(foodMap[r], 5);
		
		addMap = new int[N][N];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++)
				addMap[r][c] = Integer.parseInt(st.nextToken());
		}
		
		for(int v = 0; v < M; v++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			
			virusMap[r][c].offerLast(age);
		}
		
		for(int k = 0; k < K; k++)
			simulation();
		
		for(int r = 0; r < N; r++)
			for(int c = 0; c < N; c++)
				ans += virusMap[r][c].size();
		
		System.out.println(ans);
	}	//	main-end
	
	private static void simulation() {
		step1();
		step2();
		step3();
		step4();
	}
	
	private static void step1() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				Deque<Integer> tmp = new LinkedList<>();	//	(r, c)칸에서 양분을 먹은 바이러스가 이동할 임시 큐
				
				while(!virusMap[r][c].isEmpty()) {
					int age = virusMap[r][c].peekFirst();	//	이번에 양분을 먹을 바이러스 나이, 나이 어린 순으로 먹기 시작
					
					if(age > foodMap[r][c])	//	이번 바이러스에서 양분이 부족할 경우, 이번 바이러스 이후 바이러스들은 모두 죽게 됨
						break;
					
					foodMap[r][c] -= age;
					virusMap[r][c].pollFirst();	//	해당 바이러스가 양분을 먹음
					tmp.offerLast(age + 1);	//	양분 먹은 바이러스 나이가 1 증가함, 나이가 많을 수록 뒤에 존재
				}
				
				while(!virusMap[r][c].isEmpty())	//	양분이 부족해서 죽은 바이러스 모음
					deadMap[r][c].offerFirst(virusMap[r][c].pollFirst());
				
				virusMap[r][c] = tmp;	//	살아남은 바이러스 큐로 대체
			}
		}
	}
	
	private static void step2() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				while(!deadMap[r][c].isEmpty())		//	(r, c) 칸에서 죽은 모든 바이러스에 대하여
					foodMap[r][c] += (deadMap[r][c].pollFirst() / 2);	//	죽은 바이러스 나이 / 2 만큼 (r, c) 칸에 양분 추가
			}
		}
	}
	
	private static void step3() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				int size = virusMap[r][c].size();
				
				for(int s = 0; s < size; s++) {
					int age = virusMap[r][c].pollFirst();	//	(r, c)칸에 존재하는 모든 바이러스에 대하여
					
					if(age % 5 == 0) {	//	해당 바이러스 나이가 5의 배수일 경우
						for(int d = 0; d < 8; d++) {
							int nr = r + dy[d];
							int nc = c + dx[d];	//	해당 바이러스 주변 8칸에 대해
							
							if(isIn(nr, nc))
								newMap[nr][nc].offerFirst(1);	//	나이 1인 바이러스 추가
						}
					}
					
					virusMap[r][c].offerLast(age);	//	바이러스 다시 집어넣기
				}
			}
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				while(!newMap[r][c].isEmpty())	//	(r, c)칸에 추가된 나이 1인 모든 바이러스들에 대해
					virusMap[r][c].offerFirst(newMap[r][c].pollFirst());	//	(r, c)칸에 추가
			}
		}
	}
	
	private static void step4() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++)
				foodMap[r][c] += addMap[r][c];	//	모든 칸에 양분 보충
		}
	}
	
	private static final int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	private static final int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
	
	private static boolean isIn(int y, int x) {
		return (0 <= y && y < N) && (0 <= x && x < N);
	}
}	//	Main-class-end