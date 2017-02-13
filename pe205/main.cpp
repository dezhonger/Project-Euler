#include <iostream>

using namespace std;

int dp[2][40];

void dfs(int index, int sum, int ma, int cnt, int d) {
    if(index == cnt) {
        dp[d][sum]++;
        return ;
    }
    for(int i = 1; i <= ma; i++) {
        dfs(index + 1, sum + i, ma, cnt, d);
    }
}

int main()
{
    dfs(0, 0, 4, 9, 0);
    dfs(0, 0, 6, 6, 1);
    int s1 = 0, s2 = 0;
    for(int i = 1; i <= 36; i++) {
        s1 += dp[0][i];
        s2 += dp[1][i];
    }
    cout << s1 << " " << s2 << endl;
    return 0;
}
