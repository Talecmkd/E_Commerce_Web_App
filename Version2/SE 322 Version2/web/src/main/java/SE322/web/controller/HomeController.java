package SE322.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/home", "/"}) // localhost:8080/ and localhost:8080/home
public class HomeController {

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("bodyContent", "home"); //we put the home.html into the bodyContent variable
        return "master-template";// we show the page of home.html in the master-template
    }


}
