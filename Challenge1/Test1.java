package Challenge1;

import java.util.ArrayList;
import java.util.List;

public class Test1
{
	public static void main(String[] args)
	{
		// Create a list of rational numbers to test
		List<RationalNumber> rnList = new ArrayList<RationalNumber>();
		rnList.add(new RationalNumberImpl_Nunez(640, 480));
		rnList.add(new RationalNumberImpl_Nunez(20, 10));

		for (RationalNumber rn : rnList)
		{
			System.out.println("Rational Number: " + rn.toString());
			System.out.println("Numerator: " + rn.getNumerator());
			System.out.println("Denominator: " + rn.getDenominator());
			System.out.println("Value: " + rn.getValue());
			System.out.println("Negate: " + RationalNumberUtils_Nunez.negate(rn));
			System.out.println();
		}

		System.out.println("equals(): " + RationalNumberUtils_Nunez.equal(rnList.get(0), rnList.get(1)));
		System.out.println("Addition: " + RationalNumberUtils_Nunez.add(rnList.get(0), rnList.get(1)));
		System.out.println("Subtraction: " + RationalNumberUtils_Nunez.subtract(rnList.get(0), rnList.get(1)));
		System.out.println("Multiplication: " + RationalNumberUtils_Nunez.multiply(rnList.get(0), rnList.get(1)));
		System.out.println("Division: " + RationalNumberUtils_Nunez.divide(rnList.get(0), rnList.get(1)));
	}

}