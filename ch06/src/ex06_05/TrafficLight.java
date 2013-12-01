package ex06_05;

public class TrafficLight {

	enum Colors {
		RED("RED") {
			public Color getColor() {
				return color;
			}
		},
		GREEN("GREEN") {
			public Color getColor() {
				return color;
			}
		},
		YELLOW("YELLOW"){
			public Color getColor() {
				return color;
			}
		};

		Color color;
		Colors(String name) { color = new Color(name); }
		public abstract Color getColor();
	}

	public static void main(String[] args) {

		System.out.println("Q. getColorをabstructとして各enum定数が正しいColorオブジェクトを返すように定数固有のメソッドを定義せよ");

		System.out.println(Colors.RED.getColor().toString());
		System.out.println(Colors.GREEN.getColor().toString());
		System.out.println(Colors.YELLOW.getColor().toString());

		System.out.println("A. 今回の問題ではそれぞれの色に対して動作が変化しないため、定数固有のメソッドの使用は推奨しない");
	}

}
