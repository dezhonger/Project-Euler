package com.netease.music.pe;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by dezhonger on 2019/3/19
 *
 * (k+1) | (n+k) ， (k+1) | (n-1)
 * streak(n) = s,  1,2,...s |(n-1),   lcm(1,2,3,..s) | n-1 and lcm(1,2,3,...s,s+1) not | (n-1)
 * Gcd Algorithm and Principle of inclusion-exclusion Algorithm
 */
public class PE601 {

    static int n = 31;
    static long sum = 0;
    static BigInteger[] lcm;


    public static void main(String[] args) {
        init();
        run();
        System.out.println(sum);
    }

    private static void init() {
        //init lcm
        lcm = new BigInteger[n+2];
        lcm[1] = BigInteger.ONE;
        for (int i = 2; i <= n + 1; i++){
            BigInteger gcd = lcm[i - 1].gcd(new BigInteger(i + ""));
            lcm[i] = lcm[i - 1].multiply(new BigInteger(i + "")).divide(gcd);
        }
    }

    private static void run() {
        BigInteger b = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            b = b.multiply(new BigInteger("4"));
            sum += p(i, b.subtract(BigInteger.ONE).subtract(BigInteger.ONE));
        }
    }

    private static long p(int i, BigInteger b) {
        return b.divide(lcm[i]).subtract(b.divide(lcm[i + 1])).longValue();
    }

}
