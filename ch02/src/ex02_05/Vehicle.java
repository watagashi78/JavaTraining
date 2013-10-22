package ex02_05;


public class Vehicle {
	private double preSpeed;
	private double preRadian;
	private String owner;
	public static long nextID = 1;	// 次の乗り物の識別番号を保持するstaticフィールド
	private String carID;			// 車単位でID番号を保持する非staticフィールド

	/**
	 * デフォルトコンストラクタを禁止.
	 */
	protected Vehicle(){

	}

	/**
	 * コンストラクタ
	 * インスタンスが車だと識別する手段が分からなかったので全て車だと仮定.
	 * @param speed スピード
	 * @param rad 方角（角度）
	 * @param name 所有者
	 */
	public Vehicle(double speed, double rad, String name) {
		this.preSpeed = speed;
		this.preRadian = rad;
		this.owner = name;
		this.carID = "Car_" + nextID;
		Vehicle.nextID++;
	}

	/**
	 * 現在のスピードのゲッター.
	 * @return
	 */
	public double getPreSpeed() {
		return preSpeed;
	}

	/**
	 * 現在のスピードのセッター.
	 * @param preSpeed
	 */
	public void setPreSpeed(double preSpeed) {
		this.preSpeed = preSpeed;
	}

	/**
	 * 現在の方向（角度）のゲッター.
	 * @return
	 */
	public double getPreRadian() {
		return preRadian;
	}

	/**
	 * 現在の方向（角度）セッター.
	 * @param preRadian
	 */
	public void setPreRadian(double preRadian) {
		this.preRadian = preRadian;
	}

	/**
	 * 所有者のゲッター.
	 * @return
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * 所有者のセッター.
	 * @param owner
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * 車のID番号のゲッター.
	 * @return
	 */
	public String getCarID() {
		return carID;
	}

	public static void main(String[] args) {
		Vehicle car1 = new Vehicle(120.0, 0.0, "hase");
		Vehicle car2 = new Vehicle(100.0, 0.0, "hoge");
		Vehicle car3 = new Vehicle(160.0, 20.0, "hase");
		System.out.println("ID=" + car1.getCarID() + ", Speed=" + car1.getPreSpeed() + ", Radian=" + car1.getPreRadian() + ", Owner=" + car1.getOwner());
		System.out.println("ID=" + car2.getCarID() + ", Speed=" + car2.getPreSpeed() + ", Radian=" + car2.getPreRadian() + ", Owner=" + car2.getOwner());
		System.out.println("ID=" + car3.getCarID() + ", Speed=" + car3.getPreSpeed() + ", Radian=" + car3.getPreRadian() + ", Owner=" + car3.getOwner());
	}
}

