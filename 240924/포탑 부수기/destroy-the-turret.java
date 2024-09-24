import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
//	private static final boolean DEBUG = true;
	private static final boolean DEBUG = false;
	
	private static final int INF = Integer.MAX_VALUE;
	private static final int BROKEN = 0;
	
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static int M;	//	격자 크기
	private static int K;	//	K개 턴
	
	private static int[][] turrets;			//	turrets[r][c] : (r, c)칸에 존재하는 포탑의 공격력
	private static int[][] attackTime;		//	attackTime[r][c] : (r, c)칸에 존재하는 포탑이 가장 최근에 공격한 시간
	
	private static int alive = 0;	//	부서지지 않은 포탑 개수
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		turrets = new int[N][M];
		attackTime = new int[N][M];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				turrets[r][c] = Integer.parseInt(st.nextToken());
				
				if(turrets[r][c] != BROKEN)
					alive++;
			}
		}
		
		for(int k = 1; k <= K; k++) {
			if(DEBUG)
				System.out.println("k = " + k);
			
			print(k, "start");
			
			int[] step1 = getAttackerAndTarget();
			int attackY = step1[0];
			int attackX = step1[1];	//	공격할 포탑 좌표	
			int targetY = step1[2];
			int targetX = step1[3];	//	공격받을 포탑 좌표
			
			print(k, "after step1");
			
			if(DEBUG) {
				System.out.println("after step1");
				System.out.println("attackY = " + attackY);
				System.out.println("attackX = " + attackX);
				System.out.println("targetY = " + targetY);
				System.out.println("targetX = " + targetX);
			}
			
			boolean[][] engaged = new boolean[N][M];	//	engaged[r][c] : (r, c)칸의 포탑이공격에 연루된 경우 true
			
			step23(attackY, attackX, targetY, targetX, engaged, k);
			
			print(k, "after step23");
			
			if(alive == 1)	//	부서지지 않은 포탑이 1개만 남은 경우
				break;
			
			step4(engaged);	//	공격에 연루되지 않은 포탑들 정비하기
			
			print(k, "after step4");
		}
		
		System.out.println(maxTurret());
	}	//	main-end
	
	private static int maxTurret() {
		int maxDamage = -1;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++)
				maxDamage = Math.max(maxDamage, turrets[r][c]);
		}
		
		return maxDamage;
	}
	
	//	공격할 포탑과, 공격받을 포탑 정하기
	private static int[] getAttackerAndTarget() {
		//	공격할 포탑 정보
		int minDamage = INF;					//	1. 0을 제외하고 공격력이 가장 낮은 포탑의 공격력
		int newestTime = -1;					//	2. 공격력이 가장 낮은 포탑들 중 가장 최근에 공격한 시간 (큰 값)
		int maxRowColSum = -1;					//	3. 1, 2를 만족하는 포탑이 여러개일 경우, 행 + 열 합 가장 큰 값
		int maxCol = -1;						//	4. 1, 2, 3을 만족하는 포탑이 여러개일 경우, 열이 가장 큰 값
		int attackRow = -1;
		int attackCol = -1;						//	공격을 할 포탑 좌표
		
		//	공격받을 포탑 정보
		int maxDamage = -1;						//	1. 공격력이 가장 높은 포탑의 공격력
		int oldestTime = INF;					//	2. 공격력이 가장 높은 포탑들 중 가장 예전에 공격한 시간 (작은 값)
		int minRowColSum = INF;					//	3. 1, 2를 만족하는 포탑이 여러개일 경우, 행 + 열 합 가장 작은 값
		int minCol = INF;						//	4. 1, 2, 3을 만족하는 포탑이 여러개일 경우, 열이 가장 작은 값
		int targetRow = -1;
		int targetCol = -1;						//	공격을 받을 포탑 좌표
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(turrets[r][c] == BROKEN)	//	이미 부서진 포탑은 제외
					continue;
				
				int damage = turrets[r][c];
				int time = attackTime[r][c];
				int rowColSum = r + c;
				int col = c;
				
				//	공격할 포탑 찾기
				if(damage < minDamage) {	//	1. 공격력이 가장 낮은 포탑을 찾은 경우 
					minDamage = damage;
					newestTime = time;
					maxRowColSum = rowColSum;
					maxCol = col;
					attackRow = r;
					attackCol = c;
				}
				else if(damage == minDamage) {	//	1을 만족하는 포탑이 여러 개인 경우
					if(newestTime < time) {	//	2. 가장 최근에 공격한 포탑을 찾은 경우
						newestTime = time;
						maxRowColSum = rowColSum;
						maxCol = col;
						attackRow = r;
						attackCol = c;
					}
					else if(newestTime == time) {	//	1, 2를 만족하는 포탑이 여러 개인 경우
						if(maxRowColSum < rowColSum) {	//	3. 행, 열 값 합이 최대인 포탑을 찾은 경우
							maxRowColSum = rowColSum;
							maxCol = col;
							attackRow = r;
							attackCol = c;
						}
						else if(maxRowColSum == rowColSum) {	//	1, 2, 3을 만족하는 포탑이 여러 개인 경우
							if(maxCol < col) {	//	4. 열 값이 최대인 포탑을 찾은 경우
								maxCol = col;
								attackRow = r;
								attackCol = c;
							}
						}
					}
				}
				
				//	공격받을 포탑 찾기
				if(maxDamage < damage) {	//	1. 공격력이 가장 높은 포탑을 찾은 경우
					maxDamage = damage;
					oldestTime = time;
					minRowColSum = rowColSum;
					minCol = col;
					targetRow = r;
					targetCol = c;	
				}
				else if(maxDamage == damage) {	//	1을 만족하는 포탑이 여러 개인 경우
					if(time < oldestTime) {	//	2. 가장 예전에 공격한 포탑을 찾은 경우
						oldestTime = time;
						minRowColSum = rowColSum;
						minCol = col;
						targetRow = r;
						targetCol = c;	
					}
					else if(time == oldestTime) {	//	1, 2를 만족하는 포탑이 여러 개인 경우
						if(rowColSum < minRowColSum) {	//	3. 행, 열 값 합이 최소인 포탑을 찾은 경우
							minRowColSum = rowColSum;
							minCol = col;
							targetRow = r;
							targetCol = c;	
						}
						else if(rowColSum == minRowColSum) {	//	1, 2, 3을 만족하는 포탑이 여러 개인 경우
							if(col < minCol) {	//	4. 열 값이 최소인 포탑을 찾은 경우
								minCol = col;
								targetRow = r;
								targetCol = c;	
							}
						}
					}
				}
			}
		}
		
		
		
