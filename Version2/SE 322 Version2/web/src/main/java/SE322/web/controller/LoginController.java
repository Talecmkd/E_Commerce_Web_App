package SE322.web.controller;

import SE322.model.User;
import SE322.model.exceptions.InvalidArgumentsException;
import SE322.model.exceptions.InvalidUserCredentialsException;
import SE322.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login") //localhost:8080/login
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage(Model model) {
        model.addAttribute("bodyContent", "login");//we add the login.html into the bodyContent attribute
        return "master-template"; //we show the login.html in the master-template
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model) {
        User user = null;

        try {
            user = authService.login(request.getParameter("username"), request.getParameter("password"));
        } catch (InvalidUserCredentialsException | InvalidArgumentsException exception) {//if the credentials are not valid we catch the exception here
            model.addAttribute("bodyContent", "login");
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "master-template";
        } //we put the hasError attribute to true and put the exception message in the error attribute
          //after that we show the whole master-template with the login.html page and the hasError and error attributes

        request.getSession().setAttribute("user", user); // we set the user object to the user attribute for the session
        return "redirect:/home"; //if there is no exception we just return to the home page with the given session
    }
}
