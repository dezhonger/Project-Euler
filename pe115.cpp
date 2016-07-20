#include <iostream>

using namespace std;

typedef long long LL;
#define MMAX 200
LL dp[MMAX + 1][2];
int n, m;
void run()
{
    dp[0][1] = 1;
    for(int i = 1; i <= m; i++) {
        dp[i][1] = 1;
        dp[i][0] = 0;
    }
    dp[m][0] = 1;
    for(int i = m + 1; i; i++) {
        dp[i][1] = dp[i-1][0] + dp[i-1][1];
        for(int k = i - m; k >= 0; k--) {
            dp[i][0] += dp[k][1];
        }
        if(dp[i][0] + dp[i][1] >= 1000000) {
            cout << i << endl;
            break;
        }
    }
    for(int i = 1; i <= MMAX; i++) {
        cout << i << " " << dp[i][0] << " " << dp[i][1] << " " << dp[i][0] + dp[i][1] << endl;
    }

}

int main()
{
    m = 50;
    run();
    return 0;
}
