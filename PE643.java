package com.netease.music.pe;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dezhonger on 2019/3/19
 * 2^t = x,  ans = sigma(i, 1, upper/x)phi(i)
 *
 * 杜教筛
 * ans = 968274154
 */
public class PE643 {


    static int N = 6000010;
    static long MOD = 10_0000_0000 + 7;

    static boolean vis[] = new boolean[N];
    static int[] phi = new int[N];
    static long[] sum2 = new long[N];
    static int cnt;
    static int[] prim = new int[N];
    static Map<Long, Long> w1 = new HashMap<>();
    static long inv2 = inv(2);

    static void get(int maxn) {
        phi[1] = 1;
        for (int i = 2; i <= maxn; i++) {
            if (!vis[i]) {
                prim[++cnt] = i;
                phi[i] = i - 1;
            }
            for (int j = 1; j <= cnt && prim[j] * i <= maxn; j++) {
                vis[i * prim[j]] = true;
                if (i % prim[j] == 0) {
                    phi[i * prim[j]] = phi[i] * prim[j];
                    break;
                } else {
                    phi[i * prim[j]] = phi[i] * (prim[j] - 1);
                }
            }
        }
        for (int i = 1; i <= maxn; i++) {
            sum2[i] = add(sum2[i - 1], phi[i]);
        }
    }

    static long djsphi(long x) {
        if (x <= N - 10) return sum2[(int) x];
        if (w1.get(x) != null) return w1.get(x);
        long ans = mul(x, mul(x + 1, inv2));
        for (long l = 2, r; l <= x; l = r + 1) {
            r = x / (x / l);
            ans = add(ans, MOD - mul((r - l + 1) , djsphi(x / l)));
        }
        w1.put(x, ans);
        return ans;
    }

    static long add(long a, long b) {
        return (a + b) % MOD;
    }

    static long mul(long a, long b) {
        return ((a % MOD) * (b % MOD)) % MOD;
    }

    static long inv(long a) {
        return a == 1 ? 1 : mul(MOD - MOD / a, inv(MOD % a));
    }


    public static void main(String[] args) {
        get(N - 10);
        long sum = 0;
        long k = 1_000_0000_0000L;
//        long k = 100;
//        long k = 1000000;
        for (long i = 2; i <= k; i = (i << 1)) {
            sum += djsphi(k / i) - 1;
            sum %= MOD;
        }
        System.out.println(sum % MOD);

    }
}
