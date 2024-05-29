import java.util.Scanner;

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    // 변수 선언
    public static long n;
    
    public static long GetNumOfNumbers(long mid) {
        // moo의 수를 세어줍니다.
        long mooCnt = mid / 3 + mid / 5 - mid / 15;
    
        // 전체 수(mid)에서 moo의 수를 빼면
        // 서로 다른 수의 개수가 구해집니다.
        return mid - mooCnt;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextLong();

        // 이 문제에서는
        // [1 ~ K]까지 적혀있는 서로 다른 수의 개수가
        // N 이상인 경우 중 가능한 K의 최솟값을 구하면 답이 됩니다.

        long left = 1;                          // 가장 작은 숫자 값을 설정합니다.
        long right = INT_MAX;                   // 가장 큰 숫자 값을 설정합니다.
        long minNum = INT_MAX;                 // 최소이므로, 답이 될 수 있는 값 중 최댓값으로 설정합니다.

        while (left <= right) {                      // [left, right]가 유효한 구간이면 계속 수행합니다.
            long mid = (left + right) / 2;      // 가운데 위치를 선택합니다.
            if(GetNumOfNumbers(mid) >= n) {          // 1부터 mid까지의 서로 다른 수의 개수가 n보다 같거나 크다면
                right = mid - 1;                     // 왼쪽에 조건을 만족하는 숫자가 더 있을 가능성 때문에 right를 바꿔줍니다.
                minNum = Math.min(minNum, mid);         // 답의 후보들 중 최솟값을 계속 갱신해줍니다.
            }
            else                               
                left = mid + 1;                      // 그렇지 않다면 left를 바꿔줍니다.
        }

        System.out.print(minNum);                             // 조건을 만족하는 최솟값을 반환합니다.
    }
}