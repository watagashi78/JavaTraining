package ex02_09;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void testGetMaxID() {
		Vehicle testInstance = new Vehicle("Legacy");
		Vehicle testInstance2 = new Vehicle("GT-R");
		Vehicle testInstance3 = new Vehicle("MINI Cooper");
		assertEquals(3, Vehicle.getMaxID());
	}
}
