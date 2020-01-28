/**
 * This class is the BinarySearchTree class that will house a BST of nodes. Each Node object contains a name and
 * a GDP per Capita double value. It has the ability to traverse the tree inorder, preorder, and postorder. It also
 * can insert a Node, find a Node, delete a Node, and print the bottom 10 and top 10 values based on their
 * GDP per capita.
 * 
 * @author Zackery Crews - n00826481
 * @version 11-17-2019
 *  
 */
public class BinarySearchTree {

/**
 * This class is a Node object for the BST and will house a name, gdp per capita, a pointer to its left child and
 * a pointer to its right child. It can print the node as well
 * 
 * @author Zackery Crews - n00826481
 * @version 11-17-2019
 */
	private class Node {
	
		String name;
		double gdpPerCapita;
		Node leftChild;
		Node rightChild;
	
	/**
	 * A constructor method to created a Node
	 * @param name - Name of the country to be created
	 * @param gdpPerCapita - GDP per Capita of the country to be created
	 */
		public Node(String name, double gdpPerCapita) {
			this.name = name;
			this.gdpPerCapita = gdpPerCapita;		
		}
	
	/**
	 * A method to print the node
	 */
		public void printNode() {
			System.out.printf("%-25s%,-20.2f\n", this.name, this.gdpPerCapita);
		}
	}
	
	private Node root;
	private int count;
	
