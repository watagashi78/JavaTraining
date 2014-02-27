package ex16_03;
import java.lang.reflect.Member;
import java.util.ArrayList;

public class ClassContents {
	public static void main(String[] args) {
		try {
			Class<?> c = Class.forName(args[0]);
			System.out.println(c);
			printMembers(c.getFields(), c.getDeclaredFields());
			printMembers(c.getDeclaredConstructors());
			printMembers(c.getMethods(), c.getDeclaredMethods());
		} catch (ClassNotFoundException e) {
			System.out.println("unknown class: " + args[0]);
		}
	}

	private static void printMembers(Member[] mems) {
		for (Member m : mems) {
			if (m.getDeclaringClass() == Object.class)
				continue;
			String decl = m.toString();
			System.out.print(" ");
			System.out.println(strip(decl, "java."));
		}
	}

	private static void printMembers(Member[] publicmems, Member[] decmems) {
		ArrayList<Member> result = new ArrayList<>();
		for (Member pm : publicmems) {
			result.add(pm);
		}
		for (Member dm : decmems) {
			if (result.contains(dm))
				continue;
			else
				result.add(dm);
		}
		for (Member m : result) {
			if (m.getDeclaringClass() == Object.class)
				continue;
			String decl = m.toString();
			System.out.print(" ");
			System.out.println(strip(decl, "java."));
		}
	}

	private static String strip(String str, String stripChar) {
		String result;
		if (str.contains(stripChar))
			result = str.replaceAll(stripChar + "([a-zA-Z]*\\.)*", "");
		else
			result = str;
		return result;
	}

}
