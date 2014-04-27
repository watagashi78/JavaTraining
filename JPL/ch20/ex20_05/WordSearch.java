package ex20_05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WordSearch {
	private File file;
	private FileReader fileReader;
	private BufferedReader br;
	private ArrayList<String> textArr = new ArrayList<String>();

	public WordSearch(String filepath){
		try {
			file = new File(filepath);
			fileReader = new FileReader(file);
			br = new BufferedReader(fileReader);
			String str;
			while ((str = br.readLine()) != null) {
				textArr.add(str);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void search(String str) {
		int count = 0;
		for (int i = 0; i < textArr.size(); i++) {
			if (textArr.get(i).contains(str)) {
				System.out.println((i + 1) + ":" + textArr.get(i));
				count++;
			}
		}
		System.out.println("\"" + str + "\"が含まれる行数: " + count + "行");
	}

	public static void main(String[] args) {
		WordSearch ws = new WordSearch("C:\\Users\\Takashi\\git\\JavaTraining\\JPL\\ch20\\ex20_05\\input.txt");
		ws.search("吾輩");
	}
}
