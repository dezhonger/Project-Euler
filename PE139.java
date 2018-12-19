package com.netease.music.pe;

import com.netease.music.PeUtils;

/**
 * Created by dezhonger on 2018/12/19
 *
 * https://zh.wikipedia.org/wiki/%E5%8B%BE%E8%82%A1%E6%95%B0
 */
public class PE139 {

    static int AMAX = 1_0000;
    static int MAX = 1_0000_0000;

    public static void main(String[] args) {
        go();
    }

    public static void go() {
        int rst = 0;
        for (int a = 2; a <= AMAX; a++) {
            for (int b = 1; b < a; b++) {
                //a,b互质，且一奇一偶
                if (PeUtils.gcd(a, b) == 1 && ((a + b) & 1) == 1) {
                    int x = a * a - b * b;
                    int y = 2 * a * b;
                    int z = a * a + b * b;
                    if (z <= MAX && z % (Math.abs(x - y)) == 0) {
                        System.out.println(x + " " + y + " " + z);
                        rst += (MAX - 1) / (x + y + z);
                        System.out.println(rst);
                    }
                }
            }
        }
        System.out.println(rst);
    }
}
