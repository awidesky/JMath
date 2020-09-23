package approximation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Collections;
import java.util.LinkedList;

import taylorSeries.Pi;
import taylorSeries.Sin;

public class TrigonometricFunction {

	public final static BigDecimal PI = Pi.getPi(100);
	public final static BigDecimal a = new BigDecimal("-0.46");
	public final static BigDecimal halfPI = PI.multiply(new BigDecimal("0.5"));
	public final static double aDouble = a.doubleValue(); 
	
	
	public static void main(String[] args) {
		
		ErrorTestSinLinear();
		
	}
	
	
	/**
	 * <p>Returns the trigonometric sine of an angle.
	 * 
	 * <p>How it works:
	 * <ul><p> 1. make <code>x</code> between [0,<code>pi</code>/2]
	 * <p> 2. if 0 &lt; <code>x</code> &lt; 0.236872109056403, sin(<code>x</code>) = x
	 * <p> 3. if 0.236872109056403 &lt; <code>x</code> &lt; 0.666358419264344, sin(<code>x</code>) = -0.1<code>x</code>(<code>x</code>-<code>pi</code>)(<code>x</code>+<code>pi</code>)
	 * <p> 4. if 0.666358419264344 &lt; <code>x</code> &lt; <code>pi</code>/2 , sin(<code>x</code>) = -0.46(<code>x</code>-<code>pi</code>/2)^2 + 1</ul>
	 *  
	 * 
	 * <p>this method works 10 to 8 faster than Taylor series, and error is under 0.0099, average .
	 *  
	 * */
	public static BigDecimal getSin(final BigDecimal x, int scale) {
		
		
		if (x.compareTo(BigDecimal.ZERO) < 0) return new BigDecimal("-1").multiply(getSin(x, scale));
		
		if (x.compareTo(new BigDecimal("0.236872109056403")) <= 0) return x;
		if (x.compareTo(new BigDecimal("0.666358419264344")) <= 0) return x.multiply(new BigDecimal("-1")).multiply(x.subtract(PI)).multiply(x.add(PI)).setScale(scale, BigDecimal.ROUND_HALF_UP);
		
		if (x.compareTo(PI.multiply(new BigDecimal(2))) > 0) return getSin(x.remainder(PI.multiply(new BigDecimal(2)), new MathContext(x.precision())), scale);
		if (x.compareTo(PI) > 0) return getSin(x.subtract(PI), scale).multiply(new BigDecimal("-1"));
		if (x.compareTo(PI.divide(new BigDecimal(2))) > 0) return getSin(PI.subtract(x), scale);

		

		return a.multiply(x.subtract(halfPI).pow(2)).add(BigDecimal.ONE).setScale(scale, BigDecimal.ROUND_HALF_UP);
		
	}
	
	
	
	public static double getSin(final double x) {
		
		
		/**
		 * 
		 * this function works faster than <code>Math#sin</code> when <code>x</code>is between around 1 and 7, and error is about 0.00004.
		 * 
		 * */
		
		if (x < 0) return -getSin(x);
		if (x <= 0.236872109056403) return x;
		if (x > Math.PI*2) return getSin(x % (Math.PI*2));
		if (x > Math.PI) return (-1) * getSin(x - Math.PI);
		if (x > Math.PI/2) return getSin(Math.PI - x);

		
		
		return aDouble*Math.pow(x - (Math.PI/2), 2) + 1;
		
	}
	
	
	
	private static void ErrorTestSinLinear() {
		
		LinkedList<BigDecimal> error = new LinkedList<>(); 
		
		int scale = 100;
		
		BigDecimal half = new BigDecimal("0.5");
		BigDecimal Epsilon = new BigDecimal("0.00001");
		//BigDecimal a = new BigDecimal("96").divide(PI.pow(3), scale, BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal("24").divide(PI.pow(2), scale, BigDecimal.ROUND_HALF_UP));
		//BigDecimal b = new BigDecimal("0.1");

		//for (; a.compareTo(new BigDecimal("-0.445")) >= 0 ; a = a.add(new BigDecimal("-0.001"))) {
			
		for (BigDecimal x = new BigDecimal("0.2") ; x.compareTo(PI.multiply(half)) < 0 ; x = x.add(Epsilon)) {
			
			//BigDecimal y0 = a.multiply(x).add(b);
			BigDecimal y0 = a.multiply(x.subtract(PI.multiply(new BigDecimal("0.5"))).pow(2)).add(BigDecimal.ONE);
			BigDecimal result = Sin.getSin(x, scale).subtract(y0).abs();
			
			error.add(result);

			
		}
		
		System.out.println("a : " + a);
		System.out.println("size : " + error.size());
		System.out.println("max : " + Collections.max(error) );
		System.out.println("min : " + Collections.min(error));
		
		BigDecimal sum = BigDecimal.ZERO;
		
		for (BigDecimal i : error) {
			
			sum = sum.add(i);
			
		}
		
		System.out.println("evg : " + sum.divide(new BigDecimal(error.size()), 20, BigDecimal.ROUND_HALF_UP));
		System.out.println();
		error = new LinkedList<>();
		
		//}
		
	}
	
	
	@SuppressWarnings("unused")
	private static void BruteErrorTestSinLinear() {
		
		BigDecimal[] nums = { BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO };
		BigDecimal Epsilon = new BigDecimal("0.001");
		int scale = 100;
		
		//for (BigDecimal a = new BigDecimal("0.5") ; a.compareTo(new BigDecimal("1.5")) < 0 ; a = a.add(Epsilon) ) {
		//BigDecimal a = new BigDecimal("96").divide(PI.pow(3), scale, BigDecimal.ROUND_HALF_UP)
		BigDecimal a = new BigDecimal("0.6644388981710444470514099410686507712294696780239781942252285672359150291779164070806172475033658208505038042490639962623163895954602589466423674834494254739974578872956799335207985308140803534511382175030172960978788181795244382831802166481890987147905855296375325746881434941161170606905727469417149879115385783438468840945679653995664842305788096580825098747356529607971779506614804368335872949599448658603916471726159744643243926376386442085232364688688436432064181318849975045035647737010204935967846456875405029");
			for (BigDecimal b = new BigDecimal("-0.5") ; b.compareTo(new BigDecimal("0.5")) < 0 ; b = b.add(Epsilon) ) {
				
				for (BigDecimal x = new BigDecimal("0").add(Epsilon) ; x.compareTo(PI) < 0 ; x = x.add(Epsilon) ) {
					
					BigDecimal y0 = a.multiply(x).add(b);
					BigDecimal result = BigDecimal.ONE.subtract(y0.divide(Sin.getSin(x, scale), scale, BigDecimal.ROUND_HALF_UP)).abs();
					
					if (nums[2].compareTo(result) >= 0) { nums[0] = a; nums[1] = b; nums[2] = result; }
					//System.out.println(x);
				}
				System.out.println("one loop of b" + b);
			}
			System.out.println("one loop of a" + a);
		//}
		
		
		System.out.println("a : " + nums[0].toString());
		System.out.println("b : " + nums[1].toString());
		System.out.println("error : " + nums[2].toString());
		
	}

}
