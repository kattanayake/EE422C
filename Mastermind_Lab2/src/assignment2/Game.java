package assignment2;

import java.util.*;
import java.io.*;

public class Game {
	boolean isTesting;
	
	public Game(boolean isTesting) {
		this.isTesting = isTesting;
	}
	
	public void runGame() {
		System.out.println("Do you want to play a new game? (Y/N) :");
		Scanner scan = new Scanner(System.in);
		String start = scan.next();
		if(start.equals("Y")) {
			startGame(scan);
		}		
	}
	
	private void startGame(Scanner scan) {
		
		boolean win = false;
		boolean keepPlaying = true;
		boolean notValid = true;
		
		while(keepPlaying) {
			int numTurns = GameConfiguration.guessNumber;
			String secret_code = makeSecretCode();
			String guess = "";
			Decoder decode = new Decoder(secret_code, guess);
			Board historyBoard = new Board();
			
			while(!win && numTurns > 0) {
				while(notValid) {
					guess = askForGuess(decode, scan, numTurns);
					if(guess.equals("HISTORY")) {
						historyBoard.printHistory();
					}
					notValid = checkInputs(guess);				
				}
				win = askForClue(decode, guess, historyBoard);
				numTurns--;
				if(numTurns == 0 || win) terminationMessage(win, secret_code);
				notValid = true;
			}			
				keepPlaying = toContinue(scan);
				win = false;
			}
		}
	
	private String makeSecretCode() {
		SecretCodeGenerator code = SecretCodeGenerator.getInstance();
		String secret_code = code.getNewSecretCode();
		if(isTesting) System.out.println("Secret code: "+ secret_code);
		System.out.println();
		return secret_code;
	}
	
	private boolean toContinue(Scanner scan) {
		System.out.println();
		System.out.println("Do you want to play a new game? (Y/N) :");
		String answer = scan.next();
		if(answer.equals("N")) {
			return false;
		}
		return true;
	}
	
	private String askForGuess(Decoder decode, Scanner scan, int numTurns) {
		System.out.println("You have " + numTurns + " guess(es) left.");
		System.out.println("Enter your guess: ");
		String guess = scan.next();
		decode.setNewGuess(guess);
		return guess;
		
	}
	
	private boolean askForClue(Decoder decode, String guess, Board historyBoard) {
		boolean win;
		win = decode.makeClue();
		String summary = guess + " -> " + decode.getBlack() + "b_" + decode.getWhite() + "w";
		historyBoard.addToHistory(summary);
		System.out.println(summary);
		if(!win) System.out.println();
		return win;

	}
	
	public boolean checkInputs(String guess) {
		if((guess.length() != GameConfiguration.pegNumber || !guess.equals(guess.toUpperCase()))) {
			if(!guess.equals("HISTORY")) {
				System.out.println("INVALID_GUESS");
				System.out.println();	
			}
			return true;
		}
		return false;
	}
	
	private void terminationMessage(boolean win, String secret) {
		if(win) {
			System.out.println("You win!");
		}
		else {
			System.out.println("You lose! The patter was " + secret);
		}		
	}
}
