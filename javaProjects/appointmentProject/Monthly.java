/**
 * @author Chase Fugett
 * Specifies what a monthly appointment is
 */
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Monthly extends Appointment {

	// Creates a GregorianCalendar object to be used later
	private GregorianCalendar monthlyAppt = new GregorianCalendar();

	/**
	 * @param description
	 * @param year
	 * @param month
	 * @param day
	 * @param type
	 *            Creates a monthly appointment that will be used to repeat
	 *            until the end date requested
	 */
	public Monthly(String description, int year, int month, int day, String type) {
		super(description, year, month, day, type);
		monthlyAppt.set(year, month, day);
	}

	/**
	 * @return the monthlyAppt Returns the monthly appointment
	 */
	public GregorianCalendar getMonthlyAppt() {
		return monthlyAppt;
	}

	/**
	 * @param monthlyAppt
	 *            the monthlyAppt to set Sets the monthly appointment
	 */
	public void setMonthlyAppt(GregorianCalendar monthlyAppt) {
		this.monthlyAppt = monthlyAppt;
	}

	// Overrides the toString method
	@Override
	public String toString() {
		return super.toString();
	}

	// Checks whether the appointment falls on the specified day of the month
	// and each month after that
	public boolean occursOn(int year, int month, int day) {
		if (year >= monthlyAppt.get(Calendar.YEAR)) {
			if (month >= monthlyAppt.get(Calendar.MONTH)) {
				if (day == monthlyAppt.get(Calendar.DATE)) {
					return true;
				}
			}
		}
		return false;
	}

}
