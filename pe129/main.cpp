#include <iostream>

using namespace std;
const int N = 10000000;
int a[N];

int cal(int n)
{
    for(int i = 0; i < n; i++) a[i] = 0;
    int yushu = 0;
//    for(int i = 1; i <= n; i++) {
//        yushu = yushu * 10 + 1;
//        yushu %= n;
//        if(yushu == 0) {
//            for(int k = 1; k <= i; k++) cout << "1";
//            cout << endl;
//            return i;
//        }
//        if(a[yushu] == 0) a[yushu] = i;
//        else {
//            for(int k = 1; k <= i - a[yushu]; k++) cout << "1";
//            cout << endl;
//            return i;
//        }
//    }
//    return -1;
    for(int i = 1; i <= n; i++) {
        yushu = yushu * 10 + 1;
        yushu %= n;
        if(yushu == 0) return i;
    }
}
bool f(int x) {
    for(int a = 2; a * a <= x; a++) {
        if(x % a == 0) return 0;
    }
    return 1;
}


int main()
{
    int x = 1, cnt = 0, s = 0;
    while(cnt < 25) {
        x += 2;
        if(x % 5 == 0) continue;
        if(f(x) == 1) continue;
        int ans = cal(x);
        if((x - 1) % ans == 0) {
            cout << x << " " << ans << endl;
            cnt++;
            s += x;
        }

    }
    cout << s;
    return 0;
}
