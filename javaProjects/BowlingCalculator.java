/**
 * Chase Fugett
 * Computes the score for a bowling game and displays the score with the number
 * of strikes and spares
 */

import java.util.Scanner;

public class BowlingCalculator {

   public static void main(String[] args) {

      Scanner in = new Scanner (System.in);

      // Declaring strike and spare variables for use later
      int frame1spare, frame2spare, frame3spare, frame4spare, frame5spare,
      frame6spare, frame6spare, frame7spare, frame8spare, frame9spare,
      frame10spare, frame1strike, frame2strike, frame3strike, frame4strike,
      frame5strike, frame6strike, frame7strike, frame8strike, frame9strike,
      frame10strike;

      // Entering the scores
      ////////  Frame 1  ////////
      int frame1Ball2 = 0;
      System.out.print("Frame 1 -- Ball 1: ");
      int frame1Ball1 = in.nextInt();
      if (frame1Ball1 < 10) {
         System.out.print("        -- Ball 2: ");
         frame1Ball2 = in.nextInt();
      }
      else {
         frame1Ball2 = 0;
      }

      ////////  Frame 2  ////////
      int frame2Ball2 = 0;
      System.out.print("Frame 2 -- Ball 1: ");
      int frame2Ball1 = in.nextInt();
      if (frame2Ball1 < 10) {
         System.out.print("        -- Ball 2: ");
         frame2Ball2 = in.nextInt();
      }
      else {
         frame2Ball2 = 0;
      }

      ////////  Frame 3  ////////
      int frame3Ball2 = 0;
      System.out.print("Frame 3 -- Ball 1: ");
      int frame3Ball1 = in.nextInt();
      if (frame3Ball1 < 10) {
         System.out.print("        -- Ball 2: ");
         frame3Ball2 = in.nextInt();
      }
      else {
         frame3Ball2 = 0;
      }

      ////////  Frame 4  ////////
      int frame4Ball2 = 0;
      System.out.print("Frame 4 -- Ball 1: ");
      int frame4Ball1 = in.nextInt();
      if (frame4Ball1 < 10) {
         System.out.print("        -- Ball 2: ");
         frame4Ball2 = in.nextInt();
      }
      else {
         frame4Ball2 = 0;
      }

      ////////  Frame 5  ////////
      int frame5Ball2 = 0;
      System.out.print("Frame 5 -- Ball 1: ");
      int frame5Ball1 = in.nextInt();
      if (frame5Ball1 < 10) {
         System.out.print("        -- Ball 2: ");
         frame5Ball2 = in.nextInt();
      }
      else {
         frame5Ball2 = 0;
      }

      ////////  Frame 6  ////////
      int frame6Ball2 = 0;
      System.out.print("Frame 6 -- Ball 1: ");
      int frame6Ball1 = in.nextInt();
      if (frame6Ball1 < 10) {
         System.out.print("        -- Ball 2: ");
         frame6Ball2 = in.nextInt();
      }
      else {
         frame6Ball2 = 0;
      }

      ////////  Frame 7  ////////
      int frame7Ball2 = 0;
      System.out.print("Frame 7 -- Ball 1: ");
      int frame7Ball1 = in.nextInt();
      if (frame7Ball1 < 10) {
         System.out.print("        -- Ball 2: ");
         frame7Ball2 = in.nextInt();
      }
      else {
         frame7Ball2 = 0;
      }

      ////////  Frame 8  ////////
      int frame8Ball2 = 0;
      System.out.print("Frame 8 -- Ball 1: ");
      int frame8Ball1 = in.nextInt();
      if (frame8Ball1 < 10) {
         System.out.print("        -- Ball 2: ");
         frame8Ball2 = in.nextInt();
      }
      else {
         frame8Ball2 = 0;
      }

      ////////  Frame 9  ////////
      int frame9Ball2 = 0;
      System.out.print("Frame 9 -- Ball 1: ");
      int frame9Ball1 = in.nextInt();
      if (frame9Ball1 < 10) {
         System.out.print("        -- Ball 2: ");
         frame9Ball2 = in.nextInt();
      }
      else {
         frame9Ball2 = 0;
      }

      // Note: from here out, strikes and spares are calculated along with
      //       the scores

      ////////  Frame 10  ////////
      int frame10Ball2 = 0;
      int frame10ExtraBall1 = 0;
      int frame10ExtraBall2 = 0;
      System.out.print("Frame 10 -- Ball 1: ");
      int frame10Ball1 = in.nextInt();
      if (frame10Ball1 < 10) {
         System.out.print("         -- Ball 2: ");
         frame10Ball2 = in.nextInt();
      }
      else {
         frame10Ball2 = 0;
      }

      // If two extra balls are needed - strike
      if (frame10Ball1 == 10) {
         frame10strike = 1;
         System.out.print("Extra   -- Ball 1: ");
         frame10ExtraBall1 = in.nextInt();
         System.out.print("        -- Ball 2: ");
         frame10ExtraBall2 = in.nextInt();
         if (frame10ExtraBall1 == 10 && frame10ExtraBall2 == 10) {
            frame10strike = frame10strike + 2;
         }
         else if (frame10ExtraBall1 == 10 && frame10ExtraBall2 < 10) {
            frame10strike = frame10strike + 1;
         }
         else if (frame10ExtraBall1 + frame10ExtraBall2 == 10) {
            frame10spare = 1;
         }
         else {
            frame10strike = frame10strike;
         }
      }
      // If the two balls give a score less than 10,
      // there should be no extra balls
      else if (frame10Ball1 + frame10Ball2 < 10) {
         frame10ExtraBall1 = 0;
         frame10ExtraBall2 = 0;
      }
      // If one extra ball is needed - spare
      else {
         frame10spare = 1;
         System.out.print("Extra   -- Ball 1: ");
         frame10ExtraBall1 = in.nextInt();
         if (frame10ExtraBall1 == 10) {
            frame10strike = frame10strike + 1;
         }
      }

      // Compute score
      ////////  Frame 10  ////////
      int frame10Score = frame10Ball1 + frame10Ball2
         + frame10ExtraBall1 + frame10ExtraBall2;

      ////////  Frame 9  ////////
      int frame9Score = 0;
      // If a strike
      if (frame9Ball1 == 10) {
         frame9strike = 1;
         if (frame10Ball1 == 10) {
            frame9Score = frame9Ball1 + frame10Ball1 + frame10ExtraBall1;
         }
         else {
            frame9Score = frame9Ball1 + frame10Ball1 + frame10Ball2;
         }
      }
      // If a spare
      else if (frame9Ball1 + frame9Ball2 == 10) {
         frame9spare = 1;
         frame9Score = frame9Ball1 + frame9Ball2 + frame10Ball1;
      }
      else {
         frame9Score = frame9Ball1 + frame9Ball2;
      }

      ////////  Frame 8  ////////
      int frame8Score = 0;
      // If a strike
      if (frame8Ball1 == 10) {
         frame8strike = 1;
         if (frame9Ball1 == 10) {
            frame8Score = frame8Ball1 + frame9Ball1 + frame10Ball1;
         }
         else {
            frame8Score = frame8Ball1 + frame9Ball1 + frame9Ball2;
         }
      }
      // If a spare
      else if (frame8Ball1 + frame8Ball2 == 10) {
         frame8spare = 1;
         frame8Score = frame8Ball1 + frame8Ball2 + frame9Ball1;
      }
      else {
         frame8Score = frame8Ball1 + frame8Ball2;
      }

      ////////  Frame 7  ////////
      int frame7Score = 0;
      // If a strike
      if (frame7Ball1 == 10) {
         frame7strike = 1;
         if (frame8Ball1 == 10) {
            frame7Score = frame7Ball1 + frame8Ball1 + frame9Ball1;
         }
         else {
            frame7Score = frame7Ball1 + frame8Ball1 + frame8Ball2;
         }
      }
      // If a spare
      else if (frame7Ball1 + frame7Ball2 == 10) {
         frame7spare = 1;
         frame7Score = frame7Ball1 + frame7Ball2 + frame8Ball1;
      }
      else {
         frame7Score = frame7Ball1 + frame7Ball2;
      }

      ////////  Frame 6  ////////
      int frame6Score = 0;
      // If a strike
      if (frame6Ball1 == 10) {
         frame6strike = 1;
         if (frame7Ball1 == 10) {
            frame6Score = frame6Ball1 + frame7Ball1 + frame8Ball1;
         }
         else {
            frame6Score = frame6Ball1 + frame7Ball1 + frame7Ball2;
         }
      }
      // If a spare
      else if (frame6Ball1 + frame6Ball2 == 10) {
         frame6spare = 1;
         frame6Score = frame6Ball1 + frame6Ball2 + frame7Ball1;
      }
      else {
         frame6Score = frame6Ball1 + frame6Ball2;
      }

      ////////  Frame 5  ////////
      int frame5Score = 0;
      // If a strike
      if (frame5Ball1 == 10) {
         frame5strike = 1;
         if (frame6Ball1 == 10) {
            frame5Score = frame5Ball1 + frame6Ball1 + frame7Ball1;
         }
         else {
            frame5Score = frame5Ball1 + frame6Ball1 + frame6Ball2;
         }
      }
      // If a spare
      else if (frame5Ball1 + frame5Ball2 == 10) {
         frame5spare = 1;
         frame5Score = frame5Ball1 + frame5Ball2 + frame6Ball1;
      }
      else {
         frame5Score = frame5Ball1 + frame5Ball2;
      }

      ////////  Frame 4  ////////
      int frame4Score = 0;
      // If a strike
      if (frame4Ball1 == 10) {
         frame4strike = 1;
         if (frame5Ball1 == 10) {
            frame4Score = frame4Ball1 + frame5Ball1 + frame6Ball1;
         }
         else {
            frame4Score = frame4Ball1 + frame5Ball1 + frame5Ball2;
         }
      }
      // If a spare
      else if (frame4Ball1 + frame4Ball2 == 10) {
         frame4spare = 1;
         frame4Score = frame4Ball1 + frame4Ball2 + frame5Ball1;
      }
      else {
         frame4Score = frame4Ball1 + frame4Ball2;
      }

      ////////  Frame 3  ////////
      int frame3Score = 0;
      // If a strike
      if (frame3Ball1 == 10) {
         frame3strike = 1;
         if (frame4Ball1 == 10) {
            frame3Score = frame3Ball1 + frame4Ball1 + frame5Ball1;
         }
         else {
            frame3Score = frame3Ball1 + frame4Ball1 + frame4Ball2;
         }
      }
      // If a spare
      else if (frame3Ball1 + frame3Ball2 == 10) {
         frame3spare = 1;
         frame3Score = frame3Ball1 + frame3Ball2 + frame4Ball1;
      }
      else {
         frame3Score = frame3Ball1 + frame3Ball2;
      }

      ////////  Frame 2  ////////
      int frame2Score = 0;
      // If a strike
      if (frame2Ball1 == 10) {
         frame2strike = 1;
         if (frame3Ball1 == 10) {
            frame2Score = frame2Ball1 + frame3Ball1 + frame4Ball1;
         }
         else {
            frame2Score = frame2Ball1 + frame3Ball1 + frame3Ball2;
         }
      }
      // If a spare
      else if (frame2Ball1 + frame2Ball2 == 10) {
         frame2spare = 1;
         frame2Score = frame2Ball1 + frame2Ball2 + frame3Ball1;
      }
      else {
         frame2Score = frame2Ball1 + frame2Ball2;
      }

      ////////  Frame 1  ////////
      int frame1Score = 0;
      // If a strike
      if (frame1Ball1 == 10) {
         frame1strike = 1;
         if (frame2Ball1 == 10) {
            frame1Score = frame1Ball1 + frame2Ball1 + frame3Ball1;
         }
         else {
            frame1Score = frame1Ball1 + frame2Ball1 + frame2Ball2;
         }
      }
      // If a spare
      else if (frame1Ball1 + frame1Ball2 == 10) {
         frame1spare = 1;
         frame1Score = frame1Ball1 + frame1Ball2 + frame2Ball1;
      }
      else {
         frame1Score = frame1Ball1 + frame1Ball2;
      }

      // Now to actually compute the scores
      int bowlingScore = frame1Score + frame2Score + frame3Score +
         frame4Score + frame5Score + frame6Score + frame7Score + frame8Score +
         frame9Score + frame10Score;

      // Compute the number of strikes and spares
      int numberStrikes = frame1strike + frame2strike + frame3strike +
         frame4strike + frame5strike + frame6strike + frame7strike +
         frame8strike + frame9strike + frame10strike;
      int numberSpares = frame1spare + frame2spare + frame3spare +
         frame4spare + frame5spare + frame6spare + frame7spare +
         frame8spare + frame9spare + frame10spare;

      // Display the score and the number of strikes and spares
      System.out.print("Your total score is " + bowlingScore + ".");
      System.out.print("\n");
      System.out.print("You rolled " + numberStrikes + " strikes and "
                          + numberSpares + " spares");
   }
}
