
import java.util.Arrays;

/**
 * Created by dezhonger on 2019/10/29
 */
public class PE241 {
    public static void main(String[] args) {
        //oeis
        long qian19 = 16772742100580482L;
        //http://www.numericana.com/answer/numbers.htm#multiperfect
        long[] a = new long[]{88898072401645056L,75462255348480000L, 301183421949935616L};
        System.out.println(qian19 + Arrays.stream(a).sum());
    }
}
