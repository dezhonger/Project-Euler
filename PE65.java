package com.netease.music.p20181211;

import com.netease.music.Fraction;

import java.util.Arrays;

/**
 * Created by dezhonger on 2018/12/11
 */
public class PE65 {


    public static void main(String[] args) {
//        for (int i = 2; i <= 10; i++) {
//            cal(2, i);
//        }

        for (int i = 2; i <= 100; i++) {
            cal(i);
        }

        String result = "6963524437876961749120273824619538346438023188214475670667";
        int s = 0 ;
        for (int i = 0; i < result.length(); i++) {
            s += result.charAt(i) - '0';
        }
        System.out.println(s);
    }

    //100: Fraction{numerator=6963524437876961749120273824619538346438023188214475670667, denominator=2561737478789858711161539537921323010415623148113041714756}
    private static void cal(int n) {
        //e = [2; 1,2,1, 1,4,1, 1,6,1 , … , 1,2k,1, …]
        int[] e = new int[200];
        int cnt = 2;
        for (int i = 1; i < e.length; i++) {
            if (i % 3 == 0 || i % 3 == 1) {
                e[i] = 1;
            } else {
                e[i] = cnt;
                cnt += 2;
            }
        }
        Fraction result = null;
        Fraction f = new Fraction(e[n] + "", "1");
        for (int i = n - 1; i >= 1; i--) {
            f = Fraction.daoshu(f);
            f = Fraction.add(f, new Fraction(e[i] + "", "1"));

            result  = f;
        }
        result = Fraction.daoshu(result);
        result = Fraction.add(result, new Fraction("2", "1"));
        System.out.println( n + 1 + ": " + result);
    }

    /**
     * 计算根号2连分数展开
     *
     * @param a
     * @param n
     */
    private static void cal(int a, int n) {
        //sqrt(2) = [1;(2)]
        Fraction result = null;
        Fraction f = new Fraction("1", "2");
        for (int i = 1; i <= n; i++) {
            f = Fraction.add(f, new Fraction("2", "1"));
            f = Fraction.daoshu(f);
            result = f;
        }
        result = Fraction.add(result, new Fraction("1", "1"));
        System.out.println(n + ": " + result);
    }
}
