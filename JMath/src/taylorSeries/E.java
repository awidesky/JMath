package taylorSeries;

import java.math.BigDecimal;
import java.math.BigInteger;

public class E {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		int scale = 100;
		
		BigDecimal e = BigDecimal.ZERO;
		BigDecimal x = BigDecimal.ONE; //Exponent of e
		BigDecimal divider = BigDecimal.ONE;
		BigDecimal num = BigDecimal.ONE;
		
		BigDecimal n = BigDecimal.ONE;
		
		do {
			
			num = BigDecimal.ONE.divide(divider, scale, BigDecimal.ROUND_HALF_UP).multiply(x);
			e = e.add(num);
			
			x = x.multiply(x);
			divider = divider.multiply(n);
			n = n.add(BigDecimal.ONE);
			
		} while(num.compareTo(new BigDecimal("0.1").pow(scale)) > 0);
		
		System.out.println(e);
		
		
	}
	
	/*
	 * static BigDecimal factorial(BigInteger num) {
	 * 
	 * BigInteger x = BigInteger.ONE;
	 * 
	 * for (BigInteger i = BigInteger.ONE; !num.equals(i) ; i =
	 * i.add(BigInteger.ONE)) {
	 * 
	 * x = x.multiply(i);
	 * 
	 * }
	 * 
	 * return new BigDecimal(x);
	 * 
	 * }
	 */
}
