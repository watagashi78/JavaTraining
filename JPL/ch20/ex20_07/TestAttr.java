package ex20_07;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.junit.Test;

public class TestAttr {

	@Test
	public void test() throws IOException, ClassNotFoundException {
		String name = "hase";
		Object value = "gawa";
		Attr attrWrite = new Attr(name, value);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(baos);
		attrWrite.writeStream(out);

		DataInputStream in = new DataInputStream(new ByteArrayInputStream(baos.toByteArray()));
		Attr attrRead = new Attr(in);
		assertEquals(attrRead.getName(), "hase");
		assertEquals(attrRead.getValue(), "gawa");
	}

}
