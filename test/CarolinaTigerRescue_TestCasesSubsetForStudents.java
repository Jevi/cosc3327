package test;

import static org.junit.Assert.assertEquals;
import static tigers.Cat.FEMALE_CARACAL;
import static tigers.Cat.FEMALE_SERVAL;
import static tigers.Cat.MALE_OCELOT;
import static tigers.Cat.MALE_SERVAL;
import static tigers.Cat.MALE_TIGER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import sequence.Sequence;
import sequence.SequenceImpl;
import tigers.CarolinaTigerRescueSolver_Nunez;
import tigers.Cat;
import tigers.CatCage;
import tigers.CatCageImpl_Nunez;
import tigers.GridPacking;
import tigers.GridPackingImpl_Nunez;

public class CarolinaTigerRescue_TestCasesSubsetForStudents
{
	public static final String PACKAGE_NAME = "tigers";
	protected CarolinaTigerRescue_StudentWrapper solver;
	protected static String TEST_GOAL_MESSAGE;

	private static CatCage getCatCage(String identifier, Cat topCat, Cat rightCat, Cat bottomCat, Cat leftCat)
	{
		return new CatCageImpl_Nunez(identifier, topCat, rightCat, bottomCat, leftCat);
	}

	private static CatCage getCatCage_GoldStandard(final String identifier, final Cat topCat, final Cat rightCat, final Cat bottomCat, final Cat leftCat)
	{
		return new CatCage()
		{
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

			@Override
			public int hashCode()
			{
				return 0;
			}

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
		};
	}

	private static CarolinaTigerRescue_StudentWrapper getCarolinaTigerRescueSolver()
	{
		return new CarolinaTigerRescue_StudentWrapper(CarolinaTigerRescueSolver_Nunez.class);
	}

	@Test
	@Points(value = 0)
	public void checkThatAssertionsAreEnabled()
	{
		TEST_GOAL_MESSAGE = "Check that assetions are enabled";
		try
		{
			assert false;
			throw new RuntimeException("Assertions are not enabled! Make sure that you are running these test cases with assertions on! (-ea in VM arguments)");
		}
		catch (AssertionError ae)
		{
			// assertions are enabled as desired
		}
	}

	@Test
	@Points(value = 5)
	public void checkCatEqualityAlternative()
	{
		TEST_GOAL_MESSAGE = "Check that Cat.hashCode() and equality() work properly";
		Set<Cat> catSet = new HashSet<Cat>();
		final int ADDITION_COUNT = 3;
		for (int i = 0; i < ADDITION_COUNT; i++)
		{
			catSet.add(FEMALE_SERVAL);
		}
		final int EXPECTED_SET_SIZE = 1;
		assertEquals(String.format("catSet.size() should be %s, not %s!", EXPECTED_SET_SIZE, catSet.size()), EXPECTED_SET_SIZE, catSet.size());
	}

	@Test
	@Points(value = 5)
	public void checkThatCatCageHashCodeIsZero()
	{
		TEST_GOAL_MESSAGE = "Check that CatCageImpl_Student.hashCode() returns 0";
		CatCage catCage = new CatCageImpl_Nunez("ID", FEMALE_CARACAL, MALE_OCELOT, FEMALE_SERVAL, MALE_TIGER);
		int hashCode = catCage.hashCode();
		assertEquals("catCage.hashCode() should return 0 instead of " + hashCode + "!", 0, hashCode);
	}

	@Test
	@Points(value = 5)
	public void checkCatCatConstructor()
	{
		TEST_GOAL_MESSAGE = "Check that the CatCageImpl_Student has a proper constructor";
		final String IDENTIFIER = "TSA Approved Cat Carrier";
		final Cat TOP_CAT = FEMALE_CARACAL;
		final Cat RIGHT_CAT = MALE_OCELOT;
		final Cat BOTTOM_CAT = FEMALE_SERVAL;
		final Cat LEFT_CAT = MALE_TIGER;

		CatCage catCage_GoldStandard = getCatCage_GoldStandard(IDENTIFIER, TOP_CAT, RIGHT_CAT, BOTTOM_CAT, LEFT_CAT);
		CatCage catCage = new CatCageImpl_Nunez(IDENTIFIER, TOP_CAT, RIGHT_CAT, BOTTOM_CAT, LEFT_CAT);

		assertEquals(catCage_GoldStandard.getIdentifier(), catCage.getIdentifier());
		assertEquals(catCage_GoldStandard.getTopCell(), catCage.getTopCell());
		assertEquals(catCage_GoldStandard.getRightCell(), catCage.getRightCell());
		assertEquals(catCage_GoldStandard.getBottomCell(), catCage.getBottomCell());
		assertEquals(catCage_GoldStandard.getLeftCell(), catCage.getLeftCell());
	}

