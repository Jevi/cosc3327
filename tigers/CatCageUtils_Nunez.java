package tigers;

public class CatCageUtils_Nunez
{
	public static CatCage rotate90(CatCage catCage)
	{
		return new CatCageImpl_Nunez(catCage.getIdentifier(), catCage.getLeftCell(), catCage.getTopCell(), catCage.getRightCell(), catCage.getBottomCell());
	}

	public static CatCage rotate180(CatCage catCage)
	{
		return new CatCageImpl_Nunez(catCage.getIdentifier(), catCage.getBottomCell(), catCage.getLeftCell(), catCage.getTopCell(), catCage.getRightCell());
	}

	public static CatCage rotate270(CatCage catCage)
	{
		return new CatCageImpl_Nunez(catCage.getIdentifier(), catCage.getRightCell(), catCage.getBottomCell(), catCage.getLeftCell(), catCage.getTopCell());
	}
}
