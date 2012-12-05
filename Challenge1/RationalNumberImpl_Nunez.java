package Challenge1;

public class RationalNumberImpl_Nunez implements RationalNumber
{
	private int numerator;
	private int denominator;

	// Constructor
	public RationalNumberImpl_Nunez(int numerator, int denominator)
	{
		this.numerator = numerator;
		this.denominator = denominator;
		simplifyRationalNumber();
	}

	// rv is reduced numerator value
	@Override
	public int getNumerator()
	{
		return numerator;
	}

	// rv is reduced denominator value
	@Override
	public int getDenominator()
	{
		return denominator;
	}

	// rv is calculated rational number value
	@Override
	public double getValue()
	{
		return (double) numerator / (double) denominator;
	}

	// rv is a string representation of rational number
	@Override
	public String toString()
	{
		return numerator + "/" + denominator;
	}

	// if obj is of type RationalNumber check value for equivalence
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof RationalNumber)
		{
			if (((RationalNumber) obj).getValue() == this.getValue())
			{
				return true;
			}
			return false;
		}
		return super.equals(obj);
	}

	private void simplifyRationalNumber()
	{
		int lcd = Math.abs(getLargestCommonDivisor());

		// simplify values and redistribute negative sign if needed
		if (numerator == 0 && denominator != 0)
		{
			numerator = 0;
			denominator = 1;
		}
		else if ((numerator / lcd) < 0 && (denominator / lcd) < 0 || (numerator / lcd) > 0 && (denominator / lcd) < 0)
		{
			numerator = -numerator / lcd;
			denominator = -denominator / lcd;
		}
		else
		{
			numerator = numerator / lcd;
			denominator = denominator / lcd;
		}
	}

	// rv is largest common divisor for numerator and denominator
	private int getLargestCommonDivisor()
	{
		int lcd = 1;
		int min;

		// find minimum integer
		if (numerator == denominator)
		{
			min = numerator;
		}
		else if (numerator > denominator)
		{
			min = denominator;
		}
		else
		{
			min = numerator;
		}

		// find largest common divisor depending on starting point of min
		if (min < 0)
		{
			for (int i = min; i < -1; i++)
			{
				if (numerator % i == 0 && denominator % i == 0)
				{
					lcd = i;
					break;
				}
			}
		}
		else if (min > 0)
		{
			for (int i = min; i > 1; i--)
			{
				if (numerator % i == 0 && denominator % i == 0)
				{
					lcd = i;
					break;
				}
			}
		}

		System.out.println(lcd);
		return lcd;
	}
}
