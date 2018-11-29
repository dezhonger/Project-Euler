package com.netease.music.p20181129;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dezhonger on 2018/11/29
 */
public class PE268 {

    static class C {
        public C(int cur, long valu) {
            this.cur = cur;
            this.value = valu;
        }

        int cur;
        long value;

        @Override
        public String toString() {
            return "C{" +
                    "cur=" + cur +
                    ", valu=" + value +
                    '}';
        }
    }

    static final long max = 10_00000_00000_00000L;

    static Set<C> set = new HashSet<>();

    static long[] primes = new long[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61,
            67, 71, 73, 79, 83, 89, 97};
    //被多算的次数，需要减掉
    static long[] coff = new long[]{
            1, 4, 10, 20, 35, 56, 84, 120, 165, 220, 286, 364, 455, 560, 680, 816, 969, 1140, 1330, 1540, 1771, 2024, 2300, 2600, 2925
    };

    static void init() {
        dfs(0, 0, 1);
    }


    private static void dfs(int index, int cur, long product) {
        if (product > max) {
            return;
        }
        if (index == primes.length) {
            if (cur >= 4 && product <= max) {
                set.add(new C(cur, product));
            }
            return;
        }
        if (product * primes[index] <= max) {
            dfs(index + 1, cur + 1, product * primes[index]);

        }
        dfs(index + 1, cur, product);

    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long res = 0;
        init();
        for (C x : set) {
            if (x.value < 0) {
                System.out.println(x);
            }
            if (x.cur % 2 == 0) {
                res += max / x.value * coff[x.cur - 4];
            } else {
                res -= max / x.value* coff[x.cur - 4];
            }
        }
        System.out.println(res);
        System.out.println((System.currentTimeMillis() - start) + "ms");
    }
}
