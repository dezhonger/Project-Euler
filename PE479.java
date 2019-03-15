package com.netease.music.pe;

import com.netease.music.PeUtils;

/**
 * Created by dezhonger on 2019/3/2
 */
public class PE479 {

    static int n = 100_0000;
    static long mod = 10_0000_0007;


    public static void main(String[] args) {
//        for (int p = 2; p <= 10000; p += 1) {
//            System.out.println(p);
//            n = p;
//            long s1 = force();
//            long s2 = run();
//            System.out.println();
//            if (s1 != s2) {
//                System.out.println("error : " + p);
//            break;
//            }
//        }
        run();

    }

    private static long run() {
        long s = 0;
        for (int k = 1; k <= n; k++) {
            long t = 1 - 1L*  k * k;
            t = g2(t);
            long m1 = PeUtils.mul(t, n, mod);
            long t2 = g2(1 - t);
            long m2 = PeUtils.mul(t2, mod - 2, mod);
            long result = g(t, 1 - m1, m2);
            s += result;
            s = g2(s);
        }
        System.out.println(s);
        return s;
    }


    static long g2(long x) {
        if (x >= 0) return x % mod;
        while (x < 0) x += mod;
        return x;

    }

    private static long g(long t, long l, long m2) {
        t = g2(t);
        l = g2(l);
        m2 = g2(m2);
        long r = t * l;
        r %= mod;
        r *= m2;
        return r % mod;

    }


    private static long force() {
        long s = 0;
        for (int i = 1; i <= n; i++) {
            long x = 1 - i * i;
            s += x;
            long t = x;
            for (int j = 2; j <= n; j++) {
                t *= x;
                t %= mod;
                s += t;
            }
            s = g2(s);
        }
        System.out.println(s);
        return s;
    }
}
