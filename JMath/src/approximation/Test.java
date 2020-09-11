package approximation;

import java.math.BigDecimal;
import java.util.LinkedList;

import taylorSeries.Sin;

public class Test {

	public static void main(String[] args) {

		
	}
		
	public static void testSine() {
		long start;
		int scale = 100;
		BigDecimal x = new BigDecimal("0.345");
		
		LinkedList<Long> sin = new LinkedList<>();
		LinkedList<Long> sinApporx = new LinkedList<>();
		
		for (int i = 1 ; i < 1000000 ; i++) {
			
			start = System.nanoTime();
			
			Sin.getSin(x, scale);
			
			sin.add(System.nanoTime()-start);
			
		}
		
		
		
		for (int i = 1 ; i < 1000000 ; i++) {
			
			start = System.nanoTime();
			
			TrigonometricFunction.getSin(x, scale);
			
			sinApporx.add(System.nanoTime()-start);
			
		}
		
		BigDecimal sum = BigDecimal.ZERO;
		
		for (Long i : sin) {
			
			sum = sum.add(BigDecimal.valueOf(i));
			
		}
		
		System.out.println("evg r : " + sum.divide(new BigDecimal(sin.size()), 20, BigDecimal.ROUND_HALF_UP));
		
		BigDecimal sum2 = BigDecimal.ZERO;
		
		for (Long i : sinApporx) {
			
			sum = sum.add(BigDecimal.valueOf(i));
			
		}
		
		System.out.println("evg a : " + sum2.divide(new BigDecimal(sinApporx.size()), 20, BigDecimal.ROUND_HALF_UP));
	
		System.out.println("real : " + Sin.getSin(x, scale) + "\napprox : " + TrigonometricFunction.getSin(x, scale));
	
	}

}
