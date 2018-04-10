import java.util.PrimitiveIterator.OfDouble;

// starter class for a BalancedSearchTree
// you may implement AVL, Red-Black, 2-3 Tree, or 2-3-4 Tree
// be sure to include in class header which tree you have implemented

/////////////////////////////////////////////////////////////////////////////
// Semester:         CS400 Spring 2018
// PROJECT:          cs400_p2_201801
// FILES:            TestSearchTree.java
//                   SearchTreeADT.java
//                   BalancedSearchTree.java
//
// USER:             Taijing Chen (tchen284@wisc.edu)
//					 John Chen 
//
// Instructor:       Deb Deppeler (deppeler@cs.wisc.edu)
// Bugs:             no known bugs, but not complete either
//
// 2018 Feb 824 2018 5:13:18 PM TestSearchTree.java 
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This class implements SearchTreeADT by AVL. Users can use this class to
 * do lookup(), insert() and delete() in O(logn) time. User can also print
 * out keys in ascending order and check whether it's empty.
 * 
 * @author Taijing Chen 
 * 		   John Chen
 *
 * @param <T> comparable generic type
 */
public class BalancedSearchTree<T extends Comparable<T>> implements SearchTreeADT<T> {

	// inner node class used to store key items and links to other nodes
	
	/**
	 * This is a protected tree node class. User can use it to check whether
	 * a certain node has right child or left child and get them as well. The
	 * node also can keep track of its balance property by calculating balance 
	 * factor.
	 * 
	 * @author Taijing Chen
	 * 		   John Chen
	 *
	 * @param <K> comparable generic type
	 */
	protected class Treenode<K extends Comparable<K>> {
		K key;
		Treenode<K> leftchild;
		Treenode<K> rightchild;
		Treenode<K> parent; 
		
		/**
		 * default constructor - create a node with key of item
		 * 
		 * @param item	key of node
		 */
		public Treenode(K item) {
			this(item, null, null, null);
		}
		
		public Treenode(K item, Treenode<K> left, Treenode<K> right, Treenode<K> parent) {
			key = item;
			this.leftchild = left;
			this.rightchild = right;
			this.parent = parent;
		}
		
		/**
		 * check whether the node has left child
		 * 
		 * @return boolean - has left child or not
		 */
		public boolean hasLeftchild() {
			return leftchild != null;
		}
		
		/**
		 * check whether the node has right child
		 * 
		 * @return boolean - has right child or not
		 */
		public boolean hasRightchild() {
			return rightchild != null;
		}
		
		/**
		 * Get balance factor of the node (height of left child - height of
		 * right child). If it's a leaf, then the balance factor is 1.
		 * 
		 * @return int - the value of balance factor
		 */
		public int calculateBalanceFactor() {
			// check whether it's a leaf or interior node
			if (!hasLeftchild() && !hasRightchild()) {
				return 0;
			} else if (!hasLeftchild()) {
				return 0 - rightchild.getHeight();
			} else if (!hasRightchild()) {
				return leftchild.getHeight() - 0;
			} else { // hasLeftchild() && hasRightchild()
				return leftchild.getHeight() - rightchild.getHeight();
			}
		}
		
		/**
		 * Get the height of the node. If the node is a leaf, then the 
		 * height is 1; If it's an internal node, it's equal to the max
		 * height of its child plus one.
		 * 
		 * @return int - the value of height
		 */
		public int getHeight() {
			// check whether it's a leaf or interior node
			if (!hasLeftchild() && !hasRightchild()) {
				return 1;
			} else if (!hasLeftchild()) {
				return rightchild.getHeight() + 1;
			} else if (!hasRightchild()) {
				return leftchild.getHeight() + 1;
			} else {
				return Math.max(leftchild.getHeight(), rightchild.getHeight()) + 1;
			}
		}
		
	}

	protected Treenode<T> root;
	
