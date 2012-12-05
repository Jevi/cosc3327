package tigers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sequence.Sequence;
import sequence.SequenceImpl;
import sequence.SequenceUtils;

public class CarolinaTigerRescueSolver_Nunez
{
	private CarolinaTigerRescueSolver_Nunez()
	{
		// DO NOT INSTANTIATE!
	}

	// part of pre: "problem to be solved has fewer than 10,000 solutions"
	// part of pre: cageConfigurations.size() <= 9
	// part of pre: cageConfigurations.size() == (rowCount*columnCount)
	// part of post: rv.size() < 10,000
	// part of post: "each element of rv has size rowCount*columnCount"
	// part of post: "for each l in rv and c in cageConfigurations,
	// exactly one of {c, rotateClockwise90Degrees(c), rotateClockwise180Degrees(c), rotateClockwise270Degrees(c)}
	// is an element of l"
	public static Set<GridPacking<CatCage>> solve(Collection<CatCage> cageConfigurations, int rowCount, int columnCount)
	{
		assert cageConfigurations != null : "cageConfigurations null!";
		assert cageConfigurations.size() == rowCount * columnCount : "cageConfigurations.size() = " + cageConfigurations.size() + " <> " + (rowCount * columnCount) + " = (rowCount*columnCount)!: rowCount = " + rowCount + " columnCount = " + columnCount;

		List<CatCage> catCageList = new ArrayList<CatCage>(cageConfigurations);
		Sequence<CatCage> cagePlacementSequence = new SequenceImpl<CatCage>();
		Set<GridPacking<CatCage>> temp = solve_aux(cagePlacementSequence, catCageList, rowCount, columnCount);
		System.out.println("Final Solution Set: " + Arrays.toString(temp.toArray()));
		return temp;
	}

	private static Set<GridPacking<CatCage>> solve_aux(Sequence<CatCage> cagePlacementSequence, List<CatCage> catCageList, int rowCount, int columnCount)
	{
		assert isViable(cagePlacementSequence, rowCount, columnCount) : String.format("cagePlacementSequence is not viable! : cagePlacementSequence =\n %s", new GridPackingImpl_Nunez<CatCage>(rowCount, columnCount, SequenceUtils.asList(cagePlacementSequence)));

		Set<GridPacking<CatCage>> solutionWithPrefixSet = new HashSet<GridPacking<CatCage>>();

		if (catCageList.size() == 0)
		{
			solutionWithPrefixSet.add(new GridPackingImpl_Nunez<CatCage>(rowCount, columnCount, SequenceUtils.asList(cagePlacementSequence)));
			return solutionWithPrefixSet;
		}

		int index = 0;
		for (CatCage catCage : catCageList)
		{
			System.out.println("Current CatCageList: " + Arrays.toString(catCageList.toArray()));
			System.out.println("Current Cage: " + catCage.toString());
			System.out.println("Sequence Size: " + cagePlacementSequence.size() + "\n");

			if (canAdd(catCage, cagePlacementSequence, rowCount, columnCount))
			{
				cagePlacementSequence.extend(catCage);
				List<CatCage> newCatCageList = new ArrayList<CatCage>(catCageList);
				newCatCageList.remove(index);
				solutionWithPrefixSet.addAll(solve_aux(cagePlacementSequence, newCatCageList, rowCount, columnCount));
				cagePlacementSequence.retract();
			}

			CatCage testCage = CatCageUtils_Nunez.rotate90(catCage);
			if (canAdd(testCage, cagePlacementSequence, rowCount, columnCount))
			{
				cagePlacementSequence.extend(testCage);
				List<CatCage> newCatCageList = new ArrayList<CatCage>(catCageList);
				newCatCageList.remove(index);
				solutionWithPrefixSet.addAll(solve_aux(cagePlacementSequence, newCatCageList, rowCount, columnCount));
				cagePlacementSequence.retract();
			}

			testCage = CatCageUtils_Nunez.rotate180(catCage);
			if (canAdd(testCage, cagePlacementSequence, rowCount, columnCount))
			{
				cagePlacementSequence.extend(testCage);
				List<CatCage> newCatCageList = new ArrayList<CatCage>(catCageList);
				newCatCageList.remove(index);
				solutionWithPrefixSet.addAll(solve_aux(cagePlacementSequence, newCatCageList, rowCount, columnCount));
				cagePlacementSequence.retract();
			}

			testCage = CatCageUtils_Nunez.rotate270(catCage);
			if (canAdd(testCage, cagePlacementSequence, rowCount, columnCount))
			{
				cagePlacementSequence.extend(testCage);
				List<CatCage> newCatCageList = new ArrayList<CatCage>(catCageList);
				newCatCageList.remove(index);
				solutionWithPrefixSet.addAll(solve_aux(cagePlacementSequence, newCatCageList, rowCount, columnCount));
				cagePlacementSequence.retract();
			}
			++index;
		}
		return solutionWithPrefixSet;
	}

