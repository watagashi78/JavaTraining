package ex04_01;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void testStart() {
		Vehicle car = new Vehicle(100.0, 0.0, "hase");
		assertEquals(false, car.start());
		car.chargeGas(100.0);
		assertEquals(false, car.start());
		car.chargeBattery(100.0);
		assertEquals(true, car.start());
	}

	@Test
	public void testChargeGas() {
		Vehicle car = new Vehicle(100.0, 0.0, "hase");
		assertEquals(0.0, car.getGasGauge(), 0);
		car.chargeGas(80.0);
		assertEquals(80.0, car.getGasGauge(), 0);
	}

	@Test
	public void testChargeBattery() {
		Vehicle car = new Vehicle(100.0, 0.0, "hase");
		assertEquals(0.0, car.getBatteryGauge(), 0);
		car.chargeBattery(80.0);
		assertEquals(80.0, car.getBatteryGauge(), 0);
	}

}

