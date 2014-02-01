package ex01_16;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) throws BadDataSetException {
		MyUtilities util = new MyUtilities();
		double[] input = util.getDataSet("double_dataset");
		for(int i = 0; i < 2; i++) {
				System.out.println(input[i]);
		}
	}

}
