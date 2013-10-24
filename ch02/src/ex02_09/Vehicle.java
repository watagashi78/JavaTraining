package ex02_09;


public class Vehicle {
	private double preSpeed;
	private double preRadian;
	private String owner;
	public static int nextID = 1;	// 次の乗り物の識別番号を保持するstaticフィールド
	private String carID;			// 車単位でID番号を保持する非staticフィールド

	/**
	 * デフォルトコンストラクタを禁止.
	 */
	protected Vehicle(){

	}

	/**
	 * コンストラクタ
	 * @param name 所有者
	 */
	public Vehicle(String name) {
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

	/**
	 * 今まで使われた識別番号の最大値のゲッター.
	 */
	public static int getMaxID() {
		return nextID - 1;
	}
}