	// pre: variables must be able to create a valid GridPacking
	// post: rv == boolean ( if given sequence with rowCount and columnCount is viable according to the spec )
	private static boolean isViable(Sequence<CatCage> cagePlacementSequence, int rowCount, int columnCount)
	{
		assert cagePlacementSequence != null : "Cannot pass a null cagePlacementSequence to isViable";
		
		boolean isViable = true;
		GridPacking<CatCage> gridPacking = new GridPackingImpl_Nunez<CatCage>(rowCount, columnCount, SequenceUtils.asList(cagePlacementSequence));

		System.out.println("isViable rowCount: " + rowCount);
		System.out.println("isViable columnCount: " + columnCount);
		System.out.println("isViable sequence size: " + cagePlacementSequence.size());
		System.out.println("isViable gridPacking: " + gridPacking.toString());

		if (cagePlacementSequence.size() == 0 && (rowCount >= 0 && columnCount >= 0))
		{
			return true;
		}
		else if (cagePlacementSequence.size() > 0)
		{
			for (int row = 0; row <= gridPacking.getRowCount() - 1; row++)
			{
				for (int column = 0; column <= gridPacking.getColumnCount() - 1; column++)
				{
					CatCage currentCage;
					try
					{
						currentCage = gridPacking.getElementAt(row, column);
					}
					catch (IndexOutOfBoundsException e)
					{
						return true;
					}

					boolean[] areViable = { true, true, true, true };
					boolean hasCageAbove = false;
					boolean hasCageBelow = false;
					boolean hasCageToRight = false;
					boolean hasCageToLeft = false;

					if (row - 1 >= 0 && row - 1 < gridPacking.getRowCount())
					{
						hasCageAbove = true;
					}

					if (row + 1 >= 0 && row + 1 < gridPacking.getRowCount())
					{
						hasCageBelow = true;
					}

					if (column + 1 >= 0 && column + 1 < gridPacking.getColumnCount())
					{
						hasCageToRight = true;
					}

					if (column - 1 >= 0 && column - 1 < gridPacking.getColumnCount())
					{
						hasCageToLeft = true;
					}

					if (hasCageAbove)
					{
						try
						{
							CatCage bottomCage = currentCage;
							CatCage topCage = gridPacking.getElementAt(row - 1, column);

							if (areTopBottomEmpty(topCage, bottomCage))
							{
								return false;
							}
							else
							{
								areViable[0] = areSameSpeciesDifferentGenders(topCage.getBottomCell(), bottomCage.getTopCell());
							}
						}
						catch (IndexOutOfBoundsException e)
						{
							areViable[0] = true;
						}
					}

					if (hasCageBelow)
					{
						try
						{
							CatCage topCage = currentCage;
							CatCage bottomCage = gridPacking.getElementAt(row + 1, column);

							if (areTopBottomEmpty(topCage, bottomCage))
							{
								return false;
							}
							else
							{
								areViable[1] = areSameSpeciesDifferentGenders(topCage.getBottomCell(), bottomCage.getTopCell());
							}
						}
						catch (IndexOutOfBoundsException e)
						{
							areViable[1] = true;
						}
					}

					if (hasCageToRight)
					{
						try
						{
							CatCage leftCage = currentCage;
							CatCage rightCage = gridPacking.getElementAt(row, column + 1);

							if (areLeftRightEmpty(leftCage, rightCage))
							{
								return false;
							}
							else
							{
								areViable[2] = areSameSpeciesDifferentGenders(leftCage.getRightCell(), rightCage.getLeftCell());
							}
						}
						catch (IndexOutOfBoundsException e)
						{
							areViable[2] = true;
						}
					}

					if (hasCageToLeft)
					{
						try
						{
							CatCage rightCage = currentCage;
							CatCage leftCage = gridPacking.getElementAt(row, column - 1);

							if (areLeftRightEmpty(leftCage, rightCage))
							{
								return false;
							}
							else
							{
								areViable[3] = areSameSpeciesDifferentGenders(leftCage.getRightCell(), rightCage.getLeftCell());
							}
						}
						catch (IndexOutOfBoundsException e)
						{
							areViable[3] = true;
						}
					}

					for (boolean bool : areViable)
					{
						if (!bool)
						{
							isViable = false;
						}
					}
				}
			}

		}
		return isViable;
	}

