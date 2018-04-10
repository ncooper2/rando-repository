import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/////////////////////////////////////////////////////////////////////////////
// Semester:         CS400 Spring 2018
// PROJECT:          cs400_p2_201801
// FILES:            TestSearchTree.java
//                   SearchTreeADT.java
//                   BalancedSearchTree.java
//
// USER:             Taijing Chen & John Chen
//
// Instructor:       Deb Deppeler (deppeler@cs.wisc.edu)
// Bugs:             no known bugs, but not complete either
//
// 2018 Feb 824 2018 5:13:18 PM TestSearchTree.java 
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This class test BalancedSearchTree class.
 * 
 * @author Taijing Chen & John Chen
 *
 */
public class TestSearchTree {

	SearchTreeADT<String> tree = null;
	String expected = null;
	String actual = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tree = new BalancedSearchTree<String>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test isEmpty() method on an empty tree. Test fails if it's
	 * not true.
	 */
	@Test
	public void test01_isEmpty_on_empty_tree() {
		expected = "true";
		actual = "" + tree.isEmpty();
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
	}

	/**
	 * Test inAscendingOrder() on an empty tree. Test fails if 
	 * the string returned is not empty.
	 */
	@Test
	public void test02_ascending_order_on_empty_tree() {
		expected = "";
		actual = tree.inAscendingOrder();
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
	}

	/** tests that the height of an empty tree is 0 */
	@Test
	public void test03_height_on_empty_tree() {
		expected = "0";
		actual = "" + tree.height();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}

	
	/**
	 * Test isEmpty() on a one-node-tree. Test fails if the returned 
	 * value is not 1.
	 */
	@Test
	public void test04_isEmpty_after_one_insert() {
		tree.insert("1");
		expected = "false";
		actual = "" + tree.isEmpty();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}

	@Test
	/** tests that the ascending order after inserting A item is "A," 
	 *  Test fails if it does not return "A,"
	 */
	public void test05_ascending_order_after_one_insert() {
		tree.insert("A");
		expected = "A,";
		actual = tree.inAscendingOrder();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}

	@Test
	/** tests that the height after inserting A is 1. Test 
	 *  Test fails if it does not return 1.
	 */
	public void test06_height_after_one_insert() {
		tree.insert("A");
		expected = "1";
		actual = "" + tree.height();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}

	@Test
	/** tests that the height after inserting A and deleting it is 0 */
	public void test07_height_after_one_insert_and_delete() {
		tree.insert("A");
		tree.delete("A");
		expected = "0";
		actual = "" + tree.height();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}
	
	@Test
	/**
	 * Test lookup("A") after we insert "A" into an empty tree.
	 * Test fails if it returns false.  
	 */
	public void test08_lookup_exised_after_one_insert() {
		tree.insert("A");
		expected = "true";
		actual = "" + tree.lookup("A");
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
	}
	
	@Test
	/**
	 * Test lookup("a") after we insert "A" into an empty tree.
	 * Test fails if it returns true.
	 */
	public void test09_lookup_nonexised_after_one_insert() {
		tree.insert("A");
		expected = "false";
		actual = "" + tree.lookup("a");
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
	}
	
	@Test
	/**
	 * Test height() method after 7 insertion. Test fails if it
	 * does not return 4.
	 */
	public void test10_height_after_many_inserts() {
		tree.insert("A");
		tree.insert("B");
		tree.insert("C"); // The first three test on left-rotation
		tree.insert("E");
		tree.insert("H");
		tree.insert("I");
		tree.insert("D");
		expected = "4";
		actual = "" + tree.height();
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
	}
	
