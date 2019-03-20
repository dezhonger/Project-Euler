package com.netease.music.pe;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dezhonger on 2019/3/19
 * <p>
 * https://www.luogu.org/problemnew/show/P4213
 * 杜教筛模板题
 * <p>
 * https://www.cnblogs.com/peng-ym/p/9446555.html 这里的代码改写为java
 */
public class LOJ4213 {

    static int N = 6000010;
    static int MOD = 10_0000_0000 + 7;

    static boolean vis[] = new boolean[N];
    static int[] mu = new int[N];
    static int[] sum1 = new int[N];
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
            sum1[i] = sum1[i - 1] + mu[i];
            sum2[i] = sum2[i - 1] + phi[i];
        }
    }

    static int djsmu(int x) {
        if (x <= N - 10) return sum1[x];
        if (w.get(x) != null) return w.get(x);
        int ans = 1;
        for (int l = 2, r; l >= 0 && l <= x; l = r + 1) {
            r = x / (x / l);
            ans -= (r - l + 1) * djsmu(x / l);
        }
        w.put(x, ans);
        return ans;
    }

    static long djsphi(long x) {
        if (x <= N - 10) return sum2[(int) x];
        if (w1.get(x) != null) return w1.get(x);
        long ans = x * (x + 1) / 2;
        for (long l = 2, r; l <= x; l = r + 1) {
            r = x / (x / l);
            ans -= (r - l + 1) * djsphi(x / l);
        }
        w1.put(x, ans);
        return ans;
    }


    public static void main(String[] args) {
        get(N - 10);
        long sum = 0;
        long k = 1_000_0000_0000L;
        for (long i = 2; i <= k; i = (i << 1)) {
            sum += djsphi(k / i) - 1;
            sum %= MOD;
        }
        System.out.println(sum%MOD);

    }

}
