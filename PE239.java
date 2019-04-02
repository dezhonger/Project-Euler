package com.netease.music.rep2.common.excel.core.util;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by dezhonger on 2019/04/02
 *
 * @author dezhonger
 * @since 2019/04/02
 *
 * 容斥定理，
 * ans = C(25, 3) * (97! - 96! * C(22), 1) + 95! * C(22, 2).... + C(22, 22)) / 100!
 */
public class PE239 {

    static BigInteger[] b = new BigInteger[101];

    static void init() {
        b[0] = BigInteger.ONE;
        for (int i = 1; i <= 100; i++) {
            b[i] = b[i - 1].multiply(new BigInteger(i + ""));
        }
    }

    static BigInteger c(int m, int n) {
        return b[m].divide(b[n]).divide(b[m - n]);
    }

    public static void main(String[] args) {
        int a = 25 * 24 * 23 / 6;
        init();
        BigInteger bx = new BigInteger(b[97].toString());
        int x = 1;
        for (int i = 1, j = 96; i <= 22; i++, j--) {
            x = -x;
            BigInteger xx = new BigInteger(x + "").multiply(b[j]).multiply(c(22, i));
            bx = bx.add(xx);
        }
        BigDecimal fenzi = new BigDecimal(bx.multiply(new BigInteger(a + "")));
        System.out.println(fenzi.divide(new BigDecimal(b[100]), 30, BigDecimal.ROUND_HALF_DOWN));

    }
}