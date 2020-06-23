package businessLayer;

/**
 * @author Gorkem
 *	A self made Date class. Since both java.util.Date and java.util.Calendar are hard to use, I created this class. It's not complete,
 *	its missing the hour and minute uses, for the homework I was mainly focused on day,month and year attributes.
 */
public class Date {
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;

	public Date(int year, int month, int day, int hour, int minute) {
		this.setYear(year);
		this.setMonth(month);
		this.setDay(day);
		this.setHour(hour);
		this.setMinute(minute);
	}

	public Date(int year, int month, int day) {
		this.setYear(year);
		this.setMonth(month);
		this.setDay(day);
		this.setHour(0);
		this.setMinute(0);
	}

	public Date() {
		this.setYear(0);
		this.setMonth(0);
		this.setDay(0);
		this.setHour(0);
		this.setMinute(0);
	}

	public void setDate(int year, int month, int day, int hour, int minute) {
		this.setYear(year);
		this.setMonth(month);
		this.setDay(day);
		this.setHour(hour);
		this.setMinute(minute);
	}

	public Date addOneDay(Date date) {
		int year = date.getYear();
		int month = date.getMonth();
		int day = date.getDay();
		if (getMonth() == 2) {
			if ((getYear() % 4 == 0 && getYear() % 100 != 0) || getYear() % 400 == 0) {
				if (day < 30) {
					return new Date(year,month,day+1);
				}
				else {
					return new Date(year,month+1,1);
				}
			}
			else {
				if (day < 29) {
					return new Date(year,month,day+1);
				}
				else {
					return new Date(year,month+1,1);
				}
			}
		}
		else if (getMonth() == 1 || getMonth() == 3 || getMonth() == 5 || getMonth() == 7 || getMonth() == 8 || getMonth() == 10 || getMonth() == 12) {
			if (day < 32) {
				return new Date(year,month,day+1);
			}
			else {
				if(month == 12) {
					return new Date(year+1,1,1);
				}
				else {
					return new Date(year,month+1,1);
				}
			}
		}
		else {
			if (day < 31) {
				return new Date(year,month,day+1);
			}
			else {
				return new Date(year,month+1,1);
			}
		}
	}
	
	public void setDate(Date date) {
		this.setDate(date);
	}

	public void setDate(int year, int month, int day) {
		this.setDay(day);
		this.setMonth(month);
		this.setYear(year);
	}

	public int compareTo(Date otherDate) {//TODO complete
		if(getYear() > otherDate.getYear()) {
			return 1;
		} else if(getYear() == otherDate.getYear()){
			if(getMonth() > otherDate.getMonth()) {
				return 1;
			} else if(getMonth() == otherDate.getMonth()){
				if(getDay() > otherDate.getDay()) {
					return 1;
				} else if(getDay() == otherDate.getDay()) {
					return 0;
				} else {
					return -1;
				}
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}

	public boolean before(Object otherObject) {
		if (otherObject == this) {
			return false;
		}
		if (otherObject == null) {
			return false;
		}
		if (!(otherObject instanceof Date)) {
			return false;
		}
		Date otherDate = (Date)otherObject;
		if (getYear() < otherDate.getYear()) {
			return true;
		}
		else if (getYear() == otherDate.getYear()){
			if (getMonth() < otherDate.getMonth()) {
				return true;
			}
			else if (getMonth() == otherDate.getMonth()) {
				if (getDay() < otherDate.getDay()) {
					return true;
				}
				else if (getDay() == otherDate.getDay()) {
					if (getHour() < otherDate.getHour()) {
						return true;
					}
					else if (getHour() == otherDate.getHour()) {
						if (getMinute() < otherDate.getMinute()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean after(Object otherObject) {//TODO do with day values 365*2017+30*10 ....
		if (otherObject == this) {
			return false;
		}
		if (otherObject == null) {
			return false;
		}
		if (!(otherObject instanceof Date)) {
			return false;
		}
		Date otherDate = (Date)otherObject;
		if (getYear() > otherDate.getYear()) {
			return true;
		}
		else if (getYear() == otherDate.getYear()){
			if (getMonth() > otherDate.getMonth()) {
				return true;
			}
			else if (getMonth() == otherDate.getMonth()) {
				if (getDay() > otherDate.getDay()) {
					return true;
				}
				else if (getDay() == otherDate.getDay()) {
					if (getHour() > otherDate.getHour()) {
						return true;
					}
					else if (getHour() == otherDate.getHour()) {
						if (getMinute() > otherDate.getMinute()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public int hashCode() {
		int hashCode = 17;
		hashCode = 31 * hashCode * getYear() * getMonth() + 17 * getDay() * 17 + 15;
		return hashCode;
	}
	
	public boolean equals(Object otherObject) {
		if (otherObject == this) {
			return true;
		}
		if (otherObject == null) {
			return false;
		}
		if (!(otherObject instanceof Date)) {
			return false;
		}
		Date otherDate = (Date)otherObject;
		return otherDate.getYear() == getYear() && otherDate.getMonth() == getMonth()
				&& otherDate.getDay() == getDay() && otherDate.getHour() == getHour() && otherDate.getMinute() == getMinute();
	}
	
	public void toScreen() {
		System.out.println(getDay() + "." + getMonth() + "." + getYear());
	}
	
	public String toString() {
		return getDay() + "." + getMonth() + "." + getYear();
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		if (year > 1900 && year < 2050) {//TODO well.. 1900 or some other value?
			this.year = year;
		}
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		if (month > 0 && month < 13) {
			this.month = month;
		}
	}
	
	public int getMinute() {
		return minute;
	}
	
	public void setMinute(int minute) {
		if (minute >= 0 && minute <= 60) {
			this.minute = minute;
		}
	}
	
	public int getHour() {
		return hour;
	}
	
	public void setHour(int hour) {
		if (hour >= 0 && hour < 24)
			this.hour = hour;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setDay(int day) {
		if (day >= 0) {
			if (getMonth() == 2) {
				if ((getYear() % 4 == 0 && getYear() % 100 != 0) || getYear() % 400 == 0) {
					if (day < 30) {
						this.day = day;
					}
				}
				else {
					if (day < 29) {
						this.day = day;
					}
				}
			}
			else if (getMonth() == 1 || getMonth() == 3 || getMonth() == 5 || getMonth() == 7 || getMonth() == 8 || getMonth() == 10 || getMonth() == 12) {
				if (day < 32) {
					this.day = day;
				}
			}
			else {
				if ( day < 31) {
					this.day = day;
				}
			}
		}
	}
}
