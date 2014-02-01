package ex02_03;

import junit.framework.TestCase;

public class VehicleTest extends TestCase {

	protected static void tearDownAfterClass() throws Exception {
	}

	/* ex02_01のVehicleクラスと同様のところは省略 */


	public void testGetCarID() {
		Vehicle car1 = new Vehicle(120.0, 0.0, "hase");
		assertEquals("Car_1", car1.getCarID());
		assertEquals(2, Vehicle.nextID);
		Vehicle car2 = new Vehicle(100.0, 0.0, "hoge");
		assertEquals("Car_2", car2.getCarID());
		assertEquals(3, Vehicle.nextID);
		Vehicle car3 = new Vehicle(160.0, 20.0, "hase");
		assertEquals("Car_3", car3.getCarID());
		assertEquals(4, Vehicle.nextID);
	}

}
