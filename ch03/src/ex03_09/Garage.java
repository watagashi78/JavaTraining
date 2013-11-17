package ex03_09;

public class Garage extends Vehicle implements Cloneable{
	private Vehicle[] garage;
	private int top;

	public Garage(int maxContents) {
		garage = new Vehicle[maxContents];
		top = -1;
	}

	public void push(Vehicle obj) {
		garage[++top] = obj;
	}

	public Vehicle pop() {
		return garage[top--];
	}

	public void print() {
		for (int i = 0; i <= top; i++) {
			System.out.println(garage[i].toString());
		}
	}

	public Garage clone() {
		try {
			Garage copyGarage = (Garage) super.clone();
			copyGarage.garage = garage.clone();
			return copyGarage;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}

	public static void main(String[] args) {
		Vehicle car1 = new Vehicle(100.0, 0.0, "hase");
		Vehicle car2 = new Vehicle(120.0, 0.0, "hoge");
		Vehicle car3 = new Vehicle(140.0, 0.0, "hoge2");
		Garage g1 = new Garage(3);
		g1.push(car1);
		g1.push(car2);
		g1.print();
		g1.pop();
		g1.print();
		g1.push(car3);
		g1.print();
	}
}
