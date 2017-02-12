// SortTools.java 
/*
 * EE422C Project 1 submission by
 * <Kausthub Poondi>
 * <kp26753>
 * <16215>
 * Spring 2017
 * Slip days used: 0
 */

package assignment1;

import java.util.Arrays;

public class SortTools {
	
	/**
	  * This method tests to see if the given array is sorted.
	  * @param x is the array
	  * @param n is the size of the input to be checked
	  * @return true if array is sorted
	  */
	public static boolean isSorted(int[] x, int n) {
		if(x.length == 0 || n == 0) return false;
		
		int prev = x[0];
		
		for(int i = 0; i < n; i++) {
			if(prev > x[i]) return false;
			prev = x[i];
		}
		
		return true;
	}	
	
	/**
	  * This method tests to see if the given array has element v.
	  * @param x is the array
	  * @param n is the size of the input to be checked
	  * @param v is the value to be found
	  * @return -1 if the value is not found and 
      *			index of v if v is found
	  */
	public static int find(int[] x, int n, int v) {
		int low = 0; 
		int high = n - 1;
		
		while (low <= high) {
			int mid = (low + high) / 2; 
			
			if (x[mid] == v) {
				return mid;
			}
			else if(x[mid] < v) {
				low = mid + 1;
			}
			else {
				high = mid - 1;
			}
		}
		
		return -1;
	}
	
	
	/**
	  * This method inserts an element v into a given array while maintaining
	  * non-decreasing order. Creates a new array and copies values from x.
	  * @param x is the array
	  * @param n is the size of the input to be checked
	  * @param v is the value to be inserted
	  * @return y a new array that has v in it or
	  * 		y a copy of the old array until index n - 1
	  */
	public static int[] insertGeneral(int[] x, int n, int v) {
		int[] y;
		if(find(x, n, v) == -1) { 		//Calls the find function to check for v
			y = new int[n + 1];
		}
		else {
			y = new int[n];
			
			for(int k = 0; k < n; k++) {
				y[k] = x[k];
			}
			
			return y;
		}
		
		int i = 0;
		
		/* Iterates through array x until the point of 
		 * insertion is found.
		 */
		while(i < n && x[i] < v) {
			y[i] = x[i];
			i++;
		}
						
		y[i] = v;
		
		i++;
		
		/* Finishes copying the contents of x into y 
		 * after v has been inserted.
		 */
		while(i < y.length) {
			y[i] = x[i - 1];
			i++;
		}
	
		return y;
		
	}
	
	
	/**
	  * This method inserts an element v into a given array while maintaining
	  * non-decreasing order (in-place).
	  * @param x is the array
	  * @param n is the size of the input to be checked
	  * @param v is the value to be inserted
	  * @return x the old array with v inserted
	  * 	
	  */	
	public static int insertInPlace(int[] x, int n, int v) {
		if(find(x, n, v) != -1) {
			return n;
		}
		
		int i = 0;
		
		/* Iterates through array x until the point of 
		 * insertion is found.
		 */
		while(i < n && x[i] < v) {
			i++;
		}

		int loc = i;
		
		/* Shifts all the values in x to
		 * make room for the v value.
		 * Shifting beings from backwards to forwards
		 */
		for(int k = n; k > i; k--) {
			x[k] = x[k - 1];
		}
		
		x[loc] = v;
		
		return n + 1;
		
	}
	
	
	/**
	  * This method sorts a given array in non-decreasing order.
	  * @param x is the array
	  * @param n is the size of the input to be checked
	  * @return void
	  * 	
	  */
	public static void insertSort(int[] x, int n) {
		/*Outer loop finds the index to be moved. */
		for(int ref = 1; ref < n; ref++) {
			int pivot = x[ref];
			int off = ref - 1;
			
			/* Compares pivot value and makes the swaps necessary. */
			while(off > -1  &&  pivot < x[off]) {
				x[off + 1] = x[off];
				off--;
			}
			
			/* Places the pivot value in the correct place. */
			x[off + 1] = pivot;
					
		}
	}
}
