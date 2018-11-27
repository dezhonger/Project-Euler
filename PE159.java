package com.netease.music.p20181127;

/**
 * Created by dezhonger on 2018/11/27
 */
public class PE159 {

    static int limit = 100_0000;

    public static int digitRoot(int k) {
        return (k - 1) % 9 + 1;
    }

    public static void main(String[] args) {
        go();
    }


    static int[] mdrs = new int[limit + 1];

    private static void go() {
        for (int i = 1; i <= limit; i++) {
            mdrs[i] = digitRoot(i);
        }
        for (int i = 2; i <= limit; i++) {
            for (int j = 2; j <= i; j++) {
                if (j * i > limit) {
                    break;
                }
                mdrs[i * j] = Math.max(mdrs[i * j], mdrs[i] + mdrs[j]);
            }
        }
        int r = 0;
        for (int i = 2; i < limit; i++) {
            r += mdrs[i];
        }
        System.out.println(r);
    }
}
