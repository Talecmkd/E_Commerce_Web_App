package SE322.service.impl;

import SE322.model.Category;
import SE322.service.CategoryService;
import SE322.repository.jpa.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    //we use repositories because we want to manipulate with the data in the actual databases
    //and as previously stated the JpaRepository already has a lot of existing methods, so we don't need to
    //write our own code so much

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private boolean categoryInvalid(String name) {
        return name == null || name.isEmpty();
    }

    @Override
    public List<Category> listCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return this.categoryRepository.findById(id);
    }

    @Override
    public Category create(String name, String description) {
        if (categoryInvalid(name)) {
            throw new IllegalArgumentException();
        }

        Category category = new Category(name, description);
        return this.categoryRepository.save(category);
    }

    @Override
    public Category update(String name, String description) {
        return create(name, description);
    }

    @Override
    public void delete(String name) {
        categoryRepository.deleteByName(name);
    }

    @Override
    public List<Category> searchCategories(String text) {
        return categoryRepository.findAllByNameLike(text);
    }
}
