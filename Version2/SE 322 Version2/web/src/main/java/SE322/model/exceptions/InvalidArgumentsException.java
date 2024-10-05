package SE322.model.exceptions;

public class InvalidArgumentsException extends RuntimeException {

    public InvalidArgumentsException() {
        super("Invalid argument.");
    } // throwing this for authentication errors
}
