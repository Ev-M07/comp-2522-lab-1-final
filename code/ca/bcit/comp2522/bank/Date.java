package ca.bcit.comp2522.bank;

/**
 * Represents a calendar date.
 *
 * @author Abhia Salman
 * @author Evelyn Millman
 * @version 1.0
 */
public class Date
{
    /**
     * Min and Max year.
     */
	private static final int MIN_YEAR = 1805;
	private static final int MAX_YEAR = 2021;
    
    /**
     * Month constants.
     */
	private static final int JAN = 1;
	private static final int FEB = 2;
	private static final int MAR = 3;
	private static final int APR = 4;
	private static final int MAY = 5;
	private static final int JUN = 6;
	private static final int JUL = 7;
	private static final int AUG = 8;
	private static final int SEP = 9;
	private static final int OCT = 10;
	private static final int NOV = 11;
	private static final int DEC = 12;

    /**
     * Month codes for calculating the day of the week.
     */
	private static final int JAN_CODE = 1;
	private static final int FEB_CODE = 4;
	private static final int MAR_CODE = 4;
	private static final int APR_CODE = 0;
	private static final int MAY_CODE = 2;
	private static final int JUN_CODE = 5;
	private static final int JUL_CODE = 0;
	private static final int AUG_CODE = 3;
	private static final int SEP_CODE = 6;
	private static final int OCT_CODE = 1;
	private static final int NOV_CODE = 4;
	private static final int DEC_CODE = 6;

    /**
     * Day constants.
     */
	private static final int MIN_DAY      = 1;
	private static final int MAX_DAY_31   = 31;
	private static final int MAX_DAY_30   = 30;
	private static final int MAX_DAY_29   = 29;
	private static final int MAX_DAY_28   = 28;

    /**
     * Leap year constants. 
     */
	private static final int LEAP_DIV_4   = 4;
	private static final int LEAP_DIV_100 = 100;
	private static final int LEAP_DIV_400 = 400;
	private static final int DAY_TEN      = 10;

    /**
     * Adjustment constants for calculating the day of the week.
     */
	private static final int ADJUST_2000 = 6;
	private static final int ADJUST_1800 = 2;
	private static final int LEAP_ADJUST = 6;

    /**
     * Day of the week constants.
     */
	private static final int SATURDAY  = 0;
	private static final int SUNDAY    = 1;
	private static final int MONDAY    = 2;
	private static final int TUESDAY   = 3;
	private static final int WEDNESDAY = 4;
	private static final int THURSDAY  = 5;
	private static final int FRIDAY    = 6;

	private final int year;
	private final int month;
	private final int day;

	/**
	 * Creates a valid calendar date.
	 *
	 * @param year year from 1805 through 2021
	 * @param month month from 1 through 12
	 * @param day valid day for the month
	 * @throws IllegalArgumentException if the date is invalid
	 */
	public Date(final int year,
				final int month,
				final int day)
	{
		validate(year, month, day);
		this.year = year;
		this.month = month;
		this.day = day;
	}

	private void validate(final int year,
						  final int month,
						  final int day)
	{
		int maximumDay;

		if (year < MIN_YEAR || year > MAX_YEAR)
		{
			throw new IllegalArgumentException("Invalid year.");
		}
		if (month < JAN || month > DEC)
		{
			throw new IllegalArgumentException("Invalid month.");
		}
		maximumDay = MAX_DAY_31;

		if (month == FEB)
		{
			if (isLeap(year))
			{
				maximumDay = MAX_DAY_29;
			}
			else
			{
				maximumDay = MAX_DAY_28;
			}
		}
		if (month == APR     ||
				month == JUN ||
				month == SEP ||
				month == NOV)
		{
			maximumDay = MAX_DAY_30;
		}
		if (day < MIN_DAY ||
         day > maximumDay)
		{
			throw new IllegalArgumentException("Invalid day.");
		}
	}

	private boolean isLeap(final int year)
	{
		return year % LEAP_DIV_4 == 0 &&
				(year % LEAP_DIV_100 != 0 || 
                 year % LEAP_DIV_400 == 0);
	}

	/**
	 * @return day of the month
	 */
	public int getDay()
	{
		return day;
	}

	/**
	 * @return month number
	 */
	public int getMonth()
	{
		return month;
	}

