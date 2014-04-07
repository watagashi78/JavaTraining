package ex17_01;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MemoryCapacity {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(" ***** 起動時 ***** ");
		getMemoryInfo();

		ArrayList<Color> colors = new ArrayList<Color>();
		for(int r = 0; r < 256; r++) {
			for(int g = 0; g < 256; g++) {
				for(int b = 0; b < 50; b++) {
					Color c = new Color(r, g, b);
					colors.add(c);
				}
			}
		}
		System.out.println(" ***** オブジェクト生成後 ***** ");
		getMemoryInfo();
		Runtime.getRuntime().gc();
		System.out.println(" ***** ガベージコレクタ起動後 ***** ");
		getMemoryInfo();
	}

	/**
	 * Java 仮想マシンのメモリ総容量、使用量、
	 * 使用を試みる最大メモリ容量の情報を返します。
	 * @return Java 仮想マシンのメモリ情報
	 */
	public static void getMemoryInfo() {
	    DecimalFormat f1 = new DecimalFormat("#,###MB");
	    DecimalFormat f2 = new DecimalFormat("##.#");
	    long free = Runtime.getRuntime().freeMemory() / 1024 / 1024;
	    long total = Runtime.getRuntime().totalMemory() / 1024 / 1024;
	    long max = Runtime.getRuntime().maxMemory() / 1024 / 1024;
	    long used = total - free;
	    double ratio = (used * 100 / (double)total);
	    String info =
	    "合計         = " + f1.format(total) + "\n" +
	    "使用量       = " + f1.format(used) + " (" + f2.format(ratio) + "%)\n" +
	    "使用可能最大 = " + f1.format(max);
	    System.out.println(info);
	}

}
