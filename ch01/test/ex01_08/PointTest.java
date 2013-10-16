package ex01_08;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

public class PointTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testPoint() {
		Point testpoint = new Point();
		assertEquals(new Double(0.0), new Double(testpoint.x));
		assertEquals(new Double(0.0), new Double(testpoint.y));
	}

	@Test
	public void testClear() {
		Point testpoint = new Point(1.1, 2.2);
		testpoint.clear();
		assertEquals(new Double(0.0), new Double(testpoint.x));
		assertEquals(new Double(0.0), new Double(testpoint.y));
	}

	@Test
	public void testDistance() {
		Point currentPoint = new Point();
		Point movedPoint = new Point(3.0, 4.0);
		assertEquals( new Double(5.0), new Double(currentPoint.distance(movedPoint)) );
	}

	@Test
	public void testMoveDoubleDouble() {
		Point currentPoint = new Point();
		currentPoint.move(1.0, 2.0);
		assertEquals(new Double(1.0), new Double(currentPoint.x));
		assertEquals(new Double(2.0), new Double(currentPoint.y));
	}

	@Test
	public void testMovePoint() {
		Point currentPoint = new Point();
		Point movedPoint = new Point(3.0, 4.0);
		currentPoint.move(movedPoint);
		assertEquals(new Double(3.0), new Double(currentPoint.x));
		assertEquals(new Double(4.0), new Double(currentPoint.y));
	}

}
