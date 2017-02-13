#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

bool is_prime(int n)
{
    if(n <= 1) return false;
    for(int x = 2; x * x <= n; x++) {
        if(n % x == 0) return false;
    }
    return true;
}

vector<int> prime_sieve(int n)
{
    vector<int> ans(1);
    ans[0] = 2;
    int p = 3;
    while(p < n) {
        if(is_prime(p)) {
            ans.push_back(p);
        }
        p += 2;
    }
    return ans;
}

#define N 5000

void cal()
{
    vector<int> ans = prime_sieve(N);
    int sum = accumulate(ans.begin(), ans.end(), 0);
    cout << sum << endl;
    vector<long long> dp(sum + 1);
    dp[0] = 1;

    int sumP = 0;
    for(int i = 0; i < ans.size(); i++) {
        sumP += ans[i];
        for(int j = sumP; j >= ans[i]; j--) {
            dp[j] += dp[j - ans[i]];
            dp[j] %= 10000000000000000L;
        }
    }
    long long xx = 0;
    for(int i = 0; i <= sum; i++) {
        if(is_prime(i)) xx += dp[i];
        xx  %= 10000000000000000L;
    }
    cout << xx;
}


int main()
{
    cal();
    return 0;
}
