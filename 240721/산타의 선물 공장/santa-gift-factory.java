import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	private static final int NONE = -1;
	
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final StringBuilder sb = new StringBuilder();
	private static StringTokenizer tokens;
	
	private static int Q;			//	명령어 개수
	private static int N;			//	상자 개수
	private static int M;			//	컨베이어 벨트 개수
	
	private static Box[] heads;	//	컨베이어 벨트의 head
	private static Box[] tails;	//	컨베이어 벨트의 tail
	private static boolean[] brokens;	//	broken[i] : i번째 컨베이어 벨트가 고장났을 경우 true
	private static final Map<Integer, Box> boxMap = new HashMap<>();	//	Key : 상자 ID, Value : 해당 상자 객체
	private static final Map<Integer, Integer> beltMap = new HashMap<>();	//	Key : 상자 ID, Value : 해당 상자가 있는 벨트 번호
	
	public static void main(String[] args) throws IOException {
		Q = Integer.parseInt(br.readLine());
		
		for(int q = 0; q < Q; q++) {
			tokens = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(tokens.nextToken());
			
			switch(cmd) {
			case 100:	//	초기화
				init();
				break;
			case 200:	//	물건 하차
				int wMax = Integer.parseInt(tokens.nextToken());
				
				sb.append(unload(wMax)).append("\n");
				break;
			case 300:	//	물건 제거
				int rId = Integer.parseInt(tokens.nextToken());
				
				sb.append(removeBox(rId)).append("\n");
				break;
			case 400:	//	물건 확인
				int fId = Integer.parseInt(tokens.nextToken());
				
				sb.append(findBox(fId)).append("\n");
				break;
			case 500:	//	벨트 고장
				int bNum = Integer.parseInt(tokens.nextToken());
				
				sb.append(breakBelt(bNum)).append("\n");
				break;
			}
		}
		
		System.out.print(sb);
	}	//	main-end
	
	//	100. 공장 설립
	private static void init() {
		N = Integer.parseInt(tokens.nextToken());	//	N개의 상자
		M = Integer.parseInt(tokens.nextToken());	//	M개의 컨베이어 벨트
		
		heads = new Box[M + 1];
		tails = new Box[M + 1];
		brokens = new boolean[M + 1];
		
		for(int i = 1; i <= M; i++) {
			heads[i] = new Box(NONE, NONE);
			tails[i] = new Box(NONE, NONE);
			
			heads[i].next = tails[i];
			tails[i].prev = heads[i];
		}
		
		int[] ids = new int[N + 1];
		int[] ws = new int[N + 1];
		
		for(int i = 1; i <= N; i++)
			ids[i] = Integer.parseInt(tokens.nextToken());
		for(int i = 1; i <= N; i++)
			ws[i] = Integer.parseInt(tokens.nextToken());
		
		for(int i = 0; i < M; i++) {	//	i + 1 번째 벨트
			int start = i * (N / M) + 1;
			int end = (i + 1) * (N / M);
			
			for(int j = start; j <= end; j++) {
				Box box = new Box(ids[j], ws[j]);	//	i + 1번째 벨트의 가장 마지막에 삽입
				boxMap.put(box.id, box);
				pushBack(i + 1, box);
			}
		}
	}
	
	//	200. 물건 하차
	//	각 벨트의 가장 앞에 있는 물건 중 무게가 wMax 이하인 것들을 하차시킴
	//	wMax 초과의 물건은 해당 벨트의 가장 뒤로 보냄
	//	하차한 물건들의 무게 합을 반환
	private static long unload(int wMax) {
		long sum = 0L;
		
		for(int bNum = 1; bNum <= M; bNum++) {
			if(isEmpty(bNum) || isBroken(bNum))	//	빈 벨트이거나, 고장난 벨트는 skip
				continue;
			
			Box first = popFront(bNum);	//	bNum의 가장 앞에 있는 상자
			
			if(first.w <= wMax)	//	wMax 이하 상자일 경우
				sum += first.w;	//	하차 시킴
			else	//	wMax 초과 상자일 경우
				pushBack(bNum, first);	//	해당 벨트 가장 마지막에 다시 삽입함
		}
		
		return sum;
	}
	
	//	300. 물건 제거
	//	rId에 해당하는 상자 객체를 벨트에서 제거한다.
	public static int removeBox(int rId) {
		if(!boxMap.containsKey(rId))	//	그런 상자가 없을 경우
			return NONE;
		
		if(beltMap.getOrDefault(rId, NONE) == NONE)	//	rId 상자가 이미 어떤 벨트에도 존재하지 않는 경우
			return NONE;
		
		Box rBox = boxMap.get(rId);	//	제거할 상자
		Box before = rBox.prev;
		Box after = rBox.next;
		
		before.next = after;
		after.prev = before;
		
		rBox.prev = null;
		rBox.next = null;
		beltMap.put(rId, NONE);
		
		return rId;
	}
	
	//	400. 물건 확인
	private static int findBox(int fId) {
		if(!boxMap.containsKey(fId))	//	그런 상자가 없을 경우
			return NONE;
		
		int bNum = beltMap.getOrDefault(fId, NONE);	//	fId 상자가 존재하는 벨트 번호
		
		if(bNum == NONE)	//	fId 상자가 이미 어떤 벨트에도 존재하지 않는 경우
			return NONE;
		
		Box first = boxMap.get(fId);	//	이동시킬 가장 앞의 상자
		Box last = tails[bNum].prev;	//	이동시킬 가장 마지막 상자
		
		if(first == heads[bNum].next)
			return bNum;
		
		Box pos1 = heads[bNum].next;
		Box pos2 = first.prev;
		
		pos2.next = tails[bNum];
		tails[bNum].prev = pos2;
		
		heads[bNum].next = first;
		first.prev = heads[bNum];
		last.next = pos1;
		pos1.prev = last;
		
		return bNum;
	}
	
	//	500. 벨트 고장
	private static int breakBelt(int bNum) {
		if(isEmpty(bNum) || isBroken(bNum))
			return NONE;
		
		int toBelt = NONE;	//	현재 bNum 벨트에 있는 상자들을 옮길 벨트 찾기
		for(int num = bNum + 1; num <= M; num++) {
			if(isBroken(num))
				continue;
			
			toBelt = num;
			break;
		}
		
		if(toBelt == NONE) {
			for(int num = 1; num < bNum; num++) {
				if(isBroken(num))
					continue;
				
				toBelt = num;
				break;
			}
		}
		
		Box fromFirst = heads[bNum].next;
		Box fromLast = tails[bNum].prev;
		
		Box cur = fromFirst;
		while(cur != tails[bNum]) {
			beltMap.put(cur.id, toBelt);
			cur = cur.next;
		}
		
		Box toLast = tails[toBelt].prev;
		
		toLast.next = fromFirst;
		fromFirst.prev = toLast;
		fromLast.next = tails[toBelt];
		tails[toBelt].prev = fromLast;
		
		heads[bNum].next = tails[bNum];
		tails[bNum].prev = heads[bNum];
		
		brokens[bNum] = true;
		return bNum;
	}

	//	bNum 벨트가 비어있는지 확인
	private static boolean isEmpty(int bNum) {
		return heads[bNum].next == tails[bNum] && tails[bNum].prev == heads[bNum];
	}
	
	//	bNum 벨트가 고장났는지 확인
	private static boolean isBroken(int bNum) {
		return brokens[bNum];
	}
	
	//	bNum 벨트의 가장 처음 상자를 제거하고 반환함
	private static Box popFront(int bNum) {
		Box first = heads[bNum].next;	//	제거할 처음 상자
		Box nFirst = first.next;		//	새로 처음 상자가 될 상자
		
		heads[bNum].next = nFirst;
		nFirst.prev = heads[bNum];
		
		first.prev = null;
		first.next = null;
		
		beltMap.put(first.id, NONE);	//	first 상자를 bNum 벨트에서 제거
		
		return first;
	}
	
	//	bNum 벨트의 가장 마지막에 box를 삽입
	private static void pushBack(int bNum, Box box) {
		Box before = tails[bNum].prev;	//	bNum 벨트의 가장 마지막 상자
		
		before.next = box;
		box.prev = before;
	
		tails[bNum].prev = box;
		box.next = tails[bNum];
		
		beltMap.put(box.id, bNum);	//	box는 bNum 벨트에 존재함
	}
	
	private static class Box {
		public final int id;	//	상자 고유 번호
		public final int w;		//	상자 무게
		
		public Box prev;		//	이전 상자
		public Box next;		//	다음 상자
		
		public Box(int id, int w) {
			this.id = id;
			this.w = w;
			this.prev = null;
			this.next = null;
		}
	}
}	//	Main-class-end