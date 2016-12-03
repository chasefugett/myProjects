/** Name: Chase Fugett
 * Date: 11/22/15
 * Deals several poker hands, classifies each hand, and presents a summary of
 * the results with the time it took to analyze the hands
 */

import java.util.Scanner;
import java.util.Arrays;

public class PokerStats {

   public static void main(String[] args) {

      // Ask user for number of poker hands to deal
      System.out.print("How many poker hands should I deal? ");
      Scanner in = new Scanner(System.in);
      int n = in.nextInt();

      playAndShowStats(n);

   }

   // Prints poker hands that user is playing
    public static void printHand(Card[] cards) {
      for (int i = 0; i < cards.length; i++) {
         System.out.println(cards[i]);
      }
   }


   // Returns true if the hand has a flush
   public static boolean hasFlush(Card[] hand) {
      for (int i = 0; i < hand.length - 1; i++) {
         int value1 = hand[i].getSuit();
         int value2 = hand[i + 1].getSuit();
         if (value1 != value2) {
            return false;
         }
      }
      return true;
   }

   // Return true if the hand is a straight
   public static boolean hasStraight(Card[] hand) {
      for (int i = 0; i < hand.length - 1; i++) {
         int value1 = hand[i].getValue();
         int value2 = hand[i + 1].getValue();
         if (value1 != value2 - 1) {
            return false;
         }
      }
      return true;
   }

   // Return true if the hand is a straight flush
   public static boolean hasStraightFlush(Card[] hand) {
      for (int i = 0; i < hand.length - 1; i++) {
         int value1 = hand[i].getValue();
         int value2 = hand[i + 1].getValue();
         int suit1 = hand[i].getSuit();
         int suit2 = hand[i + 1].getSuit();
         if (value1 != value2 - 1) {
            return false;
         }
         if (suit1 != suit2) {
            return false;
         }
      }
      return true;
   }

   // Return true if the hand is a royal flush
   public static boolean hasRoyalFlush(Card[] hand) {
      if (hand[0].getValue() == 1) {
         if (hand[1].getValue() == 10) {
            if (hand[2].getValue() == 11) {
               if (hand[3].getValue() == 12) {
                  if (hand[4].getValue() == 13) {
                     if (hasFlush(hand)) {
                        return true;
                     }
                  }
               }
            }
         }
      }
      return false;
   }

   // Returns true if the hand is a four of a kind
   public static boolean has4OfAKind(Card[] hand) {
      if (hand[0].getValue() == hand[1].getValue()) {
         if (hand[1].getValue() == hand[2].getValue()) {
            if (hand[2].getValue() == hand[3].getValue()) {
               return true;
            }
         }
      }
     if (hand[1].getValue() == hand[2].getValue()) {
         if (hand[2].getValue() == hand[3].getValue()) {
            if (hand[3].getValue() == hand[4].getValue()) {
               return true;
            }
         }
      }
     return false;
   }

   // Returns true if the hand is a three of a kind
   public static boolean has3OfAKind(Card[] hand) {
      for (int i = 0; i < hand.length - 2; i++) {
         int value1 = hand[i].getValue();
         int value2 = hand[i + 1].getValue();
         int value3 = hand[i + 2].getValue();
         if (value1 == value2) {
            if (value2 == value3) {
               return true;
            }
         }
      }
      return false;
   }

   // Returns true if the hand has at least one pair
   public static boolean hasPair(Card[] hand) {
      for (int i = 0; i < hand.length - 1; i++) {
         int value1 = hand[i].getValue();
         int value2 = hand[i + 1].getValue();
         if (value1 == value2) {
               return true;
         }
      }
      return false;
   }

   // Returns true if the hand has 2 pairs
   public static boolean has2Pair(Card[] hand) {
      int numPairs = 0;
      for (int i = 0; i < hand.length - 1; i++) {
         int value1 = hand[i].getValue();
         int value2 = hand[i + 1].getValue();
         if (value1 == value2) {
               numPairs++;
         }
      }
      if (numPairs == 2) {
         return true;
      }
      else {
      return false;
      }
   }

   // Returns true if the hand is a full house
   public static boolean hasFullHouse(Card[] hand) {
         int value1 = hand[0].getValue();
         int value2 = hand[1].getValue();
         int value3 = hand[2].getValue();
         int value4 = hand[3].getValue();
         int value5 = hand[4].getValue();
         if (value1 == value2 && value2 == value3) {
            if (value4 == value5) {
               return true;
            }
         }
         if (value1 == value2) {
            if (value3 == value4 && value4 == value5) {
               return true;
            }
         }
      return false;
   }

