package ex24_02;

import java.util.Currency;
import java.util.Locale;

public class CurrencyViewer {
	private static final Locale[] LOCALES = {
			Locale.JAPAN,
			Locale.TAIWAN,
			Locale.CHINA,
			Locale.FRANCE,
			Locale.GERMANY,
			Locale.US,
			Locale.UK,
			Locale.KOREA
	};

	private static final String[] CURRENCIES = {
			"JPY",
			"TWD",
			"EUR",
			"CNY",
			"USD",
			"ZWD",
			"KRW"
	};

	public static void main(String[] args) {
		for (Locale locale : LOCALES) {
			System.out.println(locale.toString());
			for (String code : CURRENCIES) {
				System.out.printf(code + " = ");
				System.out.printf("%3s%n", Currency.getInstance(code).getSymbol(locale));
			}
			System.out.printf("%n");
		}
	}
}
