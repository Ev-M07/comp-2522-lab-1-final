package ca.bcit.comp2522.bank;

/**
 * Represents a person's first and last name.
 *
 * @author Abhia Salman
 * @author Evelyn Millman
 * @version 1.0
 */
public class Name
{
    private static final int MAX_NAME_LENGTH;

    private final String first;
    private final String last;

    static
    {
        MAX_NAME_LENGTH = 100;
    }

    /**
     * Constructs a Name with a first and last name.
     *
     * @param first the person's first name
     * @param last  the person's last name
     *
     * @throws IllegalArgumentException if either name is null, blank,
     *                                  too long, or contains "admin"
     */
    public Name(final String first,
                final String last)
    {
        validateName(first);
        validateName(last);

        this.first = first;
        this.last  = last;
    }

    /**
     * Validates one part of a person's name.
     *
     * @param name the name to validate
     *
     * @throws IllegalArgumentException if the name is invalid
     */
    private static void validateName(final String name)
    {
        if (name == null)
        {
            throw new IllegalArgumentException("Name cannot be null.");
        }

        if (name.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be blank.");
        }

        if (name.length() > MAX_NAME_LENGTH)
        {
            throw new IllegalArgumentException(
                    "Name cannot be more than {@link #MAX_NAME_LENGTH} characters.");
        }

        if (name.toLowerCase().contains("admin"))
        {
            throw new IllegalArgumentException(
                    "Name cannot contain admin.");
        }
    }

    /**
     * Returns the first name.
     *
     * @return the first name
     */
    public String getFirst()
    {
        return first;
    }

    /**
     * Returns the last name.
     *
     * @return the last name
     */
    public String getLast()
    {
        return last;
    }

    /**
     * Returns the person's initials in uppercase.
     *
     * @return the initials
     */
    public String getInitials()
    {
        return first.substring(0, 1).toUpperCase() +
                "." +
                last.substring(0, 1).toUpperCase() +
                ".";
    }

    /**
     * Returns the person's properly formatted full name.
     *
     * @return the full name
     */
    public String getFullName()
    {
        return formatName(first) + " " + formatName(last);
    }

    /**
     * Formats one name so that its first character is uppercase and
     * its remaining characters are lowercase.
     *
     * @param name the name to format
     *
     * @return the formatted name
     */
    private static String formatName(final String name)
    {
        return name.substring(0, 1).toUpperCase() +
                name.substring(1).toLowerCase();
    }

    /**
     * Returns the original first and last names completely reversed.
     *
     * @return the reversed name
     */
    public String getReverseName()
    {
        final String fullName;
        final StringBuilder reversedName;

        fullName = first + " " + last;
        reversedName = new StringBuilder(fullName);

        return reversedName.reverse().toString();
    }
}