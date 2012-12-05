package sequence;

import java.util.ArrayList;

public class SequenceImpl<E> implements Sequence<E>
{
	private ArrayList<E> elements;

	public SequenceImpl()
	{
		elements = new ArrayList<E>();
	};

	@Override
	public int size()
	{
		return elements.size();
	}

	@Override
	public E getElement(int i)
	{
		return elements.get(i);
	}

	/**
	 * Adds element to end of sequence
	 */
	@Override
	public void extend(E element)
	{
		assert element != null : "element is null!";
		elements.add(element);
	}

	/**
	 * Removes last element in Sequence
	 */
	@Override
	public void retract()
	{
		assert size() > 0 : String.format("size() = %s <= 0!", size());
		elements.remove(elements.size() - 1);
	}

	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("SIZE: " + size() + "\n");
		sb.append("<");
		for (int i = 0; i < elements.size(); i++)
		{
			sb.append(elements.get(i));
			if (i < elements.size() - 1)
				sb.append(", ");
		}
		sb.append(">");
		return sb.toString();
	}
}
