package ex01_15;

public class Main {

	/**
	 * Lookupを拡張したインタフェースChangedLookupを新たなクラスに実装せよ.
	 * @param args
	 */
	public static void main(String[] args) {
		MixedLookup mixture = new MixedLookup();
		mixture.add("stu1", 100);
		mixture.add("stu2", 86);
		mixture.add("stu3", 45);
		System.out.println(mixture.find("stu2"));
		mixture.remove("stu2");
		System.out.println(mixture.find("stu2"));
	}

}