	@Test
	@Points(value = 5)
	public void checkCatCageEqualsAgainstGoldStandard()
	{
		TEST_GOAL_MESSAGE = "Check that the CatCageImpl_Student has a proper equals() method";
		final String IDENTIFIER = "TSA Approved Cat Carrier";
		final Cat TOP_CAT = FEMALE_CARACAL;
		final Cat RIGHT_CAT = MALE_OCELOT;
		final Cat BOTTOM_CAT = FEMALE_SERVAL;
		final Cat LEFT_CAT = MALE_TIGER;

		CatCage catCage_GoldStandard = getCatCage_GoldStandard(IDENTIFIER, TOP_CAT, RIGHT_CAT, BOTTOM_CAT, LEFT_CAT);
		CatCage catCage = new CatCageImpl_Nunez(IDENTIFIER, TOP_CAT, RIGHT_CAT, BOTTOM_CAT, LEFT_CAT);

		assertEquals(true, catCage_GoldStandard.equals(catCage));
		assertEquals(true, catCage.equals(catCage_GoldStandard));
	}

	@Test
	@Points(value = 5)
	public void catCageEquality()
	{
		TEST_GOAL_MESSAGE = "Check that the CatCageImpl_Student has a proper equals() method";
		CatCage catCage = getCatCage("1", FEMALE_SERVAL, MALE_TIGER, FEMALE_CARACAL, MALE_OCELOT);
		CatCage catCageOther = getCatCage("1", FEMALE_SERVAL, MALE_TIGER, FEMALE_CARACAL, MALE_OCELOT);
		assertEquals(true, catCage.equals(catCageOther));
	}

	@Test
	@Points(value = 5)
	public void catCageEquality2()
	{
		TEST_GOAL_MESSAGE = "Check that the CatCageImpl_Student has a proper equals() method";
		CatCage catCage = getCatCage("1", FEMALE_SERVAL, MALE_TIGER, FEMALE_CARACAL, MALE_OCELOT);
		CatCage catCageOther = getCatCage("1", FEMALE_SERVAL, MALE_TIGER, FEMALE_CARACAL, MALE_OCELOT);
		assertEquals(true, catCageOther.equals(catCage));
	}

	@Test
	@Points(value = 5)
	public void checkCatCageEqualityAlternative()
	{
		TEST_GOAL_MESSAGE = "Check that the CatCageImpl_Student has a proper hashCode() and equals() method";
		CatCage catCage = getCatCage("1", FEMALE_SERVAL, MALE_TIGER, FEMALE_CARACAL, MALE_OCELOT);
		CatCage catCageOther = getCatCage("1", FEMALE_SERVAL, MALE_TIGER, FEMALE_CARACAL, MALE_OCELOT);
		Set<CatCage> catCageSet = new HashSet<CatCage>(Arrays.asList(new CatCage[] { catCage, catCageOther }));
		assertEquals(1, catCageSet.size());
	}

