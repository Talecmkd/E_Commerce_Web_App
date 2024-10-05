package SE322.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ProductAlreadyInShoppingCartException extends RuntimeException{

    public ProductAlreadyInShoppingCartException(Long id, String username) {
        super(String.format("Product with id: %d already exists in shopping cart for user with username %s", id, username));
    } // we throw this HTTP Exception when we try to add the same product in the shopping cart twice
      // this can be seen when we do inspect element and go to the 'Network' label after trying to
      // add the same product twice
}

