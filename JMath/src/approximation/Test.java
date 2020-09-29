package approximation;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;

import taylorSeries.Pi;
import taylorSeries.Sin;

public class Test {

	public static BigDecimal Epsilon = new BigDecimal("0.00001");
	
	public static void main(String[] args) throws IOException {

		testSineErr();
		
	}
		
	public static void testSineTime() {
		
		long start;
		int scale = 100;
		BigDecimal x = new BigDecimal("1.54");
		
		LinkedList<Long> sin = new LinkedList<>();
		LinkedList<Long> sinApporx = new LinkedList<>();
		
		
		for (int i = 1 ; i < 1000 ; i++) {
			
			start = System.nanoTime();
			
			Sin.getSin(x, scale);
			
			sin.add(System.nanoTime()-start);
			
		}
		
		
		
		for (int i = 1 ; i < 1000 ; i++) {
			
			start = System.nanoTime();
			
			TrigonometricFunction.getSin(x, scale);
			
			sinApporx.add(System.nanoTime()-start);
			
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
		
		System.out.println("evg time a : " + sum2.divide(new BigDecimal(sinApporx.size()), 20, BigDecimal.ROUND_HALF_UP));
	
		System.out.println("real : " + Sin.getSin(x, scale) + "\napprox : " + TrigonometricFunction.getSin(x, scale));
	
	}

	
	
	public static void testDoubleSineTime() {
		
		long start;
		double x = 7;
		
		LinkedList<Long> sin = new LinkedList<>();
		LinkedList<Long> sinApporx = new LinkedList<>();
		
		for (int i = 1 ; i < 1000000 ; i++) {
			
			start = System.nanoTime();
			
			Math.sin(x);
			
			sin.add(System.nanoTime()-start);
			
		}
		
		
		
		for (int i = 1 ; i < 1000000 ; i++) {
			
			start = System.nanoTime();
			
			TrigonometricFunction.getSin(x);
			
			sinApporx.add(System.nanoTime()-start);
			
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
	
	
	public static void testSineErr() throws IOException {
		
		
		int scale = 100;
		BigDecimal x = new BigDecimal("0");
		BigDecimal PI = Pi.getPi(scale);
		
		LinkedList<BigDecimal> sinApporx = new LinkedList<>();
		
		System.out.println("testSineErr start");
		
		new File("test.txt").createNewFile();
		//PrintWriter pw = new PrintWriter(new File("test.txt"));
		
		for (; x.compareTo(PI) <= 0 ; x = x.add(Epsilon)) {
			
			sinApporx.add(Sin.getSin(x, scale).subtract(TrigonometricFunction.getSin(x, scale)).abs());
			//pw.println(x + "\n" +Sin.getSin(x, scale).subtract(TrigonometricFunction.getSin(x, scale)).abs() + "\n");
			
		}
		
		  System.out.println("cal end");
		  
		  BigDecimal sum = BigDecimal.ZERO;
		  
		  
		  for (BigDecimal i2 : sinApporx) {
		  
		  sum = sum.add(i2);
		  
		  }
		  
		  BigDecimal xErr = BigDecimal.ZERO;
		  
		  xErr = xErr.add(Epsilon.multiply(new BigDecimal(sinApporx.indexOf(Collections.max(sinApporx)))));
		  
		  
		  
		  System.out.println("evg err     : " + sum.divide(new
		  BigDecimal(sinApporx.size()), 20, BigDecimal.ROUND_HALF_UP));
		  
		  System.out.println("highest err : " + Collections.max(sinApporx) + "\nin : " + xErr);
		 
	}


	
}
