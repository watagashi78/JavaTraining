package ex04_01;

public class Battery implements EnergySource {

	private double batteryGauge = 0.0;

	public boolean empty() {
		if (batteryGauge == 0.0) {
			return true;
		} else {
			return false;
		}
	}

	public void charge(double per) {
		if (per > 100.0) {
			System.out.println("Over 100%, Battery Gauge will be Full.");
			this.batteryGauge = 100.0;
		} else if (per <= 0.0) {
			System.out.println("Error: Your Input is " + per + " <= 0");
			System.out.println("Please Input Again.");
		} else {
			this.batteryGauge = per;
		}
	}

	public double getBatteryGauge() {
		return batteryGauge;
	}

}
