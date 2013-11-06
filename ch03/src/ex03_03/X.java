package ex03_03;

class X {

	protected final int xMask = 0x00ff;
	protected int fullMask;

	{
		System.out.printf("Xのフィールドの初期化  : 0x%04x, 0x0000, 0x%04x\n",this.xMask, this.fullMask);
	}

	public X() {
		fullMask |= xMask;
		System.out.printf("Xのコンストラクタが実行: 0x%04x, 0x0000, 0x%04x\n",this.xMask, this.fullMask);
	}

	public int mask(int orig) {
		return (orig & fullMask);
	}
}