	// pre: cat cage is correctly implemented
	// pre: sequence, rowCount and columnCount must be viable
	// part of post: rv == isViable(cagePlacementSequence.extend(catCage))
	private static boolean canAdd(CatCage catCage, Sequence<CatCage> cagePlacementSequence, int rowCount, int columnCount)
	{
		assert catCage != null : "catCage is null!";
		assert isViable(cagePlacementSequence, rowCount, columnCount) : String.format("cagePlacementSequence is not viable! : cagePlacementSequence =\n %s", new GridPackingImpl_Nunez<CatCage>(rowCount, columnCount, SequenceUtils.asList(cagePlacementSequence)));

		System.out.println("\nChecking isViable in caAdd()");
		cagePlacementSequence.extend(catCage);
		boolean canAdd = isViable(cagePlacementSequence, rowCount, columnCount);
		cagePlacementSequence.retract();
		System.out.println("canAdd: " + canAdd + "\n");
		return canAdd;
	}

	private static boolean areTopBottomEmpty(CatCage topCage, CatCage bottomCage)
	{
		return (topCage.getTopCell() == null && topCage.getRightCell() == null && topCage.getBottomCell() == null && topCage.getLeftCell() == null) && (bottomCage.getTopCell() == null && bottomCage.getRightCell() == null && bottomCage.getBottomCell() == null && bottomCage.getLeftCell() == null);
	}

	private static boolean areLeftRightEmpty(CatCage leftCage, CatCage rightCage)
	{
		return (leftCage.getTopCell() == null && leftCage.getRightCell() == null && leftCage.getBottomCell() == null && leftCage.getLeftCell() == null) && (rightCage.getTopCell() == null && rightCage.getRightCell() == null && rightCage.getBottomCell() == null && rightCage.getLeftCell() == null);
	}

	private static boolean areSameSpeciesDifferentGenders(Cat cat1, Cat cat2)
	{
		assert cat1 != null : "cat1 is null!";
		assert cat2 != null : "cat2 is null!";

		boolean pass = cat1.getSpecies() == cat2.getSpecies() && cat1.getGender() != cat2.getGender();
		System.out.println("Cat1: " + cat1.getGender() + ", " + cat1.getSpecies());
		System.out.println("Cat2: " + cat2.getGender() + ", " + cat2.getSpecies());
		System.out.println("areSameSpeciesDifferentGenders: " + pass);
		return pass;
	}
}
