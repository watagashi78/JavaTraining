package ex03_08;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	// not true => equalsメソッドはhashが一致しているのかも見てる？
	@Test
	public void testClone() {
		Vehicle car1 = new Vehicle(100.0, 0.0, "hase");
		try {
			Vehicle car2 = car1.clone();
			assertEquals(true, car1.equals(car2));
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

}
