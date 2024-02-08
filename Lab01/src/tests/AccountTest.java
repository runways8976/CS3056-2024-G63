package tests;
import model.Account;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AccountTest {

    private Account account;
    private static final String TEXT_COLOR_GREEN = "\u001B[32m";
    private static final String TEXT_COLOR_RED = "\u001B[31m";
    private static final String TEXT_COLOR_RESET = "\u001B[0m";

    public AccountTest() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        account = new Account("LAC01", "James01", "Checking", sdf.parse("2024-02-05"));
    }

    public void testAccountConstructor() {
     
        String testAccountNumber = "LAC01";
        String testUsername = "James13";
        String testAccountType = "Checking";
        String testOpeningDate = "2024-02-05";

        boolean passed = true;

  
        if (!account.getAccount_number().equals(testAccountNumber)) {
            System.out.println(TEXT_COLOR_RED + "TC1 failed: account number did not match" + TEXT_COLOR_RESET);
            passed = false;
        } else {
            System.out.println(TEXT_COLOR_GREEN + "TC1 passed: account number matched" + TEXT_COLOR_RESET);
        }
        if (!account.getUsername_of_account_holder().equals(testUsername)) {
            System.out.println(TEXT_COLOR_RED + "TC2 failed: username did not match" + TEXT_COLOR_RESET);
            passed = false;
        } else {
            System.out.println(TEXT_COLOR_GREEN + "TC2 passed: username matched" + TEXT_COLOR_RESET);
        }
        if (!account.getAccount_type().equals(testAccountType)) {
            System.out.println(TEXT_COLOR_RED + "TC3 failed: account type did not match" + TEXT_COLOR_RESET);
            passed = false;
        } else {
            System.out.println(TEXT_COLOR_GREEN + "TC3 passed: account type matched" + TEXT_COLOR_RESET);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (!sdf.format(account.getAccount_opening_date()).equals(testOpeningDate)) {
            System.out.println(TEXT_COLOR_RED + "TC4 failed: opening date did not match" + TEXT_COLOR_RESET);
            passed = false;
        } else {
            System.out.println(TEXT_COLOR_GREEN + "TC4 passed: opening date matched" + TEXT_COLOR_RESET);
        }

        if (passed) {
            System.out.println(TEXT_COLOR_GREEN + "All TC's passed." + TEXT_COLOR_RESET);
        } else {
            System.out.println(TEXT_COLOR_RED + "Some TC's failed." + TEXT_COLOR_RESET);
        }
        
        // Using assert's for validation
        assert account.getAccount_number().equals(testAccountNumber) : "TC1 failed: account number did not match";
        assert account.getUsername_of_account_holder().equals(testUsername) : "TC2 failed: username did not match";
        assert account.getAccount_type().equals(testAccountType) : "TC3 failed: account type did not match";
        assert sdf.format(account.getAccount_opening_date()).equals(testOpeningDate) : "TC4 failed: opening date did not match";

        // Failure tests for each field
        // assert false : "TC1 failure test: This should always fail for account number";
        // assert false : "TC2 failure test: This should always fail for username";
        // assert false : "TC3 failure test: This should always fail for account type";
        // assert false : "TC4 failure test: This should always fail for opening date";
        
        System.out.println("All Java assertions in the test suite passed (none failed).");
    }

    public static void main(String[] args) {
        try {
            AccountTest test = new AccountTest();
            test.testAccountConstructor();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
