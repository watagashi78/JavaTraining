package ex03_08;


public class PassengerVehicle extends Vehicle implements Cloneable {

	private int seatNum;
	private int personNum;

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

	public PassengerVehicle clone() throws CloneNotSupportedException {
		return (PassengerVehicle) super.clone();
	}

}
