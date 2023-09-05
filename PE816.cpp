#include <bits/stdc++.h>
using namespace std;
 
#ifdef LOCAL
#include "debug.h"
#else
#define debug(...) 42
#endif
 


// __builtin_popcount

// int dx[4] = {-1, 0, 1, 0}, dy[4] = {0, 1, 0, -1};
int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }


template<typename T, typename S>
inline bool upmin(T &a, const S &b) { return a > b ? a = b, 1 : 0; }

template<typename T, typename S>
inline bool upmax(T &a, const S &b) { return a < b ? a = b, 1 : 0; }

typedef long long LL;
#define SZ(v) int((v).size())
#define ALL(v) (v).begin(),(v).end()
#define F first
#define S second
typedef pair<int, int> PII;
typedef unsigned long long ULL;


int mod = (int)1e9 + 7;

int dx[4] = {-1, 0, 1, 0}, dy[4] = {0, 1, 0, -1};

// const int N = 1000010;
// int h[N], e[N], ne[N], idx;
// void add(int a, int b) {
    // e[idx] = b, ne[idx] = h[a], h[a] = idx++;
// }
LL powmod(LL a,LL b) {LL res=1;a%=mod; assert(b>=0); for(;b;b>>=1){if(b&1)res=res*a%mod;a=a*a%mod;}return res;}
const int N = 2000000;
vector<pair<LL, LL>> ps;

#define up(l,r,i) for(int i=l,END##i=r;i<=END##i;++i)
#define dn(r,l,i) for(int i=r,END##i=l;i>=END##i;--i)
typedef long long i64;
// const int INF =2147483647;
struct Point{LL x,y;};
typedef vector<Point>::iterator Iter;
bool cmpx(const Point a,const Point b){return a.x<b.x;}
bool cmpy(const Point a,const Point b){return a.y<b.y;}
double dis(const Point a,const Point b){
    return sqrt(pow(a.x-b.x,2)+pow(a.y-b.y,2));
}
void slv(const Iter l,const Iter r,double &d){
    if(r-l<=1) return;
    vector<Point> Q; Iter t=l+(r-l)/2;double w=t->x;
    slv(l,t,d),slv(t,r,d),inplace_merge(l,t,r,cmpy);
    for(Iter x=l;x!=r;++x)
        if(abs(w-x->x)<=d) Q.push_back(*x);
    for(Iter x=Q.begin(),y=x;x!=Q.end();++x){
        while(y!=Q.end()&&y->y<=x->y+d) ++y;
        for(Iter z=x+1;z!=y;++z) d=min(d,dis(*x,*z));
    }
}
vector<Point> X; int n; double ans=1e18;

int main2(){
	n = N;
    up(0,n-1,i){
		X.push_back({ps[i].F,ps[i].S});
    }
    sort(X.begin(),X.end(),cmpx),slv(X.begin(),X.end(),ans);
    printf("%.0lf\n",ans*ans);
    printf("%.10lf\n",ans);
    return 0;
}




int t;


void solve()
{

	
	LL m = 50515093;
	LL a = 290797, b = (a * a) % m;
	ps.push_back({a, b});
	for (int i = 1; i < N; i++)
	{
		a = (b * b) % m;
		b = (a * a) % m;
		ps.push_back({a, b});
	}
	
	main2();
	

}



int main() {
    ios::sync_with_stdio(false);
	cin.tie(0);
    // cin >> t;
	t = 1;
    while (t--) solve();
    return 0;
};

