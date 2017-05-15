
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int end;
	private Item[] items;
	
	private void resize(int size) {
		Item[] newArray = Arrays.copyOfRange(items, 0, size);
		this.items = newArray;
	}
	
	public RandomizedQueue()  {
		end = -1;
		this.items = (Item[]) new Object[1];
	}
	public boolean isEmpty() {
		return end < 0;
	}
	public int size() {
		return end + 1;
	}
	public void enqueue(Item item) {
		if (item == null) throw new NullPointerException();
		if (end + 1 == items.length) {
			resize(2 * items.length);
		}
		items[++end] = item;
	}
	public Item dequeue() {
		if (end < 0) throw new NoSuchElementException();
		int random = StdRandom.uniform(end + 1);
		Item remove = items[random];
		items[random] = items[end--];
		return remove;
	}
	public Item sample() {
		if (end < 0) throw new NoSuchElementException();
		int random = StdRandom.uniform(end + 1);
		return items[random];
	}
	
	
	@Override
	public Iterator<Item> iterator() {
		
		Iterator<Item> it = new Iterator<Item>() {
			int current = end;
			Item[] newItems = Arrays.copyOf(items, items.length);
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return current >= 0;
			}

			@Override
			public Item next() {
				// TODO Auto-generated method stub
				if (current < 0) throw new NoSuchElementException();
				int random = StdRandom.uniform(current + 1);
				Item rand = newItems[random];
				swap(newItems, random, current);
				current--;
				return rand;
			}
			
		};
		return it;
	}
	
	private void swap(Item[] items, int i, int j) {
		Item temp = items[i];
		items[i] = items[j];
		items[j] = temp;
	}
	public static void main(String[] args) {
		
	}

}
