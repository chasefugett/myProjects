/**
 * @author Chase Fugett
 * Creates an appointment with a description and date
 */
public abstract class Appointment {

	// Creates variables needed for the constructor later
	private String description;
	private int year;
	private int month;
	private int day;
	private String type;

	/**
	 * @param description
	 * @param year
	 * @param month
	 * @param day
	 *            Constructs a general appointment with a description and date
	 */
	public Appointment(String description, int year, int month, int day,
			String type) {
		super();
		this.description = description;
		this.year = year;
		this.month = month;
		this.day = day;
		this.type = type;
	}

	/**
	 * @return the description Returns the description of the appointment
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set Sets the description of the appointment
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the year Returns the year of the appointment
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set Sets the year of the appointment
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the month Returns the month of the appointment
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            the month to set Sets the month of the appointment
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * @return the day Returns the day of the appointment
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @param day
	 *            the day to set Sets the day of the appointment
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * @return the type Returns the type of the appointment
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set Sets the type of the appointment
	 */
	public void setType(String type) {
		this.type = type;
	}

	// Overrides the toString method
	@Override
	public String toString() {
		return "[Type: " + type + ", Description: " + description + ", Date: "
				+ month + "/" + day + "/" + year + "]";
	}

	// Method that will check whether the appointment occurs on the specified
	// date, but will be implemented later on
	abstract public boolean occursOn(int year, int month, int day);

}
