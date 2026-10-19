package ca.bcit.comp2522.bank;

/**
 * Represents a bank account belonging to a BankClient.
 *
 * @author Abhia Salman
 * @author Evelyn Millman
 * @version 1.0
 */
public class BankAccount
{
    private static final int MIN_ACCOUNT_NUMBER_LENGTH;
    private static final int MAX_ACCOUNT_NUMBER_LENGTH;

    private final BankClient client;
    private double          balanceUsd;
    private final int       pin;
    private final String    accountNumber;
    private final Date      accountOpened;
    private final Date      accountClosed;

    static
    {
        MIN_ACCOUNT_NUMBER_LENGTH = 21;
        MAX_ACCOUNT_NUMBER_LENGTH = 30;
    }

    /**
     * Constructs a bank account.
     *
     * @param client        the owner of the account
     * @param balanceUsd    the starting balance in USD
     * @param pin           the account PIN
     * @param accountNumber the six or seven character account number
     * @param accountOpened the date the account opened
     * @param accountClosed the closing date, or null if still open
     *
     * @throws IllegalArgumentException if account information is invalid
     */
    public BankAccount(final BankClient client,
                       final double     balanceUsd,
                       final int        pin,
                       final String     accountNumber,
                       final Date       accountOpened,
                       final Date       accountClosed)
    {

        this.client        = validateClient(client);
        this.balanceUsd    = validateBalanceUsd(balanceUsd);
        this.pin           = pin;
        this.accountNumber = validateAccountNumber(accountNumber);
        this.accountOpened = validateAccountOpened(accountOpened);
        this.accountClosed = accountClosed;
    }

    /**
     * Validate Bank client.
     * @param client client to validate
     * @return       client if valid
     */
    private static BankClient validateClient(final BankClient client)
    {
        if (client == null)
        {
            throw new IllegalArgumentException(
                    "Client cannot be null.");
        }

        return client;
    }

    /**
     * Validate balance in USD.
     * @param balanceUsd balanceUsd to validate
     * @return           balanceUsd if valid
     */
    private static double validateBalanceUsd(final double balanceUsd)
    {
        final int zeroBalance;
        zeroBalance = 0;

        if (balanceUsd < zeroBalance)
        {
            throw new IllegalArgumentException(
                    "Balance cannot be negative.");
        }

        return balanceUsd;
    }

    /**
     * Validate account number.
     * @param accountNumber account number to validate
     * @return           account number if valid
     */
    private static String validateAccountNumber(final String accountNumber)
    {
        if (accountNumber == null)
        {
            throw new IllegalArgumentException(
                    "Account number cannot be null.");
        }

        if (accountNumber.length() < MIN_ACCOUNT_NUMBER_LENGTH ||
                accountNumber.length() > MAX_ACCOUNT_NUMBER_LENGTH)
        {
            throw new IllegalArgumentException(
                    "Account number must contain {@link #MIN_ACCOUNT_NUMBER_LENGTH} " +
                            "or {@link #MAX_ACCOUNT_NUMBER_LENGTH} characters.");
        }

        return accountNumber;
    }

    private static Date validateAccountOpened(final Date accountOpened)
    {
        if (accountOpened == null)
        {
            throw new IllegalArgumentException(
                    "Account opening date cannot be null.");
        }

        return accountOpened;
    }

    /**
     * Deposits money into the account.
     *
     * @param amountUsd the amount in USD to deposit
     *
     * @throws IllegalArgumentException if the amount is not positive
     */
    public void deposit(final double amountUsd)
    {
        if (amountUsd <= 0)
        {
            throw new IllegalArgumentException(
                    "Deposit amount must be positive.");
        }

        balanceUsd += amountUsd;
    }

    /**
     * Withdraws money without checking a PIN.
     *
     * @param amountUsd the amount in USD to withdraw
     *
     * @throws IllegalArgumentException if the amount is invalid
     */
    public void withdraw(final double amountUsd)
    {
        validateWithdrawal(amountUsd);

        balanceUsd -= amountUsd;
    }

    /**
     * Withdraws money after checking the supplied PIN.
     *
     * @param amountUsd  the amount in USD to withdraw
     * @param pinToMatch the PIN supplied for authentication
     *
     * @throws IllegalArgumentException if the PIN or amount is invalid
     */
    public void withdraw(final double amountUsd,
                         final int pinToMatch)
    {
        if (pin != pinToMatch)
        {
            throw new IllegalArgumentException(
                    "Incorrect PIN.");
        }

        withdraw(amountUsd);
    }

    /**
     * Validates a requested withdrawal.
     *
     * @param amountUsd the requested amount in USD
     *
     * @throws IllegalArgumentException if the amount cannot be withdrawn
     */
    private void validateWithdrawal(final double amountUsd)
    {
        if (amountUsd <= 0)
        {
            throw new IllegalArgumentException(
                    "Withdrawal amount must be positive.");
        }

        if (amountUsd > balanceUsd)
        {
            throw new IllegalArgumentException(
                    "Insufficient funds.");
        }
    }

    /**
     * Returns the current account balance.
     *
     * @return the balance in USD
     */
    public double getBalanceUsd()
    {
        return balanceUsd;
    }

    /**
     * Returns formatted information about the bank account.
     *
     * @return the account details
     */
    public String getDetails()
    {
        final StringBuilder details;

        details = new StringBuilder();

        details.append(client.getName().getFullName());
        details.append(" had $");
        details.append(String.format("%.0f", balanceUsd));
        details.append(" USD in account #");
        details.append(accountNumber);
        details.append(" which ");
        details.append("opened on ");
        details.append(capitalize(accountOpened.getDayOfWeek()));
        details.append(" ");
        details.append(accountOpened.getMonth());
        details.append(" ");
        details.append(accountOpened.getDay());
        details.append(", ");
        details.append(accountOpened.getYear());

        if (accountClosed != null)
        {
            details.append(" and closed ");
            details.append(capitalize(
                    accountClosed.getDayOfWeek()));
            details.append(" ");
            details.append(accountClosed.getMonth());
            details.append(" ");
            details.append(accountClosed.getDay());
            details.append(", ");
            details.append(accountClosed.getYear());
        }

        details.append(".");

        return details.toString();
    }

    /**
     * Capitalizes the first character of a word.
     *
     * @param word the word to capitalize
     *
     * @return the capitalized word
     */
    private static String capitalize(final String word)
    {
        return word.substring(0, 1).toUpperCase() +
                word.substring(1);
    }
}
