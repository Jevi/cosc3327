package bowling;

import java.util.Scanner;

public class Game
{
	public static void main(String[] args)
	{
		SinglePlayerBowlingScoreboard bs = new SinglePlayerBowlingScoreboardImpl_Nunez("Alexei");
		Scanner reader = new Scanner(System.in);

		while (!bs.isGameOver())
		{
			System.out.printf("\nCurrent Frame: %d\nCurrent Ball: %d\nScoreboard Representation: %s\n\n", bs.getCurrentFrame(), bs.getCurrentBall(), bs.toString());

			System.out.print("# of pins to knock down: ");
			bs.recordRoll(reader.nextInt());

			System.out.print("\nGet Mark - FrameNumber: ");
			int frameNumber = reader.nextInt();
			System.out.print("\nGet Mark - BoxIndex: ");
			int boxIndex = reader.nextInt();
			System.out.println("Mark: " + bs.getMark(frameNumber, boxIndex));
		}
	}

}
