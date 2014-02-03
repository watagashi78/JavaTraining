package ex13_05;

public class Comma {
	public String addComma(String input) {
		String output = "";
		int commaCount = (input.length() - 1) / 3;
		int surplus = input.length() % 3;
		if (surplus == 0) surplus = 3;			// 桁数が3の倍数のときは初めのカンマを飛ばす
		output = input.substring(0, surplus);	// 初めのカンマまで文字列を代入
		for (int i = 0; i < commaCount; i++) {	// カンマの数だけループ
			int left = i * 3 + surplus;			// カンマで区切られた3つの数字のうち、左の数字
			output += "," + input.substring(left, left + 3);
		}
		return output;
	}
}
