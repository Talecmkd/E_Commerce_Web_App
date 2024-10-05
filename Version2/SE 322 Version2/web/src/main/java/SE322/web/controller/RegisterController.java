package SE322.web.controller;

import SE322.model.Role;
import SE322.model.exceptions.InvalidArgumentsException;
import SE322.model.exceptions.PasswordsDoNotMatchException;
import SE322.model.exceptions.UsernameAlreadyExistsException;
import SE322.service.AuthService;
import SE322.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register") //localhost:8080/register
public class RegisterController {

    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        //@RequestParam(required=false) means we don't need the parameter in order for the mapping to be completed
        //if there has been an error, we set the values to the attributes as stated below
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("bodyContent", "register");//we set the register.html into the bodyContent attribute
        return "master-template"; //we show the register.html into the master-template
    }

    @PostMapping//localhost:8080/register
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam Role role
    ) {
        //with the @RequestParam we are expecting to receive all the stated variables, otherwise an error will occur.
        //the stated variables are sent through the html form with the method=post and they have to have the same names both here and in the html
        try{ //we try to register the User
            this.userService.register(username, password, repeatedPassword, name, surname, role);
            return "redirect:/login";
        } // if there is an Exception we don't go through with the registering and we throw the exception
        catch (InvalidArgumentsException | PasswordsDoNotMatchException | UsernameAlreadyExistsException exception) {
            return "redirect:/register?error=" + exception.getMessage(); // localhost:8080/register?error=exceptionmessage
        }
    }
}