	@Test
	@Points(value = 5)
	public void checkCatCageEqualityAlternative2()
	{
		TEST_GOAL_MESSAGE = "Check that the CatCageImpl_Student has a proper hashCode() and equals() method";
		final String IDENTIFIER = "cage";

		CatCage catCage1 = new CatCageImpl_Nunez(IDENTIFIER, FEMALE_CARACAL, MALE_OCELOT, null, MALE_TIGER);
		CatCage catCage2 = new CatCageImpl_Nunez(IDENTIFIER, FEMALE_CARACAL, MALE_OCELOT, null, MALE_TIGER);
		CatCage catCage3 = new CatCageImpl_Nunez(IDENTIFIER, FEMALE_CARACAL, MALE_OCELOT, MALE_SERVAL, FEMALE_CARACAL);

		Set<CatCage> catCageSet = new HashSet<CatCage>();
		catCageSet.add(catCage1);
		int expectedCatCageSetSize = 1;
		assertEquals(String.format("catSet.size() should be %s, not %s!", expectedCatCageSetSize, catCageSet.size()), expectedCatCageSetSize, catCageSet.size());
		catCageSet.add(catCage2);
		expectedCatCageSetSize = 1;
		assertEquals(String.format("catSet.size() should be %s, not %s!", expectedCatCageSetSize, catCageSet.size()), expectedCatCageSetSize, catCageSet.size());
		catCageSet.add(catCage3);
		expectedCatCageSetSize = 2;
		assertEquals(String.format("catSet.size() should be %s, not %s!", expectedCatCageSetSize, catCageSet.size()), expectedCatCageSetSize, catCageSet.size());
	}

	@Test
	@Points(value = 5)
	public void catCageInequality()
	{
		TEST_GOAL_MESSAGE = "Check that the CatCageImpl_Student has a proper hashCode() and equals() method [can detect inequality]";

		CatCage catCage = getCatCage("1", FEMALE_SERVAL, MALE_TIGER, FEMALE_CARACAL, MALE_OCELOT);
		CatCage catCageOther = getCatCage("2", FEMALE_SERVAL, MALE_TIGER, FEMALE_CARACAL, MALE_OCELOT);
		Set<CatCage> catCageSet = new HashSet<CatCage>(Arrays.asList(new CatCage[] { catCage, catCageOther }));
		assertEquals(2, catCageSet.size());
	}

	@Test
	@Points(value = 5)
	public void checkThatGridPackingHashCodeIsZero()
	{
		TEST_GOAL_MESSAGE = "Check that GridPackingImpl_Student.hashCode() returns 0";
		final int ROW_COUNT = 7;
		final int COLUMN_COUNT = 3;

		List<CatCage> catCageList = new ArrayList<CatCage>();
		for (int i = 0; i < ROW_COUNT * COLUMN_COUNT; i++)
		{
			CatCage catCage = new CatCageImpl_Nunez("" + i, FEMALE_CARACAL, MALE_OCELOT, null, MALE_TIGER);
			catCageList.add(catCage);
		}
		GridPacking<CatCage> gridPacking = new GridPackingImpl_Nunez<CatCage>(ROW_COUNT, COLUMN_COUNT, catCageList);
		int hashCode = gridPacking.hashCode();
		assertEquals(String.format("gridPacking.hashCode() should return 0 instead of %s!", hashCode), 0, hashCode);
	}

	@Test
	@Points(value = 5)
	public void checkThatGridPackingConstructorIsCorrect()
	{
		TEST_GOAL_MESSAGE = "Check that GridPackingImpl_Student constructor is correct";
		final int ROW_COUNT = 7;
		final int COLUMN_COUNT = 3;

		List<CatCage> catCageList = new ArrayList<CatCage>();
		for (int i = 0; i < ROW_COUNT * COLUMN_COUNT; i++)
		{
			CatCage catCage = new CatCageImpl_Nunez("" + i, FEMALE_CARACAL, MALE_OCELOT, null, MALE_TIGER);
			catCageList.add(catCage);
		}
		GridPacking<CatCage> gridPacking = new GridPackingImpl_Nunez<CatCage>(ROW_COUNT, COLUMN_COUNT, catCageList);

		int i = 0;
		for (int j = 0; j < ROW_COUNT; j++)
		{
			for (int k = 0; k < COLUMN_COUNT; k++)
			{
				CatCage catCage = gridPacking.getElementAt(j, k);
				String identifier = catCage.getIdentifier();
				assertEquals(String.format("idenifier should be %s, not %s", i, identifier), "" + i, identifier);
				i++;
			}
		}
	}

