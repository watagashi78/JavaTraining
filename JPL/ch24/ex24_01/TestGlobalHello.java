package ex24_01;

import static org.junit.Assert.*;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class TestGlobalHello {
	@Test
	public void test() {
		Locale.setDefault(Locale.ENGLISH);
		ResourceBundle rb = ResourceBundle.getBundle("ex24_01.GlobalRes");
		assertEquals("Hello", rb.getString(GlobalRes.HELLO));
		assertEquals("Goodbye", rb.getString(GlobalRes.GOODBYE));

		Locale locale = new Locale("en", "AU");
		Locale.setDefault(locale);
		rb = ResourceBundle.getBundle("ex24_01.GlobalRes");
		assertEquals("G'day", rb.getString(GlobalRes.HELLO));

		locale = new Locale("ja_JP");
		Locale.setDefault(locale);
		rb = ResourceBundle.getBundle("ex24_01.GlobalRes");
		assertEquals("こんにちは", rb.getString(GlobalRes.HELLO));
		assertEquals("さようなら", rb.getString(GlobalRes.GOODBYE));

		locale = new Locale("ko_KR");
		Locale.setDefault(locale);
		rb = ResourceBundle.getBundle("ex24_01.GlobalRes");
		assertEquals("안녕하세요", rb.getString(GlobalRes.HELLO));
		assertEquals("안녕", rb.getString(GlobalRes.GOODBYE));
	}

}
