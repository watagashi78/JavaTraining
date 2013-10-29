package ex02_13;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

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

	@Test
	public void testGetCarNextID() {
		Vehicle.nextID = 1;
		Vehicle car1 = new Vehicle(120.0, 0.0, "hase");
		assertEquals(1, car1.getCarID());
		assertEquals(2, Vehicle.nextID);
		Vehicle car2 = new Vehicle(100.0, 0.0, "hoge");
		assertEquals(2, car2.getCarID());
		assertEquals(3, Vehicle.nextID);
		Vehicle car3 = new Vehicle(160.0, 20.0, "hase");
		assertEquals(3, car3.getCarID());
		assertEquals(4, Vehicle.nextID);
	}

	@Test
	public void testGetMaxID() {
		Vehicle.nextID = 1;
		Vehicle car1 = new Vehicle(120.0, 0.0, "hase");
		Vehicle car2 = new Vehicle(100.0, 0.0, "hoge");
		Vehicle car3 = new Vehicle(160.0, 20.0, "hase");
		assertEquals(3, Vehicle.getMaxID());
	}

}
