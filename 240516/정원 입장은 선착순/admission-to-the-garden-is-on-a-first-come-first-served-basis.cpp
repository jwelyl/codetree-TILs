#include <iostream>
#include <queue>

// 입력받는 사람 목록
std::priority_queue<std::pair<int, int>, std::vector<std::pair<int, int>>,
    std::greater<std::pair<int, int>>> pQue;
// 입장하기 위해 기다릴 사람들
std::priority_queue<std::pair<int, int>, std::vector<std::pair<int, int>>,
    std::greater<std::pair<int, int>>> temppQue;
int maxtime;
// 사람마다 머물시간
int timearr[100001];

int main() {
    int N;
    std::cin >> N;

    int a, t;
    for (int i = 0; i < N; i++)
    {
        std::cin >> a >> t;

        timearr[i] = t;
        // 도착시간, 번호
        pQue.emplace(a, i);
    }

    int max = 0;
    while (!pQue.empty())
    {
        // 정원에 입장하기 위해서 기다려야 할 사람들
        while (!pQue.empty())
        {
            int arrivetime = pQue.top().first;
            if (arrivetime > maxtime) break;

            // 번호, 도착시간
            temppQue.emplace(pQue.top().second, arrivetime);
            pQue.pop();
        }

        // 기다려야할 사람이 있다면
        if (!temppQue.empty())
        {
            // 도착 시간
            a = temppQue.top().second;
            // 머물 시간
            t = timearr[temppQue.top().first];
            temppQue.pop();
        }
        // 없다면
        else
        {
            // 도착 시간
            a = pQue.top().first;
            // 머물 시간
            t = timearr[pQue.top().second];
            pQue.pop();
        }

        // 최초 입장인 경우
        if (maxtime == 0)
        {
            maxtime = a + t;
        }
        else
        {
            // 입장하기 위해 기다린 경우
            if (maxtime > a)
            {
                max = std::max(max, maxtime - a);
                maxtime += t;
            }
            // 안기다린 경우
            else
                maxtime = a + t;
        }
    }

    std::cout << max;

    return 0;
}