package ca.bcit.comp2522.bank;

/**
 * Represents a name class.
 *
 * @author Abhia Salman
 * @author Evelyn Millman
 *
 * @version 1.0
 */
public class Name
{

    private static final int MAX_LENGTH   = 600;
    private static final String FORBIDDEN = "jason";

    private final String first;
    private final String last;
    private final String originalFirst;
    private final String originalLast;

    /**
     * Constructor.
    *
    * @param first first name
    * 
    * @param last last name
    * 
    * @throws IllegalArgumentException if either name is invalid
     */
    public Name(final String first,
                final String last)
    {
        validateClientName(first);
        validateClientName(last);

        this.originalFirst = first;
        this.originalLast = last;
        this.first = format(first);
        this.last = format(last);
    }

    /**
     * Validate first and last name.
    *
     * @param name name to validate
     */
    private static void validateClientName(final String name)
    {
        if (name == null ||
                name.trim().isEmpty())
        {
            throw new IllegalArgumentException("Name cannot be null or blank.");
        }

        if (name.length() >= MAX_LENGTH)
        {
            throw new IllegalArgumentException("Name too long.");
        }

        if (name.toLowerCase().contains(FORBIDDEN))
        {
            throw new IllegalArgumentException("Name cannot contain '" + FORBIDDEN + "'.");
        }
    }

    /**
     * Formats the name into initials.
    *
     * @param name name to format
     * 
     * @return formatted name
     */
    private String format(final String name)
    {
        final String lower;
        final char firstLetter;

        lower = name.toLowerCase();

        firstLetter = Character.toUpperCase(lower.charAt(0));

        return firstLetter +
                lower.substring(1);
    }

    /**
     * Get first.
     *
     * @return first
     */
    public String getFirst()
    {
        return first;
    }

    /**
     * Get last.
     *
     * @return last name
     */
    public String getLast()
    {
        return last;
    }

    /**
     * Gets the initials.
    *
     * @return the initials
     */
    public String getInitials()
    {
        return first.charAt(0) +
            "."                +
            last.charAt(0)     +
            ".";
    }

    /**
     * Get full name.
     * @return full name
     */
    public String getFullName()
    {
        return first +
            " "      +
            last;
    }

    /**
     * Get reversed name.
     * 
     * @return reversed name
     */
    public String getReverseName()
    {
        final String reversedLast;
        final String reversedFirst;

        reversedLast = new StringBuilder(originalLast).reverse().toString();
        reversedFirst = new StringBuilder(originalFirst).reverse().toString();

        return reversedLast +
            " "             +
            reversedFirst;
    }
}
