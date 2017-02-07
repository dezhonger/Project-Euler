package p1107.p20170206;

/**
 * Created by dezhonger on 2017/2/7.
 */
public class PE64 {
	// PE64
	public void f() {
		int upper = 10000;
		int result = 0;
		for(int n = 2; n <= upper; n++) {
			int limit = (int)Math.sqrt(n);
			if(limit * limit == n) continue;
			int periods = 0;
			int d = 1;
			int m = 0;
			int a = limit;
			do {
				m = d * a - m;
				d = (n - m * m) / d;
				a = (limit + m) / d;
//				System.out.println(m + " " + d + " " + a);
				periods++;
			}while(a != 2 * limit);

			if(periods % 2 == 1) result++;
		}
		System.out.println(result);
	}

	public static void main(String[] args) {
		new PE64().f();
	}
}
