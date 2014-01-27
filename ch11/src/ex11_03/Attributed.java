package ex11_03;

/**
 * AttrのElementにワイルドカード型を指定する必要がある.
 */
public interface Attributed {
	void add(Attr<?> newAttr);
	Attr<?> find(String attrName);
	Attr<?> remove(String attrName);
	java.util.Iterator<Attr<?>> attrs();
}