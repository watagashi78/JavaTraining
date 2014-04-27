package ex20_03;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

public class TestEncryption {

	@Test
	public void encryptDecrypt() {
		byte[] testByte = "Encryption Test String".getBytes();
		byte[] encryption = new byte[0];
		byte[] decryption = new byte[testByte.length];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		EncryptOutputStream eos = new EncryptOutputStream(baos);
		try {
			eos.write(testByte);
			encryption = baos.toByteArray();
			assertThat(encryption, is(not(testByte)));
			eos.close();
		} catch (IOException e) {
			fail();
		}
		ByteArrayInputStream bais = new ByteArrayInputStream(encryption);
		DecryptInputStream dis = new DecryptInputStream(bais);
		try {
			dis.read(decryption);
			assertThat(decryption, is(testByte));
			dis.close();
		} catch (IOException e) {
			fail();
		}

	}

}
