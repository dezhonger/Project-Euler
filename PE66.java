package p1107.p20170207;

import java.math.BigInteger;

/**
 * Created by dezhonger on 2017/2/7.
 * code for pe66
 * print the minimun solution for x^2 - D*y^2 = 1
 */
public class PE66 {
	public void f() {
		int result = 0;
		BigInteger pmax = BigInteger.ZERO;

		int L = 1;
		int R = 10;

		for(int D = L; D <= R; D++){
			BigInteger DD = new BigInteger(D+"");
			BigInteger limit = new BigInteger((int)Math.sqrt(D)+"");
			if (limit.multiply(limit).compareTo(new BigInteger(D+"")) == 0) continue;
			BigInteger m = BigInteger.ZERO;
			BigInteger d = BigInteger.ONE;
			BigInteger a = limit;
			BigInteger numm1 = BigInteger.ONE;
			BigInteger num = a;
			BigInteger denm1 = BigInteger.ZERO;
			BigInteger den = BigInteger.ONE;
			while(num.multiply(num).subtract(DD.multiply(den).multiply(den)).compareTo(BigInteger.ONE) != 0){
				m = d.multiply(a).subtract(m);
				d = (DD.subtract(m.multiply(m))).divide(d);
				a = (limit.add(m)).divide(d);

				BigInteger numm2 = numm1;
				numm1 = num;
				BigInteger denm2 = denm1;
				denm1 = den;

				num = a.multiply(numm1).add(numm2);
				den = a.multiply(denm1).add(denm2);
			}

			if (num.compareTo(new BigInteger(pmax+"")) > 0) {
				pmax = num;
				result = D;
			}
			System.out.println(num + "^2 - " + D + "*" + den+"^2 = 1");  //x^2 - D*y^2 = 1的最小解.
//			System.out.println(num.toString().length());
//			System.out.println(den.toString().length());
		}
		System.out.println(result);
	}

	public static void main(String[] args) {
		new PE66().f();
	}
}
