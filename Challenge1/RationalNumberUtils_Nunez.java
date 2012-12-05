package Challenge1;

public class RationalNumberUtils_Nunez
{
	// rv is true <==> r1 = r2 as rational numbers
	public static boolean equal(RationalNumber r1, RationalNumber r2)
	{
		return r1.equals(r2);
	}

	// rv = r1 + r2, where + is regular numerical addition
	public static RationalNumber add(RationalNumber r1, RationalNumber r2)
	{
		// if needed, find common factor
		if (r1.getDenominator() != r2.getDenominator())
		{
			int cf = r1.getDenominator() * r2.getDenominator();
			int r1n = r1.getNumerator() * r2.getDenominator();
			int r2n = r2.getNumerator() * r1.getDenominator();

			return new RationalNumberImpl_Nunez(r1n + r2n, cf);
		}
		else
		{
			int numerator = r1.getNumerator() + r2.getNumerator();

			return new RationalNumberImpl_Nunez(numerator, r1.getDenominator());
		}
	}

	// rv = r1 - r2, where - is regular numerical subtraction
	public static RationalNumber subtract(RationalNumber r1, RationalNumber r2)
	{
		// if needed, find common factor
		if (r1.getDenominator() != r2.getDenominator())
		{
			int cf = r1.getDenominator() * r2.getDenominator();
			int r1n = r1.getNumerator() * r2.getDenominator();
			int r2n = r2.getNumerator() * r1.getDenominator();

			return new RationalNumberImpl_Nunez(r1n - r2n, cf);
		}
		else
		{
			int numerator = r1.getNumerator() - r2.getNumerator();

			return new RationalNumberImpl_Nunez(numerator, r1.getDenominator());
		}
	}

	// rv = r1 * r2, where * is regular numerical multiplication
	public static RationalNumber multiply(RationalNumber r1, RationalNumber r2)
	{
		int numerator = r1.getNumerator() * r2.getNumerator();
		int denominator = r1.getDenominator() * r2.getDenominator();

		return new RationalNumberImpl_Nunez(numerator, denominator);
	}

	// rv = r1 / r2, where / is regular numerical division
	public static RationalNumber divide(RationalNumber r1, RationalNumber r2)
	{
		// multiply r1 w/ reciprocal rational number of r2
		return multiply(r1, new RationalNumberImpl_Nunez(r2.getDenominator(), r2.getNumerator()));
	}

	// rv = -r1, where - is regular numerical negation
	public static RationalNumber negate(RationalNumber r1)
	{
		return new RationalNumberImpl_Nunez(-r1.getNumerator(), r1.getDenominator());
	}

}
