package com.netease.music.pe;

/**
 * Created by dezhonger on 2019/3/31
 */
public class PE663SegTree {

//    static int N = 1100_0000;
    static int N;
    long[] a;
    Node[] tree;
    PE663SegTree() {


    }
    void init(int x) {
        N = x + 2;
        tree = new Node[4 * N];
        a = new long[N];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Node();
        }
    }

    long max(long a, long b) {
        return Math.max(a, b);
    }

    void build(int id, int l, int r) {
        tree[id].left = l;
        tree[id].right = r;
        if (l == r) {
            tree[id].sum_ = a[l];
            tree[id].summ = a[l];
            tree[id].suml = a[l];
            tree[id].sumr = a[l];
        } else {
            int mid = (l + r) / 2;
            build(2 * id, l, mid);
            build(2 * id + 1, mid + 1, r);
            tree[id].sum_ = tree[2 * id].sum_ + tree[2 * id + 1].sum_;
            tree[id].summ = max(max(tree[2 * id].summ, tree[2 * id + 1].summ), (tree[2 * id].sumr + tree[2 * id + 1].suml));
            tree[id].suml = max(tree[2 * id].suml, tree[2 * id].sum_ + tree[2 * id + 1].suml);
            tree[id].sumr = max(tree[2 * id + 1].sumr, tree[2 * id].sumr + tree[2 * id + 1].sum_);
        }
    }

    Node query_sum(long id, long l, long r) {
        if (tree[(int) id].left == l && tree[(int) id].right == r)
            return tree[(int) id];
        else {
            Node tmp, k1, k2;
            tmp = new Node();
            k1 = new Node();
            k2 = new Node();
            int flag1 = 0, flag2 = 0;
            long mid = (tree[(int) id].left + tree[(int) id].right) / 2;
            if (r <= mid) return query_sum(2 * id, l, r);
            if (l > mid) return query_sum(2 * id + 1, l, r);
            if (l <= mid) {
                k1 = query_sum(2 * id, l, mid);
                flag1 = 1;
            }
            if (r > mid) {
                k2 = query_sum(2 * id + 1, mid + 1, r);
                flag2 = 1;
            }
            if (flag1 == 1 && flag2 == 1) {
                tmp.sum_ = k1.sum_ + k2.sum_;
                tmp.suml = max(k1.suml, k1.sum_ + k2.suml);
                tmp.sumr = max(k2.sumr, k1.sumr + k2.sum_);
                tmp.summ = max(max(k1.summ, k2.summ), k1.sumr + k2.suml);
            } else {
                if (flag1 == 1) tmp = k1;
                else tmp = k2;
            }
            return tmp;
        }
    }

    void update(int id, int pos, long val) {
        if (tree[id].left == tree[id].right) {
            tree[id].sum_ = val;
            tree[id].summ = val;
            tree[id].suml = val;
            tree[id].sumr = val;
        } else {
            long mid = (tree[id].left + tree[id].right) / 2;
            if (pos <= mid) update(2 * id, pos, val);
            else update(2 * id + 1, pos, val);
            tree[id].sum_ = tree[2 * id].sum_ + tree[2 * id + 1].sum_;
            tree[id].summ = max(max(tree[2 * id].summ, tree[2 * id + 1].summ), (tree[2 * id].sumr + tree[2 * id + 1].suml));
            tree[id].suml = max(tree[2 * id].suml, tree[2 * id].sum_ + tree[2 * id + 1].suml);
            tree[id].sumr = max(tree[2 * id + 1].sumr, tree[2 * id].sumr + tree[2 * id + 1].sum_);
        }
    }
}

class Node {
    long left;
    long right;
    long sum_, summ, suml, sumr;

}
