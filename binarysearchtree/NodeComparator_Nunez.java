package binarysearchtree;

import java.util.Comparator;

public class NodeComparator_Nunez implements Comparator<Node_Nunez>
{

	@Override
	public int compare(Node_Nunez o1, Node_Nunez o2)
	{
		return o1.getKey() < o2.getKey() ? -1 : o1.getKey() == o2.getKey() ? 0 : 1;
	}

}
