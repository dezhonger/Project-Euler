#include <vector>
#include <list>
#include <map>
#include <set>
#include <deque>
#include <queue>
#include <stack>
#include <bitset>
#include <algorithm>
#include <functional>
#include <numeric>
#include <utility>
#include <sstream>
#include <iostream>
#include <iomanip>
#include <cstdio>
#include <cmath>
#include <cstdlib>
#include <cctype>
#include <string>
#include <cstring>
#include <ctime>
#include <cassert>
#include <string.h>
using namespace std;

#define rep(i, a, b) for(int i = (a); i <= (b); i++)
#define reps(i, a, b) for(int i = (a); i < (b); i++)
#define pb push_back
#define ps push
#define mp make_pair
#define CLR(x,t) memset(x,t,sizeof x)
#define LEN(X) strlen(X)
#define F first
#define S second
#define Debug(x) cout<<#x<<"="<<x<<endl;


const double euler_r = 0.57721566490153286060651209;
const double pi = 3.141592653589793238462643383279;
const double E = 2.7182818284590452353602874713526;
const int inf = ~0U >> 1;
const int MOD = int (1e9) + 7;
const double EPS = 1e-6;

typedef long long LL;

int w(LL x) {
	if (x == 0) return 1;
	return 1 + (int)log10(x);
}

string d(int a[]) {
	string s;
	for (int k = 0; k < 10; k++)  s += a[k] + '0';
	return s;
}
	

bool g(int v[], LL value, int len) {
	int cnt[10];
	CLR(cnt, 0);
	int last = -1;
	for (int i = 0; i < len; i++) {
		int m = value % 10;
		if (v[m] == 1) return false;
		if (cnt[m] > 0) return false;
		cnt[m]++;
		value /= 10;
		last = m;
	}
	if (len > 1 && last == 0) return false;
	return true;
}

void g2(int v[], LL value, int len) {
	for (int i = 0; i < len; i++) {
		int m = value % 10;
		v[m] = 1;
		value /= 10;
	}
}

bool dfs(int index, int a[], int v[], int x) {
	if (index == 10) {
		for (int i = 0; i < 10; i++) if (v[i] == 0) return 0;
		return 1;
	}
	LL cur = 0;
	bool result = false;
	for (int i = index; i < 10; i++) {
		cur = cur * 10 + a[i];
		if (cur % x == 0) {
			bool r = g(v, cur / x, w(cur / x));
			if (!r) continue;
			
			int tmp[10];
			for (int o = 0; o < 10; o++) tmp[o] = v[o];
			g2(v, cur / x, w(cur / x));
			result |= dfs(i + 1, a, v, x);
			for (int o = 0; o < 10; o++) v[o] = tmp[o];
		}
		if (result) return result;
	}
	return result;
		
}

bool f(int x, int a[]) {
	int v[10];
	CLR(v, 0);
	int y = x;
	
	while(y > 0) {
		v[y%10] = 1;
		y /= 10;
	}
	
	return dfs(0, a, v, x);
	
}

int main() {
	int a[10];
	bool m = false;
	for (int i = 9; i >= 0; i--) a[i] = 9 - i;
	do {
		for (int i = 2; i < 100; i++) {
			if (i % 10 == 0) continue;
			if (f(i, a)) {
				LL ans = 0;
				for (int k = 0; k < 10; k++) {
					ans = ans * 10 + a[k];
				}
				Debug(i);
				Debug(d(a));
				m = true;
				break;
			}
		}
		if (m) break;
	} while (prev_permutation(a, a + 10));
	return 0;
}
