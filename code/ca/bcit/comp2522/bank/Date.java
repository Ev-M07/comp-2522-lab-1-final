package ca.bcit.comp2522.bank;

/**
 * Represents a calendar date containing a year, month, and day.
 *
 * @author Abhia Salman
 * @author Evelyn Millman
 * @version 1.0
 */
public class Date
{
    private static final int MIN_YEAR;
    private static final int MAX_YEAR;
    private static final int MIN_MONTH;
    private static final int MAX_MONTH;
    private static final int MIN_DAY;

    private static final int JANUARY;
    private static final int FEBRUARY;
    private static final int MARCH;
    private static final int APRIL;
    private static final int MAY;
    private static final int JUNE;
    private static final int JULY;
    private static final int AUGUST;
    private static final int SEPTEMBER;
    private static final int OCTOBER;
    private static final int NOVEMBER;
    private static final int DECEMBER;

    private static final int DAYS_IN_FEBRUARY;
    private static final int DAYS_IN_LEAP_FEBRUARY;
    private static final int DAYS_IN_SHORT_MONTH;
    private static final int DAYS_IN_LONG_MONTH;

    private static final int SATURDAY;
    private static final int SUNDAY;
    private static final int MONDAY;
    private static final int TUESDAY;
    private static final int WEDNESDAY;
    private static final int THURSDAY;
    private static final int FRIDAY;

    private static final int TWELVES_DIVISOR;
    private static final int FOURS_DIVISOR;
    private static final int DAYS_PER_WEEK;
    private static final int YEAR_MODULUS;
    private static final int EIGHTEEN_HUNDREDS_OFFSET;
    private static final int EIGHTEEN_HUNDREDS_THRESHOLD;
    private static final int TWO_THOUSANDS_OFFSET;
    private static final int TWO_THOUSANDS_THRESHOLD;
    private static final int LEAP_YEAR_OFFSET;
    private static final int LEAP_YEAR_FREQUENCY;
    private static final int ONE_CENTURY;
    private static final int FOUR_CENTURY;

    private final int year;
    private final int month;
    private final int day;

    static
    {
        MIN_YEAR = 1805;
        MAX_YEAR = 2021;

        MIN_MONTH = 1;
        MAX_MONTH = 12;
        MIN_DAY   = 1;

        JANUARY   = 1;
        FEBRUARY  = 2;
        MARCH     = 3;
        APRIL     = 4;
        MAY       = 5;
        JUNE      = 6;
        JULY      = 7;
        AUGUST    = 8;
        SEPTEMBER = 9;
        OCTOBER   = 10;
        NOVEMBER  = 11;
        DECEMBER  = 12;

        DAYS_IN_FEBRUARY      = 28;
        DAYS_IN_LEAP_FEBRUARY = 29;
        DAYS_IN_SHORT_MONTH   = 30;
        DAYS_IN_LONG_MONTH    = 31;

        SATURDAY  = 0;
        SUNDAY    = 1;
        MONDAY    = 2;
        TUESDAY   = 3;
        WEDNESDAY = 4;
        THURSDAY  = 5;
        FRIDAY    = 6;

        TWELVES_DIVISOR = 12;
        FOURS_DIVISOR   = 4;
        DAYS_PER_WEEK   = 7;
        YEAR_MODULUS    = 100;

        EIGHTEEN_HUNDREDS_OFFSET    = 2;
        EIGHTEEN_HUNDREDS_THRESHOLD = 1900;
        TWO_THOUSANDS_OFFSET        = 6;
        TWO_THOUSANDS_THRESHOLD     = 2000;
        LEAP_YEAR_OFFSET            = 6;

        LEAP_YEAR_FREQUENCY = 4;
        ONE_CENTURY         = 100;
        FOUR_CENTURY        = 400;
    }

