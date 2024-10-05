package SE322.service.impl;

import SE322.model.Role;
import SE322.model.exceptions.InvalidUsernameOrPasswordException;
import SE322.model.exceptions.PasswordsDoNotMatchException;
import SE322.model.exceptions.UsernameAlreadyExistsException;
import SE322.service.UserService;
import SE322.model.User;
import SE322.model.embeddables.UserAddress;
import SE322.repository.jpa.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    //we use repositories because we want to manipulate with the data in the actual databases
    //and as previously stated the JpaRepository already has a lot of existing methods, so we don't need to
    //write our own code so much
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new InvalidUsernameOrPasswordException(); // throwing an exception if the username or password are empty
        }

        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException(); // throwing an exception if the passwords don't match while registering
        }

        if(this.userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username); // we throw an error if the username already exists
        }

        User user = new User(
                username,
                passwordEncoder.encode(password),
                name,
                surname,
                new UserAddress(),
                role
        );

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
    } // finding a User by his username, or throwing an exception if the username isn't found
}
