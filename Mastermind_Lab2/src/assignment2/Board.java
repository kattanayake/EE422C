package assignment2;

import java.util.*;

public class Board {
	ArrayList<String> list;
	
	public Board() {
		list = new ArrayList<String>();
	}
	
	public void addToHistory(String add) {
		list.add(add);
	}
	
	public void printHistory() {
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println();
	}	
}
