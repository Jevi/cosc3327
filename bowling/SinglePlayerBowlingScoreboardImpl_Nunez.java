package bowling;

public class SinglePlayerBowlingScoreboardImpl_Nunez implements SinglePlayerBowlingScoreboard
{

	private static final int MAXIMUM_ROLLS = 21;
	private String playerName;
	private int[] pinsKnockedDownArray = new int[MAXIMUM_ROLLS];
	private int rollCount = 0;

	public SinglePlayerBowlingScoreboardImpl_Nunez(String playerName)
	{
		assert playerName != null;

		this.playerName = playerName;

		for (int i = 0; i < pinsKnockedDownArray.length; i++)
		{
			pinsKnockedDownArray[i] = 0;
		}
	}

	@Override
	public String getPlayerName()
	{
		return playerName;
	}

	@Override
	public boolean isGameOver()
	{
		if (rollCount >= 20)
		{
			return true;
		}

		if (checkFrameLimit() > 10)
		{
			return true;
		}

		return false;
	}

	@Override
	public int getScore(int frameNumber)
	{
		assert 1 <= frameNumber : "frameNumber = " + frameNumber + " < 1!";
		assert frameNumber <= 10 : "frameNumber = " + frameNumber + " > 10!";

		int currentScore = 0;

		for (int i = 1; i <= frameNumber; i++)
		{
			int currentFrame = i;

			// check STRIKE
			if (getMark(currentFrame, 2) == Mark.STRIKE)
			{
				// check if score can be produced
				if (getMark(currentFrame + 1, 1) != Mark.EMPTY || getMark(currentFrame + 1, 2) != Mark.EMPTY)
				{
					// check if mark is not STRIKE
					if (getMark(currentFrame + 1, 1) != Mark.EMPTY && getMark(currentFrame + 1, 2) != Mark.EMPTY)
					{
						currentScore += (10 + getMarkValue(getMark(currentFrame + 1, 1), currentFrame + 1, 1) + getMarkValue(getMark(currentFrame + 1, 2), currentFrame + 1, 2));
					}
					// check if STRIKE
					else if (getMark(currentFrame + 1, 2) == Mark.STRIKE && (getMark(currentFrame + 2, 1) != Mark.EMPTY || getMark(currentFrame + 2, 2) != Mark.EMPTY))
					{
						if (getMark(currentFrame + 2, 1) != Mark.EMPTY)
						{
							currentScore += (10 + 10 + getMarkValue(getMark(currentFrame + 2, 1), currentFrame + 2, 1));
						}
						else
						{
							currentScore += 30;
						}
					}
				}
			}
			// check not SPARE
			else if (getMark(currentFrame, 1) != Mark.EMPTY && getMark(currentFrame, 2) != Mark.EMPTY && getMark(currentFrame, 2) != Mark.SPARE)
			{
				currentScore += getMarkValue(getMark(currentFrame, 1), currentFrame, 1) + getMarkValue(getMark(currentFrame, 2), currentFrame, 2);
			}
			// check SPARE
			else if (getMark(currentFrame, 1) != Mark.EMPTY && getMark(currentFrame, 2) == Mark.SPARE)
			{
				if (getMark(currentFrame + 1, 1) != Mark.EMPTY || getMark(currentFrame + 1, 2) != Mark.EMPTY)
				{
					if (getMark(currentFrame + 1, 1) != Mark.EMPTY)
					{
						currentScore += (10 + getMarkValue(getMark(currentFrame + 1, 1), currentFrame + 1, 1));
					}
					else
					{
						currentScore += 20;
					}
				}
			}
			System.out.println("Current Score: " + currentScore + " at currentFrame: " + currentFrame);
		}

		return currentScore;
	}

	private int getMarkValue(Mark mark, int frameNumber, int boxIndex)
	{
		int frameArrayIndex = getArrayIndexOfFirstBall(frameNumber);

		if (mark == Mark.ONE)
			return 1;
		if (mark == Mark.TWO)
			return 2;
		if (mark == Mark.THREE)
			return 3;
		if (mark == Mark.FOUR)
			return 4;
		if (mark == Mark.FIVE)
			return 5;
		if (mark == Mark.SIX)
			return 6;
		if (mark == Mark.SEVEN)
			return 7;
		if (mark == Mark.EIGHT)
			return 8;
		if (mark == Mark.NINE)
			return 9;
		if (mark == Mark.SPARE)
		{
			if (boxIndex == 2)
			{
				return pinsKnockedDownArray[frameArrayIndex + 1];
			}
			return pinsKnockedDownArray[frameArrayIndex];
		}
		if (mark == Mark.ZERO)
			return 0;
		if (mark == Mark.EMPTY)
			return 0;

		return -1;
	}

