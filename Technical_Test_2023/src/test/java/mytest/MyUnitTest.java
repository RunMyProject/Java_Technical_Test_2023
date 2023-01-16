/**
 * 
 */
package mytest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import mypack.Main;

/**
 * @author edoardo
 *
 */
class MyUnitTest {

	@Test
	void test_debug_json() {
		try {
			String[] args = {
					"/d",
					"/json"
			};
			Main.main(args);
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void test_debug_csv() {
		try {
			String[] args = {
					"/d",
					"/csv"
			};
			Main.main(args);
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

}