	/**
	 * @return year number
	 */
	public int getYear()
	{
		return year;
	}

	/**
	 * Formats the date as YYYY-MM-DD.
	 *
	 * @return formatted date
	 */
	public String getYYYYMMDD()
	{
		final String monthText;
		final String dayText;

		if (month < DAY_TEN)
		{
			monthText = "0" + month;
		}
		else
		{
			monthText = "" + month;
		}
		if (day < DAY_TEN)
		{
			dayText = "0" + day;
		}
		else
		{
			dayText = "" + day;
		}
		return year        +
				"-"        +
				monthText  +
				"-"        +
				dayText;
	}

	/**
	 * Formats the date as YYYY-MM-DD.
	 *
	 * @return formatted date
	 */
	public String getYyyyMmDd()
	{
		return getYYYYMMDD();
	}

	/**
	 * Calculates the day of the week using the lab's prescribed method.
	 *
	 * @return value from 0 for Saturday through 6 for Friday
	 */
	public int getDayOfWeek()
	{
		final int yearPart;
		final int twelves;
		final int remainder;
		final int fours;
		final int monthCode;
		int sum;

		yearPart = year % 100;
		twelves = yearPart / 12;
		remainder = yearPart % 12;
		fours = remainder / 4;
		monthCode = getMonthCode();
		sum = day         +
				twelves   +
				remainder +
				fours     +
				monthCode;
		if (year >= 2000)
		{
			sum += ADJUST_2000;
		}
		else if (year < 1900)
		{
			sum += ADJUST_1800;
		}
		if (isLeap(year) && (month == JAN ||
         month == FEB))
		{
			sum += LEAP_ADJUST;
		}
		return sum % 7;
	}

	/**
	 * Provides the alternate spelling required by the lab.
	 *
	 * @return value from 0 for Saturday through 6 for Friday
	 */
	public int getDayOfTheWeek()
	{
		return getDayOfWeek();
	}

	/**
	 * @return lowercase day name
	 */
	public String getDayName()
	{
		final int dayOfWeek;

		dayOfWeek = getDayOfWeek();
		if (dayOfWeek == SATURDAY)
		{
			return "saturday";
		}
		if (dayOfWeek == SUNDAY)
		{
			return "sunday";
		}
		if (dayOfWeek == MONDAY)
		{
			return "monday";
		}
		if (dayOfWeek == TUESDAY)
		{
			return "tuesday";
		}
		if (dayOfWeek == WEDNESDAY)
		{
			return "wednesday";
		}
		if (dayOfWeek == THURSDAY)
		{
			return "thursday";
		}
		if (dayOfWeek == FRIDAY)
		{
			return "friday";
		}
		return "friday";
	}

	/**
     
Returns the English name of the month.*
@return the month name*/
public String getMonthName(){
    if (month == JAN) return  "January";
    if (month == FEB) return  "February";
    if (month == MAR) return  "March";
    if (month == APR) return  "April";
    if (month == MAY) return  "May";
    if (month == JUN) return  "June";
    if (month == JUL) return  "July";
    if (month == AUG) return  "August";
    if (month == SEP) return  "September";
    if (month == OCT) return  "October";
    if (month == NOV) return  "November";
    return                    "December";}

/**
     
The month code used in the method used to calculate  the day of the week.*
The month codes:
Jan=1,
Feb=2,
Mar=3,
Apr=4,
May=5,
Jun=6,
Jul=7,
Aug=8,
Sep=9,
Oct=10,
Nov=11,
Dec=12*
Used to compute the day of the week using modular math.*
@return the month code for this date's month*/
private int getMonthCode() {
    if (month == JAN) return  JAN_CODE;
    if (month == FEB) return  FEB_CODE;
    if (month == MAR) return  MAR_CODE;
    if (month == APR) return  APR_CODE;
    if (month == MAY) return  MAY_CODE;
    if (month == JUN) return  JUN_CODE;
    if (month == JUL) return  JUL_CODE;
    if (month == AUG) return  AUG_CODE;
    if (month == SEP) return  SEP_CODE;
    if (month == OCT) return  OCT_CODE;
    if (month == NOV) return  NOV_CODE;
    return                    DEC_CODE;}
}