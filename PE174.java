package com.netease.music.p20181127;

/**
 * Created by dezhonger on 2018/11/27
 */
public class PE174 {
    static int M = 100_0000;
    static int[] res = new int[21];

    public static void run() {
        for (int i = 1; i <= M; i++) {
            go(i);
        }
    }

    //a*b=k&&a<b, x = (b+a)/2, y = (b-a)/2, x^2-y^2=k
    //x^2-y^2=k, (x+y)(x-y)=k, 对k分解质因数, k = a * b && a < b, x =(b + a)/2, y = (b - a) / 2
    private static void go(int k) {
        int c = 0;
        for (int a = 1; a * a < k; a++) {
            if (k % a == 0) {
                int b = k / a;
                if ((a + b) % 2 == 0) {
                    //对称
                    int x = (b + a) / 2;
                    int y = (b - a) / 2;
                    if ((x - y) % 2 == 0) {
                        c++;
                    }
                }
            }
        }
        if (c > 0 && c < 20) {
            res[c]++;
        }

    }

    public static void main(String[] args) {
        run();
        int r = 0;
        for (int i = 1; i <= 10; i++) {
            r += res[i];
        }
        System.out.println(r);
    }
}
