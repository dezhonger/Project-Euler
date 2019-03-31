package com.netease.music.pe;

/**
 * Created by dezhonger on 2019/3/31
 *
 * CDOJ 844
 */
public class PE663 {
    //    static int n = 107;
    static int n = 10000003;
    //    static int l = 1000;
    static int l = 10200000;
    static int l2 = 10000000;
    static int[] t = new int[l * 2 + 5];
    static int result = 0;
    static long[] dp = new long[n + 1];

    static void calt() {
        t[0] = t[1] = 0;
        t[2] = 1;
        for (int i = 3; i < 2 * l + 5; i++) {
            t[i] = t[i - 1] + t[i - 2] + t[i - 3];
            t[i] %= n;
        }
    }

    static void calA(long[] a) {
        for (int i = 1; i <= l2; i++) {
            int x = 2 * i - 2;
            int y = 2 * i - 1;
            int index1 = t[x] % n;
            a[index1] = a[index1] + 2 * (t[y] % n) - n + 1;
//            System.out.println(Arrays.toString(a));
//            if (i > l2) {
            long maxSu = maxSum(a, index1);
            result += maxSu;
//            }
//            System.out.println(i + " " + maxSu);
//            System.out.println(maxSu);
//            System.out.println();
        }
        System.out.println(result);
    }

    static long maxSum(long[] a, int from) {
        long s = a[0];
        long r = a[0];
        for (int i = 1; i < a.length; i++) {
            s = Math.max(a[i], s + a[i]);
            r = Math.max(r, s);
        }
        return r;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long a[] = new long[n];
        calt();
        long start1 = System.currentTimeMillis();
//        calA(a);
        System.out.println("time : " + (start1 - start));
        long start2 = System.currentTimeMillis();
        System.out.println("time : " + (start2 - start));


        for (int i = 1; i <= l2; i++) {
            int x = 2 * i - 2;
            int y = 2 * i - 1;
            int index1 = t[x] % n;
            a[index1] = a[index1] + 2 * (t[y] % n) - n + 1;
        }



        PE663SegTree pe663SegTree = new PE663SegTree();
        pe663SegTree.init(n);
        for (int i = 0; i < n; i++) {
            pe663SegTree.a[i + 1] = a[i];

        }
        System.out.println("sssssssssssssssssssssss");
        pe663SegTree.build(1, 1, n);
        long re = 0;
        for (int i = l2 + 1; i <= l; i++) {
            int x = 2 * i - 2;
            int y = 2 * i - 1;
            int index1 = t[x] % n;
            a[index1] = a[index1] + 2 * (t[y] % n) - n + 1;
            long val = a[index1];
            pe663SegTree.update(1, index1 + 1, val);
            Node node = pe663SegTree.query_sum(1, 1, n);
            re += node.summ;
        }


        System.out.println("re : " + re);


    }
}
