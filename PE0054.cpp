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

#define param vector<pp> v1, vector<pp> v2
#define F first
#define S second
typedef long long LL;
typedef pair<int, int> pp;

int getMaxSingle(vector<pp> v) {
    return v[4].F;
}

bool isDouble(vector<pp> v) {
    for (int i = 1; i < 5; i++) if (v[i].F == v[i - 1].F) return true;
    return false;
}

int calDouble(vector<pp> v) {
    int single = 1, ans = 0;
    for (int i = 0; i < 5; i++) {
        if (v[i].F != v[i + 1].F) {
            ans += single * v[i].F;
            single *= 100;
        } else {
            ans += 1000000 * v[i].F;
            i++;
        }
    }
    // debug(v);
    assert(single == 1000000);
    // debug(v,ans);
    return ans;
}

bool isDoubleDouble(vector<pp> v) {
    return (v[0].F == v[1].F && v[2].F == v[3].F) || 
            (v[0].F == v[1].F && v[3].F == v[4].F) || 
            (v[1].F == v[2].F && v[3].F == v[4].F);
}

bool calDoubleDouble(vector<pp> v) {
    if ((v[0].F == v[1].F && v[2].F == v[3].F)) {
        return v[2].F * 10000 + v[0].F * 100 + v[4].F;
    }
    if (((v[0].F == v[1].F && v[3].F == v[4].F))) {
        return v[3].F * 10000 + v[0].F * 100 + v[2].F;
    }
    if ((v[1].F == v[2].F && v[3].F == v[4].F)) {
        return v[3].F * 10000 + v[1].F * 100 + v[0].F;
    }
    cout << "error in calDoubleDouble" << endl;
    return 0;
}


int isDoubleTriple(vector<pp> v) {
    if ((v[0].F == v[1].F && v[2].F == v[4].F)) return v[2].F;
    if ((v[0].F == v[2].F && v[3].F == v[4].F)) return v[2].F;
    return 0;
}

int isTriple(vector<pp> v) {
    if (v[0].F == v[2].F) return v[0].F;
    if (v[1].F == v[3].F) return v[1].F;
    if (v[2].F == v[4].F) return v[2].F;
    return 0;
}

int isQuadruple(vector<pp> v) {
    if ((v[0].F == v[3].F)) return v[0].F;
    if ((v[1].F == v[4].F)) return v[1].F;
    return 0;
}

bool isConsecutive(vector<pp> v) {
    for (int i = 1; i < 5; i++) if (v[i].F != v[i - 1].F + 1) return false;
    return true;
}

bool isSameColor(vector<pp> v) {
    for (int i = 1; i < 5; i++) if (v[i].S != v[0].S) return false;
    return true;
}

int level(vector<pp> v) {
    if (isSameColor(v) && isConsecutive(v)) return 9;
    if (isQuadruple(v)) return 8;
    if (isDoubleTriple(v)) return 7;
    if (isSameColor(v)) return 6;
    if (isConsecutive(v)) return 5;
    if (isTriple(v)) return 4;
    if (isDoubleDouble(v)) return 3;
    if (isDouble(v)) return 2;
    return 1;
}

int levelWithConsecutive(vector<pp> v) {
    if (isConsecutive(v)) return 5;
    if (isTriple(v)) return 4;
    if (isDoubleDouble(v)) return 3;
    if (isDouble(v)) return 2;
    return 1;
}
 

int solve(vector<pp> v1, vector<pp> v2) {

    
    int l1 = level(v1), l2 = level(v2);
    // debug(v1, l1);
    // debug(v2, l2);
    if (l1 > l2) return 1;
    if (l1 < l2) return -1;
    
    if (l1 == 9) return v1[2].F - v2[2].F;
    if (l1 == 8) isQuadruple(v1) - isQuadruple(v2);
    if (l1 == 7) isDoubleTriple(v1) - isDoubleTriple(v2);
    if (l1 == 6) {
        //isSameColor
        int ll1 = levelWithConsecutive(v1);
        int ll2 = levelWithConsecutive(v2);
        if (ll1 > ll2) return 1;
        if (ll1 < ll2) return -1;
        else {
            l1 = ll1;
            goto TTT;
        }
    }
    TTT:
    if (l1 == 5) return v1[2].F - v2[2].F;
    if (l1 == 4) isTriple(v1) - isTriple(v2);
    if (l1 == 3) {
        //doubledouble
        return calDoubleDouble(v1) - calDoubleDouble(v2);
    }
    if (l1 == 2) {
        //double
        return calDouble(v1) - calDouble(v2);
    }
    if (l1 == 1) {
        for (int i = 4; i >= 0; i--) if (v1[i].F != v2[i].F) return v1[i].F - v2[i].F;
    }
    cout << "error in cal()" << endl;
    return 0;
    
    
}

unordered_map<char, int> mp1 = {{'T', 10}, {'J', 11}, {'Q', 12}, {'K', 13}, {'A', 14},};
unordered_map<char, int> mp2 = {{'S', 1}, {'H', 2}, {'C', 3}, {'D', 4},};
inline pp toPp(string str) {
    pp p;
    if (isdigit(str[0])) {
        p.F = str[0] - '0';
    } else {
        p.F = mp1[str[0]];
    }
    
    p.S = mp2[str[1]];
    return p;
    
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    auto start = std::chrono::system_clock::now();
    freopen("in.txt", "r", stdin);
    int T = 1000, res = 0;
    while (T--) {
        string str;
        vector<pp> v1, v2;
        for (int i = 0; i < 5; i++) {
            cin >> str;
            // debug(str);
            v1.push_back(toPp(str));
        }
        for (int i = 0; i < 5; i++) {
            cin >> str;
            v2.push_back(toPp(str));
        }
        // debug(v1, v2);
        sort(v1.begin(), v1.end());
        sort(v2.begin(), v2.end());
        // debug(v1, v2);
        int ans = solve(v1, v2);
        assert(ans != 0);
        if (ans > 0) res++;
        // debug(T, ans);
    }
    cout << "res: " << res << endl;
    

    auto end = std::chrono::system_clock::now();
    auto elapsed = std::chrono::duration_cast<std::chrono::milliseconds>(end - start);
    std::cout << elapsed.count() <<"ms" << '\n';
    return 0;
}

