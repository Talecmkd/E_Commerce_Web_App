package SE322.service;

import SE322.model.Product;

import java.util.List;
import java.util.Optional;
//we use service interfaces to declare the methods we want done in the service implementations

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    Optional<Product> save(String name, Double price,
                           Integer quantity, Long category, Long manufacturer);

    void deleteById(Long id);

    Optional<Product> edit(Long id, String name, Double price,
                           Integer quantity, Long category, Long manufacturer);
}
