

import java.math.BigInteger;

/**
 * Created by dezhonger on 2018/12/19
 *
 * @author dezhonger
 * @since 2018/12/19
 */
public class PE168 {

    static BigInteger r = BigInteger.ZERO;

    public static void main(String[] args) {
        for (int i = 2; i <= 100; i++) {
            f(i);
        }
        System.out.println(r.mod(new BigInteger("100000")));
    }

    /**
     * a0_a1_a2_a3_a4 * x = a4_a0_a1_a2_a3
     * 142857 * 5 = 714285
     * <p>
     * 假设已知a4,x ，可以得到a3,然后a3和x推出a2,从而可以推出整个数
     */
    public static void f(int n) {
        for (int atail = 1; atail < 10; atail++) {
            for (int x = 1; x < 10; x++) {
                String s = check(atail, x, n);
                if (s != null) {
                    r = r.add(new BigInteger(s));
                }

            }
        }
    }

    private static String check(int a, int x, int n) {
        String str = a + "";
        int tail = a;
        int add = 0;
        while (--n > 0) {
            int t = a * x + add;
            a = t % 10;
            add = t / 10;
            str = a + str;
        }
        if (a * x + add == tail && str.charAt(0) != '0') {
            return str;
        } else {
            return null;
        }
    }
}

