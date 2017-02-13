#include <iostream>

using namespace std;

#define REP(i,n) for((i)=0;(i)<(int)(n);(i)++)

typedef long long ll;
ll dp[20][140][400]; // digit, carry, s(137) - s(1) + 200

int main(void){
	int i,j,carry,s,x;

	dp[0][0][200] = 1;
	REP(i,18) REP(carry,140) REP(s,400) if(dp[i][carry][s] != 0) REP(x,10){
		int carry2 = (carry + x * 137) / 10;
		int s2 = s + (carry + x * 137) % 10 - x;
		dp[i+1][carry2][s2] += dp[i][carry][s];
	}

	ll ans = 0;
	REP(i,140) REP(j,400) if(dp[18][i][j] > 0){
		int tmp = i, sum = 0;
		while(tmp > 0) {sum += tmp % 10; tmp /= 10;}
		if(sum + j == 200) ans += dp[18][i][j];
	}

	cout << ans << endl;

	return 0;
}
