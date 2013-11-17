package ex03_07;

public class ColorAttr extends Attr {
	private ScreenColor myColor;	// 変換された色
	public int hash = 1;

	public ColorAttr(String name, Object value) {
		super(name, value);
		decodeColor();
	}

	public ColorAttr(String name) {
		this(name, "transparent");
	}

	public ColorAttr(String name, ScreenColor value) {
		super(name, value.toString());
		myColor = value;
	}

	@Override
	public Object setValue(Object newValue) {
		// スーパークラスのsetValueを最初に行う
		Object retval = super.setValue(newValue);
		decodeColor();
		return retval;
	}

	/**
	 * 値を記述ではなくScreenColorに設定する.
	 * @param newValue
	 * @return
	 */
	public ScreenColor setValue(ScreenColor newValue) {
		// スーパークラスのsetValueを最初に行う
		super.setValue(newValue.toString());
		ScreenColor oldValue = myColor;
		myColor = newValue;
		return oldValue;
	}

	/**
	 * 変換されたScreenColorオブジェクトを渡す.
	 * @return
	 */
	public ScreenColor getColor() {
		return myColor;
	}

	/**
	 * getValue()で得られる記述からScreenColorを設定する.
	 */
	protected void decodeColor() {
		if (getValue() == null)
			myColor = null;
		else
			myColor = new ScreenColor(getValue());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ColorAttr) {
			if (this == (ColorAttr)obj)
				return true;
			else
				return false;
		}
		return false;
	}

	@Override
	// 31を掛けるのは、「JavaVM内部で計算するときに乗算を高速なビットシフトに書き換えられるから」らしい
	public int hashCode() {
		hash = hash * 31 + (myColor == null ? 0 : myColor.hashCode());
		return hash;
	}
}
