package taylorSeries;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Pi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final int DIGITS = 1000;
		final int CUT = 20;
		
		BigInteger i = BigInteger.ONE;
		BigInteger x = new BigInteger("3").multiply(BigInteger.TEN.pow(DIGITS + CUT));
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
		
		BigDecimal result = new BigDecimal(pi.divide(BigInteger.TEN.pow(CUT))).divide(new BigDecimal(BigInteger.TEN.pow(DIGITS)));
		
		System.out.println(result);
		
	}

}
