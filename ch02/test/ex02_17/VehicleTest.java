package ex02_17;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void testTurnDouble() {
		Vehicle testClass = new Vehicle(120.0, 0.0, "hase");
		testClass.turn(180.0);
		assertEquals(180.0, testClass.getPreRadian(), 0.0);
	}

	@Test
	public void testTurnTurn() {
		Vehicle testClass = new Vehicle(120.0, 0.0, "hase");
		testClass.turn(Vehicle.TURN_LEFT);
		assertEquals(-90.0, testClass.getPreRadian(), 0.0);
		testClass.turn(Vehicle.TURN_RIGHT);
		assertEquals(90.0, testClass.getPreRadian(), 0.0);
	}

}
