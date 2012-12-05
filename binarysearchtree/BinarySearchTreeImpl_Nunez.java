package binarysearchtree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearchTreeImpl_Nunez implements BinarySearchTree
{
	private List<Node_Nunez> binaryTree = new ArrayList<Node_Nunez>();

	/**
	 * Returns root node of bst
	 * 
	 * @return Node_Nunez - root node of bst
	 */
	private Node_Nunez getRootNode()
	{
		return binaryTree.get(0);
	}

	public int getRootData()
	{
		assert size() > 0 : "Tree is empty. It has no root.";

		return getRootNode().getKey();
	}

	public BinarySearchTree left()
	{
		assert size() > 0 : "Tree is empty. It has no root.";
		assert getRootNode().getLeftNode() != null : "Tree has no left sub-tree";

		BinarySearchTree bst = new BinarySearchTreeImpl_Nunez();

		// create new bst of only nodes which hold key values less than the current root node key value
		for (Node_Nunez node : binaryTree)
		{
			if (node.getKey() != getRootNode().getKey() && node.getKey() < getRootNode().getKey())
			{
				bst.insert(node.getKey());
			}
		}

		return bst;
	}

	public BinarySearchTree right()
	{
		assert size() > 0 : "Tree is empty. It has no root.";
		assert getRootNode().getRightNode() != null : "Tree has no right sub-tree";

		BinarySearchTree bst = new BinarySearchTreeImpl_Nunez();

		// create new bst of only nodes which hold key values greater than the current root node key value
		for (Node_Nunez node : binaryTree)
		{
			if (node.getKey() != getRootNode().getKey() && node.getKey() > getRootNode().getKey())
			{
				bst.insert(node.getKey());
			}
		}

		return bst;
	}

	public void insert(int key)
	{
		if (!contains(key))
		{
			Node_Nunez newNode = new Node_Nunez(key);

			// insert parent node
			if (size() == 0)
			{
				binaryTree.add(newNode);
			}
			else
			{
				Node_Nunez parentNode = getRootNode();
				Node_Nunez currentNode = getRootNode();

				// search for next available node position in bst
				while (currentNode != null)
				{
					parentNode = currentNode;

					if (key < currentNode.getKey())
					{
						currentNode = currentNode.getLeftNode();
					}
					else
					{
						currentNode = currentNode.getRightNode();
					}
				}

				// store new node in next available bst position
				if (key < parentNode.getKey())
				{
					newNode.setParentNode(parentNode);
					parentNode.setLeftNode(newNode);
				}
				else
				{
					newNode.setParentNode(parentNode);
					parentNode.setRightNode(newNode);
				}

				binaryTree.add(newNode);
			}
		}
	}

	public void delete(int key)
	{
		// delete node which holds key value
		for (int i = 0; i < size(); i++)
		{
			if (binaryTree.get(i).getKey() == key)
			{
				binaryTree.remove(i);
				break;
			}
		}

		// recreate tree with value removed
		List<Node_Nunez> tempBinaryTree = new ArrayList<Node_Nunez>(binaryTree);
		binaryTree = new ArrayList<Node_Nunez>();

		for (Node_Nunez node : tempBinaryTree)
		{
			insert(node.getKey());
		}
	}

	public boolean contains(int key)
	{
		for (Node_Nunez node : binaryTree)
		{
			if (node.getKey() == key)
			{
				return true;
			}
		}
		return false;
	}

	public int size()
	{
		return binaryTree.size();
	}

	public int height()
	{
		assert size() > 0 : "Tree is empty. It has no root.";

		return heightOfBinaryTree(getRootNode()) - 1;
	}

	private int heightOfBinaryTree(Node_Nunez node)
	{
		if (node == null)
		{
			return 0;
		}
		else
		{
			return 1 + Math.max(heightOfBinaryTree(node.getLeftNode()), heightOfBinaryTree(node.getRightNode()));
		}
	}

	public int[] preorderTraversal()
	{
		if (size() != 0)
		{
			List<Integer> keys = new ArrayList<Integer>();
			preorderTraversal(getRootNode(), keys);
			int[] keysArray = new int[keys.size()];

			for (int i = 0; i < keysArray.length; i++)
			{
				keysArray[i] = keys.get(i);
			}

			return keysArray;
		}
		else
		{
			return new int[0];
		}
	}

	private void preorderTraversal(Node_Nunez node, List<Integer> keys)
	{
		if (node != null)
		{
			keys.add(node.getKey());
			preorderTraversal(node.getLeftNode(), keys);
			preorderTraversal(node.getRightNode(), keys);
		}
	}

	public int[] inorderTraversal()
	{
		if (size() != 0)
		{
			List<Integer> keys = new ArrayList<Integer>();
			inorderTraversal(getRootNode(), keys);
			int[] keysArray = new int[keys.size()];

			for (int i = 0; i < keysArray.length; i++)
			{
				keysArray[i] = keys.get(i);
			}

			return keysArray;
		}
		else
		{
			return new int[0];
		}
	}

	private void inorderTraversal(Node_Nunez node, List<Integer> keys)
	{
		if (node != null)
		{
			inorderTraversal(node.getLeftNode(), keys);
			keys.add(node.getKey());
			inorderTraversal(node.getRightNode(), keys);
		}
	}

	public int[] postorderTraversal()
	{
		if (size() != 0)
		{
			List<Integer> keys = new ArrayList<Integer>();
			postorderTraversal(getRootNode(), keys);
			int[] keysArray = new int[keys.size()];

			for (int i = 0; i < keysArray.length; i++)
			{
				keysArray[i] = keys.get(i);
			}

			return keysArray;
		}
		else
		{
			return new int[0];
		}
	}

	private void postorderTraversal(Node_Nunez node, List<Integer> keys)
	{
		if (node != null)
		{
			postorderTraversal(node.getLeftNode(), keys);
			postorderTraversal(node.getRightNode(), keys);
			keys.add(node.getKey());
		}
	}

	public int getMinKey()
	{
		assert size() > 0 : "Tree is empty. It has no root.";

		// order temp list from lowest to greatest
		List<Node_Nunez> tempBinaryTree = new ArrayList<Node_Nunez>(binaryTree);
		Collections.sort(tempBinaryTree, new NodeComparator_Nunez());
		return tempBinaryTree.get(0).getKey();
	}

	public int getMaxKey()
	{
		assert size() > 0 : "Tree is empty. It has no root.";

		// order temp list from greatest to lowest
		List<Node_Nunez> tempBinaryTree = new ArrayList<Node_Nunez>(binaryTree);
		Collections.sort(tempBinaryTree, new NodeComparator_Nunez());
		return tempBinaryTree.get(tempBinaryTree.size() - 1).getKey();
	}

	public String toString()
	{
		assert size() > 0 : "Tree is empty. It has no root.";

		StringBuilder sb = new StringBuilder();

		// display bst's characteristics
		sb.append("Size: " + size() + "\n");
		sb.append("Height: " + height() + "\n");
		sb.append("Root: " + getRootData() + "\n");
		sb.append("Min Key: " + getMinKey() + "\n");
		sb.append("Max Key: " + getMaxKey() + "\n");
		sb.append("\n");

		// display each node's characteristics
		for (Node_Nunez node : binaryTree)
		{
			sb.append("Node: " + node.getKey() + "\n");
			sb.append("Left: " + node.getLeftNode() + "\n");
			sb.append("Right: " + node.getRightNode() + "\n");
			sb.append("Parent: " + node.getParentNode() + "\n");
			sb.append("\n");
		}
		return sb.toString();
	}
}