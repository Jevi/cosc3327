package tigers;

import java.util.ArrayList;
import java.util.List;

public class GridPackingImpl_Nunez<E> implements GridPacking<E>
{
	// Use whatever encoding scheme and data members you want...

	// The linear list elementList's elements are assigned to the grid
	// in a left to right, top to bottom fashion.
	// Ex: GridPackingImpl(3, 2, ["S", "T", "X", "W", "P", "A"] results in
	// the following grid:
	// "S" "T"
	// "X" "W"
	// "P" "A"

	private int rowCount;
	private int columnCount;
	private List<E> elemeList;

	public GridPackingImpl_Nunez(int rowCount, int columnCount, List<E> elementList)
	{
		assert elementList.size() <= rowCount * columnCount : String.format("elementList.size() = %s > %s = rowCount*columnCount!", elementList.size(), rowCount * columnCount);
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.elemeList = new ArrayList<E>(elementList);
	}

	@Override
	public int getRowCount()
	{
		return rowCount;
	}

	@Override
	public int getColumnCount()
	{
		return columnCount;
	}

	@Override
	public E getElementAt(int row, int column)
	{
		assert row <= rowCount && row >= 0 : "row is out of bounds rowCount = " + rowCount + " you gave: " + row;
		assert column <= columnCount && column >= 0 : "row is out of columnCount = " + columnCount + " you gave: " + column;

		int listIndex = row * columnCount + column;
		return elemeList.get(listIndex);
	}

	/*
	 * this method is optional, student can erase if desired private void setElementAt(int row, int column, E element) { int
	 * listIndex = row * columnCount + column; elemeList.set(listIndex, element); }
	 */

	@Override
	public boolean equals(Object obj)
	{
		GridPacking<CatCage> gridPacking = (GridPacking<CatCage>) obj;

		if (getRowCount() != gridPacking.getRowCount() || getColumnCount() != gridPacking.getColumnCount())
		{
			return false;
		}

		for (int row = 0; row < gridPacking.getRowCount(); row++)
		{
			for (int col = 0; col < gridPacking.getColumnCount(); col++)
			{
				if (!getElementAt(row, col).equals(gridPacking.getElementAt(row, col)))
				{
					return false;
				}
			}
		}

		return true;
	}

	public int hashCode()
	{
		return 0;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		int index = 0;
		for (Object E : elemeList)
		{
			if (index == columnCount - 1)
			{
				sb.append(E.toString() + "\n");
				index = 0;
			}
			else
			{
				sb.append(E.toString() + " ");
				++index;
			}
		}
		return sb.toString();
	}
}