	@Test
	/**
	 * Test height() method after 7 insertions and 1 deletion. Test fails 
	 * if it does not return 3.
	 */
	public void test10_height_after_many_inserts_and_one_delete() {
		tree.insert("A");
		tree.insert("B");
		tree.insert("C");
		tree.insert("E");
		tree.insert("H");
		tree.insert("I");
		tree.insert("D");
		tree.delete("B"); // test on delete the root
		expected = "3";
		actual = "" + tree.height();
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);

	}
	
	@Test
	/**
	 * Test heigth() method after 7 insertions and 3 deletions. Test fails 
	 * if it does not return 3.
	 * NOTE: This test may fail if we do not maintain balance after deletion.
	 */
	public void test10_height_after_many_inserts_and_some_delete() {
		tree.insert("A");
		tree.insert("B");
		tree.insert("C");
		tree.insert("E");
		tree.insert("H");
		tree.insert("I");
		tree.insert("D");
		tree.delete("B");
		tree.delete("I");
		tree.delete("E");
		expected = "3";
		actual = "" + tree.height();
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
	}
	
	@Test
	/**
	 * Test isEmpty on a tree with 7 nodes. Test fails if it does not 
	 * return false.
	 */
	public void test11_isEmpty_after_many_inserts() {
		tree.insert("A");
		tree.insert("B");
		tree.insert("C"); 
		tree.insert("E");
		tree.insert("H");
		tree.insert("I");
		tree.insert("D");
		expected = "false";
		actual = "" + tree.isEmpty();
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
	}
	
	@Test
	/**
	 * Test inAscendingOrder() on a tree with 7 nodes. Test fails 
	 * if it does not return "A,B,C,D,E,H,I,"
	 */
	public void test12_ascending_order_many_inserts() {
		tree.insert("A");
		tree.insert("B");
		tree.insert("C"); 
		tree.insert("E");
		tree.insert("H");
		tree.insert("I");
		tree.insert("D");
		expected = "A,B,C,D,E,H,I,";
		actual = "" + tree.inAscendingOrder();
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
	
	}
	
	@Test
	/**
	 * Test lookup("E") on a tree with 7 nodes, and "E" has been
	 * inserted. Test fails if it returns false.
	 */
	public void test13_lookup_existed_after_many_inserts() {
		tree.insert("A");
		tree.insert("B");
		tree.insert("C"); 
		tree.insert("E");
		tree.insert("H");
		tree.insert("I");
		tree.insert("D");
		expected = "true";
		actual = "" + tree.lookup("E");
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
	}
	@Test
	/**
	 * Test lookup("d") on a tree with 7 nodes, and "d" has not been
	 * inserted. Test fails if it returns true.
	 */
	public void test14_lookup_nonexisted_after_many_inserts() {
		tree.insert("A");
		tree.insert("B");
		tree.insert("C"); 
		tree.insert("E");
		tree.insert("H");
		tree.insert("I");
		tree.insert("D");
		expected = "false";
		actual = "" + tree.lookup("d");
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
	}
	
	@Test
	// FIXME: expected answer may have problem, depended on implementations
	/**
	 * Test tree's balance property on a tree after many insertions. 
	 * Test fails if the tree is too tall.
	 */
	public void test15_balance_or_not_after_inserts() {
		tree.insert("A");
		tree.insert("B");
		tree.insert("C"); 
		tree.insert("E");
		tree.insert("H");
		tree.insert("I");
		tree.insert("D");
		tree.insert("F");
		tree.insert("M");
		tree.insert("N");
		tree.insert("Y");
		expected = "4";
		actual = "" + tree.height();
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
	}

	@Test
	// FIXME: expected answer may have problem, depended on implementations
	/**
	 * Test tree's balance property on a tree after many insertions and deletions.
	 * Test fails if the tree is too tall.
	 */
	public void test16_balance_or_not_after_inserts_and_deletes() {
		tree.insert("A");
		tree.insert("B");
		tree.insert("C"); 
		tree.insert("E");
		tree.insert("H");
		tree.insert("I");
		tree.insert("D");
		tree.insert("F");
		tree.insert("M");
		tree.insert("N");
		tree.insert("Y");
		tree.delete("A");
		tree.delete("B");
		tree.delete("Y");
		tree.delete("M");
		tree.delete("N");
		tree.delete("E");
		expected = "3";
		actual = "" + tree.height();
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
	}
	
	/**
	 * Test duplicates in a tree. Test fails if it does not
	 * throw DuplicateKeyException.
	 */
	@Test (expected = DuplicateKeyException.class)
	public void test17_duplicates_allowed_or_not() {
		tree.insert("A");
		tree.insert("A");
	}
	
	/**
	 * Insert a null item to the tree. Test fails if it does
	 * not throw IllegalArgumentException.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void test18_insert_nullItem_to_Tree() {
		tree.insert(null);
	}
	
	/**
	 * Look up a null item to the tree. Test fails if it does
	 * not throw IllegalArgumentException.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void test19_lookup_nullItem() {
		tree.lookup(null);
	}
	
	/**
	 * Delete a null item to the tree. Test fails if it does
	 * not throw IllegalArgumentException.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void test20_delete_nullItem() {
		tree.delete(null);
	}
	
	/**
	 * Insert 26 alphabets into the tree. Test fails if it does
	 * not return the correct order of alphabets.
	 */
	@Test
	public void test21_ascending_order_large_insertings() {
		expected = "";
		for (char c = 'A'; c <= 'Z'; c++) {
			tree.insert(String.valueOf(c));
			expected += String.valueOf(c) + ",";
		}
		actual = tree.inAscendingOrder();
		if (!expected.equals(actual)) {
			fail("expected: " + expected + " actual: " + actual);
		}
		
	}
	
	/**
	 * Insert 26 alphabets into the tree. Test fails if it does 
	 * not return the height of 5.
	 */
	@Test
	public void test22_height_large_inserts() {
		for (char c = 'A'; c <= 'Z'; c++) {
			tree.insert(String.valueOf(c));
		}
		expected = "5";
		actual = String.valueOf(tree.height());
		
		if (!expected.equals(actual)) {
			fail("expected: " + expected + " actual: " + actual);
		}
	}

}
