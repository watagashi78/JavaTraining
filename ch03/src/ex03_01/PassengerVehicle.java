package ex03_01;


public class PassengerVehicle extends Vehicle {

	private int seatNum;
	private int personNum;
	/**
	 * mainクラス.
	 * @param args
	 */
	public static void main(String[] args) {
		PassengerVehicle car1 = new PassengerVehicle();
		car1.setSeatNum(7);
		car1.setPersonNum(5);
		PassengerVehicle car2 = new PassengerVehicle();
		car2.setSeatNum(5);
		car2.setPersonNum(2);
		System.out.println("Seats=" + car1.getSeatNum() + ", NowSitting=" + car1.getPersonNum());
		System.out.println("Seats=" + car2.getSeatNum() + ", NowSitting=" + car2.getPersonNum());
	}

	public int getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	public int getPersonNum() {
		return personNum;
	}
	public void setPersonNum(int personNum) {
		this.personNum = personNum;
	}

}
