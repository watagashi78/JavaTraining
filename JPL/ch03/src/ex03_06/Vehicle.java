package ex03_06;

public class Vehicle {
	private double preSpeed;
	private double preRadian;
	private String owner;
	private GasTank gas;
	private Battery battery;

	public static int nextID = 1; // 次の乗り物の識別番号を保持するstaticフィールド
	private int carID = 0; // 車単位でID番号を保持する非staticフィールド
	static final double TURN_LEFT = -90.0;
	static final double TURN_RIGHT = 90.0;

	private enum Turn {
		TURN_LEFT, TURN_RIGHT
	};

	/**
	 * デフォルトコンストラクタ.
	 */
	public Vehicle() {

	}

	/**
	 * コンストラクタ.
	 * @param speed スピード
	 * @param rad 方角（角度）
	 * @param name 所有者
	 */
	public Vehicle(double speed, double rad, String name) {
		this.preSpeed = speed;
		this.preRadian = rad;
		this.owner = name;
		this.carID = nextID;
		this.gas = new GasTank();
		this.battery = new Battery();
		nextID++;
	}

	public Vehicle(String name) {
		this.owner = name;
		this.carID = nextID;
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
	public void changeSpeed(double preSpeed) {
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
	public void turn(double preRadian) {
		this.preRadian = preRadian;
	}

	/**
	 * 定数TURN_LEFTかTURN_RIGHTを引数にとるセッター.
	 * @param turn
	 */
	public void turn(Turn turn) {
		if (turn == Turn.TURN_LEFT) {
			this.preRadian = TURN_LEFT;
		} else {
			this.preRadian = TURN_RIGHT;
		}
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
	public int getCarID() {
		return carID;
	}

	/**
	 * 今まで使われた識別番号の最大値のゲッター.
	 */
	public static int getMaxID() {
		return nextID - 1;
	}

	/**
	 * インスタンスの持つフィールドを文字列で返す
	 * @return
	 */
	public String toString() {
		return "Vehicle " + Vehicle.getMaxID() + ", Speed=" + preSpeed + ", Radian=" + preRadian + ", Owner=" + owner;
	}

	public boolean start() {
		boolean startflag = false;
		if (gas.empty() && battery.empty()) {
			System.out.println("Gas and Battery are empty.");
		} else if (gas.empty() && !battery.empty()) {
			System.out.println("Gas is empty.");
		} else if (!gas.empty() && battery.empty()) {
			System.out.println("Battery is empty");
		} else {
			startflag = true;
			System.out.println("You can Start!");
		}
		return startflag;
	}

	public void chargeGas(double per) {
		gas.charge(per);
	}

	public void chargeBattery(double per) {
		battery.charge(per);
	}

	public double getGasGauge() {
		return gas.getGasGauge();
	}

	public double getBatteryGauge() {
		return battery.getBatteryGauge();
	}
}