	/**
	 * returns a string with the values in ascending order
	 * The form of the string returned is a comma separated list
	 * without additional spaces between keys ending with a comma.
	 * For example if the keys in the tree are A,B, and C
	 * then the String returned would be:
	 * 
	 *  <pre>
	 *  A,B,C,
	 *  </pre>
	 *  
	 *  If no keys have been inserted, return an empty string "" without any keys
	 *  
	 * @return string - the string of keys in the tree in an ascending order
	 */
	public String inAscendingOrder() {
		//TODO : must return comma separated list of keys in ascending order
		StringBuilder sBuilder = new StringBuilder();
		// check whether it's an empty tree
		if (root == null) {
			return "";
		}
		return inAscendingOrderHelper(root, sBuilder).toString() ;
	}

	/**
	 * This is the private helper for inAscendingOrder(). It traverses the 
	 * tree by in-order.
	 * 
	 * @param node - the current node to get strings in ascending order
	 * @param sBuilder - string builder for storing the string
	 * @return StringBuilder - the string builder form of keys in the tree in 
	 * ascending order
	 */
	private StringBuilder inAscendingOrderHelper(Treenode<T> node, StringBuilder sBuilder) {
		if (node.hasLeftchild()) {
			inAscendingOrderHelper(node.leftchild, sBuilder);
		}
		sBuilder.append(node.key.toString()).append(',');
		if (node.hasRightchild()) {
			inAscendingOrderHelper(node.rightchild, sBuilder);
		}
		return sBuilder;
	}

	/**
	 * check whether the tree is empty
	 * 
	 * @return boolean - true if it's an empty tree
	 */
	public boolean isEmpty() {
		//TODO return empty if there are no keys in structure
		return root == null;
	}

	/**
	 * Get the height of the tree. To do this, it calls getHeight() method
	 * on the tree root.
	 * 
	 * @return int - the height of the tree
	 */
	public int height() {
		//TODO return the height of this tree
		// check whether the tree is empty
		if (root == null) {
			return 0;
		}
		return root.getHeight(); 
	}

	/**
	 * Check whether certain item is in the tree. If the item we want
	 * to look up is a null item, then it throws IllegalArgumentException.
	 * 
	 * @return boolean - the result of finding a node with the item
	 * of not
	 */
	public boolean lookup(T item) {
		//TODO must return true if item is in tree, otherwise false
		// cannot call this method to find a null item
		if (item == null) {
			throw new IllegalArgumentException();
		}
		return lookupHelper(root, item) != null;
	}

	/**
	 * This is the private helper method for lookup(). It compares the value of 
	 * item of the key of current node. If item is smaller than the key, it check 
	 * the left child of current node; If item is greater than the key, it check
	 * the right child of current node; If item is equal to the key, it returns 
	 * the current node; If the item is not equal to the key but the current node
	 * is already a leaf, it returns null.
	 * @param currentNode - the node we are checking
	 * @param item - the item we want to find
	 * @return treenode - find the node with same key or not
	 */
	private Treenode<T> lookupHelper(Treenode<T> currentNode, T item) {
		int compareResult = currentNode.key.compareTo(item);
		// check the whether the node has bigger or smaller value than 
		// the current node
		if (compareResult == 0) {
			return currentNode;
		} else if ( (compareResult < 0 && currentNode.leftchild == null) 
				|| (compareResult > 0 && currentNode.rightchild == null)) {
			return null;
		} else if ( compareResult < 0 ) {
			return lookupHelper(currentNode.leftchild, item);
		} else {
			return lookupHelper(currentNode.rightchild, item);
		}
	}

	/**
	 * Insert an item to a tree while maintaining the tree's property. If the 
	 * item to insert is null, it throws IllegalArgumentException; If the item
	 * has already been inserted, it throws DuplicateKeyException; If the tree is 
	 * an empty tree, it sets the root to the new node; Otherwise 
	 * it'll call insertHelper() method to insert a node with the item.
	 * 
	 * @param	item	item of node to be inserted
	 */
	public void insert(T item) throws DuplicateKeyException, IllegalArgumentException {
		//TODO if item is null throw IllegalArgumentException, 
		// otherwise insert into balanced search tree
		if (item == null) {
			throw new IllegalArgumentException();
		}
		
		Treenode<T> nodeToInsert = new Treenode<T>(item);
		// check whether it's an empty tree or not
		if (isEmpty()) {
			root = nodeToInsert;
			return;
		}
		insertHelper(root, nodeToInsert);
	}