//		for(int r = 0; r < N; r++) {
//			for(int c = 0; c < M; c++) {
//				if(turrets[r][c] == BROKEN)	//	이미 부서진 포탑은 skip
//					continue;
//				
//				int damage = turrets[r][c];
//				int time = attackTime[r][c];
//				int rowColSum = r + c;
//				int col = c;
//				
//				//	공격할 포탑 정하기
//				if(damage < minDamage) {	//	1. 공격력이 가장 약한 포탑을 찾은 경우
//					minDamage = damage;
//					newestTime = time;
//					maxRowColSum = rowColSum;
//					maxCol = c;
//					attackRow = r;
//					attackCol = c;
//				}
//				else if(damage == minDamage) {	//	1을 만족하는 포탑이 여럿일 경우
//					if(newestTime < time) {	//	2. 가장 최근에 공격한 포탑일 경우
//						newestTime = time;
//						maxRowColSum = rowColSum;
//						maxCol = c;
//						attackRow = r;
//						attackCol = c;
//					}
//					else if(newestTime == time) {	//	1, 2를 만족하는 포탑이 여럿일 경우
//						if(maxRowColSum < rowColSum) {	//	3. 행 + 열 값이 가장 큰 경우
//							maxRowColSum = rowColSum;
//							maxCol = c;
//							attackRow = r;
//							attackCol = c;
//						}
//						else if(maxRowColSum == rowColSum) {	//	1, 2, 3을 만족하는 포탑이 여럿일 경우
//							if(maxCol < col) {	//	4. 열 값이 가장 큰 경우
//								maxCol = c;
//								attackRow = r;
//								attackCol = c;
//							}
//						}
//					}
//				}
//				
//				//	공격받을 포탑 정하기
//				if(maxDamage < damage) {	//	1. 공격력이 가장 강한 포탑을 찾은 경우
//					maxDamage = damage;
//					oldestTime = time;
//				    minRowColSum = rowColSum;
//					minCol = c;
//					targetRow = r;
//					targetCol = c;
//				}
//				else if(damage == maxDamage) {	//	1을 만족하는 포탑이 여럿일 경우
//					if(time < oldestTime) {	//	2. 가장 예전에 공격한 포탑일 경우
//						oldestTime = time;
//						minRowColSum = rowColSum;
//						minCol = c;
//						targetRow = r;
//						targetCol = c;
//					}
//					else if(oldestTime == time) {	//	1, 2를 만족하는 포탑이 여럿일 경우
//						if(rowColSum < minRowColSum) {	//	3. 행 + 열 값이 가장 작은 경우
//							minRowColSum = rowColSum;
//							minCol = c;
//							targetRow = r;
//							targetCol = c;
//						}
//						else if(minRowColSum == rowColSum) {	//	1, 2, 3을 만족하는 포탑이 여럿일 경우
//							if(col < minCol) {	//	4. 열 값이 가장 작은 경우
//								minCol = c;
//								targetRow = r;
//								targetCol = c;
//							}
//						}
//					}
//				}
//			}
//		}
		
		return new int[] {attackRow, attackCol, targetRow, targetCol};
	}
	
	private static void step23(int attackY, int attackX, int targetY, int targetX, boolean[][] engaged, int time) {
		if(DEBUG)
			System.out.println("before step23");
		
		turrets[attackY][attackX] += (N + M);	//	공격할 포탑 공격력 상승
		attackTime[attackY][attackX] = time;	//	공격한 시간 설정
		
		int[][] dist = new int[N][M];			//	dist[r][c] : (attackY, attackX)부터 (r, c)까지의 최단거리
		int[][][] prev = new int[N][M][2];		//	prev[r][c] : 최단 경로 상에서 (r, c)의 이전 좌표
		prev[attackY][attackX][0] = -1;
		prev[attackY][attackX][1] = -1;
		
		if(findLaserAttackRoute(attackY, attackX, targetY, targetX, dist, prev))	//	레이저 공격이 가능할 경우
			laserAttack(attackY, attackX, targetY, targetX, prev, engaged);
		else	//	레이저 공격이 불가능할 경우
			bombAttack(attackY, attackX, targetY, targetX, engaged);
		
		if(DEBUG)
			System.out.println("after step23");
	}
	
	private static void laserAttack(int attackY, int attackX, int targetY, int targetX, int[][][] prev, boolean[][] engaged) {
		if(DEBUG) {
			System.out.println("laserAttack start");
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					System.out.print("(" + prev[r][c][0] + ", " + prev[r][c][1] + ") ");
				}
				System.out.println();
			}
		}

		
		int damage = turrets[attackY][attackX];
		int cy = targetY;
		int cx = targetX;
		
		while(cy != -1 && cx != - 1) {
//			System.out.println("cy = " + cy);
//			System.out.println("cx = " + cx);
			engaged[cy][cx] = true;
			
			turrets[cy][cx] -= (cy == targetY && cx == targetX) ? damage : (cy == attackY && cx == attackX ? 0 : damage / 2);
			turrets[cy][cx] = Math.max(turrets[cy][cx], BROKEN);
			
			if(turrets[cy][cx] == BROKEN)
				alive--;
			
			int ny = prev[cy][cx][0];
			int nx = prev[cy][cx][1];
			
			cy = ny;
			cx = nx;
		}
		
		if(DEBUG) {
			System.out.println("laserAttack end");
		}
	}
	
	private static boolean findLaserAttackRoute(int attackY, int attackX, int targetY, int targetX, int[][] dist, int[][][] prev) {
		if(DEBUG) {
			System.out.println("findLaserAttackRoute start");
		}
		
		Queue<int[]> q = new LinkedList<>();
		
		for(int r = 0; r < N; r++)
			Arrays.fill(dist[r], INF);
		dist[attackY][attackX] = 0;
		
		q.offer(new int[] {attackY, attackX, dist[attackY][attackX]});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cy = cur[0];
			int cx = cur[1];	//	현재 칸
			int cd = cur[2];	//	현재 칸까지 최단거리
			
			for(int d = 0; d < 4; d++) {
				int ny = cy + dy4[d];
				int nx = cx + dx4[d];	//	다음 칸
				int nd = cd + 1;		//	다음 칸까지 최단거리
				
				if(ny == -1)
					ny = N - 1;
				else if(ny == N)
					ny = 0;
				if(nx == -1)
					nx = M - 1;
				else if(nx == M)
					nx = 0;
				
				if(turrets[ny][nx] != BROKEN && dist[ny][nx] == INF) {
					dist[ny][nx] = nd;
					prev[ny][nx][0] = cy;
					prev[ny][nx][1] = cx;	//	(ny, nx) 이전 칸은 (cy, cx)
					
					if(ny == targetY && nx == targetX) {	//	공격받을 포탑을 찾은 경우
						if(DEBUG) {
							System.out.println("findLaserAttackRoute end (true)");
						}
						return true;
					}
					
					q.offer(new int[] {ny, nx, dist[ny][nx]});
				}
			}
		}	//	while-end
		
		if(DEBUG) {
			System.out.println("findLaserAttackRoute end (false)");
		}
		
		return false;
	}	//	bfs-end
	
	private static void bombAttack(int attackY, int attackX, int targetY, int targetX, boolean[][] engaged) {
		int damage = turrets[attackY][attackX];
		engaged[attackY][attackX] = true;
		
		//	공격받을 포탑 공격 받음
		turrets[targetY][targetX] = Math.max(turrets[targetY][targetX] - damage, BROKEN);
		engaged[targetY][targetX] = true;
		if(turrets[targetY][targetX] == BROKEN)
			alive--;
		
		//	공격받은 포탑 주변 8개 포탑도 절반 데미지 받음
		for(int d = 0; d < 8; d++) {
			int ny = targetY + dy8[d];
			int nx = targetX + dx8[d];
			
			if(ny == -1)
				ny = N - 1;
			else if(ny == N)
				ny = 0;
			if(nx == -1)
				nx = M - 1;
			else if(nx == M)
				nx = 0;
			
			//	공격한 포탑이거나, 이미부서진 포탑은 건너뜀
			if((ny == attackY && nx == attackX) || turrets[ny][nx] == BROKEN)
				continue;
			
			turrets[ny][nx] = Math.max(turrets[ny][nx] - (damage / 2), BROKEN);
			engaged[ny][nx] = true;
			if(turrets[ny][nx] == BROKEN)
				alive--;
		}
	}
	
	private static void step4(boolean[][] engaged) {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(turrets[r][c] != BROKEN && !engaged[r][c])	//	부서지지 않은 포탑에서 공격에 연루되지 않은 포탑
					turrets[r][c]++;
			}
		}
	}
	
	private static final int[] dy4 = {0, 1, 0, -1};
	private static final int[] dx4 = {1, 0, -1, 0};
	
	private static final int[] dy8 = {0, 1, 1, 1, 0, -1, -1, -1};
	private static final int[] dx8 = {1, 1, 0, -1, -1, -1, 0, 1};
	
	private static void print(int time, String msg) {
		if(!DEBUG)
			return;
		
		System.out.println("In time " + time + ", " + msg);
		
		System.out.println("turrets");
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++)
				System.out.print(turrets[r][c] + " ");
			System.out.println();
		}
		
		System.out.println("\nattackTime");
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++)
				System.out.print(attackTime[r][c] + " ");
			System.out.println();
		}
		
		System.out.println();
	}
}	//	Main-class-end

/*
4 4 1
6 8 0 1
0 0 0 0
0 0 0 0
0 0 8 0

4 4 2
6 8 0 1
0 0 0 0
0 0 0 0
0 0 8 0

4 4 3
6 8 0 1
0 0 0 0
0 0 0 0
0 0 8 0
 */