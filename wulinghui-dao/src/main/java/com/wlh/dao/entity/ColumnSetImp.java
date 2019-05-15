package com.wlh.dao.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class ColumnSetImp  implements ColumnSet{
	List arrayList;
	public ColumnSetImp(List arrayList) {
		super();
		this.arrayList = arrayList;
	}

	@Override
	public String toString() {
		return arrayList.toString();
	}

	public  void forEach(Consumer action) {
		arrayList.forEach(action);
	}

	public int size() {
		return arrayList.size();
	}

	public boolean isEmpty() {
		return arrayList.isEmpty();
	}

	public boolean contains(Object o) {
		return arrayList.contains(o);
	}

	public Iterator iterator() {
		return arrayList.iterator();
	}

	public Object[] toArray() {
		return arrayList.toArray();
	}

	public Object[] toArray(Object[] a) {
		return arrayList.toArray(a);
	}

	public boolean add(Object e) {
		return arrayList.add(e);
	}

	public boolean remove(Object o) {
		return arrayList.remove(o);
	}

	public boolean containsAll(Collection c) {
		return arrayList.containsAll(c);
	}

	public boolean addAll(Collection c) {
		return arrayList.addAll(c);
	}

	public boolean addAll(int index, Collection c) {
		return arrayList.addAll(index, c);
	}

	public boolean removeAll(Collection c) {
		return arrayList.removeAll(c);
	}

	public boolean retainAll(Collection c) {
		return arrayList.retainAll(c);
	}

	public  void replaceAll(UnaryOperator operator) {
		arrayList.replaceAll(operator);
	}

	public  boolean removeIf(Predicate filter) {
		return arrayList.removeIf(filter);
	}

	public  void sort(Comparator c) {
		arrayList.sort(c);
	}

	public void clear() {
		arrayList.clear();
	}

	public boolean equals(Object o) {
		return arrayList.equals(o);
	}

	public int hashCode() {
		return arrayList.hashCode();
	}

	public Object get(int index) {
		return arrayList.get(index);
	}

	public Object set(int index, Object element) {
		return arrayList.set(index, element);
	}

	public void add(int index, Object element) {
		arrayList.add(index, element);
	}

	public  Stream stream() {
		return arrayList.stream();
	}

	public Object remove(int index) {
		return arrayList.remove(index);
	}

	public  Stream parallelStream() {
		return arrayList.parallelStream();
	}

	public int indexOf(Object o) {
		return arrayList.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return arrayList.lastIndexOf(o);
	}

	public ListIterator listIterator() {
		return arrayList.listIterator();
	}

	public ListIterator listIterator(int index) {
		return arrayList.listIterator(index);
	}

	public List subList(int fromIndex, int toIndex) {
		return arrayList.subList(fromIndex, toIndex);
	}

	public  Spliterator spliterator() {
		return arrayList.spliterator();
	}
	
}
