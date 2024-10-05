package SE322.web.controller;

import SE322.model.Category;
import SE322.model.Manufacturer;
import SE322.model.Product;
import SE322.service.CategoryService;
import SE322.service.ManufacturerService;
import SE322.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products") //localhost:8080/products
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             ManufacturerService manufacturerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping()
    public String getProductPage(@RequestParam(required = false) String error, Model model) {
        //@RequestParam(required=false) means we don't need the parameter in order for the mapping to be completed
        //if there has been an error, we set the values to the attributes as stated below
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Product> products = this.productService.findAll();
        model.addAttribute("products", products);//we set all the products into the products attribute
        model.addAttribute("bodyContent", "products");// we set the products.html into the bodyContent attribute
        return "master-template"; //we show the products.html into the master-template
    }

    @PostMapping("/delete/{id}") //localhost:8080/products/delete/{id}
    @PreAuthorize("hasRole('ADMIN')")// this action can be done only by the user with the role ADMIN
    public String deleteProduct(@PathVariable Long id) {
        this.productService.deleteById(id); //we delete the product from the database through the productService
        return "redirect:/products"; // we show  the localhost:8080/products page
    }

    @GetMapping("/edit-form/{id}") //localhost:8080/products/edit-form/{id}
    @PreAuthorize("hasRole('ADMIN')")// this action can be done only by the user with the role ADMIN
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.productService.findById(id).isPresent()) { //we check if the product with the given ID exists
            Product product = this.productService.findById(id).get(); //we put the product into the product object
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();//we list all the manufacturers
            List<Category> categories = this.categoryService.listCategories();//we list all the categories

            model.addAttribute("manufacturers", manufacturers);//we set the manufacturers into the manufacturers attribute
            model.addAttribute("categories", categories);//we set the categories into the categories attribute
            model.addAttribute("product", product);//we set the product into the product attribute
            model.addAttribute("bodyContent", "edit-product");//we set the edit-product.html into the bodyContent attribute
            return "master-template";//we show the edit-product.html
            //because we've set the attributes in the model, we can work with them in the edit-product.html
        }

        return "redirect:/products?error=ProductNotFound"; //redirect to localhost:8080/products?error=ProductNotFound if the product is not found by its ID
    }

    @GetMapping("/add-form")//localhost:8080/products/add-form   we get here through a html form with the method get and action /products/add-form
    @PreAuthorize("hasRole('ADMIN')")// this action can be done only by the user with the role ADMIN
    public String addProductPage(Model model) {
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        List<Category> categories = this.categoryService.listCategories();

        model.addAttribute("manufacturers", manufacturers);//we set the manufacturers into the manufacturers attribute
        model.addAttribute("categories", categories);//we set the categories into the categories attribute
        model.addAttribute("bodyContent", "add-product");//we set the add-product.html into the bodyContent attribute
        return "master-template";//we shot the add-product html
    }

    @PostMapping("/add") //localhost:8080/products/add
    @PreAuthorize("hasRole('ADMIN')")//this action can be done only by the user with the role ADMIN
    public String saveProduct(@RequestParam String name,
                              @RequestParam Double price,
                              @RequestParam Integer quantity,
                              @RequestParam Long category,
                              @RequestParam Long manufacturer) {
        //with the @RequestParam we are expecting to receive all the stated variables, otherwise an error will occur.
        //the stated variables are sent through the html form with the method=post and they have to have the same names both here and in the html
        this.productService.save(name, price, quantity, category, manufacturer); //we save the product in the database
        return "redirect:/products";//we show the page localhost:8080/products with the new product added
    }

    @PostMapping("/edit/{id}")//localhost:8080/products/edit/{id}
    @PreAuthorize("hasRole('ADMIN')")// this action can be done only by the user with the role ADMIN
    public String editProduct(@PathVariable("id") Long id,
                              @RequestParam String name,
                              @RequestParam Double price,
                              @RequestParam Integer quantity,
                              @RequestParam Long category,
                              @RequestParam Long manufacturer) {
        //with the @PathVariable and @RequestParam we are expecting to receive all the stated variables, otherwise an error will occur.
        //the stated variables are sent through the html form with the method=post and they have to have the same names both here and in the html
        this.productService.edit(id, name, price, quantity, category, manufacturer);//we save the edited product into the database
        return "redirect:/products";//we show the page with the edited product
    }
}
