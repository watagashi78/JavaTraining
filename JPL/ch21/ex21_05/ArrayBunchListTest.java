package ex21_05;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ListIterator;

import org.junit.Before;
import org.junit.Test;

public class ArrayBunchListTest {
	List<Integer> list;

	@Before
	public void setup() {
		list = new ArrayBunchList<Integer>(
				new Integer[][] { { 11, 12, 13, 14 }, { 21, 22, 23, 24, 25 }, { 31, 32, 33 } }
				);
	}

	@Test
	public void next() {
		ListIterator<Integer> it = list.listIterator();

		for (int i = 0; i < list.size(); i++) {
			assertThat(it.next(), is(list.get(i)));
		}
	}

	@Test
	public void nextIndex() {
		ListIterator<Integer> it = list.listIterator();

		for (int i = 0; i < list.size(); i++) {
			assertThat(it.nextIndex(), is(i));
			it.next();
		}
	}

	@Test
	public void previous() {
		ListIterator<Integer> it = list.listIterator();

		for (int i = 0; i < list.size(); i++) {
			assertThat(it.next(), is(list.get(i)));
		}

		for (int i = list.size() - 1; 0 <= i; i--) {
			assertThat(it.previous(), is(list.get(i)));
		}
	}

	@Test
	public void previousIndex() {
		ListIterator<Integer> it = list.listIterator();

		for (int i = 0; i < list.size(); i++) {
			assertThat(it.next(), is(list.get(i)));
		}

		for (int i = list.size() - 1; 0 <= i; i--) {
			assertThat(it.previousIndex(), is(i));
			it.previous();
		}
	}

	@Test
	public void setAfterNext() {
		ListIterator<Integer> it = list.listIterator();
		it.next();
		it.set(1);
		assertThat(list.get(1), is(1));
	}

	@Test
	public void setAfterPrevious() {
		ListIterator<Integer> it = list.listIterator();
		it.next();
		it.next();
		it.previous();
		it.set(1);
		assertThat(list.get(1), is(1));
	}

	@Test(expected = IllegalStateException.class)
	public void setWithoutNextAndPrevious() {
		ListIterator<Integer> it = list.listIterator();
		it.set(1);
	}

}
