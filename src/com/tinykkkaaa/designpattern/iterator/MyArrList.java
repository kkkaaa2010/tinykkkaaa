package com.tinykkkaaa.designpattern.iterator;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class MyArrList<E> implements Iterable<E> {
	
	private static final int INCREMENT = 10;

	private E[] array = (E[]) new Object[10];

	private int size;

	public void add(E e) {
		if (size < array.length) {
			array[size++] = e;
		} else {
			E[] copy = (E[]) new Object[array.length + INCREMENT];
			System.arraycopy(array, 0, copy, 0, size);
			copy[size++] = e;
			array = copy;
		}
	}

	public Object[] toArray() {
		Object[] copy = new Object[size];
		System.arraycopy(array, 0, copy, 0, size);
		return copy;
	}

	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}
	
	private class Itr implements Iterator<E>{
		
		int cursor = 0;

		@Override
		public boolean hasNext() {
			return cursor != size();
		}

		@Override
		public E next() {
			return array[cursor++];
		}

		@Override
		public void remove() {
		}
		
	}

}
