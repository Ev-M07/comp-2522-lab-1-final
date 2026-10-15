package ca.bcit.comp2522.bank;

/**
 * Demonstrates the banking system.
 *
 * @author Abhia Salman
 * 
 * @author Evelyn Millman
 * 
 * @version 1.0
 */
public class Main
{

    /**
     * Runs the banking system demonstration.
     *
     * @param args command-line arguments, which are not used
     */
        public static void main(final String[] args)
        {
        final Name einsteinName;
        final Date einBirth;
        final Date einDeath;
        final Date einSignup;
        final BankClient einClient;
        final BankAccount einAccount;

        final Name mandelaName;
        final Date mandelaBirth;
        final Date mandelaDeath;
        final Date mandelaSignup;
        final BankClient mandelaClient;
        final BankAccount mandelaAccount;

        final Name kahloName;
        final Date kahloBirth;
        final Date kahloDeath;
        final Date kahloSignup;
        final BankClient kahloClient;
        final BankAccount kahloAccount;

        final Name chanName;
        final Date chanBirth;
        final Date chanSignup;
        final BankClient chanClient;
        final BankAccount chanAccount;

        einsteinName = new Name("Albert", "Einstein");
        einBirth     = new Date(1879, 3, 14);
        einDeath     = new Date(1955, 4, 18);
        einSignup    = new Date(1900, 1, 1);
        einClient    = new BankClient(einsteinName, einBirth, einDeath, einSignup, "123456");
        einAccount   = new BankAccount(einClient, "123456", 3141, einSignup,
                new Date(1950, 10, 14), 1000);
        einAccount.withdraw(100);
        printDetails(einsteinName, 
        einClient,
         einAccount);

        mandelaName   = new Name("Nelson", "Mandela");
        mandelaBirth  = new Date(1918, 7, 18);
        mandelaDeath  = new Date(2013, 12, 5);
        mandelaSignup = new Date(1994, 5, 10);
        mandelaClient = new BankClient(mandelaName, mandelaBirth, mandelaDeath,
                mandelaSignup, "654321");
        mandelaAccount = new BankAccount(mandelaClient, "654321", 4664,
                mandelaSignup, null, 2000);
        mandelaAccount.withdraw(200);
        printDetails(mandelaName,
         mandelaClient,
          mandelaAccount);

        kahloName   = new Name("Frida", "Kahlo");
        kahloBirth  = new Date(1907, 7, 6);
        kahloDeath  = new Date(1954, 7, 13);
        kahloSignup = new Date(1940, 1, 1);
        kahloClient = new BankClient(kahloName, kahloBirth, kahloDeath,
                kahloSignup, "123457");
        kahloAccount = new BankAccount(kahloClient, "123457", 1907,
                kahloSignup, kahloDeath, 500);
        kahloAccount.withdraw(50);
        printDetails(kahloName,
         kahloClient,
          kahloAccount);

        chanName   = new Name("Jackie", "Chan");
        chanBirth  = new Date(1954, 4, 7);
        chanSignup = new Date(1980, 10, 1);
        chanClient = new BankClient(chanName, chanBirth, null, chanSignup, "123458");
        chanAccount = new BankAccount(chanClient, "123458", 1954,
                chanSignup, null, 3000);
        chanAccount.withdraw(500);
        printDetails(chanName,
         chanClient,
          chanAccount);
    }

        private static void printDetails(final Name name,
                                        final BankClient client,
                                        final BankAccount account)
        {
        System.out.println(name.getInitials());
        System.out.println(name.getFullName());
        System.out.println(name.getReverseName());
        System.out.println(client.getDetails());
        System.out.println(account.getDetails());
    }
}
