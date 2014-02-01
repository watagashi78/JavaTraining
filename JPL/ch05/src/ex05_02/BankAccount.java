package ex05_02;

public class BankAccount {
	private long number;	// 口座番号
	private long balance;	// 現在の残高(単位はセント)
	private Action lastAct;	// 最後に行われた処理
	private History history = new History();

	public class Action {
		private String act;
		private long amount;
		Action(String act, long amount) {
			this.act = act;
			this.amount = amount;
		}
		public String toString() {
			// identify our enclosing account
			return number + ": " + act +  " " + amount;
		}
	}

	/**
	 * HistoryクラスはBankAccount特有のクラスであるためネストしたクラスとする.
	 * また、エンクロージング(外部)クラスのprivateフィールドにアクセスする必要がないため、
	 * staticのネストしたクラスとする.
	 * @author Takashi
	 */
	public static class History {
		private Action acts[] = new Action[10];
		private int index = 0;

		public void add(Action act) {
			for (int i = acts.length - 1; i > 0; i--) {
				if (acts[i-1] != null) {
					acts[i] = acts[i-1];
				}
			}
			acts[0] = act;
		}

		public Action next() {
			if (acts[index + 1] == null) {
				index = 0;
				return null;
			} else {
				return acts[++index];
			}
		}

		public void show() {
			for (Action act: acts) {
				if (act != null) {
					System.out.println(act.toString());
				}
			}
		}
	}

	public void deposit(long amount) {
		balance += amount;
		lastAct = this.new Action("deposit", amount);
		history.add(lastAct);
	}

	public void withdraw(long amount) {
		balance -= amount;
		lastAct = this.new Action("withdraw", amount);
		history.add(lastAct);
	}

	/**
	 * 他の口座から引き出して現在の口座へ入金する.
	 * @param other 他の口座
	 * @param amount 入金額
	 */
	public void transfer(BankAccount other, long amount) {
		other.withdraw(amount);
		this.deposit(amount);
		lastAct = this.new Action("transfer", amount);
		other.lastAct = other.new Action("transfer", amount);
	}
}
