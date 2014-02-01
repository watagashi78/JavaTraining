package ex06_05;

public class Color {
	private String name;
	private int[] rgb = {0, 0, 0};

	Color(String name) {
		this.name = name;
		switch(name) {
			case "RED":
				this.rgb[0] = 255;
				break;
			case "GREEN":
				this.rgb[1] = 255;
				break;
			case "YELLOW":
				this.rgb[0] = this.rgb[1] = 255;
				break;
			default:
				System.out.println(name + " is undefined");
				break;
		}
	}

	@Override
	public String toString() {
		return "Color:" + name + ", RGB:(" + rgb[0] + "," + rgb[1] + "," + rgb[2] + ")";
	}

}
