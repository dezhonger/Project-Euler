
/**
 * Created by dezhonger on 2019/11/12
 */
public class PE358 {
    public static void main(String[] args) {
        new PE358().run();
    }

    boolean endWith56789(int p) {
        int c = 56789;
        int module = 100000;
        long product = 1L * c * p;
        return (product + 1) % module == 0;
    }

//    boolean startWith(int p) {
//        double c = 1.0 / p;
//        return c >= 0.00000000137 && c <= 0.00000000138;
//    }

    void run() {
        // 1/a=7_2992_7007
//      BigDecimal a = new BigDecimal("0.00000000137");
        // 7_2463_7681
//        BigDecimal b = new BigDecimal("0.00000000138");
        for (int i = 7_2463_7681; i <= 7_2992_7007; i++) {
            if (isPrime(i) && endWith56789(i)) {
                long res = judge(i);
                if (res > 0) {
                    System.out.println(i + " " + res);
                }

            }
        }
    }

    //判断循环节长度，同时求出结果
    long judge(int p) {
        long numerator = 1;
        long sum = 0;
        long cycleLength = 0;
        while (true) {
            cycleLength++;
            numerator *= 10;
            long c = numerator / p;
            sum += c;
            numerator %= p;
            if (numerator == 1) {
                break;
            }
        }
        if (cycleLength == p - 1) return sum;
        return 0;
    }

    boolean isPrime(long a) {
        if (a % 2 == 0 || a % 3 == 0) return false;
        for (long x = 5; x * x <= a; x += 2) {
            if (a % x == 0) return false;
        }
        return true;
    }

}
