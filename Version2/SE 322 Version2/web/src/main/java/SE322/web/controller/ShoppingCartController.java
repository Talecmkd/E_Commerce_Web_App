package SE322.web.controller;

import SE322.model.enumerations.ShoppingCartStatus;
import jakarta.servlet.http.HttpServletRequest;
import SE322.model.ShoppingCart;
import SE322.service.AuthService;
import SE322.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shopping-cart") //localhost:8080/shopping-cart
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final AuthService authService;


    public ShoppingCartController(ShoppingCartService shoppingCartService, AuthService authService) {
        this.shoppingCartService = shoppingCartService;
        this.authService = authService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error,
                                      HttpServletRequest req,
                                      Model model) {
        //@RequestParam(required=false) means we don't need the parameter in order for the mapping to be completed
        //if there has been an error, we set the values to the attributes as stated below
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        String username = req.getRemoteUser(); //req.getRemoteUser() returns the username of the client that sent the request
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username);//we get the shopping cart with the received username
        model.addAttribute("products", this.shoppingCartService.listAllProductsInShoppingCart(shoppingCart.getId()));//we set all the products from the shopping cart in the products attribute
        model.addAttribute("bodyContent", "shopping-cart");//we set the shopping-cart.html into the bodyContent attribute
        return "master-template";//we show the shopping-cart.html into the master-template
    }

    @PostMapping("/add-product/{id}") //localhost:8080/shopping-cart/add-product/{id}
    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest req) {
        //with the @PathVariable we are expecting to receive all the stated variables, otherwise an error will occur.
        //with the HttpServletRequest we are getting the user that sent the request
        try {
            String username = req.getRemoteUser();//getting the user that sent the request
            ShoppingCart shoppingCart = this.shoppingCartService.addProductToShoppingCart(username, id);//adding the product with the given id to the shopping cart of the username we got
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {//if there is an exception we don't go through with the adding product to the shopping-cart
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }

    @GetMapping("/edit")
    public String showEditShoppingCart(HttpServletRequest req, Model model) {
        String username = req.getRemoteUser();
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username);
        model.addAttribute("cart", shoppingCart);
        model.addAttribute("statuses", ShoppingCartStatus.values());
        model.addAttribute("bodyContent", "shopping-cart-edit");
        return "master-template";
    }
    @PostMapping("/update/{id}")
    public String updateShoppingCart(@RequestParam ShoppingCartStatus status,
                                     @PathVariable Long id,
                                     HttpServletRequest req) {

        Optional<ShoppingCart> shoppingCart = this.shoppingCartService.findById(id);
        shoppingCart.ifPresent((cart) -> {
            cart.setStatus(status);
            this.shoppingCartService.save(cart);
        });

        return "redirect:/shopping-cart";
    }

    @GetMapping("/filter")
    public String getFilterShoppingCartsPage(Model model) {
        List<ShoppingCart> carts = shoppingCartService.findAll();
        model.addAttribute("carts", carts);
        model.addAttribute("bodyContent", "shopping-carts-filtered");
        return "master-template";
    }
    @PostMapping("/filter")
    public String filterShoppingCarts(@RequestParam LocalDateTime from,
                                      @RequestParam LocalDateTime to,
                                      Model model) {
        List<ShoppingCart> filteredCarts = shoppingCartService.filterByDateTimeBetween(from, to);
        model.addAttribute("carts", filteredCarts);
        model.addAttribute("bodyContent", "shopping-carts-filtered");
        return "master-template";

    }









}
