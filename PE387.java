package com.netease.music.musician.backend.web.controller.production.check;

/**
 * Created by zhangweilong on 2020/11/13
 *
 * @author zhangweilong@corp.netease.com
 * @since 2020/11/13
 */
public class PE387 {
    static long result = 0;
    static long LIMIT = 100_0000_0000_0000L;

    static boolean isPrime(long x) {
        for (long i = 2; i * i <= x; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }

    static void dfs(long cur, int sum, int len) {
        if (cur >= LIMIT / 10) return;
        if (cur >= 10 && isPrime(cur / sum)) {
            for (int i = 1; i < 10; i++) {
                long nxt = cur * 10 + i;
                if (nxt <= LIMIT && isPrime(nxt)) {
                    result += nxt;
                }
            }
        }


        for (int i = 0; i < 10; i++) {
            if ((cur * 10 + i) % (sum + i)  == 0) {
                dfs(cur * 10 + i, sum + i, i + 1);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            dfs(i, i, 1);
        }
        System.out.println(result);
    }
}
