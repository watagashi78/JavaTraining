package ex20_07;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Attr {
	private final String name;
	private Object value = null;

	public Attr(String name) {
		this.name = name;
	}

	public Attr(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public Attr(DataInputStream in) throws IOException, ClassNotFoundException {
		this.name = in.readUTF();
		byte[] b = new byte[in.readInt()];
		in.read(b);
		ByteArrayInputStream bais = new ByteArrayInputStream(b);
		ObjectInputStream ois = new ObjectInputStream(bais);
		this.value = ois.readObject();
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	public Object setValue(Object newValue) {
		Object oldVal = value;
		value = newValue;
		return oldVal;
	}

	public String toString() {
		return name + "='" + value + "'";
	}

	public void writeStream(DataOutputStream out) throws IOException {
		out.writeUTF(name);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(value);
		oos.close();
		baos.close();
		out.writeInt(baos.toByteArray().length);
		out.write(baos.toByteArray());
	}
}
