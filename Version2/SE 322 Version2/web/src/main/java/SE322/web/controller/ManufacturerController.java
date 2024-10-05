package SE322.web.controller;

import SE322.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manufacturers") // localhost:8080/manufacturers
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getManufacturersPage(Model model) {
        //we put the manufacturers.html into the bodyContent attribute
        model.addAttribute("bodyContent", "manufacturers");
        //we put all the manufacturers into the manufacturers attribute so we can work with them in the html
        model.addAttribute("manufacturers", manufacturerService.findAll());
        return "master-template"; // we show the manufacturers.html
    }
}
