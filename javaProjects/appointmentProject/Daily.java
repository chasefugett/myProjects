/**
 * @author Chase Fugett
 * Specifies what a daily appointment is
 */
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Daily extends Appointment {

	// Creates a GregorianCalendar object to be used later
	private GregorianCalendar dailyAppt = new GregorianCalendar();

	/**
	 * @param description
	 * @param year
	 * @param month
	 * @param day
	 * @param type
	 *            Creates a daily appointment that will be used to repeat until
	 *            the end date requested
	 */
	public Daily(String description, int year, int month, int day, String type) {
		super(description, year, month, day, type);
		dailyAppt.set(year, month, day);
	}

	/**
	 * @return the dailyAppt Returns the daily appointment
	 */
	public GregorianCalendar getDailyAppt() {
		return dailyAppt;
	}

	/**
	 * @param dailyAppt
	 *            the dailyAppt to set Sets the daily appointment
	 */
	public void setDailyAppt(GregorianCalendar dailyAppt) {
		this.dailyAppt = dailyAppt;
	}

	// Overrides the toString method
	@Override
	public String toString() {
		return super.toString();
	}

	// Checks whether the appointment falls on the specified day and every day
	// after that
	public boolean occursOn(int year, int month, int day) {
		if (year >= dailyAppt.get(Calendar.YEAR)) {
			if (month >= dailyAppt.get(Calendar.MONTH)) {
				if (day >= dailyAppt.get(Calendar.DATE)) {
					return true;
				}
			}
		}
		return false;
	}

}
