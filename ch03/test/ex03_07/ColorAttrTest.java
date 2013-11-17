package ex03_07;

import static org.junit.Assert.*;

import org.junit.Test;

public class ColorAttrTest {

	@Test
	public void testEqualsObject() {
		ColorAttr color1 = new ColorAttr("hasegawa");
		ColorAttr color2 = new ColorAttr("watagashi");
		assertEquals(true, color1.equals(color1));
		assertEquals(false, color1.equals(color2));
	}

}
