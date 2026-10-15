package ca.bcit.comp2522.bank;

/**
 * Represents a bank account class.
 *
 * @author Abhia Salman
 * @author Evelyn Millman
 * @author Jaden McDonald
 *
 * @version 1.0
 */
public class BankAccount 
{

    private static final int    MIN_ACCOUNT_NUMBER = 0;
    private static final int    MAX_ACCOUNT_NUMBER = 0;
    private static final int    MIN_ACCOUNT_NUMBER_LENGTH = 6;
    private static final int    MAX_ACCOUNT_NUMBER_LENGTH = 7;
    private static final double MIN_BALANCE_USD = 0.0;

    private final BankClient client;
    private final String     accountNumber;
    private final int        pin;
    private final Date       opened;
    private final Date       closed;

    private double balanceUsd;

    /**
     * The bank account constructor validates the client, account number, PIN, and open and close dates.
     *
     * @param client         client of bank
     * @param accountNumber  bank account number
     * @param pin            PIN
     * @param opened         opened bank account
     * @param closed         closed bank account
     * @param initialBalance initial balance of bank account
     * 
     *  @throws IllegalArgumentException if an account value is invalid
     */
    public BankAccount(final BankClient client,
                       final String     accountNumber,
                       final int        pin,
                       final Date       opened,
                       final Date       closed,
                       final double     initialBalance) 
                       {
        if (client == null ||
         accountNumber == null ||
                (accountNumber.length() != MIN_ACCOUNT_NUMBER_LENGTH &&
                    accountNumber.length() != MAX_ACCOUNT_NUMBER_LENGTH) ||
                    !accountNumber.matches("[0-9]+") ||
                     opened == null                  ||
                    initialBalance < MIN_BALANCE_USD) 
                {
            throw new IllegalArgumentException("Invalid bank account details.");
        }
        this.client        = client;
        this.accountNumber = accountNumber;
        this.pin           = pin;
        this.opened        = opened;
        this.closed        = closed;
        this.balanceUsd    = initialBalance;
    }

    /**
     * Deposit  USD money.
     *
     * @param amountUsd amount in USD
     */
    public void deposit(final double amountUsd) 
    {
        balanceUsd += amountUsd;
    }

    /**
     * withdraw USD money.
     * 
     * @param amountUsd amount in USD
     */
    public void withdraw(final double amountUsd) 
    {
        balanceUsd -= amountUsd;
    }

    /**
     * Withdraw USD money, requires PIN.
     *
     * @param amountUsd amount in USD
     *
     * @param pinToMatch PIN
     */
    public void withdraw(final double amountUsd,
                         final int pinToMatch)
    {
        if (pinToMatch == pin)
        {
            balanceUsd -= amountUsd;
        }
    }

    /**
     * Get details for bank account.
     *
     * @return bank account details.
     */
    public String getDetails() 
    {
        final String closedStr;

        if (closed == null)
        {
            closedStr = "and is still open.";
        } 
        else
        {
            closedStr = "and closed "               +
                    capitalize(closed.getDayName()) +
                    " "                             +
                    closed.getMonthName()           +
                    " "                             +
                    closed.getDay()                 +
                    ", "                            +
                    closed.getYear()                +
                    ".";
        }

        return client.getName()
                     .getFullName()
                                                +
                " had $"                        +
                balanceUsd                      +
                " USD in account #"             +
                accountNumber                   +
                " which he opened on "          +
                capitalize(opened.getDayName()) +
                " "                             +
                opened.getMonthName()           +
                " "                             +
                opened.getDay()                 +
                ", " + opened.getYear()         +
                " "                             +
                closedStr;
    }

    /**
     * Capitalizes the first letter of a day name for account details.
     *
     * @param dayName lowercase day name
     * 
     * @return day name with an uppercase first letter
     */
    private String capitalize(final String dayName) 
    {
        return dayName.substring(0, 1).toUpperCase() +
         dayName.substring(1);
    }

}
