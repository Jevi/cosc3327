package binarysearchtree;

public class Node_Nunez
{
	private Node_Nunez leftNode;
	private Node_Nunez rightNode;
	private Node_Nunez parentNode;
	private int key;

	public Node_Nunez(int key)
	{
		this.key = key;
		leftNode = null;
		rightNode = null;
		parentNode = null;
	}

	public void setLeftNode(Node_Nunez leftNode)
	{
		this.leftNode = leftNode;
	}

	public void setRightNode(Node_Nunez rightNode)
	{
		this.rightNode = rightNode;
	}

	public void setParentNode(Node_Nunez parentNode)
	{
		this.parentNode = parentNode;
	}

	public int getKey()
	{
		return key;
	}

	public Node_Nunez getLeftNode()
	{
		return leftNode;
	}

	public Node_Nunez getRightNode()
	{
		return rightNode;
	}

	public Node_Nunez getParentNode()
	{
		return parentNode;
	}

	public String toString()
	{
		return Integer.toString(key);
	}
}
