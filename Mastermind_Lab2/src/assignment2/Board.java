package assignment2;

import java.util.*;

public class Board {
	ArrayList<String> list;
	
	/**
	 * Constructor for the board that creates an array list
	 * to store all the game history
	 */
	public Board() {
		list = new ArrayList<String>();
	}
	
	/**
	 * Adds a String to the ArrayList that contains the most 
	 * recent guess and the resulting output
	 * @param add A String that contains the most recent guess and clue
	 */
	public void addToHistory(String add) {
		list.add(add);
	}
	
	/**
	 * Called when HISTORY is entered. Prints the entire game board
	 * until that point in the game
	 */
	public void printHistory() {
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}		
		System.out.println();
	}	
}
