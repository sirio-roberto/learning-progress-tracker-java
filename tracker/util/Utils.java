package tracker.util;

public class Utils {
    public static String getMiddleStringFromArray(String[] fields) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < fields.length - 1; i++) {
            result.append(fields[i]).append(" ");
        }
        return result.toString().trim();
    }

    public static boolean areValidUserCredentials(String credentials) {
        return true;
    }
}
