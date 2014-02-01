package ex03_08;

import static org.junit.Assert.*;

import org.junit.Test;

public class PassengerVehicleTest {

	@Test
	public void testClone() {
		PassengerVehicle car1 = new PassengerVehicle();
		car1.setOwner("hase");
		car1.setPersonNum(3);
		car1.setSeatNum(5);
		try {
			PassengerVehicle car2 = car1.clone();
			assertEquals(true, car1.equals(car2));
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}
