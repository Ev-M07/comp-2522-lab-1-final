package ca.bcit.comp2522.bank;

/**
 * Represents a client of the bank.
 *
 * @author Abhia Salman
 * @author Evelyn Millman
 * @version 1.0
 */
public class BankClient
{
    private static final int MIN_ID_LENGTH;
    private static final int MAX_ID_LENGTH;

    private final Name   name;
    private final Date   birthDate;
    private final Date   deathDate;
    private final Date   signupDate;
    private final String clientID;

    static
    {
        MIN_ID_LENGTH = 12;
        MAX_ID_LENGTH = 20;
    }

    /**
     * Constructs a bank client.
     *
     * @param name       the client's name
     * @param birthDate  the client's birth date
     * @param deathDate  the client's death date, or null if alive
     * @param signupDate the date the client joined the bank
     * @param clientID   the client's six or seven character ID
     *
     * @throws IllegalArgumentException if required data is invalid
     */
    public BankClient(final Name name,
                      final Date birthDate,
                      final Date deathDate,
                      final Date signupDate,
                      final String clientID)
    {
        if (name == null)
        {
            throw new IllegalArgumentException(
                    "Name cannot be null.");
        }

        if (birthDate == null)
        {
            throw new IllegalArgumentException(
                    "Birth date cannot be null.");
        }

        if (signupDate == null)
        {
            throw new IllegalArgumentException(
                    "Signup date cannot be null.");
        }

        if (clientID == null)
        {
            throw new IllegalArgumentException(
                    "Client ID cannot be null.");
        }

        if (clientID.length() < MIN_ID_LENGTH
                || clientID.length() > MAX_ID_LENGTH)
        {
            throw new IllegalArgumentException(
                    "Client ID must contain {@link #MIN_ID_LENGTH} to {@link #MAX_ID_LENGTH} characters.");
        }

        this.name = name;
        this.birthDate  = birthDate;
        this.deathDate  = deathDate;
        this.signupDate = signupDate;
        this.clientID   = clientID;
    }

    /**
     * Returns the client's name.
     *
     * @return the client's name
     */
    public Name getName()
    {
        return name;
    }

    /**
     * Returns the client's birth date.
     *
     * @return the birth date
     */
    public Date getBirthDate()
    {
        return birthDate;
    }

    /**
     * Returns the client's death date.
     *
     * @return the death date, or null if the client is alive
     */
    public Date getDeathDate()
    {
        return deathDate;
    }

    /**
     * Returns the signup date.
     *
     * @return the signup date
     */
    public Date getSignupDate()
    {
        return signupDate;
    }

    /**
     * Returns the client ID.
     *
     * @return the client ID
     */
    public String getClientID()
    {
        return clientID;
    }

    /**
     * Determines whether this client is alive.
     *
     * @return true if there is no death date
     */
    public boolean isAlive()
    {
        return deathDate == null;
    }

    /**
     * Returns formatted information about the bank client.
     *
     * @return the client's details
     */
    public String getDetails()
    {
        final String lifeStatus;

        if (isAlive())
        {
            lifeStatus = "alive";
        }
        else
        {
            lifeStatus = "not alive";
        }

        return name.getFullName() +
                " client #" +
                clientID +
                " (" +
                lifeStatus +
                ") joined the bank on " +
                signupDate.getDayOfWeek() +
                ", " +
                signupDate.getMonth() +
                " " +
                signupDate.getDay() +
                ", " +
                + signupDate.getYear();
    }
}