package taylorSeries;

import java.math.BigDecimal;

import parallelism.NThread;

public class EParallelism {

	
	public static BigDecimal E = BigDecimal.ZERO;
	public static int collected = 0;
	public static final int cores = Runtime.getRuntime().availableProcessors();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		start(cores);
		
	}
	
	
	public static void start(int threadNumbers)	{
		
		//TODO : fix error
		for (int n = 1 ; n <= threadNumbers ; n++) {
			
			NThread th = new NThread(n) {
			
				@Override
				public void run() {
				
					int scale = 100;
					
					BigDecimal e = BigDecimal.ZERO;
					BigDecimal x = BigDecimal.ONE; //Exponent of e
					BigDecimal divider = BigDecimal.ONE;
					BigDecimal num = BigDecimal.ONE;
					
					BigDecimal n1 = BigDecimal.ONE; // 항 번호
					
					do {
						
						num = BigDecimal.ONE.divide(divider, scale, BigDecimal.ROUND_HALF_UP).multiply(x);
						e = e.add(num);
						
						for (int i = 1 ; i <= n ; i++) {
						
							x = x.multiply(x);
							n1 = n1.add(BigDecimal.ONE);
							divider = divider.multiply(n1);
						
						}
						// TODO : n의 배수항 계산법 찾아내기
						n1 = n1.add(BigDecimal.ONE);
						
					} while(num.compareTo(new BigDecimal("0.1").pow(scale)) > 0);
					
					EParallelism.collect(e);
					
				}
				
			};
			
			th.start();
			
		}
		
	}

	public static synchronized void collect(BigDecimal e) {
		/** Collect value of e of each <code>Thread</code>s and adds them*/
		
		collected++;
		E = E.add(e);
		if (collected == cores) { System.out.println(E); }
		
	}
		

}
