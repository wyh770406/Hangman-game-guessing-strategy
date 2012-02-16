/* HangmanApp.java */
/**
 * This class is the main class of the Hangman Game Application.
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.LinkedList;

public class HangmanApp {

        public static void main(String arg[]) {
               String validWords = "words.txt"; // the file from which obtain list of valid words 
	       int maxWrongGuesses = 5; // maximum number of wrong guesses . Defaults to 5.
	       String wordsToScore = "score.txt"; // the file from which obtain list of words to score 	

	       int lookAhead = 1;   // number of guesses to look ahead . Defaults to 1.
	
	       int randomSeed = 0;  // the random seed generator . Defaults to 0.	
	       int randomTestSize = 100; // number of random words selected from valid words file for test. Defaults to 100.
               
               randomWordsScore(validWords, maxWrongGuesses, lookAhead, randomSeed, randomTestSize);
       }


         // running the guessing strategy on a random set of words
       private static void randomWordsScore(String validWords, int maxWrongGuesses, int lookAhead, int randomSeed, int randomTestSize){

		ArrayList<String> lvalidWords = FileReader.readLines(validWords);		
		List<String> lwordsToScore = new LinkedList<String>();
		
		Random randomGenerator = new Random(randomSeed);
		final int numOfWords = lvalidWords.size();			

		for (int gameNumber = 1; gameNumber <= randomTestSize; gameNumber++) {
			int randomIndex = randomGenerator.nextInt(numOfWords);
			String secretWord = lvalidWords.get(randomIndex);
			lwordsToScore.add(secretWord);
		}

                List<char[]> lvalidWords2 = FileReader.readLinesAsArrays(validWords);

                output(lwordsToScore,maxWrongGuesses,lookAhead,lvalidWords2);
       }


         // running the guessing strategy on a fixed set of words
       private static void fixedWordsScore(String validWords, int maxWrongGuesses, int lookAhead, String wordsToScore){

               List<String> lwordsToScore = FileReader.readLines(wordsToScore);

	       List<char[]> lvalidWords = FileReader.readLinesAsArrays(validWords);

               output(lwordsToScore,maxWrongGuesses,lookAhead,lvalidWords);

       }


        //output the result
       private static void output(List<String> lwordsToScore, int maxWrongGuesses, int lookAhead, List<char[]> lvalidWords){
	       int totalScore = 0;				

               GuessingStrategy strategy = new EntropyGuessingStrategy(lvalidWords, lookAhead);	

               long startTime = Timer.startTime();

		
		for (String secretWord : lwordsToScore) {
			
			HangmanGame game = new HangmanGame(secretWord, maxWrongGuesses);

			int score = run(game,strategy);
                        //int score = game.score2(strategy);
			
			System.out.printf(" secret word = %-30s score = %2d",
					secretWord,
					score);
			System.out.println();

			totalScore += score;
		}
		System.out.println();
		System.out.printf("total score = %5d", totalScore);
		System.out.println();
	
	    System.out.printf("scoring " + Timer.elapsedTime(startTime));
	    System.out.println();
       }

        // run the guessing strategy on a game
       private static int run(HangmanGame game, GuessingStrategy strategy) {

	  char rightMostVowel = StringOps.rightMostVowel(game.getSecretWord());
	  Guess firstGuess = new GuessLetter(rightMostVowel);
	  firstGuess.makeGuess(game);
	  
	  while (game.gameStatus() == HangmanGame.Status.KEEP_GUESSING) {
		  Guess guess = strategy.nextGuess(game);
		  guess.makeGuess(game);
	  }

          return game.score();
       }
}
