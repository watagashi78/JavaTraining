package ex21_07;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

public class MyStackTest {
	MyStack<Integer> stack;

	@Before
	public void setup() {
		stack = new MyStack<Integer>();
	}

	@Test
	public void empty() {
		assertThat(stack.empty(), is(true));

		stack.push(1);
		assertThat(stack.empty(), is(false));
	}

	@Test
	public void peek() {
		stack.push(1);
		assertThat(stack.peek(), is(1));
		assertThat(stack.peek(), is(1));
	}

	@Test
	public void pop() {
		stack.push(1);
		stack.push(2);
		assertThat(stack.pop(), is(2));
		assertThat(stack.pop(), is(1));
	}

	@Test
	public void search() {
		stack.push(1);
		stack.push(2);
		stack.push(3);
		assertThat(stack.search(1), is(3));
		assertThat(stack.search(2), is(2));
		assertThat(stack.search(3), is(1));
		assertThat(stack.search(4), is(-1));
	}

	@Test(expected = EmptyStackException.class)
	public void peekFromEmptyStack() {
		stack.peek();
	}

	@Test(expected = EmptyStackException.class)
	public void popFromEmptyStack() {
		stack.peek();
	}
}