	BinarySearchTree()
	{
		this.root = null;
		this.count = 0;
	}
	
/**
 * A method to assist recursive methods being called from somewhere that isn't this class
 * @return Node - returns the current root Node
 */
	public Node getRoot()
	{
		return this.root;
	}
	
/**
 * A method to insert a Node into the BST.
 * @param name - The name of the country to be inserted
 * @param gdpPerCapita - The GDP per Capita of the country to be inserted
 */
	public void insert(String name, double gdpPerCapita)
	{
		Node input = new Node(name, gdpPerCapita);

		if(this.root == null)
			this.root = input;
		else {
			Node current = this.root;
			Node parent;
			while(true) {
				parent = current;
				if(current.name.compareToIgnoreCase(name) > 0) {
					current = current.leftChild;
					if(current == null) {
						parent.leftChild = input;
						return;
					}
				}
				else {
					current = current.rightChild;
					if(current == null) {
						parent.rightChild = input;
						return;
					}
				}
			}
		}
	}
	
/**
 * A method to search the BST for a specified country and returns its GDP per capita if found, and -1 if not found
 * @param name - The name of the country to be searched for
 * @return int - the GDP per Capita value of the country found, or -1 if not found
 */
	public int find(String name)
	{
		int output = -1;
		Node current = this.root;
		int count = 0;
		
		while(current.name.compareToIgnoreCase(name) != 0) {
			if(current.name.compareToIgnoreCase(name) > 0) {
				current = current.leftChild;
				count++;
			}
			else {
				current = current.rightChild;
				count++;
			}
			if(current == null) {
				System.out.printf("\n%d nodes visited to find %s! ", count, name);
				return output;
			}
		}
		output = (int)current.gdpPerCapita;
		System.out.printf("\n%d nodes visited to find %s! ", count, name);
		return output;
	}
	
/**
 * A method to delete a country from the BST. It will process each case possible: If the Node has no children, if the
 * node has 1 child, or if the node has 2 children. It will then adjust the BST accordingly
 * @param name - the name of the country to be deleted
 */
	public void delete(String name)
	{
		Node current = this.root;
		Node parent = this.root;
		boolean isLeft = true;
		
		while(current.name.compareToIgnoreCase(name) != 0) {
			parent = current;
			if(current.name.compareToIgnoreCase(name) > 0) {
				isLeft = true;
				current = current.leftChild;
			}
			else {
				isLeft = false;
				current = current.rightChild;
			}
			if(current == null) {
				System.out.println("ERROR NODE NOT FOUND - TRY AGAIN");
				return;
			}
		}
		
		if(current.leftChild == null && current.rightChild == null) {
			if(current == this.root)
				this.root = null;
			else if(isLeft)
				parent.leftChild = null;
			else
				parent.rightChild = null;
		}
		else if(current.rightChild == null) {
			if(current == root)
				root = current.leftChild;
			else if(isLeft)
				parent.leftChild = current.leftChild;
			else
				parent.rightChild = current.leftChild;
		}
		else if(current.leftChild == null) {
			if(current == root)
				root = current.rightChild;
			else if(isLeft)
				parent.rightChild = current.rightChild;
			else
				parent.leftChild = current.leftChild;
		}
		else {
			Node sucParent = current;
			Node successor = current;
			Node sucCurrent = current.rightChild;
			while(sucCurrent != null) {
				sucParent = successor;
				successor = sucCurrent;
				sucCurrent = sucCurrent.leftChild;
			}
			
			if(successor != current.rightChild) {
				sucParent.leftChild = successor.rightChild;
				successor.rightChild = current.rightChild;
			}
			
			if(current == root)
				root = successor;
			else if(isLeft)
				parent.leftChild = successor;
			else
				parent.rightChild = successor;
			
			successor.leftChild = current.leftChild;
		}
		
		System.out.printf("%s has been deleted from the tree\n", name);
	}
	
/**
 * A recursive method to traverse and print each Node in the BST via inorder traversal
 * @param localRoot - the root of the current subtree being traversed
 */
	public void printInOrder(Node localRoot)
	{
		if(localRoot == null) return;
		
		printInOrder(localRoot.leftChild);
		localRoot.printNode();
		printInOrder(localRoot.rightChild);
	}
	
/**
 * A recursive method to traverse and print each Node in the BST via preorder traversal
 * @param localRoot - the root of the current subtree being traversed
 */
	public void printPreOrder(Node localRoot)
	{
		if(localRoot == null) return;
		
		localRoot.printNode();
		printPreOrder(localRoot.leftChild);
		printPreOrder(localRoot.rightChild);
	}
	
/**
 * A recursive method to traverse and print each Node in the BST via postorder traversal
 * @param localRoot - the root of the current subtree being traversed
 */
	public void printPostOrder(Node localRoot)
	{
		if(localRoot == null) return;
		
		printPostOrder(localRoot.leftChild);
		printPostOrder(localRoot.rightChild);
		localRoot.printNode();
	}
	
/**
 * This is a recursive method to assist in the traversal of the BST to find the country with the
 * smallest GDP per Capita that is not smaller than the limit. This limit is so that if it has been
 * called before and the smallest country has already been returned, the limit will be that of the
 * GDP per Capita of the smallest country and therefor return the second smallest, then updating the limit
 * to the second smallest and therefor returning the third smallest, etc etc.
 * @param localRoot - the root of the current subtree being traversed
 * @param limit - a limiter used so that a previously searched "smallest" country isn't returned again
 * @return Node - returns the smallest Node of that subtree
 */
	public Node traverseBot(Node localRoot, double limit)
	{
		if(localRoot == null || localRoot.rightChild == null && localRoot.leftChild == null) return new Node("", 10000000);
		Node current, left, right;
		
		current = localRoot;
		left = traverseBot(localRoot.leftChild, limit);
		right = traverseBot(localRoot.rightChild, limit);
		
		if(current.gdpPerCapita < left.gdpPerCapita && current.gdpPerCapita < right.gdpPerCapita && current.gdpPerCapita > limit)
			return current;
		else if(left.gdpPerCapita < current.gdpPerCapita && left.gdpPerCapita < right.gdpPerCapita && left.gdpPerCapita > limit)
			return left;
		else if(right.gdpPerCapita < current.gdpPerCapita && right.gdpPerCapita < left.gdpPerCapita && right.gdpPerCapita > limit)
			return right;
		else
			return new Node("", 10000000);
	}
	
/**
 * This method will iterate 10 times to find the 10 smallest countries in the tree, and each time it will update the limit
 * to the GDP per capita of the country returned so that it will never return the smallest country twice, and instead return
 * the second smallest, which it will update the limit to the second smallest and there for return the third smallest, etc etc.
 */
	public void printBottomTen()
	{
		Node limit = new Node("", 0);
		
		while(count < 10)
		{
			limit = traverseBot(this.root, limit.gdpPerCapita);
			limit.printNode();
			count++;
		}
		
		count = 0;
	}
	
/**
 * This is a recursive method to assist in the traversal of the BST to find the country with the
 * largest GDP per Capita that is not bigger than the limit. This limit is so that if it has been
 * called before and the largest country has already been returned, the limit will be that of the
 * GDP per capita of the largest country and there for return the second largest, then updating the limit
 * to the second largest and there for returning the third largest, etc etc.
 * @param localRoot - the root of the current subtree being traversed
 * @param limit - a limiter used so that a previously searched "largest" country isn't returned again
 * @return Node - returns the largest Node of that subtree
 */
	public Node traverseTop(Node localRoot, double limit)
	{
		if(localRoot == null || localRoot.rightChild == null && localRoot.leftChild == null) return new Node("", 0);
		Node current, left, right;
		
		current = localRoot;
		left = traverseTop(localRoot.leftChild, limit);
		right = traverseTop(localRoot.rightChild, limit);
		
		if(current.gdpPerCapita > left.gdpPerCapita && current.gdpPerCapita > right.gdpPerCapita && current.gdpPerCapita < limit)
			return current;
		else if(left.gdpPerCapita > current.gdpPerCapita && left.gdpPerCapita > right.gdpPerCapita && left.gdpPerCapita < limit)
			return left;
		else if(right.gdpPerCapita > current.gdpPerCapita && right.gdpPerCapita > left.gdpPerCapita && right.gdpPerCapita < limit)
			return right;
		else
			return new Node("", 0);
	}
	
/**
 * This method will iterated 10 times to find the 10 largest countries in the tree, and each time it will update the limit
 * to the GDP per Capita of the country returned so that it will never return the largest country twice, and instead return
 * the second largest, which it will update the limit to the second largest and there for return the third largest, etc etc.
 */
	public void printTopTen()
	{
		Node limit = new Node("", 10000000);
		
		while(count < 10)
		{
			limit = traverseTop(this.root, limit.gdpPerCapita);
			limit.printNode();
			count++;
		}
		
		count = 0;
	}
}