	@Override
	public Mark getMark(int frameNumber, int boxIndex)
	{
		assert 1 <= frameNumber : "frameNumber = " + frameNumber + " < 1!";
		assert frameNumber <= 10 : "frameNumber = " + frameNumber + " > 10!";
		assert 1 <= boxIndex : "boxIndex = " + boxIndex + " < 1!";
		assert boxIndex <= 3 : "boxIndex = " + boxIndex + " > 3!";

		/*
		 * System.out.println("CFN: " + getCurrentFrame()); System.out.println("CB " + getCurrentBall());
		 * System.out.println("FN: " + frameNumber); System.out.println("BI: " + boxIndex); System.out.println();
		 */

		if (getArrayIndexOfFirstBall(frameNumber) < 0)
		{
			return Mark.EMPTY;
		}

		if (getCurrentFrame() < frameNumber)
		{
			return Mark.EMPTY;
		}

		if (getCurrentFrame() == frameNumber && getCurrentBall() == boxIndex)
		{
			if (boxIndex == 2)
			{
				if (getArrayIndexOfFirstBall(frameNumber) + 1 <= rollCount)
				{
					return Mark.EMPTY;
				}
			}
		}

		int totalPinsKnockedDownInFrame = 0;
		if (pinsKnockedDownArray[getArrayIndexOfFirstBall(frameNumber)] == 10)
		{
			totalPinsKnockedDownInFrame = pinsKnockedDownArray[getArrayIndexOfFirstBall(frameNumber)];
		}
		else
		{
			totalPinsKnockedDownInFrame = pinsKnockedDownArray[getArrayIndexOfFirstBall(frameNumber)] + pinsKnockedDownArray[getArrayIndexOfFirstBall(frameNumber) + 1];
		}

		if (pinsKnockedDownArray[getArrayIndexOfFirstBall(frameNumber)] == 10)
		{
			if (boxIndex == 1)
			{
				return Mark.EMPTY;
			}
			else if (boxIndex == 2)
			{
				return Mark.STRIKE;
			}
		}
		else
		{
			if (boxIndex == 1)
			{
				return Mark.translate(pinsKnockedDownArray[getArrayIndexOfFirstBall(frameNumber)]);
			}
			else if (boxIndex == 2)
			{
				if (totalPinsKnockedDownInFrame == 10)
				{
					return Mark.SPARE;
				}
				else
				{
					return Mark.translate(pinsKnockedDownArray[getArrayIndexOfFirstBall(frameNumber) + 1]);
				}
			}
		}
		return null;
	}

	private int checkFrameLimit()
	{
		int currentFrame = 1;
		for (int i = 0; i < rollCount; i++)
		{
			int pinsKnockedDown = pinsKnockedDownArray[i];

			if (currentFrame == 10)
			{
				return 10;
			}

			if (pinsKnockedDown == 10)
			{
				currentFrame++;
			}
			else if (pinsKnockedDown > 0)
			{
				int j = i + 1;
				if (j < rollCount)
				{
					currentFrame++;
					i++;
				}
			}
		}
		return currentFrame;
	}

	@Override
	public int getCurrentFrame()
	{
		assert !isGameOver();

		int currentFrame = 1;
		for (int i = 0; i < rollCount; i++)
		{
			int pinsKnockedDown = pinsKnockedDownArray[i];

			if (currentFrame == 10)
			{
				return 10;
			}

			if (pinsKnockedDown == 10)
			{
				currentFrame++;
			}
			else if (pinsKnockedDown > 0)
			{
				int j = i + 1;
				if (j < rollCount)
				{
					currentFrame++;
					i++;
				}
			}
		}
		return currentFrame;
	}

	private int getArrayIndexOfFirstBall(int frameNumber)
	{
		int currentFrame = 1;
		int currentRollCount = rollCount;

		if (frameNumber > getCurrentFrame())
		{
			return ++currentRollCount;
		}

		for (int i = 0; i < rollCount; i++)
		{
			if (currentFrame == frameNumber)
			{
				return i;
			}

			int pinsKnockedDown = pinsKnockedDownArray[i];
			if (pinsKnockedDown == 10)
			{
				currentFrame++;
			}
			else if (pinsKnockedDown > 0)
			{
				int j = i + 1;
				if (j < rollCount)
				{
					currentFrame++;
					i++;
				}
			}
		}
		return -1;
	}

	@Override
	public int getCurrentBall()
	{
		assert !isGameOver();

		int currentBall = 10;

		if (getCurrentFrame() == 10)
		{
			currentBall = 0;
		}

		for (int i = 0; i < rollCount; i++)
		{
			int pinsKnockedDown = pinsKnockedDownArray[i];

			if (getCurrentFrame() == 10)
			{
				currentBall += 11;
			}
			else
			{
				if (pinsKnockedDown != 10)
				{
					currentBall += 5;
				}
				else
				{
					currentBall += 10;
				}
			}
		}

		if (getCurrentFrame() < 10)
		{
			if (currentBall % 2 == 0)
			{
				return 1;
			}
			return 2;
		}
		else if (getCurrentFrame() == 10)
		{
			System.out.println("CB = " + currentBall);

			if (currentBall % 2 == 0)
			{
				return 1;
			}
			else if (currentBall % 2 == 1)
			{
				return 2;
			}
			return 3;
		}
		return -2;
	}

	@Override
	public void recordRoll(int pinsKnockedDown)
	{
		assert 0 <= pinsKnockedDown : "pinsKnockedDown = " + pinsKnockedDown + " < 0!";
		assert pinsKnockedDown <= 10 : "pinsKnockedDown = " + pinsKnockedDown + " > 10!";

		assert (getCurrentBall() == 1) || ((getCurrentBall() == 2) && (((getCurrentFrame() == 10) && isStrikeOrSpare(getMark(10, 1))) || ((pinsKnockedDownArray[rollCount - 1] + pinsKnockedDown) <= 10))) || ((getCurrentBall() == 3) && (((getCurrentFrame() == 10) && isStrikeOrSpare(getMark(10, 2))) || ((pinsKnockedDownArray[rollCount - 1] + pinsKnockedDown) <= 10)));

		assert !isGameOver() : "Game is over!";

		pinsKnockedDownArray[rollCount] = pinsKnockedDown;
		rollCount++;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		for (int i : pinsKnockedDownArray)
		{
			sb.append(i + "| ");
		}
		return sb.toString();
	}

	private boolean isStrikeOrSpare(Mark mark)
	{
		return ((mark == Mark.STRIKE) || (mark == Mark.SPARE));
	}

	@Override
	public double getHoursSpentWorkingOnThisAssignment()
	{
		return 5;
	}

	@Override
	public int getScoreAgainstTestCasesSubset()
	{
		return 120;
	}

}