   // Creates a deck of cards, shuffles them, deals 5 cards to a "hand",
   // sorts the cards, then checks and returns the type of hand dealt
   public static String evaluateOneHandOfPoker() {
      // Creates the deck and shuffles it
      Deck myDeck = new Deck();
      myDeck.shuffle();

      // Creates hand and deals it
      Card[] cards = new Card[5];
      for (int i = 0; i < cards.length; i++) {
         cards[i] = myDeck.deal();
      }

      // Sorts the hand
      Arrays.sort(cards);

      // Finds the highest hand and immediately returns it
      if (hasRoyalFlush(cards)) {
         return "Royal Flush";
      }
      else if (hasStraightFlush(cards)) {
         return "Straight Flush";
      }
      else if (has4OfAKind(cards)) {
         return "Four of a Kind";
      }
      if (hasFullHouse(cards)) {
         return "Full House";
      }
      else if (hasFlush(cards)) {
         return "Flush";
      }
      else if (hasStraight(cards)) {
         return "Straight";
      }
      else if (has3OfAKind(cards)) {
         return "Three of a Kind";
      }
      else if (has2Pair(cards)) {
         return "Two Pair";
      }
      else if (hasPair(cards)) {
         return "One Pair";
      }
      else {
         return "Loser";
      }
   }

   // Plays n hands of poker and then gathers and displays the statistics
   public static void playAndShowStats (int n) {
      // Starts counting the computation time
      double startTime = System.currentTimeMillis();

      // Declaring the counters for the types of hands
      int loser = 0;
      int onePair = 0;
      int twoPair = 0;
      int threeKind = 0;
      int straight = 0;
      int flush = 0;
      int fullHouse = 0;
      int fourKind = 0;
      int straightFlush = 0;
      int royalFlush = 0;

      // Counting the number of each type of hand n times
      for (int i = 0; i < n; i++) {
         String singleHand = evaluateOneHandOfPoker();
         if (singleHand.equals("Loser")) {
            loser++;
         }
         if (singleHand.equals("One Pair")) {
            onePair++;
         }
         if (singleHand.equals("Two Pair")) {
            twoPair++;
         }
         if (singleHand.equals("Three of a Kind")) {
            threeKind++;
         }
         if (singleHand.equals("Straight")) {
            straight++;
         }
         if (singleHand.equals("Flush")) {
            flush++;
         }
         if (singleHand.equals("Full House")) {
            fullHouse++;
         }
         if (singleHand.equals("Four of a Kind")) {
            fourKind++;
         }
         if (singleHand.equals("Straight Flush")) {
            straightFlush++;
         }
         if (singleHand.equals("Royal Flush")) {
            royalFlush++;
         }
      }

      // Finding the percentage occurance of each type of hand
      double perLoser = ((loser + 0.0) / n) * 100;
      double perOnePair = ((onePair + 0.0) / n) * 100;
      double perTwoPair = ((twoPair + 0.0) / n) * 100;
      double perThreeKind = ((threeKind + 0.0) / n) * 100;
      double perStraight = ((straight + 0.0) / n) * 100;
      double perFlush = ((flush + 0.0) / n) * 100;
      double perFullHouse = ((fullHouse + 0.0) / n) * 100;
      double perFourKind = ((fourKind + 0.0) / n) * 100;
      double perStraightFlush = ((straightFlush + 0.0) / n) * 100;
      double perRoyalFlush = ((royalFlush + 0.0) / n) * 100;

      // Printing out the number of hand types and the percentage
      // of the time they occured
      System.out.printf("                      Loser: %7d %11.5f%%\n", loser, perLoser);
      System.out.printf("                   One Pair: %7d %11.5f%%\n", onePair, perOnePair);
      System.out.printf("                   Two Pair: %7d %11.5f%%\n", twoPair, perTwoPair);
      System.out.printf("            Three of a Kind: %7d %11.5f%%\n", threeKind, perThreeKind);
      System.out.printf("                   Straight: %7d %11.5f%%\n", straight, perStraight);
      System.out.printf("                      Flush: %7d %11.5f%%\n", flush, perFlush);
      System.out.printf("                 Full House: %7d %11.5f%%\n", fullHouse, perFullHouse);
      System.out.printf("             Four of a Kind: %7d %11.5f%%\n", fourKind, perFourKind);
      System.out.printf("             Straight Flush: %7d %11.5f%%\n", straightFlush, perStraightFlush);
      System.out.printf("                Royal Flush: %7d %11.5f%%\n", royalFlush, perRoyalFlush);

      System.out.print("--------------------------------------------");
      System.out.println("------------------------");

      // Ends counting the computation time and finds the difference
      double endTime = System.currentTimeMillis();
      double difference = endTime - startTime;
      difference = difference / 1000.0;
      System.out.print(n + " hands analyzed in ");
      System.out.printf("%.3f", difference);
      System.out.println(" seconds.");

   }
}
