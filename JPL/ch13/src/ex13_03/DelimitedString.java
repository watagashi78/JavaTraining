package ex13_03;

import java.util.ArrayList;
import java.util.LinkedList;

public class DelimitedString {

	/**
	 * テキストに記載のメソッド.
	 * １つの入力文字列に１つの区切られた文字列しかないと仮定している.
	 * @param from
	 * @param start
	 * @param end
	 * @return
	 */
	public String delimitedString(String from, char start, char end) {
		int startPos = from.indexOf(start);
		int endPos = from.lastIndexOf(end);
		if (startPos == -1) {
			return null;
		} else if (endPos == -1) {
			return from.substring(startPos);
		} else if (startPos > endPos) {
			return null;
		} else {
			return from.substring(startPos, endPos + 1);
		}
	}

	/**
	 * １つの入力文字列に複数区切られた文字列があってもその全ての文字列を配列で返す.
	 * 《test1》, 《test2》		-> ["test1","test2"]
	 * 《test1《test2》》		-> ["test1《test2"]
	 *  》《test1》》《test2》	-> ["test1","test2"]
	 * @param from
	 * @param start
	 * @param end
	 * @return
	 */
	public String[] allDelimitedString(String from, char start, char end) {
		String[] result;
		ArrayList<String> str = new ArrayList<String>();
		LinkedList<Integer> startPosList = getAllIndexes(from, start);
		LinkedList<Integer> endPosList = getAllIndexes(from, end);
		int startPos, endPos;
		while(!startPosList.isEmpty()) {	// 開始文字が存在する限りループ
			startPos = startPosList.poll();
			endPos = -1;
			if (!endPosList.isEmpty()) endPos = endPosList.poll();
			// 終了文字が開始文字より先の場合、次の終了文字を取り出す
			while (startPos > endPos && !endPosList.isEmpty()) endPos = endPosList.poll();
			if (startPos > endPos) {		// 最後の終了文字が開始文字よりも先の場合
				str.add(from.substring(startPos));
				startPosList.clear();
			} else {
				str.add(from.substring(startPos + 1, endPos));
				// 次の開始文字が現在の終了文字よりも先にある場合、全てリストから削除する
				while (!startPosList.isEmpty() && startPosList.peek() < endPos) startPosList.poll();
 			}
		}
		result = str.toArray(new String[str.size()]);
		return result;
	}

	/**
	 * 入力文字列中に含まれる、指定した文字のインデックスのリストを返すメソッド.
	 * @param input
	 * @param check
	 * @return
	 */
	public LinkedList<Integer> getAllIndexes(String input, char check) {
		LinkedList<Integer> indexes = new LinkedList<Integer>();
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == check) indexes.add(i);
		}
		return indexes;
	}
}
