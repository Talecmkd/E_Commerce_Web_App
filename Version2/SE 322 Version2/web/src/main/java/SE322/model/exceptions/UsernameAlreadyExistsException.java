package SE322.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{

    public UsernameAlreadyExistsException(String username) {
        super(String.format("User with username: %s already exists", username));
    } // throwing this error when someone tries to register with a username that already exists
}

