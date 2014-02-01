package ex04_01;

public class GasTank implements EnergySource{
	private double gasGauge = 0.0;

	public boolean empty() {
		if (gasGauge == 0.0) {
			return true;
		} else {
			return false;
		}
	}

	public void charge(double per) {
		if (per > 100.0) {
			System.out.println("Over 100%, Now Gas Gauge is Full.");
			this.gasGauge = 100.0;
		} else if (per <= 0.0) {
			System.out.println("Error: Your Input is " + per + " <= 0");
			System.out.println("Please Input Again.");
		} else {
			this.gasGauge = per;
		}
	}

	public double getGasGauge() {
		return gasGauge;
	}
}
