package com.netease.music.p20181211;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by dezhonger on 2018/12/11
 */
public class PE90 {

    static List<List<Integer>> pre = new ArrayList<>();

    public static void main(String[] args) {
        dfs(0, 0, new ArrayList<>());
        System.out.println(pre.size());
//        System.out.println(pre);
        int result = 0;
        for (int i = 0; i < pre.size(); i++) {
            for (int j = i; j < pre.size(); j++) {
                List<Integer> a = pre.get(i);
                List<Integer> b = pre.get(j);
                if (check(a, b)) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    private static boolean check(List<Integer> a, List<Integer> b) {
        int[] s = new int[]{1, 4, 9, 16, 25, 36, 49, 64, 81};
        for (int i = 0; i < s.length; i++) {
            int t = s[i];
            int x = t / 10;
            int y = t % 10;
            if (!((check(a, x) && check(b, y)) || (check(b, x) && check(a, y)))) {
                return false;
            }
        }
        return true;
    }

    private static boolean check(List<Integer> a, int x) {
        if (a.contains(x)) return true;
        if (x == 6 && a.contains(9)) return true;
        if (x == 9 && a.contains(6)) return true;
        return false;
    }

    private static void dfs(int a, int cur, List<Integer> integers) {
        if (cur == 6) {
            pre.add(new ArrayList<>(integers));
            return;
        }
        if (a > 9) {
            return;
        }
        for (int i = a; i < 10; i++) {
            List<Integer> tmp = new ArrayList<>(integers);
            tmp.add(i);
            dfs(i + 1, cur + 1, tmp);
        }
    }


}
