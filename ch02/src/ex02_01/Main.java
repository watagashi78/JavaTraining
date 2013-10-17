package ex02_01;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Vehicle car = new Vehicle(120.0, 0.0, "hase");
		System.out.println("(MaxSpeed, Radian, Owner = (" + car.getPreSpeed() + ", " + car.getPreRadian() + ", " + car.getOwner() + ")" );
	}
}