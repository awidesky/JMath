package taylorSeries;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Pi {

		private static final int cutOff = 20;
	
	public static void main(String[] args) {

		System.out.println(getPi(100));
			
	}
	
	
	public static BigDecimal getPi(int digit) {
		
		BigInteger i = BigInteger.ONE;
		BigInteger x = new BigInteger("3").multiply(BigInteger.TEN.pow(digit + cutOff));
		BigInteger pi = x;
		
		final BigInteger TWO = new BigInteger("2");
		final BigInteger FOUR = new BigInteger("4");
		
		//long num = 1L;
		
		while (x.compareTo(BigInteger.ZERO) == 1) {
			
			x = x.multiply(i).divide(i.add(BigInteger.ONE).multiply(FOUR));		
		    pi = pi.add(x.divide(i.add(TWO)));
			i = i.add(TWO);
			
			//if (num % 100 == 1) System.out.println(x);
			//num++;
			
		}
		
		//BigDecimal result = new BigDecimal(pi.divide(BigInteger.TEN.pow(CUT))).divide(new BigDecimal(BigInteger.TEN.pow(DIGITS)));
		
		return new BigDecimal(pi.divide(BigInteger.TEN.pow(cutOff))).divide(new BigDecimal(BigInteger.TEN.pow(digit)));
		
	}

}
