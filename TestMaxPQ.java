/**
 * P1 Priority Queue
 * @author Brian Tang, bjtang2@wisc.edu
 * Due Feb 5
 * Files used: EmptyQueueException.java, PriorityQueueADT.java, MaxPQ.java, TestMaxPQ.java, results.txt
 * 
 * Possible bugs: Console log reader may have an issue if results.txt does not exist. size() method is
 * variable, meaning if an insert or remove is made to the queue, the value of size() also updates.
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * @author Brian Tang
 *
 * Priority Queue tester class with a total of 12 different tests.
 * The tests check for method functionality.
 */
public class TestMaxPQ {

	/**
	 * Main method that tests the priority queue.
	 */
	public static void main(String[] args) {
		int boardDim = 10;
		int xPos = (int) (Math.random() * boardDim);
		System.out.println(xPos);
		int yPos = (int) (Math.random() * boardDim);
		System.out.println(yPos);
	}

}
