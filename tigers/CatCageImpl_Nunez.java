package tigers;

import java.util.HashMap;
import java.util.Map;

public class CatCageImpl_Nunez implements CatCage
{
	private String identifier;
	private Cat topCat;
	private Cat rightCat;
	private Cat bottomCat;
	private Cat leftCat;

	private static Map<Gender, String> genderToStringMap = new HashMap<Gender, String>();
	static
	{
		genderToStringMap.put(Gender.FEMALE, "F");
		genderToStringMap.put(Gender.MALE, "M");
	}

	private static Map<Species, String> speciesToStringMap = new HashMap<Species, String>();
	static
	{
		speciesToStringMap.put(Species.CARACAL, "C");
		speciesToStringMap.put(Species.OCELOT, "O");
		speciesToStringMap.put(Species.SERVAL, "S");
		speciesToStringMap.put(Species.TIGER, "T");
	}

	public CatCageImpl_Nunez(String identifier, Cat topCat, Cat rightCat, Cat bottomCat, Cat leftCat)
	{
		assert identifier != null : "identifier is null!";
		this.identifier = identifier;
		this.topCat = topCat;
		this.rightCat = rightCat;
		this.bottomCat = bottomCat;
		this.leftCat = leftCat;
	}
	
	public CatCageImpl_Nunez(CatCage catCage)
	{
		this.identifier = catCage.getIdentifier();
		this.topCat = catCage.getTopCell();
		this.rightCat = catCage.getRightCell();
		this.bottomCat = catCage.getBottomCell();
		this.leftCat = catCage.getLeftCell();
	}

	@Override
	public String getIdentifier()
	{
		return identifier;
	}

	@Override
	public Cat getTopCell()
	{
		return topCat;
	}

	@Override
	public Cat getLeftCell()
	{
		return leftCat;
	}

	@Override
	public Cat getRightCell()
	{
		return rightCat;
	}

	@Override
	public Cat getBottomCell()
	{
		return bottomCat;
	}

	// this method is optional, student can erase if desired
	private boolean areCellContentsEqual(Cat cat1, Cat cat2)
	{
		return (cat1 == null ? cat2 == null : cat1.equals(cat2));
	}

	@Override
	public boolean equals(Object obj)
	{
		boolean areEqual = false;
		if (obj != null && CatCage.class.isAssignableFrom(obj.getClass()))
		{
			CatCage catCageOther = (CatCage) obj;
			areEqual = (catCageOther.getIdentifier().equals(getIdentifier())) && areCellContentsEqual(getBottomCell(), catCageOther.getBottomCell()) && areCellContentsEqual(getLeftCell(), catCageOther.getLeftCell()) && areCellContentsEqual(getTopCell(), catCageOther.getTopCell()) && areCellContentsEqual(getRightCell(), catCageOther.getRightCell());
		}
		assert !areEqual || (hashCode() == obj.hashCode()) : "If two instances are equal then their hashcodes must match! hashCode() = " + hashCode() + " obj.hashCode() = " + obj.hashCode();
		return areEqual;
	}

	@Override
	public int hashCode()
	{
		return 0;
	}

	private String getCatString(Cat cat)
	{
		if (cat != null)
		{
			StringBuffer catStringBuffer = new StringBuffer();
			Gender catGender = cat.getGender();
			Species catSpecies = cat.getSpecies();
			catStringBuffer.append(genderToStringMap.get(catGender));
			catStringBuffer.append(speciesToStringMap.get(catSpecies));
			return catStringBuffer.toString();
		}
		return "null";
	}

	public String toString()
	{
		return String.format("[(%s), T:%s R:%s B:%s L:%s]", getIdentifier(), getCatString(getTopCell()), getCatString(getRightCell()), getCatString(getBottomCell()), getCatString(getLeftCell()));
	}
}
