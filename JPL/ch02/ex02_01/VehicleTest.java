package ex02_01;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

public class VehicleTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetPreSpeed() {
		Vehicle testClass = new Vehicle(120.0, 0.0, "hase");
		assertEquals(120.0, testClass.getPreSpeed(), 0.0);
	}

	@Test
	public void testSetPreSpeed() {
		Vehicle testClass = new Vehicle(120.0, 0.0, "hase");
		testClass.setPreSpeed(200.0);
		assertEquals(200.0, testClass.getPreSpeed(), 0.0);
	}

	@Test
	public void testGetPreRadian() {
		Vehicle testClass = new Vehicle(120.0, 0.0, "hase");
		assertEquals(0.0, testClass.getPreRadian(), 0.0);
	}

	@Test
	public void testSetPreRadian() {
		Vehicle testClass = new Vehicle(120.0, 0.0, "hase");
		testClass.setPreRadian(180.0);
		assertEquals(180.0, testClass.getPreRadian(), 0.0);
	}

	@Test
	public void testGetOwner() {
		Vehicle testClass = new Vehicle(120.0, 0.0, "hase");
		assertEquals("hase", testClass.getOwner());
	}

	@Test
	public void testSetOwner() {
		Vehicle testClass = new Vehicle(120.0, 0.0, "hase");
		testClass.setOwner("hoge");
		assertEquals("hoge", testClass.getOwner());
	}

}
