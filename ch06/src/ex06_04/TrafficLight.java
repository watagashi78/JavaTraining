package ex06_04;

public class TrafficLight {

	enum Colors {
		RED("RED"),
		GREEN("GREEN"),
		YELLOW("YELLOW");

		Color color;
		Colors(String name) { color = new Color(name); }
		public Color getColor() { return color; }
	}

	public static void main(String[] args) {
		System.out.println(Colors.RED.getColor().toString());
		System.out.println(Colors.GREEN.getColor().toString());
		System.out.println(Colors.YELLOW.getColor().toString());
	}

}
