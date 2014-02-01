package ex09_01;

public class Infinity {

	public static void main(String[] args) {
		double inf1 = Double.POSITIVE_INFINITY;
		double inf2 = Double.NEGATIVE_INFINITY;


		double sum, difference, product, quotient;

		sum = inf1 + inf1;
		difference = inf1 - inf1;
		product = inf1 * inf1;
		quotient = inf1 / inf1;
		System.out.println("Sum = ∞ + ∞ = " + sum);
		System.out.println("Difference = ∞ - ∞ = " + difference);
		System.out.println("Product = ∞ * ∞ = " + product);
		System.out.println("Quotient = ∞ / ∞ = " + quotient);

		sum = inf2 + inf2;
		difference = inf2 - inf2;
		product = inf2 * inf2;
		quotient = inf2 / inf2;
		System.out.println("Sum = -∞ + (-∞) = " + sum);
		System.out.println("Difference = -∞ - (-∞) = " + difference);
		System.out.println("Product = -∞ *  (-∞) = " + product);
		System.out.println("Quotient = -∞ / (-∞) = " + quotient);

		sum = inf1 + inf2;
		difference = inf1 - inf2;
		product = inf1 * inf2;
		quotient = inf1 / inf2;
		System.out.println("Sum = ∞ + (-∞) = " + sum);
		System.out.println("Difference = ∞ - (-∞) = " + difference);
		System.out.println("Product = ∞ * (-∞) = " + product);
		System.out.println("Quotient = ∞ / (-∞) = " + quotient);
	}

}
