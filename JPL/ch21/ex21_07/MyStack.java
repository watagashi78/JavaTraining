package ex21_07;

import java.util.ArrayList;
import java.util.EmptyStackException;

// ArrayListを内部で使用すべき
// * ArrayListのサブクラスとすると本来のStackにはないremoveなどのメソッドを定義する必要がある.
//  * ArrayListのメソッドからMyStackに意図しない挙動が行われる可能性がある.
public class MyStack<E> {
	private ArrayList<E> array = new ArrayList<E>();

	boolean empty() {
		return array.isEmpty();
	}

	E peek() {
		if(empty()) {
            throw new EmptyStackException();
        }
		return array.get(array.size() - 1);
	}

	E pop() {
		if(empty()) {
            throw new EmptyStackException();
        }
		return array.remove(array.size() - 1);
	}

	E push(E item) {
		array.add(item);
		return item;
	}

	int search(Object o) {
		int index = array.indexOf(o);
		return index == -1 ? -1 : array.size() - index;
	}
}
