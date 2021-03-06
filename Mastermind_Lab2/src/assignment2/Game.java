package assignment2;

import java.util.*;
import java.io.*;

public class Game {
	boolean isTesting;
	
	/**
	 * Constructor for the Game that determines if the code
	 * is being played or tested
	 * @param isTesting
	 */
	public Game(boolean isTesting) {
		this.isTesting = isTesting;
	}
	
	/**
	 * Main function of the project that starts the game and asks the user
	 * if he wishes to play a game. Continues if he says Y (Yes) and any other 
	 * input will terminate the program. 
	 */
	public void runGame() {
		System.out.println("Do you want to play a new game? (Y/N):");
		Scanner scan = new Scanner(System.in);
		String start = scan.next();
		if(start.equals("Y")) {
			startGame(scan);
		}		
	}
	
	/**
	 * Main engine of the game that keeps the game playing as long as the user wants to play
	 * and also tracks the status of each individual game to determine if and why it should 
	 * terminate. 
	 * @param scan the Scanner object that allows the user to enter guesses
	 */
	private void startGame(Scanner scan) {
		
		boolean win = false;
		boolean keepPlaying = true;
		boolean notValid = true;
		
		/**
		 * Main loop of the game that keeps the game playing as long as the user keeps
		 * entering Y after each game terminates. Exits when, after a game, the user does
		 * not wish to continue.
		 */
		while(keepPlaying) {			
			/**
			 * Obtains the number of turns and initializes a new Board and 
			 * Decoder for the given iteration of the game.
			 */
			int numTurns = GameConfiguration.guessNumber;
			String secret_code = makeSecretCode();
			String guess = "";
			Decoder decode = new Decoder(secret_code, guess);
			Board historyBoard = new Board();
			
			/**
			 * This loop is incharge of tracking each game. The loop keeps running as long
			 * as the user has a given number of turns left or if he has not won yet 
			 * by guessing the correct code.
			 */
			while(!win && numTurns > 0) {
				/**
				 * Checks for valid user input by continually checking and asking for
				 * a guess if the user enters an incorrect format for guess.
				 */
				while(notValid) {
					guess = askForGuess(decode, scan, numTurns);
					
					/**
					 * If the user asks for HISTORY, will print the game board
					 * as it stands. If not, will process the input as a guess
					 */
					if(guess.equals("HISTORY")) {
						historyBoard.printHistory();
					}
					notValid = checkInputs(guess);				
				}
				
				/**
				 * Obtains the result from askForClue and determines the state of 
				 * the game after the entered guess
				 */
				win = askForClue(decode, guess, historyBoard);
				numTurns--;
				if(numTurns == 0 || win) terminationMessage(win, secret_code);
				notValid = true;
			}	
			
				keepPlaying = toContinue(scan);
				win = false;
			}
		}
	
	/**
	 * Creates a new secret code for every new game
	 * @return secret_code String that contains the new secret_code generated
	 */
	private String makeSecretCode() {
		SecretCodeGenerator code = SecretCodeGenerator.getInstance();
		String secret_code = code.getNewSecretCode();
		
		if(isTesting) System.out.println("Secret code: "+ secret_code);
		System.out.println();
		
		return secret_code;
	}
	
	/**
	 * Asks the user if the he wants to continue playing
	 * @param scan the Scanner that asks the user for input
	 * @return true if he wants to keep playing
	 * 			false if he doesn't want to keep playing
	 */
	private boolean toContinue(Scanner scan) {
		System.out.println();
		System.out.println("Do you want to play a new game? (Y/N):");
		
		String answer = scan.next();
		if(answer.equals("N")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Asks the user for a guess and sets the new guess as guess in Decoder object
	 * @param decode the Decoder object for the current game
	 * @param scan the Scanner that asks the user for input
	 * @param numTurns the number of turns the user has left after this guess
	 * @return a String that contains the guess entered
	 */
	private String askForGuess(Decoder decode, Scanner scan, int numTurns) {
		System.out.println("You have " + numTurns + " guess(es) left.");
		System.out.println("Enter guess:");
		
		String guess = scan.next();
		decode.setNewGuess(guess);
		
		return guess;
		
	}
	
	/**
	 * Asks the program for a clue based on the given guess
	 * @param decode the Decoder object for the current game
	 * @param scan the Scanner that asks the user for input
	 * @param historyBoard the Board of this current game
	 * @return win a boolean that has true if the guess was perfect
	 * 		   false if the guess was not perfect
	 */
	private boolean askForClue(Decoder decode, String guess, Board historyBoard) {
		boolean win;
		win = decode.makeClue();
		String summary = guess + " -> " + decode.getBlack() + "b_" + decode.getWhite() + "w";	
		/* Adds the guess and resulting clue to the Board object and checks for a win. */
		historyBoard.addToHistory(summary);	
		System.out.println(summary);
		
		if(!win) System.out.println();
		return win;

	}
	
	/**
	 * Checks the input to see if the entered guess was an incorrect length or if
	 * is has lowercase letters or illegal characters
	 * @param guess a String that contains the guess
	 * @return true if it is an invalid guess
	 * 		   false if it is a valid guess
	 */
	public boolean checkInputs(String guess) {
		if((guess.length() != GameConfiguration.pegNumber || 
				!guess.equals(guess.toUpperCase()))) {
			
			if(!guess.equals("HISTORY")) {
				System.out.println("INVALID_GUESS");
				System.out.println();	
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Prints a termination message when each game ends, based on the
	 * situation (if the numTurns expired or if the user won the game
	 * @param win that has whether the user won or no
	 * @param secret String that has the secret to be printed if he lost
	 */
	private void terminationMessage(boolean win, String secret) {
		if(win) {
			System.out.println("You win!");
		}
		else {
			System.out.println("You lose! The pattern was " + secret);
		}		
	}
}
