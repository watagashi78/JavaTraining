package ex01_08;

public class Point {
	public double x, y;

	/** 原点を表すstaticフィールドorigin
	 * (全てのPointオブジェクトで共有されるフィールド).
	 */
	public static Point origin = new Point();

	/** 座標(x, y)を(0, 0)にクリアするメソッド. */
	public void clear() {
		x = 0.0;
		y = 0.0;
	}

	/** 現在地(x, y)からある地点thatまでの距離を返すメソッド.
	 * @param that ある地点
	 * @return 距離
	 */
	public double distance(Point that) {
		double xdiff = x - that.x;
		double ydiff = y - that.y;
		return Math.sqrt(xdiff * xdiff + ydiff * ydiff);
	}

	/**
	 * 現在地(x, y)を入力地点(x, y)に更新するメソッド.
	 * @param x 移動後のx座標
	 * @param y 移動後のy座標
	 */
	public void move(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 現在地(x, y)をある地点thatに更新するメソッド.
	 * @param that
	 */
	public void move(Point that) {
		this.x = that.x;
		this.y = that.y;
	}

}
