#include <iostream>
#include <set>
#include <tuple>

using namespace std;

// 변수 선언
int n, m;
set<int> s_num;
set<tuple<int, int, int> > s_len; // (길이, 시작 숫자, 끝 숫자)

int main() {
    // 입력:
    cin >> n >> m;

    // s_num : 지워진 숫자 모음(코드의 깔끔한 처리를 위해
    // 범위 밖의 숫자를 treeset에 추가했습니다)
    s_num.insert(-1);
    s_num.insert(n + 1);
    // s_len : (구간의 길이, 시작 숫자, 끝 숫자)
    // 길이가 긴 구간부터 나오도록 구간의 길이에 -를 붙여서 넣어줍니다.
    s_len.insert(make_tuple(n + 1, -1, n + 1));

    for(int i = 0; i < m; i++) {
        // 숫자를 입력받습니다.
        int y;
        cin >> y;

        // 입력받은 숫자 y를 기준으로
        // 그 바로 뒤의 숫자를 z, 바로 앞의 숫자를 x라고 두었습니다.
        set<int>::iterator it;
        it = s_num.upper_bound(y);
        int z = *it;
        
        it--;
        int x = *it;

        // 입력받은 숫자를 treeset에 추가합니다.
        s_num.insert(y);
        
        // 구간의 길이는 (x ~ z) 구간이 사라지며,
        // (x ~ y) 구간과 (y ~ z) 구간이 새로 생겨납니다.
        s_len.erase(make_tuple(
            (z - x - 1), x, z
        ));
        s_len.insert(make_tuple(
            (y - x - 1), x, y
        ));
        s_len.insert(make_tuple(
            (z - y - 1), y, z
        ));
        

        // y가 추가된 후로 구간 중 가장 긴 구간을 찾아 출력합니다.
        int best_length;
        tie(best_length, ignore, ignore) = *s_len.rbegin();
        cout << best_length << endl;
    }

    return 0;
}