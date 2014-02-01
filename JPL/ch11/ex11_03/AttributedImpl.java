package ex11_03;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AttributedImpl implements Attributed, Iterable<Attr<?>> {
	protected Map<String, Attr<?>> attrTable = new HashMap<String, Attr<?>>();

	// パラメーターとして一切の型の値を渡すことができない
	public static void main(String[] args) {
		AttributedImpl sample = new AttributedImpl();
		// sample.add(Attr<String> newAttr); // コンパイルエラー
		sample.find("attrName");
		sample.remove("attrName");
	}

	@Override
	public Iterator<Attr<?>> iterator() {
		return attrs();
	}

	@Override
	public void add(Attr<?> newAttr) {
		attrTable.put(newAttr.getName(), newAttr);
	}

	@Override
	public Attr<?> find(String attrName) {
		return attrTable.get(attrName);
	}

	@Override
	public Attr<?> remove(String attrName) {
		return attrTable.remove(attrName);
	}

	@Override
	public Iterator<Attr<?>> attrs() {
		return attrTable.values().iterator();
	}

}
