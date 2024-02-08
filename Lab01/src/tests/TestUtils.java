package tests;

public class TestUtils {

    public static final String TEXT_COLOR_GREEN = "\u001B[32m";
    public static final String TEXT_COLOR_RED = "\u001B[31m";
    public static final String TEXT_COLOR_YELLOW = "\u001B[33m";
    public static final String TEXT_COLOR_RESET = "\u001B[0m";

    public static String formatTextInColor(String text, String colorCode) {
        return colorCode + text + TEXT_COLOR_RESET;
    }

    public static void logPass(String description) {
        String message = "[PASS] " + description;
        System.out.println(formatTextInColor(message, TEXT_COLOR_GREEN));
    }

    public static void logFail(String description) {
        String message = "[FAIL] " + description;
        System.out.println(formatTextInColor(message, TEXT_COLOR_RED));
    }

    public static void logWarning(String description) {
        String message = "[WARNING] " + description;
        System.out.println(formatTextInColor(message, TEXT_COLOR_YELLOW));
    }
}
