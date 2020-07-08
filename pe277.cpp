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

bool f = false;
void cal(string s, LL init, LL minV) {
	int len = s.length();
	LL p = 3;
	for (int i = len - 2; i >= 0; i--, p*=3) {
		if (s[i] == 'D') init = init * 3;
		else if (s[i] == 'd') {
				init = (init * 3 + 1);
				if (init % 2 !=0) return ;
				init /= 2;
				
			}
		else {
			init = (init * 3 - 2);
			if (init % 4 !=0) return ;
			init /= 4;
		}
	}
	f = true;
	cout << init << " " << p << endl;
	LL times = (minV - init + p - 1) / p;
	cout << init + 1LL * times * p << endl;
}

int main() {
	//cal("DdDddUUdDD", 3, 1000000);
	//分析最后四个可以得到初始项满足的条件
	for (int c = 0; !f; c++) {
		int a = 16 * c + 8;
		LL init = 1LL * 3 * a + 2;
		cal("UDDDUdddDDUDDddDdDddDDUDDdUUDd", init, 1000000000000000);
	}

	
	


	
	
	return 0;
}

