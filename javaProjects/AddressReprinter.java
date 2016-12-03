/**
 * Chase Fugett
 * Reprint an address with correct formatting upon user input
 */

import java.util.Scanner;

public class AddressReprinter {

   public static void main(String[] args) {

      // User should enter name, address, and phone number
      Scanner in = new Scanner(System.in);
      System.out.print("Name: ");
      String originalName = in.nextLine().trim();

      System.out.print("Address: ");
      String originalNumberAddress = in.nextLine().trim();

      System.out.print("Phone: ");
      String originalPhoneNumber = in.nextLine().trim();

      // Correctly formatting the name
      int indexOfComma = originalName.indexOf(',');
      String lastName = originalName.substring(0, indexOfComma).trim();

      int indexOfLastSpace = originalName.lastIndexOf(' ');
      String firstName =
         originalName.substring(indexOfLastSpace,
                                originalName.length()).trim();

      // Putting correct capitalization on the names
      String lastNameFirstLetter = lastName.substring(0, 1).trim();
      String lastNameAfterFirstLetter =
         lastName.substring(1, lastName.length()).trim();

      lastNameFirstLetter = lastNameFirstLetter.toUpperCase();
      lastNameAfterFirstLetter = lastNameAfterFirstLetter.toLowerCase();

      String firstNameFirstLetter = firstName.substring(0, 1).trim();
      String firstNameAfterFirstLetter =
         firstName.substring(1, firstName.length()).trim();

      firstNameFirstLetter = firstNameFirstLetter.toUpperCase();
      firstNameAfterFirstLetter = firstNameAfterFirstLetter.toLowerCase();

      // Putting correct format on the address
      int lastAddressComma = originalNumberAddress.lastIndexOf(',');
      String zipCodeAndState =
         originalNumberAddress.substring(lastAddressComma + 1,
                                     originalNumberAddress.length()).trim();

      // Takes off the zip code and state from the address
      originalNumberAddress =
         originalNumberAddress.substring(0, lastAddressComma).trim();


      int secondLastAddressComma = originalNumberAddress.lastIndexOf(',');
      String city =
         originalNumberAddress.substring(secondLastAddressComma + 1,
                                         originalNumberAddress.length()).trim();

      // Takes off the city from the address
      originalNumberAddress =
         originalNumberAddress.substring(0, secondLastAddressComma + 1).trim();

      int lastCommaBeforeCity = originalNumberAddress.lastIndexOf(',');
      String streetAddress =
         originalNumberAddress.substring(0,
                                         lastCommaBeforeCity).trim();

      // Putting correct format on phone number
      originalPhoneNumber = originalPhoneNumber.replaceAll(" ", "");

      String areaCode = originalPhoneNumber.substring(1, 4);
      String threeDigitExchange = originalPhoneNumber.substring(5, 8);
      String fourDigitEnding =
         originalPhoneNumber.substring(9, originalPhoneNumber.length());

      // Prints out correctly formatted name, address, and phone number
      System.out.println("***** RESULTS *****");
      System.out.println(firstNameFirstLetter + firstNameAfterFirstLetter
                            + " " + lastNameFirstLetter +
                         lastNameAfterFirstLetter);

      System.out.print(streetAddress + ", ");
      System.out.print(city + ", ");
      System.out.println(zipCodeAndState);

      System.out.println(areaCode + "-" + threeDigitExchange
                           + "-" + fourDigitEnding);

   }
}
