package main.utilities;

public class Utilities {
    /**
     * Validates if the given phone number is in a valid format.
     *
     * @param phoneNumber   The phone number to validate
     * @return  True if the phone number is valid, false otherwise
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}");
    }
}
