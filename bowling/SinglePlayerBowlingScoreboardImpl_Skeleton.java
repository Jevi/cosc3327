package bowling;

/**
 * @author kart NOTE: You can "strip this skeleton for parts" Don't assume that all of the preconditions are included Be sure to
 *         check this code for correctness
 */
public class SinglePlayerBowlingScoreboardImpl_Skeleton implements SinglePlayerBowlingScoreboard
{
	private static final int MAXIMUM_ROLLS = 21; // Maximum rolls in a one
													// player game
	private String playerName;
	private int[] pinsKnockedDownArray = new int[MAXIMUM_ROLLS];
	private int rollCount = 0;

	public SinglePlayerBowlingScoreboardImpl_Skeleton(String playerName)
	{
		assert playerName != null : "playerName is null!";
		this.playerName = playerName;
	}

	public String getPlayerName()
	{
		return playerName;
	}

	public int getCurrentFrame()
	{
		assert !isGameOver() : "Game is over!";
		throw new RuntimeException("NOT IMPLEMENTED!");
	}

	public Mark getMark(int frameNumber, int boxIndex)
	{
		assert 1 <= frameNumber : "frameNumber = " + frameNumber + " < 1!";
		assert frameNumber <= 10 : "frameNumber = " + frameNumber + " > 10!";
		assert 1 <= boxIndex : "boxIndex = " + boxIndex + " < 1!";
		assert boxIndex <= 3 : "boxIndex = " + boxIndex + " > 3!";
		// Exercise for student: Fix
		// assert (boxIndex != 2) || (!isStrike(frameNumber) &&
		// !isSpare(frameNumber)) : "boxIndex = " + boxIndex +
		// ", but there was a Strike! : frameNumber = " + frameNumber;
		// assert (boxIndex != 2) || (!isSpare(frameNumber)) : "boxIndex = " +
		// boxIndex + ", but there was a Spare! : frameNumber = " + frameNumber;
		Mark mark = null;
		if (frameNumber < 10)
			mark = getMarkSingleDigitFrameNumber(frameNumber, boxIndex);
		else
			mark = getMarkTenthFrame(boxIndex);
		return mark;
	}

	private Mark getMarkSingleDigitFrameNumber(int frameNumber, int boxIndex)
	{
		assert 1 <= frameNumber : "frameNumber = " + frameNumber + " < 1!";
		assert frameNumber <= 9 : "frameNumber = " + frameNumber + " > 9!";
		assert 1 <= boxIndex : "boxIndex = " + boxIndex + " < 1!";
		assert boxIndex <= 2 : "boxIndex = " + boxIndex + " > 2!";

		throw new RuntimeException("NOT IMPLEMENTED!");
	}

	private Mark getMarkTenthFrame(int boxIndex)
	{
		assert 1 <= boxIndex : "boxIndex = " + boxIndex + " < 1!";
		assert boxIndex <= 3 : "boxIndex = " + boxIndex + " > 3!";

		throw new RuntimeException("NOT IMPLEMENTED!");
	}

	public int getScore(int frameNumber)
	{
		assert 1 <= frameNumber : "frameNumber = " + frameNumber + " < 1!";
		assert frameNumber <= 10 : "frameNumber = " + frameNumber + " > 10!";

		throw new RuntimeException("NOT IMPLEMENTED!");
	}

	public boolean isGameOver()
	{
		throw new RuntimeException("NOT IMPLEMENTED!");
	}

	public void recordRoll(int pinsKnockedDown)
	{
		assert 0 <= pinsKnockedDown : "pinsKnockedDown = " + pinsKnockedDown + " < 0!";
		assert pinsKnockedDown <= 10 : "pinsKnockedDown = " + pinsKnockedDown + " > 10!";
		assert (getCurrentBall() == 1) || ((getCurrentBall() == 2) && (((getCurrentFrame() == 10) && isStrikeOrSpare(getMark(10, 1))) || ((pinsKnockedDownArray[rollCount - 1] + pinsKnockedDown) <= 10))) || ((getCurrentBall() == 3) && (((getCurrentFrame() == 10) && isStrikeOrSpare(getMark(10, 2))) || ((pinsKnockedDownArray[rollCount - 1] + pinsKnockedDown) <= 10)));
		assert !isGameOver() : "Game is over!";

		pinsKnockedDownArray[rollCount] = pinsKnockedDown;
		rollCount++;
	}

	public int getCurrentBall()
	{
		assert !isGameOver() : "Game is over!";

		throw new RuntimeException("NOT IMPLEMENTED!");
	}

	private static String VERTICAL_SEPARATOR = "#";
	private static String HORIZONTAL_SEPARATOR = "#";
	private static String LEFT_EDGE_OF_SMALL_SQUARE = "[";
	private static String RIGHT_EDGE_OF_SMALL_SQUARE = "]";

