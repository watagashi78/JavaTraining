package ex04_05;

public class Ex04_05 {

	public static void main(String[] args) {
		System.out.println("Q. 次の型がインタフェース、抽象クラス、具象クラスのどれで設計すべきか考えよ");
		System.out.println("A. 以下のように設計した");

		System.out.println("(a) N分木のノードを表すTreeNode -> 具象クラス");
		System.out.println("幅を持たせて今後拡張するような実装が考えにくいため");

		System.out.println("(b) 特定の順番（深さ優先、幅優先など）で木を探索するTreeWalker -> 抽象クラス");
		System.out.println("共通部分の実装は共有して、探索等の異なる部分はabstractでオーバーライドする");

		System.out.println("(c) グラフィックシステムにより描画可能なオブジェクトのためのDrawable -> インタフェース");
		System.out.println("描画可能な印であるマーカーの役割とその他の多重継承が必要だと考えられるため");

		System.out.println("(d) グラフィックデスクトップから実行できるプログラムのためのApplication -> 具象クラス");
		System.out.println("実際に実行するApplicationの実装部分を記述するため");
	}

}
