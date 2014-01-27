package ex11_02;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestAttr {

	@Test
	public void testAttrString() {
		Attr<Object> color = new Attr<Object>("White");
		assertEquals("White", color.getName());
	}

	@Test
	public void testAttrStringE() {
		Attr<Object> color = new Attr<Object>("White", 1);
		assertEquals("White", color.getName());
		assertEquals(1, color.getValue());
	}

	@Test
	public void testGetName() {
		Attr<Object> color = new Attr<Object>("White");
		assertEquals("White", color.getName());
	}

	@Test
	public void testGetValue() {
		Attr<Object> color = new Attr<Object>("White", 1);
		assertEquals(1, color.getValue());
	}

	@Test
	public void testSetValue() {
		Attr<Object> color = new Attr<Object>("White");
		color.setValue(1);
		assertEquals(1, color.getValue());
	}
}
