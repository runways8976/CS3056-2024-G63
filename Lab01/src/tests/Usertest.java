package tests;
import model.User;

public class Usertest {

    private User user;
    private static final String TEXT_COLOR_GREEN = "\u001B[32m";
    private static final String TEXT_COLOR_RED = "\u001B[31m";
    private static final String TEXT_COLOR_RESET = "\u001B[0m";

    public Usertest() {
        user = new User("James13", "LAC01", "James", "Harden", "001LAC01");
    }

    public void testUserConstructor() {
        // Expected values
    	String testUsername = "James13";
    	String testPassword = "Bklyn013";
        String testFirstName = "James";
        String testLastName = "Harden";
        String testMobileNumber = "001Bklyn013";

        boolean passed = true;

        // Using if-else for validation
        if (!user.getUsername().equals(testUsername)) {
            System.out.println(TEXT_COLOR_RED + "TC1 failed: username did not match" + TEXT_COLOR_RESET);
            passed = false;
        } else {
            System.out.println(TEXT_COLOR_GREEN + "TC1 passed: username matched" + TEXT_COLOR_RESET);
        }
        if (!user.getPassword().equals(testPassword)) {
            System.out.println(TEXT_COLOR_RED + "TC2 failed: password did not match" + TEXT_COLOR_RESET);
            passed = false;
        } else {
            System.out.println(TEXT_COLOR_GREEN + "TC2 passed: password matched" + TEXT_COLOR_RESET);
        }
        if (!user.getFirst_name().equals(testFirstName)) {
            System.out.println(TEXT_COLOR_RED + "TC3 failed: first_name did not match" + TEXT_COLOR_RESET);
            passed = false;
        } else {
            System.out.println(TEXT_COLOR_GREEN + "TC3 passed: first_name matched" + TEXT_COLOR_RESET);
        }

        if (!user.getLast_name().equals(testLastName)) {
            System.out.println(TEXT_COLOR_RED + "TC4 failed: last_name did not match" + TEXT_COLOR_RESET);
            passed = false;
        } else {
            System.out.println(TEXT_COLOR_GREEN + "TC4 passed: last_name matched" + TEXT_COLOR_RESET);
        }

        if (!user.getMobile_number().equals(testMobileNumber)) {
            System.out.println(TEXT_COLOR_RED + "TC5 failed: mobile_number did not match" + TEXT_COLOR_RESET);
            passed = false;
        } else {
            System.out.println(TEXT_COLOR_GREEN + "TC5 passed: mobile_number matched" + TEXT_COLOR_RESET);
        }

        if (passed) {
            System.out.println(TEXT_COLOR_GREEN + "All TC's passed." + TEXT_COLOR_RESET);
        } else {
            System.out.println(TEXT_COLOR_RED + "Some TC's failed." + TEXT_COLOR_RESET);
        }
        
        assert user.getUsername().equals(testUsername):"TC1 failed: username did not match";
        assert user.getPassword().equals(testPassword):"TC2 failed: username did not match";
        assert user.getFirst_name().equals(testFirstName) : "TC3 failed: first_name did not match";
        assert user.getLast_name().equals(testLastName) : "TC4 failed: last_name did not match";
        assert user.getMobile_number().equals(testMobileNumber) : "TC5 failed: mobile_number did not match";
      
     // Failure tests for each field
        //assert false : "TC1 failure test: This should always fail for username";
        //assert false : "TC2 failure test: This should always fail for password";
        //assert false : "TC3 failure test: This should always fail for first_name";
       // assert false : "TC4 failure test: This should always fail for last_name";
        //assert false : "TC5 failure test: This should always fail for mobile_number";

        
        System.out.println("All Java assertions in the test suite passed (none failed).");
    }

    public static void main(String[] args) {
        Usertest test = new Usertest();
        test.testUserConstructor();
    }
}
