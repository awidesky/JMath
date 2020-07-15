package taylorSeries;

import java.math.BigDecimal;
import java.math.BigInteger;

import parallelism.NThread;

public class EParallelism {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		final int cores = Runtime.getRuntime().availableProcessors();
		
		for (int n = 1 ; n <= cores ; n++) {
			
			NThread th = new NThread(n) {
			
				@Override
				public void run() {
				
					int scale = 100;
					
					BigDecimal e = BigDecimal.ZERO;
					BigDecimal x = BigDecimal.ONE; //Exponent of e
					BigDecimal divider = BigDecimal.ONE;
					BigDecimal num = BigDecimal.ONE;
					
					BigDecimal n1 = BigDecimal.ONE;
					
					do {
						
						num = BigDecimal.ONE.divide(divider, scale, BigDecimal.ROUND_HALF_UP).multiply(x);
						e = e.add(num);
						
						for (int i = 1 ; i <= n ; i++) {
						
							x = x.multiply(x);
							divider = divider.multiply(n1);
						
						}
						// TODO : n의 배수항 계산법 찾아내기
						n1 = n1.add(BigDecimal.ONE);
						
					} while(num.compareTo(new BigDecimal("0.1").pow(scale)) > 0);
					
					System.out.println(e);
					
				}
				
			};
			
		}
		
	}
		

}