	@Test
	@Points(value = 5)
	public void checkThatGridPackingConstructorIsDoingTheRightThingInternally()
	{
		// ************************************************************************************
		// Note: This isn't a standard test case to run in industry, but is a great test case
		// for students who will encounter this problem for the first time
		// ************************************************************************************
		TEST_GOAL_MESSAGE = "Check that GridPackingImpl_Student constructor is doing the right thing internally";
		final int ROW_COUNT = 7;
		final int COLUMN_COUNT = 3;

		List<CatCage> catCageList = new ArrayList<CatCage>();
		for (int i = 0; i < ROW_COUNT * COLUMN_COUNT; i++)
		{
			CatCage catCage = new CatCageImpl_Nunez("" + i, FEMALE_CARACAL, MALE_OCELOT, null, MALE_TIGER);
			catCageList.add(catCage);
		}
		GridPacking<CatCage> gridPacking = new GridPackingImpl_Nunez<CatCage>(ROW_COUNT, COLUMN_COUNT, catCageList);

		int i = 0;
		for (int j = 0; j < ROW_COUNT; j++)
		{
			for (int k = 0; k < COLUMN_COUNT; k++)
			{
				CatCage catCage = gridPacking.getElementAt(j, k);
				String identifier = catCage.getIdentifier();
				assertEquals(String.format("idenifier should be %s, not %s", i, identifier), "" + i, identifier);
				i++;
			}
		}

		// ************************************************************************************
		// THE REAL TEST IS COMING UP
		// ************************************************************************************
		int indexOfLastElement = catCageList.size() - 1;
		catCageList.remove(indexOfLastElement);
		// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
		// Changing catCageList here should not
		// affect the gridPacking. If you are
		// failing this test after getting this
		// far, figure out how catCageList and
		// gridPacking are still "connected" at
		// this point.
		// ************************************************************************************

		i = 0;
		for (int j = 0; j < ROW_COUNT; j++)
		{
			for (int k = 0; k < COLUMN_COUNT; k++)
			{
				CatCage catCage = gridPacking.getElementAt(j, k);
				String identifier = catCage.getIdentifier();
				assertEquals(String.format("idenifier should be %s, not %s", i, identifier), "" + i, identifier);
				i++;
			}
		}
	}

	@Test
	@Points(value = 5)
	public void oneCageFourCatsFourTypes()
	{
		TEST_GOAL_MESSAGE = "Check that CarolinaTigerRescueSolver_Student can properly solve a 1x1 problem (with no rotational cage symmetry)";
		// Notice that the quality of this test (and others like it) depends on
		// the correctness of your GridPackingImpl
		solver = getCarolinaTigerRescueSolver();

		final int ROW_COUNT = 1;
		final int COLUMN_COUNT = 1;
		final String CAT_CAGE_ID = "1";
		Collection<CatCage> cageConfigurations = new ArrayList<CatCage>();
		cageConfigurations.add(getCatCage(CAT_CAGE_ID, FEMALE_SERVAL, MALE_TIGER, FEMALE_CARACAL, MALE_OCELOT));
		Set<GridPacking<CatCage>> solutionSet = solver.solve(cageConfigurations, ROW_COUNT, COLUMN_COUNT);

		GridPacking<CatCage> solution1 = new GridPackingImpl_Nunez<CatCage>(ROW_COUNT, COLUMN_COUNT, Arrays.asList(new CatCage[] { getCatCage(CAT_CAGE_ID, FEMALE_SERVAL, MALE_TIGER, FEMALE_CARACAL, MALE_OCELOT) }));
		GridPacking<CatCage> solution2 = new GridPackingImpl_Nunez<CatCage>(ROW_COUNT, COLUMN_COUNT, Arrays.asList(new CatCage[] { getCatCage(CAT_CAGE_ID, MALE_TIGER, FEMALE_CARACAL, MALE_OCELOT, FEMALE_SERVAL) }));
		GridPacking<CatCage> solution3 = new GridPackingImpl_Nunez<CatCage>(ROW_COUNT, COLUMN_COUNT, Arrays.asList(new CatCage[] { getCatCage(CAT_CAGE_ID, FEMALE_CARACAL, MALE_OCELOT, FEMALE_SERVAL, MALE_TIGER) }));
		GridPacking<CatCage> solution4 = new GridPackingImpl_Nunez<CatCage>(ROW_COUNT, COLUMN_COUNT, Arrays.asList(new CatCage[] { getCatCage(CAT_CAGE_ID, MALE_OCELOT, FEMALE_SERVAL, MALE_TIGER, FEMALE_CARACAL) }));

		assertEquals(true, solutionSet.contains(solution1));
		assertEquals(true, solutionSet.contains(solution2));
		assertEquals(true, solutionSet.contains(solution3));
		assertEquals(true, solutionSet.contains(solution4));
		assertEquals(4, solutionSet.size());
	}

