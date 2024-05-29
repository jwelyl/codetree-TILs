#include<iostream>

using namespace std;

int N;

long long fnd(long long n) {
	return n - n / 3 - n / 5 + n / 15;
}

long long para_search() {
	
	long long left = 1;
	long long right = 2000000000;
	long long mid;
	long long min_num=2000000000;

	while (left <= right) {
		mid = (left + right) / 2;

		if (fnd(mid) >= N) {
			right = mid - 1;
			min_num = min(min_num, mid);
		}
		else if (fnd(mid) < N) left = mid + 1;
	}
	
	return min_num;
}

int main(void) {

	cin >> N;

	cout << para_search();

	return 0;
}