	/**
	 * This is the private helper method for insert(). It compares the current
	 * node with the node to insert. If the node to insert has bigger value, it 
	 * checks the right child of current node; If the node to insert has smaller
	 * value, it checks the left child of current node; If the node to insert 
	 * has the same value of the current node, it throws a duplicateKeyException;
	 * If there's no node to check, it inserts the node to the right place.
	 * After insertion, it calls fixUp() method on the current node to rebalance 
	 * the tree
	 * 
	 * @param currentNode - the node we are checking
	 * @param nodeToInsert - the node we want to insert
	 * @return treenode - node to check
	 */
	private Treenode<T> insertHelper(Treenode<T> currentNode, Treenode<T> nodeToInsert) {
		int compareResult = nodeToInsert.key.compareTo(currentNode.key);
		// check whether it's a duplicate
		// avoid operating on duplicates
		if (compareResult == 0) {
			throw new DuplicateKeyException();
		} 
		// check whether the node has smaller value than
		// the current node
		else if (compareResult < 0) {
			// check whether it's the place to insert the node or not
			if (currentNode.hasLeftchild()) {
				return insertHelper(currentNode.leftchild, nodeToInsert);
			} else {
				currentNode.leftchild = nodeToInsert;
				nodeToInsert.parent = currentNode;
				fixUp(currentNode);
				return currentNode;
			}
		} 
		// check whether the node has bigger value than the current node
		else { // compareResult > 0
			// check whether it's the place to insert the node
			if (currentNode.hasRightchild()) {
				return insertHelper(currentNode.rightchild, nodeToInsert);
			} else {
				currentNode.rightchild = nodeToInsert;
				nodeToInsert.parent = currentNode;
				fixUp(currentNode);
				return currentNode;
			}
		}
	}

	/**
	 * This is a private method for maintaining balancing property of
	 * the tree. It uses rotation to re-balance the tree.
	 * 
	 * @param node_x - the node to fix
	 * @return new root of subtree
	 */
	private Treenode<T> fixUp(Treenode<T> node_x) {
		// check whether the tree is right heavy
		if (node_x.calculateBalanceFactor() < -1) {
			Treenode<T> node_w = node_x.rightchild;
			// check to use left-rotation or right-left-rotation
			if (node_w.calculateBalanceFactor() < 0) {
				left_rotate(node_x);
			} else { // node.rightchild.balance_factor > 0, it cannot be equal to 0 when out of
						// balance
				right_left_rotate(node_x);
			}
			return fixUp(node_w);
		} 
		// check whether the tree is left heavy
		else if (node_x.calculateBalanceFactor() > 1) {
			Treenode<T> node_w = node_x.leftchild;
			// check to use right-rotation or left-right-rotation
			if (node_w.calculateBalanceFactor() > 0) {
				right_rotate(node_x);
			} else {
				left_right_rotate(node_x);
			}
			return fixUp(node_w);
		} 
		// fix up til the root
		else {
			if (node_x != root) {
				return fixUp(node_x.parent);
			}
			return node_x;
		}
	}

	/**
	 * Delete the node with a key same as the given item. If the item
	 * to delete is a null item, it throws out IllegalArgumentException.
	 * Otherwise, it calls the private deleteHelper method to delete
	 * the node to delete and keep the balancing property.
	 * 
	 * @param	item	item of node to be deleted
	 */
	public void delete(T item) {
		//TODO if item is null or not found in tree, return without error
		// else remove this item key from the tree and rebalance

		if (item == null) {
			throw new IllegalArgumentException();
		}
		Treenode<T> nodeToDelete = new Treenode<T>(item);
		deleteHelper(item);
		
	}
	