	@Test
	@Points(value = 5)
	public void oneCageFourCatOneType()
	{
		TEST_GOAL_MESSAGE = "Check that CarolinaTigerRescueSolver_Student can properly solve a 1x1 problem (with complete rotational cage symmetry)";
		solver = getCarolinaTigerRescueSolver();

		final int ROW_COUNT = 1;
		final int COLUMN_COUNT = 1;
		final String CAT_CAGE_ID = "1";
		Collection<CatCage> cageConfigurations = new ArrayList<CatCage>();
		cageConfigurations.add(getCatCage(CAT_CAGE_ID, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL));
		Set<GridPacking<CatCage>> solutionSet = solver.solve(cageConfigurations, ROW_COUNT, COLUMN_COUNT);

		GridPacking<CatCage> solution1 = new GridPackingImpl_Nunez<CatCage>(ROW_COUNT, COLUMN_COUNT, Arrays.asList(new CatCage[] { getCatCage(CAT_CAGE_ID, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL) }));

		assertEquals(true, solutionSet.contains(solution1));
		assertEquals(1, solutionSet.size());
	}

	@Test
	@Points(value = 5)
	public void oneEmptyCage()
	{
		TEST_GOAL_MESSAGE = "Check that CarolinaTigerRescueSolver_Student can properly solve a 1x1 problem (with empty cage)";
		solver = getCarolinaTigerRescueSolver();

		final int ROW_COUNT = 1;
		final int COLUMN_COUNT = 1;
		final String CAT_CAGE_ID = "1";
		Collection<CatCage> cageConfigurations = new ArrayList<CatCage>();
		cageConfigurations.add(getCatCage(CAT_CAGE_ID, null, null, null, null));
		Set<GridPacking<CatCage>> solutionSet = solver.solve(cageConfigurations, ROW_COUNT, COLUMN_COUNT);

		GridPacking<CatCage> solution1 = new GridPackingImpl_Nunez<CatCage>(ROW_COUNT, COLUMN_COUNT, Arrays.asList(new CatCage[] { getCatCage(CAT_CAGE_ID, null, null, null, null) }));

		assertEquals(true, solutionSet.contains(solution1));
		assertEquals(1, solutionSet.size());
	}

	@Test
	@Points(value = 5)
	public void twoEmptyCages()
	{
		TEST_GOAL_MESSAGE = "Check that CarolinaTigerRescueSolver_Student can properly solve a 1x2 problem (with two empty cages)";
		solver = getCarolinaTigerRescueSolver();

		final int ROW_COUNT = 1;
		final int COLUMN_COUNT = 2;
		final String CAT_CAGE_ID_1 = "1";
		final String CAT_CAGE_ID_2 = "2";
		Collection<CatCage> cageConfigurations = new ArrayList<CatCage>();
		CatCage catCage1 = getCatCage(CAT_CAGE_ID_1, null, null, null, null);
		CatCage catCage2 = getCatCage(CAT_CAGE_ID_2, null, null, null, null);

		cageConfigurations.add(catCage1);
		cageConfigurations.add(catCage2);
		Set<GridPacking<CatCage>> solutionSet = solver.solve(cageConfigurations, ROW_COUNT, COLUMN_COUNT);

		assertEquals(0, solutionSet.size());
	}

