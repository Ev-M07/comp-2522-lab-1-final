package ca.bcit.comp2522.bank;

/**
 * Represents a bank client class.
 *
 * @author Abhia Salman
 * @author Evelyn Millman
 *
 * @version 1.0
 */
public class BankClient 
{

    private static final int MIN_ID_LENGTH = 6;
    private static final int MAX_ID_LENGTH = 7;

    private final Name   name;
    private final Date   birthDate;
    private final Date   deathDate;
    private final Date   signupDate;
    private final String clientId;

    /**
     * Bank client constructor.
     *
     * @param name       client name
     * @param birthDate  birthdate
     * @param deathDate  deathdate
     * @param signupDate sign up date
     * @param clientId   client ID
     * 
    * @throws IllegalArgumentException if a required value or client ID is invalid
     */
    public BankClient(final Name   name,
                      final Date   birthDate,
                      final Date   deathDate,
                      final Date   signupDate,
                      final String clientId)
    {
        if (name == null ||
         birthDate == null ||
          signupDate == null) 
          {
            throw new IllegalArgumentException("Client dates and name are required.");
        }
        if (clientId == null ||
                (clientId.length() != MIN_ID_LENGTH &&
                    clientId.length() != MAX_ID_LENGTH) ||
                !clientId.matches("[0-9]+"))
        {
            throw new IllegalArgumentException("Client ID must contain 6 or 7 characters.");
        }
        this.name       = name;
        this.birthDate  = birthDate;
        this.deathDate  = deathDate;
        this.signupDate = signupDate;
        this.clientId   = clientId;
    }

    /**
     * Get client name.
     *
     * @return client name
     */
    public Name getName() 
    {
        return name;
    }

    /**
     * Gets the client's birth date.
     *
     * @return client's birth date
     */
    public Date getBirthDate()
    {
        return birthDate;
    }

    /**
     * Checks if someone is alive.
     *
    * @return true if alive
    */
    public boolean isAlive()
    {
        return deathDate == null;
    }

    /**
        * Formats the client's details, including the living status and signup date.
     *
    * @return formatted details
    */
    public String getDetails()
    {
        final String status;

        if (isAlive())
        {
            status = "(alive)";
        } 
        else 
        {
            status = "(not alive)";
        }

        return name.getFullName()      +
            " client #"                +
            clientId                   +
            " "                        +
            status                     +
            " joined the bank on "     +
            signupDate.getDayName()    +
            ", "                       +
            signupDate.getMonthName()  +
            " "                        +
            signupDate.getDay()        +
            ", "                       +
            signupDate.getYear();
    }

}
