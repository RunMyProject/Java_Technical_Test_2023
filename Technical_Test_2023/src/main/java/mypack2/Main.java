/**
 * 
 */
package mypack2;

import java.util.Arrays;

/**
 * @author edoardo
 *
 */
public class Main {

	private static boolean IS_DEBUG = false;
	
	@FunctionalInterface
	interface NewMultiplication {
	   int asAddition(Integer[] multiplying, Integer[] multiplier);
	}
	
	final public static int MAX_SIZE = 10;
	final public static int MY_DECIMAL = 10;

	private static void initialize(Integer[] multiplying, Integer[] multiplier)	{
		for(int i=0; i<MAX_SIZE; i++) {
			multiplying[i] = 0;
			multiplier[i] = 0;	
		}		
	}

	private static void setMultiplicationStringExpression(String expression, Integer[] multiplying, Integer[] multiplier) {
		
		// Splitting of expression string
		//
		String[] splitter = expression.trim().split("x");

		for(int i=0; i<splitter.length; i++) {

			splitter[i] = splitter[i].trim();
			
			if(i==0) {
				char[] subSplitter = splitter[i].toCharArray();
				int offset = MAX_SIZE - subSplitter.length;
				
				for(int j=0; j<subSplitter.length; j++) {
					multiplying[offset + j]=Character.getNumericValue(subSplitter[j]);
				}
			}
			if(i==1) {
				char[] subSplitter = splitter[i].toCharArray();
				int offset = MAX_SIZE - subSplitter.length;
				
				for(int j=0; j<subSplitter.length; j++) {
					multiplier[offset + j]=Character.getNumericValue(subSplitter[j]);
				}
			}
		}
		
		// DEBUG: System.out.println("splitter length: " + splitter.length);
	}
	
	private static void displayMultiplicationArrays(Integer[] multiplying, Integer[] multiplier) {		
		System.out.println("----------------------------------------------------------------");						
		System.out.print("multiplying: ");
		Arrays.stream(multiplying).forEach(num -> System.out.print(num));
		System.out.println("");
		System.out.print("multiplier:  ");
		Arrays.stream(multiplier).forEach(num -> System.out.print(num));
		System.out.println("");
		System.out.println("----------------------------------------------------------------");								
	}

	private static Integer[] reverseOrder(Integer[] array) {

		Integer[] arrayOut = new Integer[MAX_SIZE];
	
		int index_out = 0;
		for(int i=MAX_SIZE-1; i>=0; i--) {		
			arrayOut[index_out]=array[i];
			index_out++;
		}
		
		return arrayOut;
	}
	
	private static int calculate(String expression, Integer[] multiplying, Integer[] multiplier) {

		initialize(multiplying, multiplier);
		setMultiplicationStringExpression(expression, multiplying, multiplier);
		if(IS_DEBUG) displayMultiplicationArrays(multiplying, multiplier);

		// Using Lambda Expression
		//
		NewMultiplication newMultiplication = (_multiplying_input, _multiplier_input) -> {    // lambda expression

			// Reverse matrix of numbers
			//			
			Integer[] _multiplying = reverseOrder(_multiplying_input);
			Integer[] _multiplier  = reverseOrder(_multiplier_input);
			
			displayMultiplicationArrays(_multiplying, _multiplier);

			int result = 0;
			
			for(int i=0; i<MAX_SIZE; i++) { // First cycle of addictions

				if(_multiplying[i]==0) continue;
				
				int max = (int) Math.pow(MY_DECIMAL, i);
				
				if(i>0) for(int l=0; l<max; l++) { // SHIFT DECIMAL
					
							for(int j=0; j<_multiplying[i]; j++) { // Second cycle of addictions										
							
								for(int k=0; k<MAX_SIZE; k++) {					
									
									int max_2 = (int) Math.pow(MY_DECIMAL, k);
									
									if(k>0) for(int h=0; h<(max_2); h++) result+=_multiplier[k]; // SHIFT DECIMAL
									else result+=_multiplier[k]; // NO DECIMAL							
								}
							}
				} // end for decimal
				else { // Cycle NO DECIMAL
					for(int j=0; j<_multiplying[i]; j++) { // Second cycle of addictions										
						
						for(int k=0; k<MAX_SIZE; k++) {					
							
							int max_2 = (int) Math.pow(MY_DECIMAL, k);
							
							if(k>0) for(int h=0; h<max_2; h++) result+=_multiplier[k]; // SHIFT DECIMAL
							else result+=_multiplier[k]; // NO DECIMAL							
						}
					}					
				}
			}
			
			return result;
	    };
		
	    return newMultiplication.asAddition(multiplying, multiplier);
	}

	public static void demo(String demoTest, Integer[] multiplying, Integer[] multiplier) {

		System.out.println("demo test:" + demoTest);

		int testResult = calculate(demoTest, multiplying, multiplier);
		
		System.out.println("result = " + testResult);
	}

	private static int calculateFactorial(int n) {

		Integer[] multiplying = new Integer[MAX_SIZE];
		Integer[] multiplier  = new Integer[MAX_SIZE];
		
		int result = 1;
		
		if(n<2) return result;
		
		for(int i=2; i<=n; i++) {
			String expression = result + "x" + i;
			System.out.println(expression);
			result = calculate(expression, multiplying, multiplier);
		}
		
		return result;
	}

	public static void demoFactorial(int n) {
		System.out.println(n + "!");
		int result = calculateFactorial(n);		
		System.out.println("result = " + result);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("EXERCISE #2");
		
		// initialize arrays
		//
		Integer[] multiplying = new Integer[MAX_SIZE];
		Integer[] multiplier  = new Integer[MAX_SIZE];

		String test_1 = " 15 x 2";
		demo(test_1, multiplying, multiplier);

		System.out.println("");		
		String test_2 = " 15 x 20";
		demo(test_2, multiplying, multiplier);

		System.out.println("");
		String test_3= " 15 x 15";
		demo(test_3, multiplying, multiplier);

		System.out.println("");		
		String test_4= " 0 x 15";
		demo(test_4, multiplying, multiplier);

		System.out.println("");
		String test_5= " 15 x 0";
		demo(test_5, multiplying, multiplier);
	
		System.out.println("");
		String test_6= "120 x 6";
		demo(test_6, multiplying, multiplier);

		System.out.println("");		
		demoFactorial(1);

		System.out.println("");		
		demoFactorial(2);

		System.out.println("");		
		demoFactorial(3);

		System.out.println("");		
		demoFactorial(4);

		System.out.println("");
		demoFactorial(5);

		System.out.println("");
		demoFactorial(6);

		System.out.println("");
		demoFactorial(7);

		System.out.println("done.");
	}
}
