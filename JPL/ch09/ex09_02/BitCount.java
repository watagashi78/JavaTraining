package ex09_02;

public class BitCount {
	public static int getBitCount(int num) {
		int count = 0;
		while(num != 0) {
			if((num & 1) == 1) {
				count++;
			}
			num = num >> 1;
		}
		return count;
	}
}
