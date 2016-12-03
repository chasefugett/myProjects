/**
 * @author Chase Fugett
 * Specifies what a onetime appointment is
 */
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Onetime extends Appointment {

	// Creates a GregorianCalendar object to be used later
	private GregorianCalendar onetimeAppt = new GregorianCalendar();

	/**
	 * @param description
	 * @param year
	 * @param month
	 * @param day
	 * @param type
	 *            Creates a onetime appointment
	 */
	public Onetime(String description, int year, int month, int day, String type) {
		super(description, year, month, day, type);
		onetimeAppt.set(year, month, day);
	}

	/**
	 * @return the onetimeAppt Returns the onetime appointment
	 */
	public GregorianCalendar getOnetimeAppt() {
		return onetimeAppt;
	}

	/**
	 * @param onetimeAppt
	 *            the onetimeAppt to set Sets the onetime appointment
	 */
	public void setOnetimeAppt(GregorianCalendar onetimeAppt) {
		this.onetimeAppt = onetimeAppt;
	}

	// Overrides the toString method
	@Override
	public String toString() {
		return super.toString();
	}

	// Checks whether the appointment falls on the specified date
	public boolean occursOn(int year, int month, int day) {
		if (year == onetimeAppt.get(Calendar.YEAR)) {
			if (month == onetimeAppt.get(Calendar.MONTH)) {
				if (day == onetimeAppt.get(Calendar.DATE)) {
					return true;
				}
			}
		}
		return false;
	}

}
