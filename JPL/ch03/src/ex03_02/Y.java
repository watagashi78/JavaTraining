package ex03_02;

class Y extends X {
	protected int yMask = 0xff00;

	{
		System.out.printf("Yのフィールドの初期化  : 0x%04x, 0x%04x, 0x%04x\n",this.xMask, this.yMask, this.fullMask);
	}

	public Y() {
		fullMask |= yMask;
		System.out.printf("Yのコンストラクタが実行: 0x%04x, 0x%04x, 0x%04x\n",this.xMask, this.yMask, this.fullMask);
	}


	/**
	 * メインメソッド.
	 * @param args
	 */
	public static void main(String[] args) {
		Y y = new Y();
	}
}
