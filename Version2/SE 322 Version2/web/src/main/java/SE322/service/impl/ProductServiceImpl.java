package SE322.service.impl;

import SE322.model.Category;
import SE322.model.Manufacturer;
import SE322.model.Product;
import SE322.model.exceptions.CategoryNotFoundException;
import SE322.model.exceptions.ManufacturerNotFoundException;
import SE322.model.exceptions.ProductNotFoundException;
import SE322.service.ProductService;
import SE322.repository.jpa.CategoryRepository;
import SE322.repository.jpa.ManufacturerRepository;
import SE322.repository.jpa.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    //we use repositories because we want to manipulate with the data in the actual databases
    //and as previously stated the JpaRepository already has a lot of existing methods, so we don't need to
    //write our own code so much
    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ManufacturerRepository manufacturerRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    public Optional<Product> save(
            String name,
            Double price,
            Integer quantity,
            Long categoryId,
            Long manufacturerId
    ) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId)); // throwing an exception if the manufacturer doesn't exist
        return Optional.of(this.productRepository.save(new Product(name, price, quantity, category, manufacturer)));
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> edit(Long id, String name, Double price, Integer quantity, Long category, Long manufacturer) {
        Product p = productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id));
        Category c = categoryRepository.findById(category).orElseThrow(()->new CategoryNotFoundException(category));
        Manufacturer m = manufacturerRepository.findById(manufacturer).orElseThrow(()->new ManufacturerNotFoundException(manufacturer));
        p.setName(name);
        p.setCategory(c);
        p.setManufacturer(m);
        p.setPrice(price);
        p.setQuantity(quantity);
        productRepository.save(p);
        return Optional.of(p);
    }
}