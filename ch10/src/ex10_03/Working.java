package ex10_03;

public class Working {
	enum Days {
		MONDAY,
		TUESDAY,
		WEDNESDAY,
		THURSDAY,
		FRIDAY,
		SATURDAY,
		SUNDAY
	}

	public static void main(String[] args) {
		System.out.println(isWorkDay(Days.MONDAY));
		System.out.println(isWorkDay(Days.TUESDAY));
		System.out.println(isWorkDay(Days.WEDNESDAY));
		System.out.println(isWorkDay(Days.THURSDAY));
		System.out.println(isWorkDay(Days.FRIDAY));
		System.out.println(isWorkDay(Days.SATURDAY));
		System.out.println(isWorkDay(Days.SUNDAY));
	}

	/*
	public static boolean isWorkDay(Days day) {
		if (day.equals(Days.MONDAY)) {
			return true;
		} else if (day.equals(Days.TUESDAY)) {
			return true;
		} else if (day.equals(Days.WEDNESDAY)) {
			return true;
		} else if (day.equals(Days.THURSDAY)) {
			return true;
		} else if (day.equals(Days.FRIDAY)) {
			return true;
		} else if (day.equals(Days.SATURDAY)) {
			return false;
		} else {
			return false;
		}
	}
	*/
	public static boolean isWorkDay(Days day) {
		switch (day) {
			case MONDAY: case TUESDAY: case WEDNESDAY: case THURSDAY: case FRIDAY:
				return true;
			case SATURDAY: case SUNDAY:
				return false;
			default:
				return false;
		}
	}
}
