package ex10_02;

import static org.junit.Assert.*;

import org.junit.Test;

import ex10_01.Translation;

public class TestTranslation {

	@Test
    public void testTranslate() {
            String escaped = Translation.translate("\n\t\b\r\f\\\'\"hoge");
            assertEquals("\\n\\t\\b\\r\\f\\\\\\\'\\\"hoge", escaped);
    }

}