	/**
	 * This is the private helper method for delete(). If there's no
	 * such node with the same item to delete, then it does nothing;
	 * Otherwise, we'll use the leftmost of the right subtree to replace
	 * the node we want to delete. 
	 * If the node to delete is the root, then will do the operation on
	 * root; If the node to delete is the left or right child of its parent, 
	 * re-link its children with its parent. Call fix up in the end
	 * to re-balance the tree.
	 * 
	 * @param item	node with item to be deleted
	 */
	private void deleteHelper(T item) {
		Treenode<T> node_x = locateNode(root, item);
		// If the item to delete is not in this tree
		if (node_x.key.compareTo(item) != 0 ) {
			return;
		}
		
		// check whether the node to delete is the root
		if (node_x == root) {
			if (!node_x.hasLeftchild() && !node_x.hasRightchild()) {
				root = null;
			} else if (!node_x.hasRightchild()) {
				root = node_x.leftchild;
				node_x.leftchild.parent = null;
				fixUp(root);
			} else if (!node_x.rightchild.hasLeftchild()) {
				node_x.rightchild.leftchild = root.leftchild;
				if (node_x.hasLeftchild())  root.leftchild.parent = node_x.rightchild;
				root = node_x.rightchild;
				root.parent = null;
				fixUp(root);
			} else { // node_x.hasLeftchild() && node_x.hasRightchild()
				Treenode<T> nodeToReplace = leftMost(node_x.rightchild);
				Treenode<T> nodeToFix = nodeToReplace.parent;
				nodeToFix.leftchild = nodeToReplace.rightchild;
				nodeToReplace.parent = nodeToFix;
				if (node_x.hasLeftchild()) {
					nodeToReplace.leftchild = node_x.leftchild;
					node_x.leftchild.parent = nodeToReplace;
				}
				if (node_x.hasRightchild()) {
					nodeToReplace.rightchild = node_x.rightchild;
					node_x.rightchild.parent = nodeToReplace;
				}
				root = nodeToReplace;
				root.parent = null;
				fixUp(nodeToFix);
			}
			return;
		} 
		
		// operation on non-root children
		Treenode<T> node_p = node_x.parent;
		// check whether
		if (node_x == node_p.leftchild) {
			if (!node_x.hasLeftchild() && !node_x.hasRightchild()) {
				node_p.leftchild = null;
				fixUp(node_p);
			} else if (!node_x.hasRightchild()) {
				node_p.leftchild = node_x.leftchild;
				fixUp(node_x.leftchild);
			} else if (!node_x.rightchild.hasLeftchild()) {
				Treenode<T> nodeToReplace = node_x.rightchild;
				nodeToReplace.leftchild = node_x.leftchild;
				if (node_x.hasLeftchild()) node_x.leftchild.parent = nodeToReplace;
				node_p.leftchild = nodeToReplace;
				nodeToReplace.parent = node_p;
				fixUp(nodeToReplace);
			} else { // node_x.hasRightchild() == true
				Treenode<T> nodeToReplace = leftMost(node_x.rightchild);
				Treenode<T> nodeToFix = nodeToReplace.parent;
				nodeToFix.leftchild = nodeToReplace.rightchild;
				nodeToReplace.parent = nodeToFix;
				if (node_x.hasLeftchild()) {
					nodeToReplace.leftchild = node_x.leftchild;
					node_x.leftchild.parent = nodeToReplace;
				}
				if (node_x.hasRightchild()) {
					nodeToReplace.rightchild = node_x.rightchild;
					node_x.rightchild.parent = nodeToReplace;
				}
				node_p.leftchild = nodeToReplace;
				nodeToReplace.parent = node_p;
				fixUp(nodeToFix);
			} 

		} else { // node_x == node_x.parent.rightchild
			if (!node_x.hasLeftchild() && !node_x.hasRightchild()) {
				node_p.rightchild = null;
				fixUp(node_p);
			} else if (!node_x.hasRightchild()) {
				node_p.rightchild = node_x.leftchild;
				fixUp(node_p.rightchild);
			} else if (!node_x.rightchild.hasLeftchild()) {
				Treenode<T> nodeToReplace = node_x.rightchild;
				nodeToReplace.leftchild = node_x.leftchild;
				if (node_x.hasLeftchild())  node_x.leftchild.parent = nodeToReplace;
				node_p.rightchild = nodeToReplace;
				nodeToReplace.parent = node_p;
				fixUp(nodeToReplace);
			} else { // node_x.hasRightchild() == true
				Treenode<T> nodeToReplace = leftMost(node_x.rightchild);
				Treenode<T> nodeToFix = nodeToReplace.parent;
				nodeToFix.leftchild = nodeToReplace.rightchild;
				nodeToReplace.parent = nodeToFix;
				if (node_x.hasLeftchild()) {
					nodeToReplace.leftchild = node_x.leftchild;
					node_x.leftchild.parent = nodeToReplace;
				}
				if (node_x.hasRightchild()) {
					nodeToReplace.rightchild = node_x.rightchild;
					node_x.rightchild.parent = nodeToReplace;
				}
				node_p.rightchild = nodeToReplace;
				nodeToReplace.parent = node_p;
				fixUp(nodeToFix);
			}
		}
	}

