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

	public class History {
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
		lastAct = new Action("deposit", amount);
		history.add(lastAct);
	}

	public void withdraw(long amount) {
		balance -= amount;
		lastAct = new Action("withdraw", amount);
		history.add(lastAct);
	}

	public static void main(String args[]) {
		BankAccount hase = new BankAccount();
		hase.deposit(100);
		hase.withdraw(60);
		hase.withdraw(40);
		hase.deposit(1);
		hase.deposit(2);
		hase.deposit(3);
		hase.deposit(4);
		hase.deposit(5);
		hase.deposit(6);
		hase.deposit(7);
		hase.history.show();
		hase.deposit(8);
		hase.history.show();
	}
}
