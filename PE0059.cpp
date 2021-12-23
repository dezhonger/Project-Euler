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

vector<int> s;
int space;

void solve(vector<int>& v) {
    string str;
    int ans = 0;
    for (int o = 0; o < s.size(); o++) {
        char c = (s[o] ^ v[o % v.size()]);
        str += c;
        ans += c;
    }
    for (int i = 0; i < v.size(); i++) cout << v[i] << " \n"[i == v.size() - 1];
    cout << str << endl;
    debug(ans);
}

void solve() {
    int t, c = 0;
    while (cin >> t) {
        c++;
        s.push_back(t);
    }
    
    unordered_map<string , int> cc;
    for (int i = 4; i < s.size(); i++) {
        string sk = to_string(s[i - 4]) + ","
        + to_string(s[i - 3]) + ","
        + to_string(s[i - 2]) + ","
        + to_string(s[i - 1]) + ","
        + to_string(s[i - 0]);
        cc[sk]++;
    }
    
    int maxCount = 0;
    string the;
    for (auto kv: cc) {
        if (kv.second > maxCount) {
            maxCount =  kv.second;
            the = kv.first;
        }
    }
    //the means the ASCII of string " the ".
    debug(the);
    //88 4 13 29 80
    //   t h  e 
    
    // then
    // debug(88 ^ ' ');
    // debug(4 ^ 't');
    // debug(13 ^ 'h');
    // debug(29 ^ 'e');
    // debug(80 ^ ' ');
    // 13 29 80 => 101 120 112 the password is 101 120 112
    
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    freopen("in.txt", "r", stdin);
    // freopen("out.txt", "w", stdout);
    
    auto start = std::chrono::system_clock::now();
    vector<int> vv = {101,120,112};
    solve();
    solve(vv);
    // solve();
    

    auto end = std::chrono::system_clock::now();
    auto elapsed = std::chrono::duration_cast<std::chrono::milliseconds>(end - start);
    std::cout << elapsed.count() <<"ms" << '\n';
    return 0;
}

