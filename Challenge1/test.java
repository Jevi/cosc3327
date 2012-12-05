package Challenge1;

public class test
{
	public static void main(String[] args)
	{
		RationalNumber r1 = new RationalNumberImpl_Nunez(77, 158);
		RationalNumber r2 = new RationalNumberImpl_Nunez(43659, 89586);
		System.out.println(RationalNumberUtils_Nunez.equal(r1, r2));
		System.out.println(r1.getNumerator() + " " + r1.getDenominator());
		System.out.println(r2.getNumerator() + " " + r2.getDenominator());
	}
}