	@Test
	@Points(value = 5)
	public void twoEmptyCagesTranspose()
	{
		TEST_GOAL_MESSAGE = "Check that CarolinaTigerRescueSolver_Student can properly solve a 2x1 problem (with two empty cages)";
		solver = getCarolinaTigerRescueSolver();

		final int ROW_COUNT = 2;
		final int COLUMN_COUNT = 1;
		final String CAT_CAGE_ID_1 = "1";
		final String CAT_CAGE_ID_2 = "2";
		Collection<CatCage> cageConfigurations = new ArrayList<CatCage>();
		CatCage catCage1 = getCatCage(CAT_CAGE_ID_1, null, null, null, null);
		CatCage catCage2 = getCatCage(CAT_CAGE_ID_2, null, null, null, null);

		cageConfigurations.add(catCage1);
		cageConfigurations.add(catCage2);
		Set<GridPacking<CatCage>> solutionSet = solver.solve(cageConfigurations, ROW_COUNT, COLUMN_COUNT);

		assertEquals(0, solutionSet.size());
	}

	@Test
	@Points(value = 5)
	public void twoCages()
	{
		TEST_GOAL_MESSAGE = "Check that CarolinaTigerRescueSolver_Student can properly solve a 2x1 problem (with two distinct cages)";
		solver = getCarolinaTigerRescueSolver();

		final int ROW_COUNT = 1;
		final int COLUMN_COUNT = 2;
		final String CAT_CAGE_ID_1 = "1";
		final String CAT_CAGE_ID_2 = "2";
		Collection<CatCage> cageConfigurations = new ArrayList<CatCage>();
		CatCage catCage1 = getCatCage(CAT_CAGE_ID_1, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL);
		CatCage catCage2 = getCatCage(CAT_CAGE_ID_2, FEMALE_SERVAL, MALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL);
		CatCage catCage2_Rotated180 = getCatCage(CAT_CAGE_ID_2, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL, MALE_SERVAL);

		cageConfigurations.add(catCage1);
		cageConfigurations.add(catCage2);
		Set<GridPacking<CatCage>> solutionSet = solver.solve(cageConfigurations, ROW_COUNT, COLUMN_COUNT);

		GridPacking<CatCage> solution1 = new GridPackingImpl_Nunez<CatCage>(ROW_COUNT, COLUMN_COUNT, Arrays.asList(new CatCage[] { catCage1, catCage2_Rotated180 }));
		GridPacking<CatCage> solution2 = new GridPackingImpl_Nunez<CatCage>(ROW_COUNT, COLUMN_COUNT, Arrays.asList(new CatCage[] { catCage2, catCage1 }));

		assertEquals(true, solutionSet.contains(solution1));
		assertEquals(true, solutionSet.contains(solution2));
		assertEquals(2, solutionSet.size());
	}

	@Test
	@Points(value = 5)
	public void twoCagesTranspose()
	{
		TEST_GOAL_MESSAGE = "Check that CarolinaTigerRescueSolver_Student can properly solve a 2x1 problem (with two distinct cages)";
		solver = getCarolinaTigerRescueSolver();

		final int ROW_COUNT = 2;
		final int COLUMN_COUNT = 1;
		final String CAT_CAGE_ID_1 = "1";
		final String CAT_CAGE_ID_2 = "2";
		Collection<CatCage> cageConfigurations = new ArrayList<CatCage>();
		CatCage catCage1 = getCatCage(CAT_CAGE_ID_1, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL);
		CatCage catCage2 = getCatCage(CAT_CAGE_ID_2, FEMALE_SERVAL, MALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL);
		CatCage catCage2_Rotated270 = getCatCage(CAT_CAGE_ID_2, MALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL);
		CatCage catCage2_Rotated90 = getCatCage(CAT_CAGE_ID_2, FEMALE_SERVAL, FEMALE_SERVAL, MALE_SERVAL, FEMALE_SERVAL);

		cageConfigurations.add(catCage1);
		cageConfigurations.add(catCage2);
		Set<GridPacking<CatCage>> solutionSet = solver.solve(cageConfigurations, ROW_COUNT, COLUMN_COUNT);

		GridPacking<CatCage> solution1 = new GridPackingImpl_Nunez<CatCage>(ROW_COUNT, COLUMN_COUNT, Arrays.asList(new CatCage[] { catCage1, catCage2_Rotated270 }));
		GridPacking<CatCage> solution2 = new GridPackingImpl_Nunez<CatCage>(ROW_COUNT, COLUMN_COUNT, Arrays.asList(new CatCage[] { catCage2_Rotated90, catCage1 }));

		assertEquals(true, solutionSet.contains(solution1));
		assertEquals(true, solutionSet.contains(solution2));
		assertEquals(2, solutionSet.size());
	}

