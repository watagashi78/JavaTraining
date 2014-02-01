package ex01_16;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class BadDataSetExceptionTest {

	@Test
	public void testBadDataSetException() {
		String name = "hoge";
		IOException e = new IOException();

		BadDataSetException bad = new BadDataSetException(name, e);
		assertEquals("hoge", bad.name);
		assertEquals(e, bad.e);
	}

}
