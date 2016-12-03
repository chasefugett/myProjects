/**
 * Chase Fugett
 * Simulate as many games of craps the user enters, whether they win or lose
 * the game, and the win percentage
 */

import java.util.Scanner;

public class CrapsGame {

   public static void main(String[] args) {

      System.out.print("How many many rounds of craps do you want to play? ");
      Scanner input = new Scanner(System.in);
      int rounds = input.nextInt();

      // Checking for a positive number
      while (rounds < 1) {
         System.out.print("Enter a positive number please: ");
         rounds = input.nextInt();
      }

      // Displaying the results of each round and creating counter
      int numWins = 0;
      for (int i = 1; i <= rounds; i++) {
         System.out.print("Round #" + i + ": ");
         boolean winner = craps();
         if (winner) {
            System.out.println("(win)");
            numWins++;
         }
         else {
            System.out.println("(lose)");
         }

      }

      // Displaying the win percentage
      System.out.println("------------------------------");
      System.out.print("Wins: " + numWins + " out of " + rounds);
      double winPercentage = ((double) numWins / rounds) * 100;
      System.out.print(" (");
      System.out.printf("%.1f", winPercentage);
      System.out.print("%)");
   }

   // Simulates the roll of two dice and adds their sum together
   public static int rollDice () {
    int die1 = (int) (Math.random() * 6) + 1;
    int die2 = (int) (Math.random() * 6) + 1;
    return die1 + die2;
   }

   // Simulates one round of craps, prints the sums that are rolled,
   // and returns a boolean that indicates whether each round
   // was won or lost
   public static boolean craps() {
      int firstRoll = rollDice();
      int nextRoll = 0;

      // For the first roll with automatic wins or losses
      if (firstRoll == 7 || firstRoll == 11) {
         System.out.print(firstRoll + " ");
         return true;
      }
      else if (firstRoll == 2 || firstRoll == 3 || firstRoll == 12) {
         System.out.print(firstRoll + " ");
         return false;
      }

      // For the second roll on until a win or loss is found
      else {
         System.out.print(firstRoll + " ");
         while (firstRoll != nextRoll) {
            nextRoll = rollDice();
            System.out.print(nextRoll + " ");

            // A 7 is a loss at this point in the game
            if (nextRoll == 7) {
               return false;
            }
         }
         // firstRoll and nextRoll now are equal, which means we have a win
         return true;
      }
   }
}
