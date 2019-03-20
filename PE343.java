package com.netease.music.rep2.dolphin.consumer.core.kafka;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by dezhonger on 2019/3/2
 */
public class PE343 {

    static int n = 200_0000;
    //    static int n = 200 ;
    static List<Integer> primes = new ArrayList<>();
    static int[] maxPrime = new int[n + 10];

    public static void main(String[] args) {
//        brute();
        long start = System.currentTimeMillis();
        init();
        init2();
        run();
        System.out.println((System.currentTimeMillis() - start) + "ms");
    }

    private static void init() {
        primes.add(2);
        for (int i = 3; i < n + 10; i += 2) {
            if (PeUtils.isPrime(i)) primes.add(i);
        }
        System.out.println("初始化素数完毕");
    }

    private static void init2() {
        int len = primes.size();
        maxPrime[1] = 1;
        for (int i = 0; i < len; i++) {
            int p = primes.get(i);
            for (int j = p; j <= n + 6; j += p) {
                maxPrime[j] = Math.max(maxPrime[j], p);
            }
        }
        System.out.println("初始化最大素因子完毕");
    }


    public static long getLastPrimeFactor(int x) {
        long p1 = x + 1;
        long p1max = maxPrime[(int) p1];
        long p2 = 1L * x * x - x + 1;
        if (p2 <= (long) n) {
            return Math.max(maxPrime[(int) p2], p1max);
        }
        long p3 = p2;
        long p2max = -1;
        if (PeUtils.isPrimeByXXX(p2)) return p2;
        else {
            int len = primes.size();
            for (int i = 0; i < len; i++) {
                int p = primes.get(i);
                if (1L * p * p > p3 && p2max == -1) {
                    p2max = p3;
                    break;
                }
                while (p2 % p == 0) {
                    p2 /= p;
                    p2max = p;
                }
            }
        }
        return Math.max(Math.max(p2max, p2), p1max);
    }

    public static void run() {
        long s = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 10000 == 0) System.out.println(i);
            long h = getLastPrimeFactor(i) - 1;
            s += h;
//            System.out.println(i+": "+h);
        }
        System.out.println(s);
    }


    /**
     * 找出规律  f(n) = lastPrimeFactor(n + 1) - 1;
     */
    public static void brute() {
        int s = 1;
        List<Long> l = new ArrayList<>();
        for (long i = 1; i <= n; i++) {
            long a = 1;
            long b = i * i * i;
            long r;
            while (true) {
                a++;
                b--;
                if (b == 1) {
                    r = a;
                    break;
                }
                long g = PeUtils.gcd(a, b);
                if (g == b) {
                    r = a / g;
                    break;
                }
                a /= g;
                b /= g;

            }
//            System.out.println(i+": "+r);
            l.add(r);
            s += r;
        }
        System.out.println(s);
    }
}
