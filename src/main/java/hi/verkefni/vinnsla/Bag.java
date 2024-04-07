package hi.verkefni.vinnsla;

public class Bag<E>
{
	class Node<E>
	{
		E item;
		Node next;
	}

	Node<E> first;
	Node<E> last;

	int count;

	public Bag()
	{
		first = new Node();
		last = first;
		count = 0;
	}

	public void add(E item)
	{
		Node<E> next = new Node<E>();
		next.item = item;
		last.item = next.item;
		last.next = next;
		last = next;
		count++;
	}

	public boolean isEmpty()
	{
		if(first == last)
			return true;
		return false;
	}

	public int size()
	{
		return count;
	}

	public E peek(int k)
	{
		E item = first.item;
		Node<E> n = first;
		for(int i = 0; i < k; i++)
		{
			n = n.next;
			item = n.item;
		}
		return item;
	}

	public static void main(String[] args)
	{
		Bag<Integer> b = new Bag<Integer>();
		b.add(2);
		b.add(3);

		System.out.println();
	}
}
