package ex24_03;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

// デフォルトが寛大で、厳格な場合は曜日が違うとparseできなかった.
public class DateViewer {
	private static final Integer[] DF_INT = { DateFormat.FULL, DateFormat.LONG, DateFormat.MEDIUM, DateFormat.SHORT };
	private static final String[] DF_STR = { "FULL", "LONG", "MEDIUM", "SHORT" };
	private static final Locale LOC = Locale.US;

	public static void main(String[] args) {
		show("8/29/86 5:00 PM");
		show("Aug 29, 1986 5:00:00 PM");
		show("August 29, 1986 5:00:00 PM EDT");
		show("Friday, August 29, 1986 5:00:00 PM EDT");
		show("Saturday, August 29, 1986 5:00:00 PM EDT");
	}

	public static void show(String source) {
		System.out.println("***** " + source + " *****");
		for (Integer i : DF_INT) {
			DateFormat df = DateFormat.getDateTimeInstance(i, i, LOC);
			df.setLenient(true); // df.isLenient? 寛大 : 厳格
			try {
				Date date = df.parse(source);
				System.out.println("** parsed with " + DF_STR[i] + " **");
				for (Integer j : DF_INT) {
					DateFormat df2 = DateFormat.getDateTimeInstance(j, j, LOC);
					System.out.printf("%6s: %s%n", DF_STR[j], df2.format(date));
				}
			} catch (ParseException e) {
				System.out.println("** Cannot parse with " + DF_STR[i] + " **");
			}
		}
		System.out.printf("%n");
	}
}