	@Test
	@Points(value = 5)
	public void threeCagesNoSolution()
	{
		TEST_GOAL_MESSAGE = "Check that CarolinaTigerRescueSolver_Student can properly determine the solution set to an unsolvable 1x3 problem";
		solver = getCarolinaTigerRescueSolver();

		final int ROW_COUNT = 1;
		final int COLUMN_COUNT = 3;
		final String CAT_CAGE_ID_1 = "1";
		final String CAT_CAGE_ID_2 = "2";
		final String CAT_CAGE_ID_3 = "3";
		Collection<CatCage> cageConfigurations = new ArrayList<CatCage>();
		CatCage catCage1 = getCatCage(CAT_CAGE_ID_1, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL);
		CatCage catCage2 = getCatCage(CAT_CAGE_ID_2, FEMALE_SERVAL, MALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL);
		CatCage catCage3 = getCatCage(CAT_CAGE_ID_3, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL);

		cageConfigurations.add(catCage1);
		cageConfigurations.add(catCage2);
		cageConfigurations.add(catCage3);
		Set<GridPacking<CatCage>> solutionSet = solver.solve(cageConfigurations, ROW_COUNT, COLUMN_COUNT);

		assertEquals(0, solutionSet.size());
	}

	@Test
	@Points(value = 5)
	public void threeCagesNoSolutionTranspose()
	{
		TEST_GOAL_MESSAGE = "Check that CarolinaTigerRescueSolver_Student can properly determine the solution set to an unsolvable 1x3 problem";
		solver = getCarolinaTigerRescueSolver();

		final int ROW_COUNT = 3;
		final int COLUMN_COUNT = 1;
		final String CAT_CAGE_ID_1 = "1";
		final String CAT_CAGE_ID_2 = "2";
		final String CAT_CAGE_ID_3 = "3";
		Collection<CatCage> cageConfigurations = new ArrayList<CatCage>();
		CatCage catCage1 = getCatCage(CAT_CAGE_ID_1, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL);
		CatCage catCage2 = getCatCage(CAT_CAGE_ID_2, FEMALE_SERVAL, MALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL);
		CatCage catCage3 = getCatCage(CAT_CAGE_ID_3, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL);

		cageConfigurations.add(catCage1);
		cageConfigurations.add(catCage2);
		cageConfigurations.add(catCage3);
		Set<GridPacking<CatCage>> solutionSet = solver.solve(cageConfigurations, ROW_COUNT, COLUMN_COUNT);

		assertEquals(0, solutionSet.size());
	}

