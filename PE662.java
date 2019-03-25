package com.netease.music.pe;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by dezhonger on 2019/3/25
 */

class P {
    public P(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int x, y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        P p = (P) o;
        return x == p.x &&
                y == p.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class PE662 {
    static int maxN = 10000;
    static Set<P> sp = new HashSet<>();

    static void fib() {
        int maxn = 20;
        sp.add(new P(1, 0));
        sp.add(new P(2, 0));
        int[] f = new int[maxn];
        f[0] = 1;
        f[1] = 2;
        for (int i = 2; i < f.length; i++) {
            f[i] = f[i - 1] + f[i - 2];
            sp.add(new P(f[i], 0));
        }

        for (int i = 3; i < f.length; i++) {
            int x = f[i];
            for (int j = 1; j <= Math.sqrt(x * x / 2); j++) {
                int k = (int) Math.sqrt(x * x - j * j);
                if (j * j + k * k == x * x && j <= k) {
                    sp.add(new P(j, k));
                }
                k++;
                if (j * j + k * k == x * x && j <= k) {
                    sp.add(new P(j, k));
                }
            }
        }

    }


    static int[][] dp = new int[10001][10001];

    static int mod = 10_0000_0007;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        fib();
        System.out.println(sp.size());
        dp[0][0] = 1;
        int cnt = 0;
        for (int i = 0; i <= maxN; i++) {
            for (int j = 0; j <= maxN; j++) {
                if (i == 0 && j == 0) continue;
                for (P p : sp) {
                    int dx = p.x;
                    int dy = p.y;
                    if (i - dx >= 0 && j - dy >= 0) {
                        dp[i][j] += dp[i - dx][j - dy];
                        dp[i][j] %= mod;
                        cnt++;
                    }

                    if (dx != dy && j - dx >= 0 && i - dy >= 0) {
                        dp[i][j] += dp[i - dy][j - dx];
                        dp[i][j] %= mod;
                        cnt++;
                    }

                }
            }
        }
        System.out.println(cnt);
        System.out.println(dp[maxN][maxN]);
        long end = System.currentTimeMillis();
        System.out.println(1.0 * (end - start) / 1000 + "ms");
    }
}
