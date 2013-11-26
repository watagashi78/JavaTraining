package ex04_06;

public class Ex04_06 {

	public static void main(String[] args) {
		System.out.println("Q. 練習問題4.5の各問題に対する仮定をどのように変更するとあなたの考えが変更されますか");
		System.out.println("A. 以下のように変更する");

		System.out.println("(a) N分木のノードを表すTreeNode -> 具象クラス");
		System.out.println("ノード毎に他とは異なる動作を持っている -> 抽象クラス");

		System.out.println("(b) 特定の順番（深さ優先、幅優先など）で木を探索するTreeWalker -> 抽象クラス");
		System.out.println("探索するアルゴリズムがすでに決まっている -> 具象クラス");
		System.out.println("特定の順番かどうかすら決まっておらず、とりあえずどうにかして木を探索する -> インタフェース");

		System.out.println("(c) グラフィックシステムにより描画可能なオブジェクトのためのDrawable -> インタフェース");
		System.out.println("多重継承を認めない場合 -> 抽象クラス");

		System.out.println("(d) グラフィックデスクトップから実行できるプログラムのためのApplication -> 具象クラス");
		System.out.println("動作内容は同じだが、OSやバージョンなどによりプログラムの実装が若干変化する場合 -> 抽象クラス");
	}
}
