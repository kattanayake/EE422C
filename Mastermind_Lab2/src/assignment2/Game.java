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
		
		while(keepPlaying) {
			int numTurns = GameConfiguration.guessNumber;
			String secret_code = makeSecretCode();
			String guess = "";
			Decoder decode = new Decoder(secret_code, guess);
			while(!win && numTurns > 0) {
				System.out.println("You have " + numTurns + " guess(es) left.");
				win = askForGuess(decode, scan, secret_code);
				numTurns--;
				if(numTurns == 0 || win) terminationMessage(win);
			}			
			keepPlaying = toContinue(scan);			
		}
	}
	
	private String makeSecretCode() {
		SecretCodeGenerator code = SecretCodeGenerator.getInstance();
		String secret_code = code.getNewSecretCode();
		System.out.println("Secret code: "+ secret_code);
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
	
	private boolean askForGuess(Decoder decode, Scanner scan, String secret_code) {
		boolean win;
		System.out.println("Enter your guess: ");
		String guess = scan.next();
		decode.setNewGuess(guess);
		checkInputs(guess);
		win = decode.makeClue();
		if(win) return true;
		System.out.println(guess + " -> " + decode.getBlack() + "b_" + decode.getWhite() + "w");
		System.out.println();
		return win;
	}
	
	public void checkInputs(String guess) {
		if(guess.length() > GameConfiguration.pegNumber || !guess.equals(guess.toUpperCase())) {
			System.out.println("INVALID_GUESS");
			System.out.println();			
		}
	}
	
	private void terminationMessage(boolean win) {
		if(win) {
			System.out.println("You win!");
		}
		else {
			System.out.println("You lose!");
		}		
	}
}