	@Test
	@Points(value = 5)
	public void threeCagesTwoSolutions()
	{
		TEST_GOAL_MESSAGE = "Check that CarolinaTigerRescueSolver_Student can properly determine the solution set to an solvable 1x3 problem";
		solver = getCarolinaTigerRescueSolver();

		final int ROW_COUNT = 1;
		final int COLUMN_COUNT = 3;
		final String CAT_CAGE_ID_1 = "1";
		final String CAT_CAGE_ID_2 = "2";
		final String CAT_CAGE_ID_3 = "3";
		Collection<CatCage> cageConfigurations = new ArrayList<CatCage>();
		CatCage catCage1 = getCatCage(CAT_CAGE_ID_1, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL);
		CatCage catCage2 = getCatCage(CAT_CAGE_ID_2, FEMALE_SERVAL, MALE_SERVAL, FEMALE_SERVAL, MALE_SERVAL);
		CatCage catCage3 = getCatCage(CAT_CAGE_ID_3, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL);

		cageConfigurations.add(catCage1);
		cageConfigurations.add(catCage2);
		cageConfigurations.add(catCage3);
		Set<GridPacking<CatCage>> solutionSet = solver.solve(cageConfigurations, ROW_COUNT, COLUMN_COUNT);

		GridPacking<CatCage> solution1 = new GridPackingImpl_Nunez<CatCage>(ROW_COUNT, COLUMN_COUNT, Arrays.asList(new CatCage[] { catCage1, catCage2, catCage3 }));
		GridPacking<CatCage> solution2 = new GridPackingImpl_Nunez<CatCage>(ROW_COUNT, COLUMN_COUNT, Arrays.asList(new CatCage[] { catCage3, catCage2, catCage1 }));
		System.out.println("solutionSet = " + solutionSet);

		assertEquals(true, solutionSet.contains(solution1));
		assertEquals(true, solutionSet.contains(solution2));
		assertEquals(2, solutionSet.size());
	}
	//@Test
	public void isViableTest()
	{
		final int ROW_COUNT = 1;
		final int COLUMN_COUNT = 1;
		
		Sequence<CatCage> cagePlacementSequence = new SequenceImpl<CatCage>();	
		
		CatCage catCage = new CatCageImpl_Nunez("1", FEMALE_CARACAL, MALE_OCELOT, Cat.FEMALE_CARACAL, MALE_TIGER); // t, r, b, l
		CatCage catCage1 = new CatCageImpl_Nunez("2", Cat.MALE_CARACAL, Cat.MALE_SERVAL, Cat.MALE_SERVAL, Cat.FEMALE_OCELOT);
		cagePlacementSequence.extend(catCage);
		//cagePlacementSequence.extend(catCage1);
				
		//CarolinaTigerRescueSolver_Nunez.isViable(cagePlacementSequence, ROW_COUNT, COLUMN_COUNT);
	}

	@Test
	@Points(value = 5)
	public void threeCagesTwoSolutionsTranspose()
	{
		TEST_GOAL_MESSAGE = "Check that CarolinaTigerRescueSolver_Student can properly determine the solution set to an solvable 1x3 problem";
		solver = getCarolinaTigerRescueSolver();

		final int ROW_COUNT = 3;
		final int COLUMN_COUNT = 1;
		final String CAT_CAGE_ID_1 = "1";
		final String CAT_CAGE_ID_2 = "2";
		final String CAT_CAGE_ID_3 = "3";
		Collection<CatCage> cageConfigurations = new ArrayList<CatCage>();
		CatCage catCage1 = getCatCage(CAT_CAGE_ID_1, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL);
		CatCage catCage2 = getCatCage(CAT_CAGE_ID_2, FEMALE_SERVAL, MALE_SERVAL, FEMALE_SERVAL, MALE_SERVAL);
		CatCage catCage3 = getCatCage(CAT_CAGE_ID_3, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL, FEMALE_SERVAL);
		CatCage catCage2_Rotated180 = getCatCage(CAT_CAGE_ID_2, MALE_SERVAL, FEMALE_SERVAL, MALE_SERVAL, FEMALE_SERVAL);

		cageConfigurations.add(catCage1);
		cageConfigurations.add(catCage2);
		cageConfigurations.add(catCage3);
		Set<GridPacking<CatCage>> solutionSet = solver.solve(cageConfigurations, ROW_COUNT, COLUMN_COUNT);

		GridPacking<CatCage> solution1 = new GridPackingImpl_Nunez<CatCage>(ROW_COUNT, COLUMN_COUNT, Arrays.asList(new CatCage[] { catCage1, catCage2_Rotated180, catCage3 }));
		GridPacking<CatCage> solution2 = new GridPackingImpl_Nunez<CatCage>(ROW_COUNT, COLUMN_COUNT, Arrays.asList(new CatCage[] { catCage3, catCage2_Rotated180, catCage1 }));
		System.out.println("solutionSet = " + solutionSet);

		assertEquals(true, solutionSet.contains(solution1));
		assertEquals(true, solutionSet.contains(solution2));
		assertEquals(2, solutionSet.size());
	}
}
