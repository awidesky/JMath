package approximation;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;

import taylorSeries.Pi;
import taylorSeries.Sin;

public class SinTest {

	public static BigDecimal Epsilon = new BigDecimal("0.00001");

	public static void main(String[] args) throws IOException {

		testErrRatio();

	}

	public static void testTime() {

		long start;
		int scale = 100;
		BigDecimal x = new BigDecimal("1.54");

		LinkedList<Long> sin = new LinkedList<>();
		LinkedList<Long> sinApporx = new LinkedList<>();

		for (int i = 1; i < 1000; i++) {

			start = System.nanoTime();

			Sin.getSin(x, scale);

			sin.add(System.nanoTime() - start);

		}

		for (int i = 1; i < 1000; i++) {

			start = System.nanoTime();

			TrigonometricFunction.getSin(x, scale);

			sinApporx.add(System.nanoTime() - start);

		}

		BigDecimal sum = BigDecimal.ZERO;

		for (Long i : sin) {

			sum = sum.add(BigDecimal.valueOf(i));

		}

		System.out.println("evg time r : " + sum.divide(new BigDecimal(sin.size()), 20, BigDecimal.ROUND_HALF_UP));

		BigDecimal sum2 = BigDecimal.ZERO;

		for (Long i2 : sinApporx) {

			sum2 = sum2.add(BigDecimal.valueOf(i2));

		}

		System.out
				.println("evg time a : " + sum2.divide(new BigDecimal(sinApporx.size()), 20, BigDecimal.ROUND_HALF_UP));

		System.out.println("real : " + Sin.getSin(x, scale) + "\napprox : " + TrigonometricFunction.getSin(x, scale));

	}

	public static void testDoubleSineTime() {

		long start;
		double x = 7;

		LinkedList<Long> sin = new LinkedList<>();
		LinkedList<Long> sinApporx = new LinkedList<>();

		for (int i = 1; i < 1000000; i++) {

			start = System.nanoTime();

			Math.sin(x);

			sin.add(System.nanoTime() - start);

		}

		for (int i = 1; i < 1000000; i++) {

			start = System.nanoTime();

			TrigonometricFunction.getSin(x);

			sinApporx.add(System.nanoTime() - start);

		}

		BigDecimal sum = BigDecimal.ZERO;

		for (Long i : sin) {

			sum = sum.add(BigDecimal.valueOf(i));

		}

		System.out.println("evg r : " + sum.divide(new BigDecimal(sin.size()), 20, BigDecimal.ROUND_HALF_UP));

		BigDecimal sum2 = BigDecimal.ZERO;

		for (Long i2 : sinApporx) {

			sum2 = sum2.add(BigDecimal.valueOf(i2));

		}

		System.out.println("evg a : " + sum2.divide(new BigDecimal(sinApporx.size()), 20, BigDecimal.ROUND_HALF_UP));

		System.out.println("real : " + Math.sin(x) + "\napprox : " + TrigonometricFunction.getSin(x));

	}

	/**
	 * 
	 * test error amount of sin approximation in [0, <code>pi</code>] <s>and save result to a file</s>
	 * 
	 */
	public static void testErrAmount() throws IOException {

		int scale = 100;
		BigDecimal x = new BigDecimal("0");
		BigDecimal PI = Pi.getPi(scale);

		LinkedList<BigDecimal> errorAmount = new LinkedList<>();

		System.out.println("testSineErrAmount start");

		//new File("testSineErrAmount.txt").createNewFile();
		// PrintWriter pw = new PrintWriter(new File("test.txt"));

		for (; x.compareTo(PI) <= 0; x = x.add(Epsilon)) {

			errorAmount.add(Sin.getSin(x, scale).subtract(TrigonometricFunction.getSin(x, scale)).abs());
			// pw.println(x + "\n" +Sin.getSin(x,
			// scale).subtract(TrigonometricFunction.getSin(x, scale)).abs() + "\n");

		}

		System.out.println("cal end");

		BigDecimal sum = BigDecimal.ZERO;

		for (BigDecimal i2 : errorAmount) {

			sum = sum.add(i2);

		}

		BigDecimal xErr = BigDecimal.ZERO;

		xErr = xErr.add(Epsilon.multiply(new BigDecimal(errorAmount.indexOf(Collections.max(errorAmount)))));

		System.out.println("evg err     : " + sum.divide(new BigDecimal(errorAmount.size()), 20, BigDecimal.ROUND_HALF_UP));

		System.out.println("highest err : " + Collections.max(errorAmount) + "\nin x = " + xErr);

	}
	
	/**
	 * 
	 * test error ratio of sin approximation in [0, <code>pi</code>] <s>and save result to a file</s>
	 * 
	 */
	public static void testErrRatio() throws IOException {

		int scale = 100;
		BigDecimal x = Epsilon;
		BigDecimal PI = Pi.getPi(scale);

		LinkedList<BigDecimal> errorAmount = new LinkedList<>();

		System.out.println("testSineErrRatio start");

		//new File("testSineErrRatio.txt").createNewFile();
		// PrintWriter pw = new PrintWriter(new File("test.txt"));

		for (; x.compareTo(PI) <= 0; x = x.add(Epsilon)) {

			errorAmount.add(BigDecimal.ONE.subtract(TrigonometricFunction.getSin(x, scale).divide(Sin.getSin(x, scale), scale, BigDecimal.ROUND_HALF_UP)).abs());
			// pw.println(x + "\n" +Sin.getSin(x,
			// scale).subtract(TrigonometricFunction.getSin(x, scale)).abs() + "\n");

		}

		System.out.println("cal end");

		BigDecimal sum = BigDecimal.ZERO;

		for (BigDecimal i2 : errorAmount) {

			sum = sum.add(i2);

		}

		BigDecimal xErr = BigDecimal.ZERO;

		xErr = xErr.add(Epsilon.multiply(new BigDecimal(errorAmount.indexOf(Collections.max(errorAmount)))));

		System.out.println("evg err ratio     : " + sum.divide(new BigDecimal(errorAmount.size()), 20, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)) + "%");

		System.out.println("highest err ratio : " + Collections.max(errorAmount).multiply(new BigDecimal(100)) + "%" + "\nin x = " + xErr);

	}


}
