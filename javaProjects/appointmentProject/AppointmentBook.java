/**
 * @author Chase Fugett
 * Creates a a collection of appointments, saves them to a file, 
 * and/or loads them to a file
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AppointmentBook {

	// Creates the arraylist for the appointment book
	private ArrayList<Appointment> apptBook = new ArrayList<Appointment>();

	// Constants to use for sorting appointments later
	private final String ONETIME = "Onetime";
	private final String DAILY = "Daily";
	private final String MONTHLY = "Monthly";

	/**
	 * @param apptBook
	 *            Constructs an AppointmentBook object
	 */
	public AppointmentBook() {
		super();
		this.apptBook = apptBook;
	}

	/**
	 * @param apptBook
	 *            Constructs an AppointmentBook object
	 */
	public AppointmentBook(ArrayList<Appointment> apptBook) {
		super();
		this.apptBook = apptBook;
	}

	/**
	 * @return the apptBook Returns the appointment book arraylist
	 */
	public ArrayList<Appointment> getApptBook() {
		return apptBook;
	}

	/**
	 * @param i
	 * @return the Appointment Returns the element at i of the apptBook
	 */
	public Appointment getApptBookElement(int i) {
		return apptBook.get(i);
	}

	/**
	 * @return the size of the array of apptBook Returns the size of the array
	 *         of appointments
	 */
	public int getApptBookSize() {
		return apptBook.size();
	}

	/**
	 * @param apptBook
	 *            the apptBook to set Sets the appointment book arraylist
	 */
	public void setApptBook(ArrayList<Appointment> apptBook) {
		this.apptBook = apptBook;
	}

	// Adds an appointment to the appointment book by type of appointment
	// Note: user must correctly spell the type of appointment
	public void add(String description, int year, int month, int day,
			String type) {
		if (type.equalsIgnoreCase(ONETIME)) {
			Onetime appt = new Onetime(description, year, month, day, type);
			apptBook.add(appt);
		} else if (type.equalsIgnoreCase(DAILY)) {
			Daily appt = new Daily(description, year, month, day, type);
			apptBook.add(appt);
		} else {
			Monthly appt = new Monthly(description, year, month, day, type);
			apptBook.add(appt);
		}
	}

	// Saves the appointment book to an output file specified by the user
	public void save(String file) throws FileNotFoundException {
		String outputFileName = file;
		PrintWriter out = new PrintWriter(outputFileName);
		for (int i = 0; i < apptBook.size(); i++) {
			Appointment appt = apptBook.get(i);
			String type = appt.getType();
			String description = appt.getDescription();
			int year = appt.getYear();
			int month = appt.getMonth();
			int day = appt.getDay();
			out.print(type + " ");
			out.print(description + " ");
			out.println(year);
			out.println(month);
			out.println(day);
		}
		out.close();
	}

	// Loads a set of appointments from an input file specified by the user
	// into the appointment book arraylist
	public void load(String file) throws FileNotFoundException {
		String inputFileName = file;
		File inputFile = new File(inputFileName);
		Scanner input = new Scanner(inputFile);
		while (input.hasNext()) {
			String type = input.next();
			String description = input.next();
			// Continues to add words to the description until a number is 
			// reached
			while (input.hasNextInt() == false) {
				description = description + " " + input.next();
			}
			// After a number is reached, the full appointment is put into 
			// the arraylist
			int year = input.nextInt();
			int month = input.nextInt();
			int day = input.nextInt();
			add(description, year, month, day, type);
		}
		input.close();
	}

}
