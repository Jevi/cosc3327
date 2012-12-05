package tictactoe;

import java.util.Scanner;

public class PlayGame
{

	public static void main(String[] args)
	{
		TicTacToeBoard game = new TicTacToeBoardImpl_Nunez();
		Scanner r = new Scanner(System.in);

		while (!game.isGameOver())
		{
			System.out.println(TicTacToeBoardUtils_Nunez.toString(game));

			System.out.println("Game Turn: " + game.getTurn());
			System.out.print("row (0-2): ");
			int row = r.nextInt();
			System.out.print("column (0-2): ");
			int column = r.nextInt();
			game.setMark(row, column);
			System.out.println("Mark Placed: " + game.getMark(row, column));
			System.out.println();

		}
		System.out.println(TicTacToeBoardUtils_Nunez.toString(game));
		System.out.println("Game Winner: " + game.getWinner());
		r.close();
	}
}
