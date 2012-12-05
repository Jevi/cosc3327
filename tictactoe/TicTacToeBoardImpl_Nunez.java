package tictactoe;

public class TicTacToeBoardImpl_Nunez implements TicTacToeBoard
{
	// Symbolics:
	protected static final int NO_MOVE = -1;
	protected static final int NO_MATCH = -1;

	protected int[] movesArray;

	public TicTacToeBoardImpl_Nunez()
	{
		final int CELL_COUNT = ROW_COUNT * COLUMN_COUNT;
		movesArray = new int[CELL_COUNT];
		for (int i = 0; i < CELL_COUNT; i++)
		{
			movesArray[i] = NO_MOVE;
		}
	}

	// gets mark at position if conditions are met
	public Mark getMark(int row, int column)
	{
		assert 0 <= row : "0 > " + row + " = row!";
		assert 0 <= column : "0 > " + column + " = column!";
		assert row < ROW_COUNT : "row = " + row + " >= " + ROW_COUNT + " = ROW_COUNT";
		assert column < COLUMN_COUNT : "column = " + column + " >= " + COLUMN_COUNT + " = COLUMN_COUNT";

		int index = getGridIndex(row, column);
		int valueIndex = getArrayIndex(movesArray, index);

		if (valueIndex != NO_MATCH)
		{
			return getArrayIndexMark(valueIndex);
		}
		return null;
	}

	// rv == null <==> it is neither player's turn (i.e. game is over)
	public Mark getTurn()
	{
		if (!isGameOver())
		{
			int index = getArrayIndex(movesArray, NO_MOVE);
			return getArrayIndexMark(index);
		}
		return null;
	}

	// part of post: rv == null <==> game ended in a tie
	public Mark getWinner()
	{
		assert isGameOver() == true : "Game is not OVER!";

		return hasWinner();
	}

	// rv: if current state has winner return winner else null
	protected Mark hasWinner()
	{
		// top horizontal
		if (getMark(0, 0) == getMark(0, 1) && getMark(0, 0) == getMark(0, 2))
		{
			if (getMark(0, 0) != null)
			{
				System.out.println("Top Horizontal Win");
				return getMark(0, 0);
			}
		}

		// middle horizontal
		if (getMark(1, 0) == getMark(1, 1) && getMark(1, 0) == getMark(1, 2))
		{
			if (getMark(1, 0) != null)
			{
				System.out.println("Middle Horizontal Win");
				return getMark(1, 0);
			}
		}

		// bottom horizontal
		if (getMark(2, 0) == getMark(2, 1) && getMark(2, 0) == getMark(2, 2))
		{
			if (getMark(2, 0) != null)
			{
				System.out.println("Bottom Horizontal Win");
				return getMark(2, 0);
			}
		}

		// middle vertical
		if (getMark(0, 1) == getMark(1, 1) && getMark(0, 1) == getMark(2, 1))
		{
			if (getMark(0, 1) != null)
			{
				System.out.println("Middle Vertical Win");
				return getMark(0, 1);
			}
		}

		// right vertical
		if (getMark(0, 2) == getMark(1, 2) && getMark(0, 2) == getMark(2, 2))
		{
			if (getMark(0, 2) != null)
			{
				System.out.println("Right Vertical Win");
				return getMark(0, 2);
			}
		}

		// left vertical
		if (getMark(0, 0) == getMark(1, 0) && getMark(0, 0) == getMark(2, 0))
		{
			if (getMark(0, 0) != null)
			{
				System.out.println("Left Vertical Win");
				return getMark(0, 0);
			}
		}

		// left to right diagonal - from top
		if (getMark(0, 0) == getMark(1, 1) && getMark(0, 0) == getMark(2, 2))
		{
			if (getMark(0, 0) != null)
			{
				System.out.println("Left to Right Diagonal (from top) Win");
				return getMark(0, 0);
			}
		}

		// left to right diagonal - from bottom
		if (getMark(2, 0) == getMark(1, 1) && getMark(2, 0) == getMark(0, 2))
		{
			if (getMark(2, 0) != null)
			{
				System.out.println("Right to Left Diagonal (from bottom) Win");
				return getMark(2, 0);
			}
		}
		return null;
	}

	// rv: Mark who is assigned to that index position
	protected Mark getArrayIndexMark(int i)
	{
		assert i < movesArray.length : "i > movesArray.length! : " + i;

		if (i % 2 == 0)
		{
			return Mark.X;
		}
		return Mark.O;
	}

	// rv : true if there is a winner or if game ended in tie
	public boolean isGameOver()
	{
		if (isTie())
		{
			System.out.println("Tie Game");
			return true;
		}
		else if (hasWinner() != null)
		{
			return true;
		}
		return false;
	}

	// rv: true if game has no winner and if the board is filled
	protected boolean isTie()
	{
		if (getArrayIndex(movesArray, NO_MOVE) == NO_MATCH && hasWinner() == null)
		{
			return true;
		}
		return false;
	}

	// sets board index in moves array - if position is empty
	public void setMark(int row, int column)
	{
		assert 0 <= row : "0 > " + row + " = row!";
		assert 0 <= column : "0 > " + column + " = column!";
		assert row < ROW_COUNT : "row = " + row + " >= " + ROW_COUNT + " = ROW_COUNT";
		assert column < COLUMN_COUNT : "column = " + column + " >= " + COLUMN_COUNT + " = COLUMN_COUNT";

		assert !isGameOver() : "Game is over!";
		assert getMark(row, column) == null : "getMark(" + row + ", " + column + ") = " + getMark(row, column) + " is not null!";

		int gridIndex = getGridIndex(row, column);
		int arrayIndex = getArrayIndex(movesArray, gridIndex);
		assert arrayIndex == NO_MATCH : "arrayIndex = " + arrayIndex + "! : row = " + row + " column = " + column + " already occupied!";
		int firstNoMovesIndex = getArrayIndex(movesArray, NO_MOVE);

		// part of pre:
		assert firstNoMovesIndex != NO_MATCH : "gridIndex = " + gridIndex + " already appears in movesArray at array index = " + firstNoMovesIndex + "!";

		movesArray[firstNoMovesIndex] = gridIndex;
	}

	// convert coordinates to single value array index
	protected static int getGridIndex(int row, int column)
	{
		assert row >= 0 : "row = " + row + " < 0!";
		assert row < ROW_COUNT : "row = " + row + " >= " + ROW_COUNT + " = NUM_ROWS!";
		assert column >= 0 : "column = " + column + " < 0!";
		assert column < ROW_COUNT : "column = " + column + " >= " + COLUMN_COUNT + " = NUM_COLUMNS!";

		return row * COLUMN_COUNT + column;
	}

	// return array index where the key is first matched
	protected static int getArrayIndex(int[] intArray, int key)
	{
		int i = 0;
		boolean foundMatch = false;
		while (!foundMatch && i < intArray.length)
		{
			foundMatch = (intArray[i] == key);
			if (!foundMatch)
			{
				i++;
			}
		}

		if (!foundMatch)
		{
			return NO_MATCH;
		}
		else
		{
			return i;
		}
	}
}
