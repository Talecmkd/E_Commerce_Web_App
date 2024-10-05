package SE322.web.controller;

import SE322.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories") //localhost:8080/categories takes us to this page
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping // we show the page which is written in the categories.html
    public String getCategoriesPage(Model model) {
        model.addAttribute("bodyContent", "categories"); //we pass the categories.html to the bodyContent in the master-template
        model.addAttribute("categories", categoryService.listCategories());// the categories variable has all the categories listed in it, we do this with the categoryService.listCategories()
        return "master-template"; //we show the master template
    }
}
