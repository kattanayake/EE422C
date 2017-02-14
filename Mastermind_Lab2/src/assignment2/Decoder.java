package assignment2;

import java.util.*;

public class Decoder {
	private String guess;
	private String secret;
	private int white;
	private int black;
	
	/**
	 * Constructor for the Decoder class that initializes the secret code,
	 * the user guess, and the number of black and white pegs
	 * @param secret The secret code generated by the program
	 * @param guess The guess the user enters into the program
	 */
	public Decoder(String secret, String guess) {
		this.secret = secret;
		this.guess = guess;
		this.white = 0;
		this.black = 0;
	}
	
	/**
	 * Updates the guess instance variable when a new guess in entered
	 * @param guess The new guess the user entered
	 */
	public void setNewGuess(String guess) {
		this.guess = guess;
	}
	
	/**
	 * Compares the user guess to the secret code to compute the number
	 * of black and white pegs to be returned. Uses two HashMaps to store the
	 * number of each color present in the secret and guess and then systematically 
	 * counts the number of black and white pegs.
	 * @return true if the user has guessed correctly and wins
	 *         false if the user has errors in his guess
	 */
	public boolean makeClue() {
		HashMap<Character, Integer> sec = new HashMap<Character, Integer>();
		HashMap<Character,Integer> gues = new HashMap<Character, Integer>();
		
		reset();
		
		/**
		 * Gets the number of black pegs before counting the number of whites
		 * If the number of blacks is the length of the secret, then the user wins
		 * and return true.
		 */
		if(getNumBlacks()) return true;	
		
		/**
		 * Maps each character from secret into a HashMap and stores the number of
		 * each character present
		 */
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
		
		/**
		 * Maps each character from guess into a HashMap and stores the number of
		 * each character present
		 */
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
		
		/**
		 * Traverses the secret HashMap and compares the number of characters for 
		 * each key to that of the guess HashMap. It then finds the minimum of these 
		 * two numbers and adds that minimum to the white peg count.
		 */
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
	
	/**
	 * Finds the number of black pegs by comparing the guess and secret 
	 * and determining the number of matches on position AND color
	 * @return true if the number of black pegs is the length of secret
	 *         false if the number of black pegs is not the length of secret
	 */
	private boolean getNumBlacks() {
		for(int i = 0; i < secret.length(); i++) {
			if(secret.charAt(i) == guess.charAt(i)) {
				this.black++;
			}			
		}
		
		if(black == secret.length()) return true;
		return false;		
	}
	
	/**
	 * Gets the number of black pegs
	 * @return this.black the number of black pegs
	 */
	public int getBlack() {
		return this.black;
	}
	
	/**
	 * Gets the number of white pegs
	 * @return this.black the number of white pegs
	 */
	public int getWhite() {
		return this.white;
	}
	
	/**
	 * Resets the number of black and white pegs at every guess
	 * in order to avoid errors in output
	 */
	private void reset() {
		this.black = 0;
		this.white = 0;
	}
}
