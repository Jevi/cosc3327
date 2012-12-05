package bowling;

public interface SinglePlayerBowlingScoreboard
{
	public String getPlayerName();

	public boolean isGameOver();

	/**
	 * <h1>Pre:</h1> 1 <= frameNumber <= 10 <br>
	 * 
	 * frameNumber == 10 ==> isGameOver() <br>
	 * 
	 * !isGameOver ==> (getCurrentFrame() > frameNumber) <br>
	 * 
	 * getCurrentFrame() == frameNumber + 2 ==> !(STRIKE.equals(getMark(frameNumber,2) && STRIKE.equals(getMark(frameNumber + 1,
	 * 2)))) <br>
	 * 
	 * (getCurrentFrame() == frameNumber + 1 && getCurrentBall() == 1) ==> !SPARE.equals(getMark(frameNumber, 2)) <br>
	 * 
	 * !isGameOver ==> getCurrentFrame() > frameNumber <br>
	 * 
	 * @param frameNumber
	 * @return
	 */
	public int getScore(int frameNumber);

	/**
	 * <h1>Pre:</h1> 1 <= frameNumber <= 10 <br>
	 * 
	 * 1 <= boxIndex <= 3 <br>
	 * 
	 * frameNumber < 10 ==> getCurrentFrame() > frameNumber <br>
	 * 
	 * (frameNumber == 10 && !isGameOver()) ==> (getCurrentBall() > boxIndex) <br>
	 * 
	 * (1 <= frameNumber < 10) ==> (1 <= boxIndex <= 2)<br>
	 * 
	 * (frameNumber ==10) ==> (1 <= boxIndex <= 3)<br>
	 * 
	 * boxIndex = 3 ==> frameNumber = 10<br>
	 * 
	 * <h1>Post:</h1> ((rv = Mark.STRIKE) && (1 <= frameNumber <= 9)) ==> (boxIndex == 2)<br>
	 * 
	 * ((rv == Mark.SPARE) && (1 <= frameNumber <= 9)) ==> (boxIndex == 2)<br>
	 * 
	 * ((rv = MARK.SPARE) && (frameNumber = 10)) ==> (boxIndex == 2)<br>
	 * 
	 * @param frameNumber
	 * @param boxIndex
	 * @return
	 */
	public Mark getMark(int frameNumber, int boxIndex);

	/**
	 * <h1>Pre:</h1>
	 * 
	 * !isGameOver<br>
	 * 
	 * <h1>Post:</h1>
	 * 
	 * 1 <= rv <= 10 <br>
	 * 
	 * @return
	 */
	public int getCurrentFrame();

	/**
	 * Attempt in current frame
	 * 
	 * <h1>Pre:</h1>
	 * 
	 * !isGameOver<br>
	 * 
	 * <h1>Post:</h1>
	 * 
	 * 1 <= rv <= 3<br>
	 * 
	 * frameNumber < 10 ==> rv <= 2 <br>
	 * 
	 * ((frameNumber < 10) && (rv == 2)) ==> getMark(frameNumber, 1).equals(Mark.STRIKE)<br>
	 * 
	 * @return
	 */
	public int getCurrentBall();

	/**
	 * 
	 * <h1>Pre:</h1>
	 * 
	 * 0 <= pinsKnockedDown <= 10
	 * 
	 * <h1>Post:</h1>
	 * 
	 * @param pinsKnockedDown
	 */
	public void recordRoll(int pinsKnockedDown);

	public String toString();

	/**
	 * Your implementation here will look like: return 6.5
	 * 
	 * @return
	 */
	public double getHoursSpentWorkingOnThisAssignment();

	/**
	 * Your return value needs to match your score on the distributed test cases <br>
	 * Your implementation here will look like: return 105 <br>
	 * 
	 * @return
	 */

	public int getScoreAgainstTestCasesSubset();
}
