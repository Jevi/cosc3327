package tigers;

//Conceptually, a cage instance has 4 cells, each of which can hold
//at most one cat:
//*******
//**   **
//* * * *
//*  *  *
//* * * *
//**   **
//*******
public interface CatCage
{
	// part of post: rv != null
	public String getIdentifier();

	// part of post: rv = null <==> cell is empty
	public Cat getTopCell();

	// part of post: rv = null <==> cell is empty
	public Cat getLeftCell();

	// part of post: rv = null <==> cell is empty
	public Cat getRightCell();

	// part of post: rv = null <==> cell is empty
	public Cat getBottomCell();
}
