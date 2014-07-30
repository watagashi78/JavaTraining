package ex22_04;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.junit.Test;

public class TestAttrImpl {

	@Test
	public void test() {
		AttributedImpl<String> test = new AttributedImpl<String>();
		Eye observer = new Eye(test);

		test.add(new Attr<String>("key", "value"));
		assertEquals(observer.attrTable.size(), 1);
		assertEquals(observer.attrTable.get("key").getValue(), "value");
		test.remove("key");
		assertEquals(observer.attrTable.size(), 0);
	}

	private class Eye implements Observer {
		private AttributedImpl<String> watching;
		private Map<String, Attr<String>> attrTable;

		public Eye(AttributedImpl<String> target) {
			watching = target;
			target.addObserver(this);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void update(Observable target, Object map) {
			if (target != watching) {
				throw new IllegalArgumentException();
			}
			attrTable = (Map<String, Attr<String>>) map;
		}
	}
}
