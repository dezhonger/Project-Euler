package com.netease.music.pe;

/**
 * Created by dezhonger on 2019/10/20
 */
public class PE150 {
    static long t = 0;

    final int n = 1000;
    long[][] a = new long[n + 1][n + 1];
    long[][] sum = new long[n + 1][n + 1];

    static long next() {
        t = (615949 * t + 797807) % (1 << 20);
        return t - (1 << 19);
    }


    long res = Long.MAX_VALUE;

    public static void main(String[] args) {
        PE150 pe150 = new PE150();
        pe150.init();
        pe150.run();

    }

    void run() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                long tmp = a[i][j];
                updateRes(tmp);
                for (int k = i + 1; k <= n; k++) {
                    //这一行的末尾列
                    int l = j + k - i;
                    //这一行的数字个数
                    int curNumber = k - i + 1;
                    tmp += sum[k][l] - sum[k][l - curNumber];
                    updateRes(tmp);
                }
            }
        }
        System.out.println(res);
    }

    private void updateRes(long tmp) {
        res = Math.min(res, tmp);
    }


    void init() {
        //初始化数组
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                a[i][j] = next();
            }
        }
        //每行前缀和
        for (int i = 1; i <= n; i++) {
            sum[i][0] = 0;
            for (int j = 1; j <= i; j++) {
                sum[i][j] = sum[i][j - 1] + a[i][j];
            }
        }

    }
}
