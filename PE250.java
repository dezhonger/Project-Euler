package com.netease.music.pe;

import com.netease.music.PeUtils;

import java.util.Arrays;

/**
 * Created by dezhonger on 2019/3/5
 * <p>
 * 3860_5036_9585_1519 250
 *
 * 8897802889723903 250250
 * 8897802889723904
 * 1425480602091519
 */
public class PE250 {
    static int n = 250250;
    static int nmod = 250;
    static long mod = 1_0000_0000_0000_0000L;

    public static void run() {
        long start = System.currentTimeMillis();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            long mul = PeUtils.mul(i, i, n);
            a[i] = (int) mul;
        }
        long[][] dp = new long[2][n + 1];
        dp[0][0] = 1;
        int cur = 0;
        for (int i = 1; i <= n; i++) {
            int x = a[i];
            for (int j = 0; j < nmod; j++) dp[1 - cur][j] = dp[cur][j];
            for (int j = 0; j < nmod; j++) {
                dp[1 - cur][(j + x) % nmod] += dp[cur][j];
                dp[1 - cur][(j + x) % nmod] %= mod;
            }
            cur = 1 - cur;
        }
        System.out.println(dp[cur][0]);
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");
    }

    public static void main(String[] args) {
        run();
    }
}
