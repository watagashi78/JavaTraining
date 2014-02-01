package ex01_08;


public class Moving {

	/**
	 * 地点間の移動を行うメインメソッド.
	 * @param args
	 */
	public static void main(String[] args) {
		Point lowerLeft = new Point();			// 左下(スタート)
		Point upperRight = new Point();			// 右上(ゴール)
		Point middlePoint = new Point();		// 中間地点
		Point currentLocation = new Point();	// 現在地

		lowerLeft.x = 0.0;
		lowerLeft.y = 0.0;

		upperRight.x = 1280.0;
		upperRight.y = 1024.0;

		middlePoint.x = 640.0;
		middlePoint.y = 512.0;

		currentLocation.move(lowerLeft);
		System.out.println("You are (" + currentLocation.x + ", " + currentLocation.y + ").");
		currentLocation.move(middlePoint);
		System.out.println("You are (" + currentLocation.x + ", " + currentLocation.y + ").");
		currentLocation.move(upperRight);
		System.out.println("You are (" + currentLocation.x + ", " + currentLocation.y + ").");
	}

}
