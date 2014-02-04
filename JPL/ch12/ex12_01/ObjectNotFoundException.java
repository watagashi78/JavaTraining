package ex12_01;

public class ObjectNotFoundException extends Exception {
	public final String type;	// オブジェクトの型を文字列で保持する
	public final String value;	// オブジェクトの値を文字列で保持する

	// 例外をスローせずにnullをreturnすると、正常な動作でnullが入った場合と異常な動作でnullが入った場合で区別ができない
	public ObjectNotFoundException(Object obj) {
		super("Object Not Found");
		this.type = obj.getClass().toString();
		this.value = obj.toString();
	}
}
