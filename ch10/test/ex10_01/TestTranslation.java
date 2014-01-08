package ex10_01;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTranslation {

	@Test
    public void testTranslate() {
            String escaped = Translation.translate("\n\t\b\r\f\\\'\"hoge");
            assertEquals("\\n\\t\\b\\r\\f\\\\\\\'\\\"hoge", escaped);
    }

}
