package SE322.repository.jpa;

import SE322.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//Every Repository that extends the JpaRepository has embedded methods in itself
//we can create additional methods easily, just like below
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);


}
