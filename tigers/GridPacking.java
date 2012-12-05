package tigers;

public interface GridPacking<E>
{
	public int getRowCount();

	public int getColumnCount();

	public E getElementAt(int row, int column);
}
