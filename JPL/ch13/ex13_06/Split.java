package ex13_06;

public class Split {
	public String split(String input, char split, int digit) {
		String output = "";
		int commaCount = (input.length() - 1) / digit;
		int surplus = input.length() % digit;
		if (surplus == 0) surplus = digit;			// 桁数が3の倍数のときは初めのカンマを飛ばす
		output = input.substring(0, surplus);	// 初めのカンマまで文字列を代入
		for (int i = 0; i < commaCount; i++) {	// カンマの数だけループ
			int left = i * digit + surplus;			// カンマで区切られた3つの数字のうち、左の数字
			output += split + input.substring(left, left + digit);
		}
		return output;
	}
}
