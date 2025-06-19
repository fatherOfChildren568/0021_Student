package utility;

import constant.Message;

public class Validator {

    // constructor
    private Validator() {

    }

    // check limit in range
    public static boolean isLimitInRange(int input, int min, int max) {
        return input >= min && input <= max;
    }

    // is yes or no. Yes => true. No => false
    public static boolean isValidYesNo(String addMore) {
        if (addMore.equalsIgnoreCase("Y")) {
            return true;
        } else if (addMore.equalsIgnoreCase("N")) {
            return false;
        } else {
            System.out.println(Message.ERROR_INPUT_YES_NO);
            return false;
        }
    }

    public static boolean isValidId(int id) {
        return id > 0;
    }

    public static boolean isValidString(String input) {
        return (!input.trim().isEmpty() && input != null);
    }

    public static boolean isValidUpdateDelete(String action) {
        return action != null && (action.equalsIgnoreCase("U") ||
                action.equalsIgnoreCase("D"));
    }

    public static boolean isValidSemester(int semester, int min, int max) {
        return semester >= min && semester <= max;
    }
}
