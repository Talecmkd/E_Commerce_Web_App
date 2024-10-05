package SE322.repository.jpa;

import SE322.model.ShoppingCart;
import SE322.model.User;
import SE322.model.enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
//Every Repository that extends the JpaRepository has embedded methods in itself
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {

    Optional<ShoppingCart> findByUserUsernameAndStatus(String username, ShoppingCartStatus created);
    List<ShoppingCart> findByDateCreatedBetween(LocalDateTime from, LocalDateTime to);



}
