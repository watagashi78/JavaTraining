package ex03_01;

import static org.junit.Assert.*;

import org.junit.Test;

import ex03_01.PassengerVehicle;


public class PassengerVehicleTest {

	PassengerVehicle car = new PassengerVehicle();

	@Test
	public void testSetGetSeatNum() {
		car.setSeatNum(5);
		assertEquals(5, car.getSeatNum());
	}

	@Test
	public void testSetGetPersonNum() {
		car.setPersonNum(4);
		assertEquals(4, car.getPersonNum());
	}

}
