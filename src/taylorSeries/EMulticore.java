package taylorSeries;

import java.math.BigDecimal;

public class EMulticore { //TODO : any way for calculating with thread pool?

	private static final int cutOff = 20;
	public static BigDecimal E = BigDecimal.ZERO;
	public static int collected = 0;
	public static final int CORES = Runtime.getRuntime().availableProcessors();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		start(100, CORES);

	}

	public static void start(int scale, int threadNumbers) {

		BigDecimal e = BigDecimal.ZERO;
		BigDecimal x = BigDecimal.ONE; // Exponent of e
		BigDecimal divider = BigDecimal.ONE;
		BigDecimal num = BigDecimal.ONE;

		BigDecimal n = BigDecimal.ONE;

		do {

			num = BigDecimal.ONE.divide(divider, scale + cutOff, BigDecimal.ROUND_HALF_UP).multiply(x);
			e = e.add(num);

			x = x.multiply(x);
			divider = divider.multiply(n);
			n = n.add(BigDecimal.ONE);

		} while (num.compareTo(new BigDecimal("0.1").pow(scale + cutOff)) > 0);

		// System.err.println(num);
		// System.err.println(new BigDecimal("0.1").pow(scale + cutOff));

		EMulticore.collect(e);

	}

	public static synchronized void collect(BigDecimal e) {
		/** Collect value of e of each <code>Thread</code>s and adds them */

		collected++;
		E = E.add(e);
		if (collected == CORES) {
			System.out.println(E);
		}

	}

}
