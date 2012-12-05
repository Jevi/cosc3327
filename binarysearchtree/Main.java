package binarysearchtree;

import java.util.Arrays;
import java.util.Random;

public class Main
{
	public static void main(String[] args)
	{
		BinarySearchTree bst = new BinarySearchTreeImpl_Nunez();
		Random rn = new Random();
		int[] values = { 10, 7, 15, 5, 9, 12, 17 };

		for (int i : values)
		{
			bst.insert(i);
		}

		System.out.println("Pre Order: " + Arrays.toString(bst.preorderTraversal()) + "\n");
		System.out.println("Post Order: " + Arrays.toString(bst.postorderTraversal()) + "\n");
		System.out.println("In Order: " + Arrays.toString(bst.inorderTraversal()) + "\n");
		System.out.println("Main BST\n\n" + bst.toString());

		BinarySearchTree lbst = bst.left();
		System.out.println("Left BST\n\n" + lbst.toString());

		BinarySearchTree rbst = bst.right();
		System.out.println("Right BST\n\n" + rbst.toString());

	}
}
