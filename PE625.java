package com.netease.music.pe;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dezhonger on 2019/3/26
 */
public class PE625 {

    static int N = 6000010;
    static long MOD = 998244353;

    static boolean vis[] = new boolean[N];
    static int[] mu = new int[N];
    static int[] phi = new int[N];
    static long[] sum2 = new long[N];
    static int cnt;
    static int[] prim = new int[N];
    static Map<Long, Long> w1 = new HashMap<>();
    static Map<Integer, Integer> w = new HashMap<>();

    static void get(int maxn) {
        phi[1] = mu[1] = 1;
        for (int i = 2; i <= maxn; i++) {
            if (!vis[i]) {
                prim[++cnt] = i;
                mu[i] = -1;
                phi[i] = i - 1;
            }
            for (int j = 1; j <= cnt && prim[j] * i <= maxn; j++) {
                vis[i * prim[j]] = true;
                if (i % prim[j] == 0) {
                    phi[i * prim[j]] = phi[i] * prim[j];
                    break;
                } else {
                    mu[i * prim[j]] = -mu[i];
                    phi[i * prim[j]] = phi[i] * (prim[j] - 1);
                }
            }
        }
        for (int i = 1; i <= maxn; i++) {
            sum2[i] = sum2[i - 1] + phi[i];
            sum2[i] %= MOD;
        }
    }


    static long djsphi(long x) {
        if (x <= N - 10) return sum2[(int) x];
        if (w1.get(x) != null) return w1.get(x);
        long xx = x % MOD;
        long ans = xx * (xx + 1) / 2;
        ans %= MOD;
        for (long l = 2, r; l <= x; l = r + 1) {
            r = x / (x / l);
            ans = subs(ans, ((r - l + 1) % MOD) * djsphi(x / l) % MOD);
//            ans -= (r - l + 1) * djsphi(x / l);
        }
        w1.put(x, ans);
        return ans;
    }

    static long subs(long a, long b) {

        long x = a % MOD - b % MOD;
        return (x + MOD) % MOD;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        get(N - 10);
        long s = 0;
//        long n = 10L;
        long n = 1000_0000_0000L;
        long r;
        for (long l = 1; l <= n; l = r + 1) {
            r = n / (n / l);
            long ph = djsphi(n / l);
            long t;
            if ((l + r) % 2 == 0) {
                long p1 = ((l + r) / 2) % MOD;
                long p2 = ((r - l + 1)) % MOD;
                t = (p1 * p2) % MOD;
            } else {
                long p1 = ((l + r)) % MOD;
                long p2 = ((r - l + 1) / 2) % MOD;
                t = (p1 * p2) % MOD;
            }

            s += 1L * (t * ph % MOD);
            s %= MOD;
        }
        System.out.println(s);
        long end = System.currentTimeMillis();
        System.out.println((1.0 * (end - start) / 1000) + "ms");
    }
}
