package sequence;

//We will use the notation <e_0, e_1, e_2, ..., e_(k-1)> to denote the
//sequence of size k with the elements e_0, e_1, e_2, ..., e_(k-1).
public interface Sequence<E>
{
	public int size();

	// part of pre: i >= 0
	// part of pre: i < size()
	public E getElement(int i);

	// <e_0, e_1, e_2, ..., e_(k-1)>.extend(e) = <e_0, e_1, e_2, ..., e_(k-1), element>
	// part of pre: element != null
	public void extend(E element);

	// <e_0, e_1, e_2, ..., e_(k-1)>.retract() = <e_0, e_1, e_2, ..., e_(k-2)>
	// <e_0, e_1, e_2, ..., e_(k-1)>.retract().size() = (k-1)
	// part of pre: size() > 0
	public void retract();
}
