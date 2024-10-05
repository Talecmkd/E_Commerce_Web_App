package SE322.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout") // localhost:8080/logout
public class LogoutController {

    @GetMapping
    public String logout(HttpServletRequest request, Model model) {
        request.getSession().invalidate(); // we invalidate the session and we are waiting for a new one
        return "redirect:/login"; //we show the localhost:8080/login page
    }
}
