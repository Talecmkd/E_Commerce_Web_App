package SE322.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException {

    public PasswordsDoNotMatchException() {
        super("The Password and Repeat password fields do not match.");
    }
    // Throwing this error when the passwords in the register field don't match
}
