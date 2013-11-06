package ex03_03;

class Y extends X {
	protected final int yMask = 0xff00;

	{
		fullMask |= yMask;
		System.out.printf("Yのフィールドの初期化  : 0x%04x, 0x%04x, 0x%04x\n",this.xMask, this.yMask, this.fullMask);
	}

	public Y() {
		System.out.printf("Yのコンストラクタが実行: 0x%04x, 0x%04x, 0x%04x\n",this.xMask, this.yMask, this.fullMask);
	}

	/**
	 * メインメソッド.
	 * @param args
	 */
	public static void main(String[] args) {
		Y y = new Y();
		System.out.println("Q.オブジェクトの生成の間に拡張したクラスからの値を使用して、マスクの値を設定するのが重要な場合にはどうすれば問題を回避できますか");
		System.out.println("A.コンストラクタより前の初期化ブロックでマスクを設定する");
	}
}
