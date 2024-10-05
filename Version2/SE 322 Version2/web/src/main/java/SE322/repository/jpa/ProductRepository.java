package SE322.repository.jpa;

import SE322.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//Every Repository that extends the JpaRepository has embedded methods in itself

public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByName(String name);
}
