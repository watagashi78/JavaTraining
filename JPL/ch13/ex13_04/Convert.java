package ex13_04;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Convert {
	private String type, value;

	public void read(String filepath) {
		ArrayList<Object> list = new ArrayList<Object>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filepath));
			while (br.ready()) {
				list.add(convert(br.readLine()));
			}
			System.out.println(list.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public Object convert(String type_value) {
		String[] arrStr = type_value.split(" ");
		this.type = arrStr[0];
		this.value = arrStr[1];

		switch (type) {
			case "Byte":
				return Byte.valueOf(value);
			case "Short":
				return Short.valueOf(value);
			case "Integer":
				return Integer.valueOf(value);
			case "Long":
				return Long.valueOf(value);
			case "Character":
				return value.charAt(0);
			case "Float":
				return Float.valueOf(value);
			case "Double":
				return Double.valueOf(value);
			case "Boolean":
				return Boolean.valueOf(value);
			default:
				throw new AssertionError("Type(" + type + ") is invalid.");
		}
	}
}