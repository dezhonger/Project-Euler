package com.netease.music.pe;

import java.util.ArrayList;
import java.util.List;

import static com.netease.music.PeUtils.isPrime;

/**
 * Created by dezhonger on 2019/10/13
 */
public class PE234 {

    long upper = 999966663333L;
//    long upper = 15L;
//    long upper = 1000L;

    public static void main(String[] args) {
        PE234 sol = new PE234();
        sol.init();
        sol.run();
    }

    List<Integer> primes = new ArrayList<>();

    void run(){
        long result = 0;
        for (int i = 0; i < primes.size() - 1; i++) {
            int x = primes.get(i);
            int y = primes.get(i + 1);
            if (1L * x * x >= upper) break;
            result += fa(1L * x * x, Math.min(1L * y * y, upper + 1), x, y);
        }
        System.out.println(result);
    }

    //求出100w之内的素数
    void init() {
        for (int i = 2; i <= 100_0000; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
    }

    //计算 >x && < n 中的和,x和y是相邻的素数
    long fa(long m, long n, long x, long y) {
        long d = n - m;
        long cx = d / x;
        long cy = (n - 1) / y - m / y;
        long cxy = (n - 1) / (x * y) - m / (x * y);

        long s = 0;
        long sx = fb(m, n, x, cx);
        long sy = fb(m, n, y, cy);
        long sxy = fb(m, n, x * y, cxy);
        s += sx;
        s += sy;
        s -= 2 * sxy;
        return s;
    }

    long fb(long m, long n, long x, long cx) {
        if (cx == 0) return 0;
        long yushu = m % x;
        //第一个是x的倍数的数
        long start = m + (x - yushu);
        //最后一个数
        long end = start + (cx - 1) * x;
        //求和
        long sum = (start + end) * cx / 2;
        return sum;
    }

}