    /**
     * Constructs a {@code Date}, validating that the year, month, and day
     * together form a real calendar date.
     *
     * @param year  must be valid
     * @param month must be valid
     * @param day   must be valid
     *
     * @throws IllegalArgumentException if year, month, or day is not valid
     */
    public Date(final int year,
                final int month,
                final int day)
    {
        validateYear(year);
        validateMonth(month);
        validateDay(year, month, day);

        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Validates that a year falls within the supported range.
     *
     * @param year the year to validate
     *
     * @throws IllegalArgumentException if {@code year} is less than
     *                                  {@link #MIN_YEAR} or greater than
     *                                  {@link #MAX_YEAR}
     */
    private static void validateYear(final int year)
    {
        if (year < MIN_YEAR || year > MAX_YEAR)
        {
            throw new IllegalArgumentException("Year must be between " +
                    MIN_YEAR + " and " + MAX_YEAR + ". Received: " + year);
        }
    }

    /**
     * Validates that a month is within the valid calendar range.
     *
     * @param month the month to validate
     *
     * @throws IllegalArgumentException if {@code month} is less than
     *                                  {@link #MIN_MONTH} or greater than
     *                                  {@link #MAX_MONTH}
     */
    private static void validateMonth(final int month)
    {
        if (month < MIN_MONTH || month > MAX_MONTH)
        {
            throw new IllegalArgumentException("Month must be between " +
                    MIN_MONTH + " and " + MAX_MONTH + ". Received: " + month);
        }
    }

    /**
     * Validates that a day is within the valid range for the given month
     * and year, accounting for leap years.
     *
     * @param year  the year the day belongs to
     * @param month the month the day belongs to
     * @param day   the day to validate
     *
     * @throws IllegalArgumentException if {@code day} is less than
     *                                  {@link #MIN_DAY} or greater than the
     *                                  number of days in the given month/year
     */
    private static void validateDay(final int year,
                                    final int month,
                                    final int day)
    {
        if (day < MIN_DAY || day > getMaximumDays(month, year))
        {
            throw new IllegalArgumentException("Day must be between " +
                    MIN_DAY + " and " + getMaximumDays(month, year) + ". Received: " + day);
        }
    }

    /**
     * Returns the maximum number of days in the given month.
     *
     * @param month the month to check
     * @param year  the year of the month
     *
     * @return the maximum number of days in the month
     */
    private static int getMaximumDays(final int month,
                                      final int year)
    {
        final int maximumDays;

        if (month == FEBRUARY)
        {
            if (isLeapYear(year))
            {
                maximumDays = DAYS_IN_LEAP_FEBRUARY;
            }
            else
            {
                maximumDays = DAYS_IN_FEBRUARY;
            }
        }
        else if (month == APRIL ||
                month == JUNE ||
                month == SEPTEMBER ||
                month == NOVEMBER)
        {
            maximumDays = DAYS_IN_SHORT_MONTH;
        }
        else
        {
            maximumDays = DAYS_IN_LONG_MONTH;
        }

        return maximumDays;
    }

    /**
     * Determines whether the given year is a leap year.
     *
     * @param year the year to check
     *
     * @return true if the year is a leap year, false otherwise
     */
    private static boolean isLeapYear(final int year)
    {
        final boolean leapYear;

        if (year % FOUR_CENTURY == 0)
        {
            leapYear = true;
        }
        else if (year % ONE_CENTURY == 0)
        {
            leapYear = false;
        }
        else
        {
            leapYear = year % LEAP_YEAR_FREQUENCY == 0;
        }

        return leapYear;
    }

    /**
     * Returns the year.
     *
     * @return the year
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Returns the month.
     *
     * @return the month
     */
    public int getMonth()
    {
        return month;
    }

    /**
     * Returns the day.
     *
     * @return the day
     */
    public int getDay()
    {
        return day;
    }

    /**
     * Returns the date formatted as YYYY-MM-DD.
     *
     * @return the formatted date
     */
    public String getYYYYMMDD()
    {
        final String monthString;
        final String dayString;

        if (month < OCTOBER)
        {
            monthString = "0" + month;
        }
        else
        {
            monthString = String.valueOf(month);
        }

        if (day < OCTOBER)
        {
            dayString = "0" + day;
        }
        else
        {
            dayString = String.valueOf(day);
        }

        return year + "-" + monthString + "-" + dayString;
    }

    /**
     * Determines the day of the week for the date represented by this
     * {@code Date}.
     * The last two digits of the year are extracted by taking the year modulo
     * {@link #YEAR_MODULUS}. That value is divided by {@link #TWELVES_DIVISOR}
     * to get the number of complete groups of twelve, and the remainder is
     * computed as the two-digit year minus the number of twelves multiplied by
     * {@link #TWELVES_DIVISOR}. The remainder is then divided by
     * {@link #FOURS_DIVISOR} using integer division to get the number of
     * complete groups of four it contains.
     * The number of twelves, the remainder, the number of fours, the day of the
     * month, and the month code returned by {@link #getMonthCode()} are summed
     * into a running total. The following adjustments are then applied to that
     * total:
     * - If the year is less than {@link #EIGHTEEN_HUNDREDS_THRESHOLD},
     *   {@link #EIGHTEEN_HUNDREDS_OFFSET} is added.
     * - If the year is greater than or equal to
     *   {@link #TWO_THOUSANDS_THRESHOLD}, {@link #TWO_THOUSANDS_OFFSET} is
     *   added.
     * - If {@link #isLeapYear(int)} returns {@code true} for the year and the
     *   month is January or February, {@link #LEAP_YEAR_OFFSET} is added. This
     *   compensates for the leap day not having occurred yet in those months.
     * Years that are greater than or equal to
     * {@link #EIGHTEEN_HUNDREDS_THRESHOLD} and less than
     * {@link #TWO_THOUSANDS_THRESHOLD} require no century adjustment.
     * The adjusted total is reduced modulo {@link #DAYS_PER_WEEK}, and the
     * result is passed to {@link #getDayName(int)}. The values correspond to:
     * 0 = Saturday, 1 = Sunday, 2 = Monday, 3 = Tuesday, 4 = Wednesday,
     * 5 = Thursday, and 6 = Friday.
     * This method does not modify the state of this object. It assumes the
     * {@code year}, {@code month}, and {@code day} fields were validated by the
     * constructor and performs no validation of its own.
     * @return the lowercase name of the day of the week on which this date
     *         falls (for example, {@code "monday"})
     */
    public String getDayOfWeek()
    {
        final int twoDigitYear;
        final int numberOfTwelves;
        final int remainder;
        final int numberOfFours;
        final int monthCode;

        int total;
        final int dayOfWeek;

        twoDigitYear = year % YEAR_MODULUS;

        numberOfTwelves = twoDigitYear / TWELVES_DIVISOR;

        remainder = twoDigitYear -
                numberOfTwelves * TWELVES_DIVISOR;

        numberOfFours = remainder / FOURS_DIVISOR;

        monthCode = getMonthCode();

        total = numberOfTwelves +
                remainder +
                numberOfFours +
                day +
                monthCode;

        if (year < EIGHTEEN_HUNDREDS_THRESHOLD)
        {
            total += EIGHTEEN_HUNDREDS_OFFSET;
        }

        if (year >= TWO_THOUSANDS_THRESHOLD)
        {
            total += TWO_THOUSANDS_OFFSET;
        }

        if (isLeapYear(year) &&
                (month == JANUARY || month == FEBRUARY))
        {
            total += LEAP_YEAR_OFFSET;
        }

        dayOfWeek = total % DAYS_PER_WEEK;

        return getDayName(dayOfWeek);
    }

    /**
     * Returns the month code required by the lab's day-of-week
     * algorithm.
     *
     * @return the code for this date's month
     * @throws IllegalArgumentException if the month is invalid
     */
    private int getMonthCode() throws IllegalArgumentException
    {
        final int monthCode;

        if (month == JANUARY)
        {
            monthCode = 1;
        }
        else if (month == FEBRUARY)
        {
            monthCode = 4;
        }
        else if (month == MARCH)
        {
            monthCode = 4;
        }
        else if (month == APRIL)
        {
            monthCode = 0;
        }
        else if (month == MAY)
        {
            monthCode = 2;
        }
        else if (month == JUNE)
        {
            monthCode = 5;
        }
        else if (month == JULY)
        {
            monthCode = 0;
        }
        else if (month == AUGUST)
        {
            monthCode = 3;
        }
        else if (month == SEPTEMBER)
        {
            monthCode = 6;
        }
        else if (month == OCTOBER)
        {
            monthCode = 1;
        }
        else if (month == NOVEMBER)
        {
            monthCode = 4;
        }
        else if (month == DECEMBER)
        {
            monthCode = 6;
        }
        else
        {
            throw new IllegalArgumentException("Month must be between {@link #MIN_MONTH} and {@link #MAX_MONTH} (inclusively");
        }

        return monthCode;
    }

    /**
     * Converts a numeric day-of-week value into its name.
     *
     * @param dayOfWeek the numeric day-of-week value
     *
     * @return the lowercase day name
     *
     * @throws IllegalArgumentException if the day is invalid
     */
    private static String getDayName(final int dayOfWeek)
            throws IllegalArgumentException
    {
        final String dayName;

        if (dayOfWeek == SATURDAY)
        {
            dayName = "saturday";
        }
        else if (dayOfWeek == SUNDAY)
        {
            dayName = "sunday";
        }
        else if (dayOfWeek == MONDAY)
        {
            dayName = "monday";
        }
        else if (dayOfWeek == TUESDAY)
        {
            dayName = "tuesday";
        }
        else if (dayOfWeek == WEDNESDAY)
        {
            dayName = "wednesday";
        }
        else if (dayOfWeek == THURSDAY)
        {
            dayName = "thursday";
        }
        else if (dayOfWeek == FRIDAY)
        {
            dayName = "friday";
        }
        else
        {
            throw new IllegalArgumentException("Day must be between 0 and 6 (inclusively");
        }

        return dayName;
    }

    /**
     * Returns the English name of this date's month.
     *
     * @return the month name
     */
    public String getMonthName()
    {
        final String monthName;

        if (month == JANUARY)
        {
            monthName = "January";
        }
        else if (month == FEBRUARY)
        {
            monthName = "February";
        }
        else if (month == MARCH)
        {
            monthName = "March";
        }
        else if (month == APRIL)
        {
            monthName = "April";
        }
        else if (month == MAY)
        {
            monthName = "May";
        }
        else if (month == JUNE)
        {
            monthName = "June";
        }
        else if (month == JULY)
        {
            monthName = "July";
        }
        else if (month == AUGUST)
        {
            monthName = "August";
        }
        else if (month == SEPTEMBER)
        {
            monthName = "September";
        }
        else if (month == OCTOBER)
        {
            monthName = "October";
        }
        else if (month == NOVEMBER)
        {
            monthName = "November";
        }
        else
        {
            monthName = "December";
        }

        return monthName;
    }
}