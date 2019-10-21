package com.netease.music.pe;

import com.netease.music.PeUtils;

/**
 * Created by dezhonger on 2019/10/20
 */
public class PE153 {

        final int n = 1_0000_0000;
//    final int n = 5;
    static long res = 0;
    int[] f = new int[n + 1];
    //约数和
    int[] g = new int[n + 1];
    long [] gsum = new long[n + 1];

    void cal1() {
        for (int i = 1; i <= n; i++) {
            res += n / i * i;
        }
    }

    void cal2() {
        for (int a = 1; a * a <= n; a++) {
            for (int b = 1; b * b <= n; b++) {
                if (PeUtils.gcd(a, b) == 1) {
                    int c = a * a + b * b;
                    if (c > n) break;

                    f[c] += a;
                }
            }
        }
        for (int i = 1; i <= n; i++) g[i] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                g[j] += i;
            }
        }

        for (int i = 1; i <= n; i++) gsum[i] = gsum[i - 1] + g[i];

        long tmp = 0;
        for (int i = 1; i <= n; i++) {
            if (f[i] > 0) {
                tmp += 1L *  gsum[n / i] * f[i];
            }
        }
        res += tmp * 2;
    }

}
