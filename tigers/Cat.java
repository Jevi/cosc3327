package tigers;

import static tigers.Species.*;
import static tigers.Gender.*;

//Student questions to ponder [but student should not alter a byte of this class]:
//Should this be an enum?
//Should this concept be renamed?
public enum Cat
{
	FEMALE_CARACAL(CARACAL, FEMALE), FEMALE_OCELOT(OCELOT, FEMALE), FEMALE_SERVAL(SERVAL, FEMALE), FEMALE_TIGER(TIGER, FEMALE), MALE_CARACAL(CARACAL, MALE), MALE_OCELOT(OCELOT, MALE), MALE_SERVAL(SERVAL, MALE), MALE_TIGER(TIGER, MALE);

	private Species species;
	private Gender gender;

	private Cat(Species species, Gender gender)
	{
		this.species = species;
		this.gender = gender;
	}

	public Species getSpecies()
	{
		return species;
	}

	public Gender getGender()
	{
		return gender;
	}
}
