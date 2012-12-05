package binarysearchtree;

public interface BinarySearchTree
{
	/**
	 * pre: <br>
	 * size() > 0<br>
	 * <br>
	 * 
	 * post:<br>
	 * rv > left().getMaxKey()<br>
	 * rv < right().getMinKey()<br>
	 * contains(rv)<br>
	 * <br>
	 * 
	 * @return
	 */
	public int getRootData();

	/**
	 * 
	 * @return
	 */
	public BinarySearchTree left();

	/**
	 * 
	 * @return
	 */
	public BinarySearchTree right();

	/**
	 * pre: <br>
	 * can call insert on an empty tree
	 * 
	 * @param key
	 */
	public void insert(int key);

	/**
	 * pre: <br>
	 * can call delete on empty tree
	 * 
	 * @param key
	 */
	public void delete(int key);

	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean contains(int key);

	/**
	 * 
	 * @return
	 */
	public int size();

	/**
	 * post: <br>
	 * size() == 1 ==> rv == 0
	 * 
	 * @return
	 */
	public int height();

	public int[] preorderTraversal();

	public int[] inorderTraversal();

	public int[] postorderTraversal();

	/**
	 * 
	 * @return
	 */
	public int getMinKey();

	/**
	 * 
	 * @return
	 */
	public int getMaxKey();
}
