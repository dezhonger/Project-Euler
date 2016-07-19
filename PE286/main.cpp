#include <iostream>
#include <cmath>
using namespace std;

double q;
double ans[51][21];
bool hasCal[51][21];

double f(int n) {
    return 1.0 * (1 - 1.0 * n / q);
}

double result(int n = 50, int k = 20) {
//    dp[n][k] = dp[n-1][k-1]*(f(n)) + dp[n-1][k]*(1-f(n));
    if(hasCal[n][k]) return ans[n][k];
    double temp;
    if(n < k) return 0;
    if(n > 1 && k > 0)
        temp =  1.0 * result(n - 1, k - 1) * f(n) + result(n - 1, k) * (1 - f(n));
    if(n > 1 && k == 0) temp = result(n - 1, k) * (1 - f(n));
    if(n == 1 && k == 1) temp = f(1);
    if(n == 1 && k == 0) temp = 1 - f(1);
    hasCal[n][k] = true;
    return ans[n][k] = temp;
}
void init() {
    for(int i = 0; i < 51; i++) {
        for(int j = 0; j < 21; j++) {
            hasCal[i][j] = false;
        }
    }
}
int main()
{
    q = 50;
//    for(int i = 50; i <= 100; i++) {
//        init();
//        q = i;
//        cout << result() << endl;
//    }

    double L = 50, R = 60;
    cout.precision(16);
    while(L + 1e-13 < R) {
        init();
        double mid = (L + R) / 2;
        q = mid;
        cout << mid << " " << result() << endl;
        if(fabs(result() - 0.2) <= 0.001) {
            cout << mid << endl;
            break;
        }
        if(result() > 0.02) L = mid;
        else R = mid;
    }
    return 0;
}
