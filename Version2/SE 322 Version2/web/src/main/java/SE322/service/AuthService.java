package SE322.service;

import SE322.model.User;

import java.util.List;

//we use service interfaces to declare the methods we want done in the service implementations
public interface AuthService {
    User login(String username, String password);

    List<User> findAll();
}
