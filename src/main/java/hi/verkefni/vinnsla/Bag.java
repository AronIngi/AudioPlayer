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

	/***
	 * Smiður sem býr til Bag hlut
	 *
	 */
	public Bag()
	{
		first = new Node();
		last = first;
		count = 0;
	}

	/**
	 * bætir við nýjum hlut
	 * @param item hlutur til að bæta við
	 */
	public void add(E item)
	{
		Node<E> next = new Node<E>();
		next.item = item;
		last.item = next.item;
		last.next = next;
		last = next;
		count++;
	}

	/**+
	 * Skilar stærð pokans
	 * @return fjöldi hluta í poka
	 */
	public int size()
	{
		return count;
	}

	/***
	 * finnur og skilar hlut k í listanum
	 * @param k index
	 * @return hlutur á index k
	 */
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
