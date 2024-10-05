package SE322.service;

import SE322.model.Category;

import java.util.List;
import java.util.Optional;
//we use service interfaces to declare the methods we want done in the service implementations

public interface CategoryService {
    List<Category> listCategories();

    Optional<Category> findById(Long id);

    Category create(String name, String description);

    Category update(String name, String description);

    void delete(String name);

    List<Category> searchCategories(String text);
}
