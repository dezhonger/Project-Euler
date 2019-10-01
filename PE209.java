package com.dezhonger.pe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dezhonger on 2019/10/01
 *
 * @author dezhonger
 * @since 2019/10/01
 */
public class PE209 {

    public static void main(String[] args) {
        new PE209().run();
    }


    List<List<Integer>> lists = new ArrayList<>();
    Set<Integer> set = new HashSet<>();

    void run() {
        for (int a = 0; a < 2; a++)
            for (int b = 0; b < 2; b++)
                for (int c = 0; c < 2; c++)
                    for (int d = 0; d < 2; d++)
                        for (int e = 0; e < 2; e++)
                            for (int f = 0; f < 2; f++) {
                                int cur = a * 1 + b * 10 + c * 100 + d * 1000 + e * 10000 + f * 10_0000;
                                int dur = cur;
                                if (!set.contains(cur)) {
                                    List<Integer> tmp = new ArrayList<>();
                                    tmp.add(cur);
                                    while (true) {
                                        int nxt = run(cur);
                                        if (nxt != dur) {
                                            tmp.add(nxt);
                                            cur = nxt;
                                        } else {
                                            break;
                                        }
                                    }
                                    set.addAll(tmp);
                                    lists.add(tmp);
                                }
                            }

        //http://oeis.org/A000032
        long[] lucas = new long[50];
        lucas[1] = 1;
        lucas[2] = 3;
        lucas[3] = 4;
        for (int i = 4; i < lucas.length; i++) lucas[i] = lucas[i - 1] + lucas[i - 2];
        long res = 1L;
        for (int i = 0; i < lists.size(); i++) {
            int size = lists.get(i).size();
            res = res * lucas[size];

        }
        System.out.println(res);
    }


    int[] f(int x) {
        int[] a = new int[6];
        int index = 5;
        while (x > 0) {
            a[index--] = x % 10;
            x /= 10;
        }
        return a;
    }

    int run(int o) {
        int[] t = f(o);
        int a, b, c, d, e, f;
        a = t[0];
        b = t[1];
        c = t[2];
        d = t[3];
        e = t[4];
        f = t[5];
        int x = b * c;
        x = (a + x) % 2;
        return f * 10 + e * 100 + d * 1000 + c * 10000 + b * 10_0000 + x ;
    }
}
