/**
 * 
 */
package mytest2;

import org.junit.jupiter.api.Test;

import mypack2.Main;

/**
 * @author edoardo
 *
 */
class MyUnitTest {

	/*
	 * 
	@Test
	void main_total_test() {
		Main.main(null);
	}
	*
	*/
	
	@Test
	void test_15_x_2() {
		Integer[] multiplying = new Integer[Main.MAX_SIZE];
		Integer[] multiplier  = new Integer[Main.MAX_SIZE];
		String test = " 15 x 2 ";	
		Main.demo(test, multiplying, multiplier);
	}

	@Test
	void test_15_x_20() {
		Integer[] multiplying = new Integer[Main.MAX_SIZE];
		Integer[] multiplier  = new Integer[Main.MAX_SIZE];
		String test = " 15 x 20 ";		
		Main.demo(test, multiplying, multiplier);
	}

	@Test
	void test_15_x_15() {
		Integer[] multiplying = new Integer[Main.MAX_SIZE];
		Integer[] multiplier  = new Integer[Main.MAX_SIZE];
		String test = "15x15";		
		Main.demo(test, multiplying, multiplier);
	}

	@Test
	void test_0_x_15() {
		Integer[] multiplying = new Integer[Main.MAX_SIZE];
		Integer[] multiplier  = new Integer[Main.MAX_SIZE];
		String test = "0x15";		
		Main.demo(test, multiplying, multiplier);
	}

	@Test
	void test_15_x_0() {
		Integer[] multiplying = new Integer[Main.MAX_SIZE];
		Integer[] multiplier  = new Integer[Main.MAX_SIZE];
		String test = "15x0";		
		Main.demo(test, multiplying, multiplier);
	}

	@Test
	void test_120_x_5() {
		Integer[] multiplying = new Integer[Main.MAX_SIZE];
		Integer[] multiplier  = new Integer[Main.MAX_SIZE];
		String test = "120x6";		
		Main.demo(test, multiplying, multiplier);
	}

	@Test
	void test_factorial_1() {
		Main.demoFactorial(1);
	}

	@Test
	void test_factorial_2() {
		Main.demoFactorial(2);
	}

	@Test
	void test_factorial_3() {
		Main.demoFactorial(3);
	}

	@Test
	void test_factorial_4() {
		Main.demoFactorial(4);
	}

	@Test
	void test_factorial_5() {
		Main.demoFactorial(5);
	}

	@Test
	void test_factorial_6() {
		Main.demoFactorial(6);
	}

	@Test
	void test_factorial_7() {
		Main.demoFactorial(7);
	}

/*
 * 	WARNING: THIS IS UNTESTED!
 *****************************
	@Test
	void test_factorial_10() {
		Main.demoFactorial(10);
	}
*/

/*
 * 	WARNING: THIS IS UNTESTED!
 *****************************
	@Test
	void test_factorial_100() {
		Main.demoFactorial(100);
	}
 */
}