	public String getScoreboardDisplay()
	{
		StringBuffer frameNumberLineBuffer = new StringBuffer();
		StringBuffer markLineBuffer = new StringBuffer();
		StringBuffer horizontalRuleBuffer = new StringBuffer();
		StringBuffer scoreLineBuffer = new StringBuffer();
		frameNumberLineBuffer.append(VERTICAL_SEPARATOR);

		markLineBuffer.append(VERTICAL_SEPARATOR);
		horizontalRuleBuffer.append(VERTICAL_SEPARATOR);
		scoreLineBuffer.append(VERTICAL_SEPARATOR);

		for (int frameNumber = 1; frameNumber <= 9; frameNumber++)
		{
			frameNumberLineBuffer.append("  " + frameNumber + "  ");
			markLineBuffer.append(" ");
			markLineBuffer.append(getMark(frameNumber, 1));
			markLineBuffer.append(LEFT_EDGE_OF_SMALL_SQUARE);
			markLineBuffer.append(getMark(frameNumber, 2));
			markLineBuffer.append(RIGHT_EDGE_OF_SMALL_SQUARE);

			final int CHARACTER_WIDTH_SCORE_AREA = 5;
			for (int i = 0; i < CHARACTER_WIDTH_SCORE_AREA; i++)
				horizontalRuleBuffer.append(HORIZONTAL_SEPARATOR);
			if (isGameOver() || frameNumber < getCurrentFrame())
			{
				int score = getScore(frameNumber);
				final int PADDING_NEEDED_BEHIND_SCORE = 1;
				final int PADDING_NEEDED_IN_FRONT_OF_SCORE = CHARACTER_WIDTH_SCORE_AREA - ("" + score).length() - PADDING_NEEDED_BEHIND_SCORE;
				for (int i = 0; i < PADDING_NEEDED_IN_FRONT_OF_SCORE; i++)
					scoreLineBuffer.append(" ");
				scoreLineBuffer.append(score);
				for (int i = 0; i < PADDING_NEEDED_BEHIND_SCORE; i++)
					scoreLineBuffer.append(" ");
			}
			else
			{
				for (int i = 0; i < CHARACTER_WIDTH_SCORE_AREA; i++)
					scoreLineBuffer.append(" ");
			}

			frameNumberLineBuffer.append(VERTICAL_SEPARATOR);
			markLineBuffer.append(VERTICAL_SEPARATOR);
			horizontalRuleBuffer.append(VERTICAL_SEPARATOR);
			scoreLineBuffer.append(VERTICAL_SEPARATOR);
		}
		// Frame 10:
		{
			final String THREE_SPACES = "   ";
			frameNumberLineBuffer.append(THREE_SPACES + 10 + THREE_SPACES);

			markLineBuffer.append(" ");
			markLineBuffer.append(getMark(10, 1));
			markLineBuffer.append(LEFT_EDGE_OF_SMALL_SQUARE);
			markLineBuffer.append(getMark(10, 2));
			markLineBuffer.append(RIGHT_EDGE_OF_SMALL_SQUARE);
			markLineBuffer.append(LEFT_EDGE_OF_SMALL_SQUARE);
			markLineBuffer.append(getMark(10, 3));
			markLineBuffer.append(RIGHT_EDGE_OF_SMALL_SQUARE);

			final int CHARACTER_WIDTH_SCORE_AREA = 8;
			for (int i = 0; i < CHARACTER_WIDTH_SCORE_AREA; i++)
				horizontalRuleBuffer.append(HORIZONTAL_SEPARATOR);
			if (isGameOver())
			{
				int score = getScore(10);
				final int PADDING_NEEDED_BEHIND_SCORE = 1;
				final int PADDING_NEEDED_IN_FRONT_OF_SCORE = CHARACTER_WIDTH_SCORE_AREA - ("" + score).length() - PADDING_NEEDED_BEHIND_SCORE;
				for (int i = 0; i < PADDING_NEEDED_IN_FRONT_OF_SCORE; i++)
					scoreLineBuffer.append(" ");
				scoreLineBuffer.append(score);
				for (int i = 0; i < PADDING_NEEDED_BEHIND_SCORE; i++)
					scoreLineBuffer.append(" ");
			}
			else
			{
				for (int i = 0; i < CHARACTER_WIDTH_SCORE_AREA; i++)
					scoreLineBuffer.append(" ");
			}

			frameNumberLineBuffer.append(VERTICAL_SEPARATOR);
			markLineBuffer.append(VERTICAL_SEPARATOR);
			horizontalRuleBuffer.append(VERTICAL_SEPARATOR);
			scoreLineBuffer.append(VERTICAL_SEPARATOR);
		}

		return getPlayerName() + "\n" + horizontalRuleBuffer.toString() + "\n" + frameNumberLineBuffer.toString() + "\n" + horizontalRuleBuffer.toString() + "\n" + markLineBuffer.toString() + "\n" + scoreLineBuffer.toString() + "\n" + horizontalRuleBuffer.toString();
	}

	public double getHoursSpentWorkingOnThisAssignment()
	{
		throw new RuntimeException("NOT IMPLEMENTED!");
	}

	public int getScoreAgainstTestCasesSubset()
	{
		throw new RuntimeException("NOT IMPLEMENTED!");
	}

	public String toString()
	{
		return getScoreboardDisplay();
	}

	private int getRollIndexOfFirstBall(int frameNumber)
	{
		throw new RuntimeException("NOT IMPLEMENTED!");
	}

	private boolean isStrike(int frameNumber)
	{
		throw new RuntimeException("NOT IMPLEMENTED!");
	}

	private boolean isSpare(int frameNumber)
	{
		throw new RuntimeException("NOT IMPLEMENTED!");
	}

	private boolean isStrikeOrSpare(Mark mark)
	{
		return ((mark == Mark.STRIKE) || (mark == Mark.SPARE));
	}
}