package assignment2;

import java.util.*;

public class Decoder {
	private String guess;
	private String secret;
	private int white;
	private int black;
	
	public Decoder(String secret, String guess) {
		this.secret = secret;
		this.guess = guess;
		this.white = 0;
		this.black = 0;
	}
	
	public void setNewGuess(String guess) {
		this.guess = guess;
	}
	
	public boolean makeClue() {
		HashMap<Character, Integer> sec = new HashMap<Character, Integer>();
		HashMap<Character,Integer> gues = new HashMap<Character, Integer>();
		
		reset();
		
		if(getNumBlacks()) return true;	
		
		for(int k = 0; k < secret.length(); k++) {
			if(secret.charAt(k) != guess.charAt(k)) {
				if(sec.containsKey(secret.charAt(k))) {
					sec.put(secret.charAt(k), sec.get(secret.charAt(k)) + 1);
				}
				else {
					sec.put(secret.charAt(k), 1);
				}
			}
		}
		
		for(int j = 0; j < guess.length(); j++) {
			if(guess.charAt(j) != secret.charAt(j)) {
				if(gues.containsKey(guess.charAt(j))) {
					gues.put(guess.charAt(j), gues.get(guess.charAt(j)) + 1);
				}
				else {
					gues.put(guess.charAt(j), 1);
				}
			}
		}
		
		for(Character key: sec.keySet()) {
			if(gues.containsKey(key)) {
				if(gues.get(key) >= sec.get(key)) {
					this.white += sec.get(key);
				}
				else {
					this.white += gues.get(key);
				}
			}
		}
		return false;
	}
	
	private boolean getNumBlacks() {
		for(int i = 0; i < secret.length(); i++) {
			if(secret.charAt(i) == guess.charAt(i)) {
				this.black++;
			}			
		}
		
		if(black == secret.length()) return true;
		return false;		
	}
	
	public int getBlack() {
		return this.black;
	}
	
	public int getWhite() {
		return this.white;
	}
	
	private void reset() {
		this.black = 0;
		this.white = 0;
	}
}
