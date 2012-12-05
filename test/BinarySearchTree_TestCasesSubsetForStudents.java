package test;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import binarysearchtree.BinarySearchTree;

public class BinarySearchTree_TestCasesSubsetForStudents
{
	public static final String PACKAGE_NAME = "binarysearchtree";
	protected static BinarySearchTree binarySearchTree_STUDENT;
	protected static String TEST_GOAL_MESSAGE;

	@Before
	public void initBeforeEachTestMethod()
	{
		binarySearchTree_STUDENT = new binarysearchtree.BinarySearchTreeImpl_Nunez();
	}

	@Points(value = 0)
	@Test
	public void checkThatAssertionsAreEnabled()
	{
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

	@Points(value = 3)
	@Test
	public void emptyTreeSizeTest()
	{
		TEST_GOAL_MESSAGE = "Get correct size for emptyTree.size()";
		assertEquals("size() should be 0", 0, binarySearchTree_STUDENT.size());
	}

	@Points(value = 3)
	@Test
	public void emptyTreeDeleteTest()
	{
		final int KEY = 42;
		TEST_GOAL_MESSAGE = "Don't blow up on emptyTree.delete(" + KEY + ")";
		binarySearchTree_STUDENT.delete(KEY);
	}

	@Points(value = 3)
	@Test
	public void addTenOnesTest()
	{
		TEST_GOAL_MESSAGE = "Call insert(1) ten times starting with emptyTree. Get correct size()";
		for (int i = 0; i < 10; i++)
		{
			binarySearchTree_STUDENT.insert(1);
		}
		assertEquals("size() should be 1", 1, binarySearchTree_STUDENT.size());
	}

	@Points(value = 3)
	@Test
	public void emptyInorderTraversalLengthTest()
	{
		TEST_GOAL_MESSAGE = "Get correct size for emptyTree.inorderTraversal().length";
		assertEquals("inorderTraversal().length should be 0", 0, binarySearchTree_STUDENT.inorderTraversal().length);
	}

	@Points(value = 3)
	@Test
	public void emptyPreorderTraversalLengthTest()
	{
		TEST_GOAL_MESSAGE = "Get correct size for emptyTree.preorderTraversal().length";
		assertEquals("preorderTraversal().length should be 0", 0, binarySearchTree_STUDENT.preorderTraversal().length);
	}

	@Points(value = 3)
	@Test
	public void emptyPostorderTraversalLengthTest()
	{
		TEST_GOAL_MESSAGE = "Get correct size for emptyTree.postorderTraversal().length";
		assertEquals("postorderTraversal().length should be 0", 0, binarySearchTree_STUDENT.postorderTraversal().length);
	}

	@Points(value = 5)
	@Test
	public void oneElementSizeTest()
	{
		TEST_GOAL_MESSAGE = "Get correct size for one element tree";
		binarySearchTree_STUDENT.insert(1);
		assertEquals("size() should be 1", 1, binarySearchTree_STUDENT.size());
	}

	@Points(value = 5)
	@Test
	public void oneElementGetRootDataTest()
	{
		final int KEY = 42;

		TEST_GOAL_MESSAGE = "Get correct size for one element tree";
		binarySearchTree_STUDENT.insert(KEY);
		assertEquals("getRootData() should equal " + KEY, KEY, binarySearchTree_STUDENT.getRootData());
	}

	@Points(value = 5)
	@Test
	public void twoElementSizeTest()
	{
		TEST_GOAL_MESSAGE = "Get correct size for two element tree";
		binarySearchTree_STUDENT.insert(1);
		binarySearchTree_STUDENT.insert(3);
		assertEquals("size() should equal 2", 2, binarySearchTree_STUDENT.size());
	}

	@Points(value = 5)
	@Test
	public void addDeleteSameElementTest()
	{
		TEST_GOAL_MESSAGE = "Get correct size for (new BinarySearchTreeImpl()).insert(1).delete(1)";
		binarySearchTree_STUDENT.insert(1);
		binarySearchTree_STUDENT.delete(1);
		assertEquals("size() should equal 0", 0, binarySearchTree_STUDENT.size());
	}

	@Points(value = 5)
	@Test
	public void containsTest()
	{
		final int KEY = 42;

		TEST_GOAL_MESSAGE = "Get correct answer for emptyTree.insert(" + KEY + ").contains(" + KEY + ")";
		binarySearchTree_STUDENT.insert(KEY);
		assertEquals("contains(" + KEY + ") should be true", true, binarySearchTree_STUDENT.contains(KEY));
	}

	@Points(value = 4)
	@Test
	public void oneElementInorderTraversalLengthTest()
	{
		final int KEY = 42;
		TEST_GOAL_MESSAGE = "Get correct size for emptyTree.insert(" + KEY + ").inorderTraversal().length";
		binarySearchTree_STUDENT.insert(KEY);
		assertEquals("inorderTraversal().length should be 1", 1, binarySearchTree_STUDENT.inorderTraversal().length);
	}

	@Points(value = 4)
	@Test
	public void oneElementPreorderTraversalSizeTest()
	{
		final int KEY = 42;
		TEST_GOAL_MESSAGE = "Get correct size for emptyTree.insert(" + KEY + ").preorderTraversal().length";
		binarySearchTree_STUDENT.insert(KEY);
		assertEquals("preorderTraversal().length should be 1", 1, binarySearchTree_STUDENT.preorderTraversal().length);
	}

	@Points(value = 4)
	@Test
	public void oneElementPostorderTraversalSizeTest()
	{
		final int KEY = 42;
		TEST_GOAL_MESSAGE = "Get correct size for emptyTree.insert(" + KEY + ").postorderTraversal().length";
		binarySearchTree_STUDENT.insert(KEY);
		assertEquals("postorderTraversal().length should be 1", 1, binarySearchTree_STUDENT.postorderTraversal().length);
	}

	@Points(value = 4)
	@Test
	public void oneElementInorderTraversalTest()
	{
		final int KEY = 42;
		TEST_GOAL_MESSAGE = "Get correct element for emptyTree.insert(" + KEY + ").inorderTraversal()[0]";
		binarySearchTree_STUDENT.insert(KEY);
		assertEquals("inorderTraversal()[0] should equal " + KEY, KEY, binarySearchTree_STUDENT.inorderTraversal()[0]);
	}

	@Points(value = 3)
	@Test
	public void oneElementPreorderTraversalTest()
	{
		final int KEY = 42;
		TEST_GOAL_MESSAGE = "Get correct element for emptyTree.insert(" + KEY + ").preorderTraversal()[0]";
		binarySearchTree_STUDENT.insert(KEY);
		assertEquals("preorderTraversal()[0] should be " + KEY, KEY, binarySearchTree_STUDENT.preorderTraversal()[0]);
	}

	@Points(value = 4)
	@Test
	public void oneElementPostorderTraversalTest()
	{
		final int KEY = 42;
		TEST_GOAL_MESSAGE = "Get correct element for emptyTree.insert(" + KEY + ").postorderTraversal()[0]";
		binarySearchTree_STUDENT.insert(KEY);
		assertEquals("postorderTraversal()[0] should be " + KEY, KEY, binarySearchTree_STUDENT.postorderTraversal()[0]);
	}

	@Points(value = 3)
	@Test
	public void twoElementInorderTraversalTest()
	{
		final int KEY1 = 53;
		final int KEY2 = 42;
		assert KEY2 < KEY1 : String.format("KEY2 = %s >= %s = KEY1!", KEY2, KEY1);
		TEST_GOAL_MESSAGE = "Get correct elements for emptyTree.insert(" + KEY1 + ").insert(" + KEY2 + ").inorderTraversal()";
		binarySearchTree_STUDENT.insert(KEY1);
		binarySearchTree_STUDENT.insert(KEY2);
		assertEquals("inorderTraversal()[0] should be " + KEY2, KEY2, binarySearchTree_STUDENT.inorderTraversal()[0]);
		assertEquals("inorderTraversal()[1] should be " + KEY1, KEY1, binarySearchTree_STUDENT.inorderTraversal()[1]);
	}

	@Points(value = 3)
	@Test
	public void twoElementPreorderTraversalSizeTest()
	{
		final int KEY1 = 42;
		final int KEY2 = 53;
		TEST_GOAL_MESSAGE = "Get correct size for emptyTree.insert(" + KEY1 + ").insert(" + KEY2 + ").preorderTraversal()";
		binarySearchTree_STUDENT.insert(KEY1);
		binarySearchTree_STUDENT.insert(KEY2);
		assertEquals("preorderTraversal().length should be 2", 2, binarySearchTree_STUDENT.preorderTraversal().length);
	}

	@Points(value = 3)
	@Test
	public void twoDuplicateElementsPreorderTraversalSizeTest()
	{
		final int KEY1 = 42;
		final int KEY2 = KEY1;
		TEST_GOAL_MESSAGE = "Get correct size for emptyTree.insert(" + KEY1 + ").insert(" + KEY2 + ").preorderTraversal()";
		binarySearchTree_STUDENT.insert(KEY1);
		binarySearchTree_STUDENT.insert(KEY2);
		assertEquals("preorderTraversal().length should be 1", 1, binarySearchTree_STUDENT.preorderTraversal().length);
	}

	@Points(value = 3)
	@Test
	public void manyElementsInsertedSizeTest()
	{
		TEST_GOAL_MESSAGE = "Get correct size for emptyTree.insert(many, many elements)";
		final int INSERTION_ATTEMPTS = 4200;
		Set<Integer> elementSet = new HashSet<Integer>();
		Random random = new Random(8376474578549922L);
		int i = 0;
		while (i < INSERTION_ATTEMPTS)
		{
			int nextElement = random.nextInt();
			elementSet.add(nextElement);
			binarySearchTree_STUDENT.insert(nextElement);
			i++;
		}
		assertEquals("size() should be " + elementSet.size(), elementSet.size(), binarySearchTree_STUDENT.size());
	}
}