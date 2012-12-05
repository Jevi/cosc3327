package sequence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SequenceUtils
{
	private SequenceUtils()
	{
		// DO NOT INSTANTIATE!
	}

	public static <T> Set<T> getElementsAsSet(Sequence<T> sequence)
	{
		Set<T> elementSet = new HashSet<T>();
		for (int i = 0; i < sequence.size(); i++)
		{
			elementSet.add(sequence.getElement(i));
		}
		return elementSet;
	}

	public static <T> Sequence<T> shallowCopy(Sequence<T> sequence)
	{
		Sequence<T> newSequence = new SequenceImpl<T>();
		for (int i = 0; i < sequence.size(); i++)
		{
			newSequence.extend(sequence.getElement(i));
		}
		return newSequence;
	}

	public static <T> List<T> asList(Sequence<T> sequence)
	{
		List<T> list = new ArrayList<T>();
		for (int i = 0; i < sequence.size(); i++)
		{
			list.add(sequence.getElement(i));
		}
		return list;
	}
}