	/**
	 * The is private helper method for deletion to locate the node 
	 * to delete. If the current node has the same value as the given
	 * item, the current node is returned; If the current node has 
	 * smaller value, check its left child; If the current node has
	 * larger value, check its right child; If there's no such item,
	 * we still return the current node.
	 * @param currentNode	node to be checked
	 * @param item	item that target node has
	 * @return	node that matches the key, else return last node checked
	 */
	private Treenode<T> locateNode(Treenode<T> currentNode, T item) {
		int compareResult = item.compareTo(currentNode.key);
		if (compareResult < 0) {
			return locateNode(currentNode.leftchild, item);
		} else if (compareResult > 0) {
			return locateNode(currentNode.rightchild, item);
		} else {
			return currentNode;
		}
	}

	/**
	 * HINT: define this helper method that can find the smallest key 
	 * in a sub-tree with "node" as its root
	 * PRE-CONDITION: node is not null
	 * 
	 * @param node - the node of which we want to find the left most
	 * @return	left leaf node at bottom of all left subtrees
	 */
	private Treenode<T> leftMost(Treenode<T> node) {
		// TODO return the key value of the left most node in this subtree
		// or return node's key if node does not have a left child
		if (node.leftchild == null) {
			return node;
		} else {
			return leftMost(node.leftchild);
		}
	}
	
	/**
	 * left rotate current node to re-balance the tree
	 * PRE-CONDITION: The height of the current node_x should not be updated
	 * call when balance factor < 0
	 * @param node_x - the node that requires rotation
	 */
	private void left_rotate(Treenode<T> node_x) {
		Treenode<T> node_w = node_x.rightchild; // set up node_w
		if (node_x == root) {
			root = node_w;
		} else if (node_x == node_x.parent.leftchild) {
			node_x.parent.leftchild = node_w; 
		} else { // node_x == node_x.parent.rightchild
			node_x.parent.rightchild = node_w;
		}
		node_x.rightchild = node_w.leftchild;
		if (node_w.hasLeftchild()) node_w.leftchild.parent = node_x;
		node_w.leftchild = node_x;
		node_w.parent = node_x.parent;
		node_x.parent = node_w;
	}
	
	/**
	 * right rotate current node to re-balance the tree
	 * PRE-CONDITION: The height of the current node_x should not be updated
	 * call when balance factor > 0
	 * @param node_x - the node that requires rotation
	 */
	private void right_rotate(Treenode<T> node_x) {
		Treenode<T> node_w = node_x.leftchild;
		
		if (node_x == root) {
			root = node_w;
		} else if (node_x == node_x.parent.rightchild) {
			node_x.parent.rightchild = node_w;
		} else { // node_x == node_x.parent.leftchild
			node_x.parent.leftchild = node_w;
		}
		node_x.leftchild =node_w.rightchild;
		if (node_w.hasRightchild()) node_w.rightchild.parent = node_x;
		node_w.rightchild = node_x;
		node_w.parent = node_x.parent;
		node_x.parent = node_w;
	}
	
	/**
	 * right rotate on node's right child first, and then left rotate
	 * the current node
	 * @param node_x - the node that requires rotation
	 */
	private void right_left_rotate(Treenode<T> node_x) {
		Treenode<T> node_w = node_x.rightchild;
		right_rotate(node_w);
		left_rotate(node_x);
	}
	
	/**
	 * left rotate on node's left child first, and then right rotate
	 * the current node
	 * @param node_x - the node that requires rotation
	 */
	private void left_right_rotate(Treenode<T> node_x) {
		Treenode<T> node_w = node_x.leftchild;
		left_rotate(node_w);
		right_rotate(node_x);
	}
}

