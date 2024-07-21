import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final int EMPTY = 0;
	
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final StringBuilder sb = new StringBuilder();
	private static StringTokenizer tokens;
	
	private static int N;										//	땅 격자 크기
	private static int M;										//	플레이어 수
	private static int K;										//	라운드 수
	private static PriorityQueue<Integer>[][] gunMap;			//	gunMap[i][j] : (i, j)에 놓인 총들의 공격력, 공격력 높은 총이 우선
	private static int[][] playerMap;							//	playerMap[i][j] : (i, j)에 위치한 플레이어 번호
	private static Player[] players;							//	players[i] : i번째 플레이어
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());

		gunMap = new PriorityQueue[N][N];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++)
				gunMap[r][c] = new PriorityQueue<Integer>(Collections.reverseOrder());
		}
		
		playerMap = new int[N][N];
		players = new Player[M + 1];
		
		for(int r = 0; r < N; r++) {
			tokens = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				int gunPower = Integer.parseInt(tokens.nextToken());
				
				if(gunPower > EMPTY)	//	해당 칸에 총이 존재할 경우
					gunMap[r][c].offer(gunPower);
			}
		}

		for(int pNum = 1; pNum <= M; pNum++) {
			tokens = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(tokens.nextToken()) - 1;
			int y = Integer.parseInt(tokens.nextToken()) - 1;
			int d = Integer.parseInt(tokens.nextToken());
			int s = Integer.parseInt(tokens.nextToken());
			
			players[pNum] = new Player(pNum, x, y, d, s);
			playerMap[x][y] = pNum;	//	pNum번 플레이어를 (x, y) 칸에 배치
		}
		
		for(int k = 0; k < K; k++)
			simulation(k + 1);
		
		for(int pNum = 1; pNum <= M; pNum++)
			sb.append(players[pNum].point).append(" ");

		System.out.print(sb);
	}	//	main-end
	
	//	한 라운드를 진행함
	private static void simulation(int round) {
		for(int pNum = 1; pNum <= M; pNum++) {
			Player cPlayer = players[pNum];
			
			playerMap[cPlayer.x][cPlayer.y] = EMPTY;	//	일단 현재 칸 비우기
			
			//	1. 현재 플레이어가 한 칸 이동
			move(cPlayer);

			
			int cx = cPlayer.x;
			int cy = cPlayer.y;	//	현재 플레이어가 이동한 칸
			
			//	2-1. 현재 플레이어가 이동한 칸에 다른 플레이어가 없을 경우
			if(playerMap[cx][cy] == EMPTY) {
				//	2-1-1. 현재 플레이어가 이동한 칸에 총이 있을 경우
				if(!gunMap[cx][cy].isEmpty()) {
					int maxGun = gunMap[cx][cy].peek();	//	현재 칸에 존재하는 총 중 가장 공격력이 높은 총
					
					if(cPlayer.gun == 0)	//	현재 플레이어가 총이 없을 경우
						cPlayer.gun = gunMap[cx][cy].poll();	//	현재 칸의 가장 강한 총을 가져감
					else if(maxGun > cPlayer.gun) {	//	현재 플레이어가 총이 있지만 현재 칸의 가장 강한 총보다는 약할 경우
						gunMap[cx][cy].offer(cPlayer.gun);	//	현재 플레이어가 가진 총을 내려놓음
						cPlayer.gun = gunMap[cx][cy].poll();
					}
				}
				
				playerMap[cx][cy] = pNum;	//	(cx, cy)칸에는 pNum 플레이어가 위치함
			}
			else {	//	2-2. 다른 플레이어가 있을 경우
				int otherNum = playerMap[cx][cy];			//	다른 플레이어 번호
				Player otherPlayer = players[otherNum];		//	다른 플레이어
				
				if(cPlayer.fight(otherPlayer))	//	현재 플레이어가 이긴 경우
					result(cPlayer, otherPlayer, pNum, otherNum);
				else	//	다른 플레이어가 이긴 경우
					result(otherPlayer, cPlayer, otherNum, pNum);
			}
		}
	}
	
	//	승자는 포인트를 얻음
	//	패자는 총을 내려놓고 이동함
	//	승자는 해당 칸에 있는 총 중 가장 강한 총으로 갈아탐
	private static void result(Player winner, Player loser, int winnerNum, int loserNum) {
		int add = (winner.s + winner.gun) - (loser.s + loser.gun);	//	승자가 얻게 될 포인트
		winner.point += add;
		
		if(loser.gun > 0) {	//	패자가 총을 가지고 있을 경우
			gunMap[loser.x][loser.y].offer(loser.gun);	//	패자가 가지고 있던 총을 현재 칸에 내려놓음
			loser.gun = 0;
		}
		
		//	패자는 다른 칸으로 이동함
		while(true) {
			int nx = loser.x + dx[loser.d];
			int ny = loser.y + dy[loser.d];	//	이동할 다른 칸
			
			if(!isIn(nx, ny) || playerMap[nx][ny] != EMPTY) {	//	이동할 다른 칸이 범위 밖이거나, 다른 플레이어가 있을 경우
				loser.d = turn90(loser.d);	//	이동 방향 오른쪽으로 90도 회전
				continue;
			}
			
			if(!gunMap[nx][ny].isEmpty())	//	이동할 칸에 총이 있다면
				loser.gun = gunMap[nx][ny].poll();	//	가장 강한 총 획득하기
		
			loser.x = nx;
			loser.y = ny;
			playerMap[loser.x][loser.y] = loserNum;	//	패배한 플레이어는 이동
			break;
		}
		
		//	승자는 현재 칸에 놓인 총기 중 가장 강한 총기로 갈아탐
		if(!gunMap[winner.x][winner.y].isEmpty() && winner.gun < gunMap[winner.x][winner.y].peek()) {	//	현재 가지고 있는 총보다 바닥에 놓인 총이 더 강할 경우
			gunMap[winner.x][winner.y].offer(winner.gun);
			winner.gun = gunMap[winner.x][winner.y].poll();
		}
		
		playerMap[winner.x][winner.y] = winnerNum;
	}
	
	//	player가 이동
	private static void move(Player player) {
		int nx = player.x + dx[player.d];
		int ny = player.y + dy[player.d];	//	플레이어가 바라 보는 방향으로 한 칸 이동할 칸
		
		if(!isIn(nx, ny)) {	//	이동할 칸이 격자 밖인 경우
			player.d = reverseDir(player.d);	//	플레이어 반대 방향 보도록 변경
			nx = player.x + dx[player.d];
			ny = player.y + dy[player.d];		//	반대 방향으로 한 칸 이동
		}
		
		player.x = nx;
		player.y = ny;
	}
	
	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, 1, 0, -1};
	
	private static boolean isIn(int x, int y) {
		return (0 <= x && x < N) && (0 <= y && y < N);
	}
	
	//	dir의 반대 방향 찾기
	private static int reverseDir(int dir) {
		return (dir < 2) ? dir + 2 : dir - 2; 
	}
	
	//	dir에서 오른쪽으로 90도 회전한 방향 찾기
	private static int turn90(int dir) {
		return (dir + 1) % 4;
	}
	
	private static class Player {
		public final int num;		//	플레이어 번호
		public int x;
		public int y;		//	플레이어 위치
		public int d;		//	플레이어가 바라보는 방향
		public final int s;	//	플레이어 초기 능력치
		public int gun;		//	플레이어가 가지고 있는 총기 공격력
		public int point;	//	플레이어가 획득한 포인트
		
		public Player(int num, int x, int y, int d, int s) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.d = d;
			this.s = s;
		}
		
		//	this 플레이어랑 other 플레이어가 싸워서 this가 이기면 true, other가 이기면 false
		public boolean fight(Player other) {
			if(this.s + this.gun > other.s + other.gun)	//	(초기 능력치 + 총기 공격력)이 더 클 경우
				return true;
			else if(this.s + this.gun < other.s + other.gun)	//	(초기 능력치 + 총기 공격력)이 더 작을 경우
				return false;
			else {	//	(초기 능력치 + 총기 공격력)이 같을 경우
				if(this.s > other.s)	//	this의 초기 능력치가 높을 경우
					return true;
				else					//	other의 초기 능력치가 높을 경우
					return false;
			}
		}
	}
}	//	Main-class-end