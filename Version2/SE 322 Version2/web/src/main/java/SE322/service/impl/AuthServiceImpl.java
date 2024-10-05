package SE322.service.impl;

import SE322.model.exceptions.InvalidArgumentsException;
import SE322.model.exceptions.InvalidUserCredentialsException;
import SE322.service.AuthService;
import SE322.model.User;
import SE322.repository.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    //we use repositories because we want to manipulate with the data in the actual databases
    //and as previously stated the JpaRepository already has a lot of existing methods, so we don't need to
    //write our own code so much
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new InvalidArgumentsException();//throwing an error if username or password are empty
        }

        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
