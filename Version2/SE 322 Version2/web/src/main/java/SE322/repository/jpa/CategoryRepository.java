package SE322.repository.jpa;

import SE322.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//Every Repository that extends the JpaRepository has embedded methods in itself
//if we need some specific methods, we can easily do them like below
//
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByNameLike(String text);
    void deleteByName(String name);
}
