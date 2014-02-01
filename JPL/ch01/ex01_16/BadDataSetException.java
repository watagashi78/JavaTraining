package ex01_16;

import java.io.IOException;

public class BadDataSetException extends Exception {
	public String name;
	public IOException e;

	public BadDataSetException(String name, IOException e) {
		this.name = name;
		this.e = e;
	}
}
