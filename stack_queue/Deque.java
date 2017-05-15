import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private class Node {
		Node pre;
		Node next;
		Item val;
		public Node(Item item) {
			this.val = item;
		}
	}
	
	private Node head;
	private Node tail;
	private int size;
	public Deque() {
		size = 0;
	}
	public boolean isEmpty() {
		return this.size == 0;
	}
	public int size() {
		return size;
	}
	public void addFirst(Item item) {
		if (item == null) throw new NullPointerException();
		size++;
		if (head == null) {
			head = new Node(item);
			tail = head;
			return;
		}
		Node i = new Node(item);
		head.pre = new Node(item);
		head.pre.next = head;
		head = head.pre;
	}
	public void addLast(Item item) {
		if (item == null) throw new NullPointerException();
		size++;
		if (tail == null) {
			tail = new Node(item);
			head = tail;
			return;
		}
		tail.next = new Node(item);
		tail.next.pre = tail;
		tail = tail.next;
		
	}
	public Item removeFirst() {
		if (size == 0) throw new NoSuchElementException();
		Node remove = head;
		
		if (size == 1) {
			head = tail = null;
			size--;
			return remove.val;
		}
		size--;
		head = head.next;
		head.pre = null;
		return remove.val;
		
	}
	public Item removeLast() {
		if (size == 0) throw new NoSuchElementException();
		Node remove = tail;
		if (size == 1) {
			head = tail = null;
			size--;
			return remove.val;
		}
		size--;
		tail = tail.pre;
		tail.next = null;
		return remove.val;
	}
	public Iterator<Item> iterator() {
		
		Iterator<Item> it = new Iterator<Item>() {
			Node cur = head;
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return cur!=null;
			}

			@Override
			public Item next() {
				// TODO Auto-generated method stub
				if (cur == null) throw new NoSuchElementException();
				Node remove = cur;
				cur = cur.next;
				return remove.val;
			}
			
			public void remove(){
				throw new UnsupportedOperationException();
			}
		};
		return it;
	}

}