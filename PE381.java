package com.netease.music.pe;

import com.netease.music.PeUtils;

/**
 * Created by dezhonger on 2018/12/20
 */
public class PE381 {

    static int MAX = 1_0000_0000;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        go();
        System.out.println((System.currentTimeMillis() - start) + "ms");
    }

    public static void go() {
        long rst = 0;
        for (int i = 5; i <= MAX; i++) {
            if (PeUtils.isPrimeByXXX(i)) {
                rst += cal(i);
                System.out.println(i + " " + rst);
            }
        }

    }

    private static long cal(int p) {
//        if p=7, (7-1)! + (7-2)! + (7-3)! + (7-4)! + (7-5)! = 6! + 5! + 4! + 3! + 2! = 720+120+24+6+2 = 872.
//        (p-1)!≡-1(mod p),  (p-1)!%p = p - 1
        int rst = p - 1;
        long last = p - 1;
        for (int i = p - 1; i >= p - 4; i--) {
            //求p-1对p的逆元，根据费马小定理，为(p-1)^(p-2)
//          (p-2)!%p = (p-1)!/(p-1) % p
            int niyuan = (int) PeUtils.mul(i, p - 2, p);
            last = ((1L * last * niyuan) % (p));
            rst += last;
        }
        return rst % p;

    }

}
