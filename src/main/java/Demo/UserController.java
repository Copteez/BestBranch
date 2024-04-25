package Demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @GetMapping("/login")
    public String loginForm() {
        return "index";  // This assumes the login form is named 'index.html'
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam("email") String email,
                              @RequestParam("password") String password,
                              Model model) {
        if ("user@example.com".equals(email) && "password".equals(password)) {
            return "dashboard"; // Redirect to the dashboard on successful login
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "index"; // This should be "index" to return to the login page
        }
    }


    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Add attributes to the model as needed for the dashboard
        return "Dashboard";  // Ensure there's a 'Dashboard.html' in your templates directory
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";  // This assumes the registration form is named 'register.html'
    }
}
