package SE322.model.exceptions;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(Long categoryId) {
        super(String.format("Category with id %d does not exist.", categoryId));
    } // throwing this error when we can't find a category
}
