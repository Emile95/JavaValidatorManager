class PasswordMaximumLengthException extends Exception {
    PasswordMaximumLengthException(String password, int maximum) {
        super("Maximum " + Integer.toString(maximum) + " characters : you provide " + password.length() + " characters");
    }
}
