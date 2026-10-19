package ca.bcit.comp2522.bank;

/**
 * Runs the banking system simulation for Lab 1.
 *
 * @author Abhia Salman
 * @author Evelyn Millman
 * @version 1.0
 */
public class Main
{
    /**
     * Runs the banking system examples required by the lab.
     *
     * @param args unused command-line arguments
     */
    public static void main(final String[] args)
    {
        final Name einsteinName;
        final BankClient einsteinClient;
        final BankAccount einsteinAccount;

        final Name mandelaName;
        final BankClient mandelaClient;
        final BankAccount mandelaAccount;

        final Name kahloName;
        final BankClient kahloClient;
        final BankAccount kahloAccount;

        final Name chanName;
        final BankClient chanClient;
        final BankAccount chanAccount;

        einsteinName = new Name("Albert", "Einstein");

        einsteinClient = new BankClient(
                einsteinName,
                new Date(1879, 3, 14),
                new Date(1955, 4, 18),
                new Date(1900, 1, 1),
                "1234567891011");

        einsteinAccount = new BankAccount(
                einsteinClient,
                1000.0,
                3141,
                "abc1234567891011121314",
                new Date(1900, 1, 1),
                new Date(1950, 10, 14));

        System.out.println(einsteinName.getInitials());
        System.out.println(einsteinName.getFullName());
        System.out.println(einsteinName.getReverseName());
        System.out.println(einsteinClient.getDetails());

        einsteinAccount.withdraw(100.0);

        System.out.println(einsteinAccount.getDetails());
        System.out.println();

        mandelaName = new Name("Nelson", "Mandela");

        mandelaClient = new BankClient(
                mandelaName,
                new Date(1918, 7, 18),
                new Date(2013, 12, 5),
                new Date(1994, 5, 10),
                "1110987654321");

        mandelaAccount = new BankAccount(
                mandelaClient,
                2000.0,
                4664,
                "151413121110987654321",
                new Date(1994, 5, 10),
                null);

        System.out.println(mandelaName.getInitials());
        System.out.println(mandelaName.getFullName());
        System.out.println(mandelaName.getReverseName());
        System.out.println(mandelaClient.getDetails());

        mandelaAccount.withdraw(200.0);

        System.out.println(mandelaAccount.getDetails());
        System.out.println();

        kahloName = new Name("Frida", "Kahlo");

        kahloClient = new BankClient(
                kahloName,
                new Date(1907, 7, 6),
                new Date(1954, 7, 13),
                new Date(1940, 1, 1),
                "frd123456789");

        kahloAccount = new BankAccount(
                kahloClient,
                500.0,
                1907,
                "frd123456789101112131415",
                new Date(1940, 1, 1),
                new Date(1954, 7, 13));

        System.out.println(kahloName.getInitials());
        System.out.println(kahloName.getFullName());
        System.out.println(kahloName.getReverseName());
        System.out.println(kahloClient.getDetails());

        kahloAccount.withdraw(50.0);

        System.out.println(kahloAccount.getDetails());
        System.out.println();

        chanName = new Name("Jackie", "Chan");

        chanClient = new BankClient(
                chanName,
                new Date(1954, 4, 7),
                null,
                new Date(1980, 10, 1),
                "chan789101112");

        chanAccount = new BankAccount(
                chanClient,
                3000.0,
                1954,
                "chan78910111213141516",
                new Date(1980, 10, 1),
                null);

        System.out.println(chanName.getInitials());
        System.out.println(chanName.getFullName());
        System.out.println(chanName.getReverseName());
        System.out.println(chanClient.getDetails());

        chanAccount.withdraw(500.0);

        System.out.println(chanAccount.getDetails());
    }
}