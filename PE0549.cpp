// Min25有亚线性做法 计算S(10^10)仅需要2s

#include <bits/stdc++.h>
using namespace std;

template <typename A, typename B>
string to_string(pair<A, B> p);
 
template <typename A, typename B, typename C>
string to_string(tuple<A, B, C> p);
 
template <typename A, typename B, typename C, typename D>
string to_string(tuple<A, B, C, D> p);
 
string to_string(const string& s) {
  return '"' + s + '"';
}
 
string to_string(const char* s) {
  return to_string((string) s);
}
 
string to_string(bool b) {
  return (b ? "true" : "false");
}
 
string to_string(vector<bool> v) {
  bool first = true;
  string res = "{";
  for (int i = 0; i < static_cast<int>(v.size()); i++) {
    if (!first) {
      res += ", ";
    }
    first = false;
    res += to_string(v[i]);
  }
  res += "}";
  return res;
}
 
template <size_t N>
string to_string(bitset<N> v) {
  string res = "";
  for (size_t i = 0; i < N; i++) {
    res += static_cast<char>('0' + v[i]);
  }
  return res;
}
 
template <typename A>
string to_string(A v) {
  bool first = true;
  string res = "{";
  for (const auto &x : v) {
    if (!first) {
      res += ", ";
    }
    first = false;
    res += to_string(x);
  }
  res += "}";
  return res;
}
 
template <typename A, typename B>
string to_string(pair<A, B> p) {
  return "(" + to_string(p.first) + ", " + to_string(p.second) + ")";
}
 
template <typename A, typename B, typename C>
string to_string(tuple<A, B, C> p) {
  return "(" + to_string(get<0>(p)) + ", " + to_string(get<1>(p)) + ", " + to_string(get<2>(p)) + ")";
}
 
template <typename A, typename B, typename C, typename D>
string to_string(tuple<A, B, C, D> p) {
  return "(" + to_string(get<0>(p)) + ", " + to_string(get<1>(p)) + ", " + to_string(get<2>(p)) + ", " + to_string(get<3>(p)) + ")";
}
 
void debug_out() { cerr << endl; }
 
template <typename Head, typename... Tail>
void debug_out(Head H, Tail... T) {
  cerr << " " << to_string(H);
  debug_out(T...);
}
 
#ifdef LOCAL
#define debug(...) cerr << "[" << #__VA_ARGS__ << "]:", debug_out(__VA_ARGS__)
#else
#define debug(...) 42
#endif
 
void my_assert(bool x) {
  if (!x) {
    cout << "NO" << '\n';
    exit(0);
  }
}

// __builtin_popcount

typedef long long LL;

const int N = 100000000;

const int M = 100000000;
int primes[M / 10], cnt;
bool st[M + 10];

void get_primes(int n)
{
    for (int i = 2; i <= n; i ++ )
    {
        if (!st[i]) primes[cnt ++ ] = i;
        for (int j = 0; primes[j] <= n / i; j ++ )
        {
            st[primes[j] * i] = true;
            if (i % primes[j] == 0) break;
        }
    }
}

int cal(int num, int x) {
    int ans = 0, t = num;
    while (num <= x) {
        ans += x / num;
        num *= t;
    }
    return ans;
}

int find(int num, int c) {
    // debug(num, c);
    int l = num, r = num * c;
    while (l < r) {
        int mid = l + r >> 1;
        if (cal(num, mid) >= c) r = mid;
        else l = mid + 1;
    }
    return l;
}

int solve(int x) {
    vector<pair<int, int>> vp;
    int cur = x;
    for (int i = 0; primes[i] <= x; i++) {
        if (x % primes[i] == 0) {
            int c = 0;
            while (x % primes[i] == 0) {
                c++;
                x /= primes[i];
            }
            vp.push_back({primes[i], c});
        }
        if (x == 1) break;
        if (!st[x]) {
            vp.push_back({x, 1});
            break;
        }
    }
    
    
    // debug(cur, vp);
    
    int t = 0;
    for (int i = 0; i < vp.size(); i++) {
        int c = vp[i].second;
        int num = vp[i].first;
        //n / num + n / num^2 + ..n / num^3 + ... n / num^y> = c, find min y
        int y = find(num, c);
        t = max(t, y);
    }
    // debug(cur, t);
    return t;
}

void solve() {
    get_primes(N + 10);
    // for (int i = 0; i < cnt; i++) cout << primes[i] << " ";
    
    LL ans = 0;
    for (int i = 2; i <= N; i++) {
        ans += solve(i);
    }
    debug(N, ans);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    
    auto start = std::chrono::system_clock::now();
    solve();

    auto end = std::chrono::system_clock::now();
    auto elapsed = std::chrono::duration_cast<std::chrono::milliseconds>(end - start);
    std::cout << elapsed.count() <<"ms" << '\n';
    return 0;
}

