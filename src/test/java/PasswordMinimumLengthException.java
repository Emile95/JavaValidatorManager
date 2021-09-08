class PasswordMinimumLengthException extends Exception {
    PasswordMinimumLengthException(String password, int minimum) {
        super("Minimum " + Integer.toString(minimum) + " characters : you provide " + password.length() + " characters");
    }
}
