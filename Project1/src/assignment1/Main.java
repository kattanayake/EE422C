/* 
 * This file is how you might test out your code.  Don't submit this, and don't 
 * have a main method in SortTools.java.
 */

package assignment1;
import java.util.*; 

public class Main {
	public static void main(String [] args) {
		System.out.println("***TEST 4***");
		int[] arrayA = {1, 2, 3, 5, 19, 27, 39, 52};
		int[] arrayB = {-41, -20};
		int[] unsorted = {40, 28, 37, 1, 9, 67, 5, 10};
		int[] unsorted2 = {-90, 1000, 98, 57, 4, -10, -19, 8, 30, -34};
		
		System.out.println(SortTools.isSorted(arrayA, 5));
		System.out.println(SortTools.isSorted(arrayB, 2));
		
		System.out.println(SortTools.find(arrayB, arrayB.length, -20));
		
		int[] newArray = SortTools.insertGeneral(arrayA, 5, 20);
		System.out.println(Arrays.toString(newArray));
		
		System.out.println(SortTools.insertInPlace(arrayA, 5, 4));
		System.out.println(Arrays.toString(arrayA));
		
		System.out.println();
		
		SortTools.insertSort(unsorted, unsorted.length);
		System.out.println(Arrays.toString(unsorted));
	
		SortTools.insertSort(unsorted2, unsorted2.length);
		System.out.println(Arrays.toString(unsorted2));
	}
}
