#include <iostream>

using namespace std;

typedef long long LL;
LL dp[51][2];
const int N = 50;
void run()
{
    dp[0][1] = 1;
    dp[1][1] = dp[2][1] = dp[3][1] = 1;
    dp[1][0] = dp[2][0] = 0;
    dp[3][0] = 1;
    for(int i = 4; i <= N; i++) {
        dp[i][1] = dp[i-1][0] + dp[i-1][1];
        for(int k = i - 3; k >= 0; k--) {
            dp[i][0] += dp[k][1];
        }
    }
    for(int i = 1; i <= N; i++) {
        cout << dp[i][0] << " " << dp[i][1] << " " << dp[i][0] + dp[i][1] << endl;
    }

}

int main()
{
    run();
    return 0;
}
