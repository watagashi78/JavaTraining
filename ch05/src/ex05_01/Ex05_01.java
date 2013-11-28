package ex05_01;

public class Ex05_01 {

	public static void main(String[] args) {
		System.out.println("Q. 第4章のAttrクラスとAttributedインタフェースについて考えてみてください." +
				"これらはどちらかがどちらかのネストした型であるべきですか" +
				"もしそうであればどちらかがネストした型であることに意味がありますか");
		System.out.println("A. AttrクラスがAttributedをネストした型にすることで、" +
				"インタフェースの外部でAttrクラスが意図せずに作成/変更されるのを防ぐことができる");
	}

}
