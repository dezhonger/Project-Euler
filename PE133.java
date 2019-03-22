
import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;

/**
 * Created by zhangweilong on 2019/03/22
 *
 * @author zhangweilong@corp.netease.com
 * @since 2019/03/22
 *
 * 找到循环节说明不可能存在这个质因数了 3特殊处理下
 */
public class PE133 {
    static int mod = 10_0000;
    static List<Integer> primes = new ArrayList<>();
    static long qml(long a, long b, int m) {
        long r = 1;
        long x = a;
        while (b > 0) {
            if (b % 2 == 1)r *= x;
            x = x * x;
            b >>=1;
            x%=m;
            r%=m;
        }
        return r;
    }
    static boolean isP(int a) {
        for(int i = 2; i * i <= a; i++) if (a%i==0)return false;
        return true;
    }
    static void init() {
        for (int i = 2; i<mod; i++) if (isP(i)) primes.add(i);
    }
    static long last = 0;
    static boolean check(int x) {
        int[] m = new int[mod + 1];
        int start = 1;
        m[start] = 1;
        int cnt= 1;
        for (;;) {
            if (cnt == 1) {
                cnt++;
            start = r(10, x);
            } else {
                start = r2(x);
                cnt++;
            }
            if (start == 0) {
                System.out.println(x + " " + cnt);
                return true;
            }
            if (m[start] ==0) m[start] = 1;
            else {
                return false;
            }
        }
    }

    static int r(int x, int m) {
        long b = qml(10, x, m);
        last = b;
        long a =b + m - 1;
        a = a * qml(9, m - 2, m);
        return (int) (a % m);
    }

    static int r2(int m) {
        long b = qml(last, 10, m);
        last = b;
        long a =b + m - 1;
        a = a * qml(9, m - 2, m);
        return (int) (a % m);
    }

    public static void main(String[] args) {
        init();
        long s = 0;
        for (int x : primes) {
            boolean b = check(x);
            if (b) {
                System.out.println(x);
                s+=x;
            }
        }
        s-=3;
        System.out.println(primes.stream().mapToInt((ToIntFunction) value -> (int) value).sum() - s);
    }
}
