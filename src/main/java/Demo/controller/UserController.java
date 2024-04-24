package Demo.controller;

import Demo.model.User;
import Demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String loginForm() {
        return "index";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam("email") String email,
                              @RequestParam("password") String password,
                              Model model) {
        Optional<User> user = userRepository.findById(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return "redirect:/dashboard";
        } else {
            return "redirect:/login";
        }
    }
    @PostMapping("/register")
    public String handleregister(@RequestParam("regisemail") String regisemail,
                              @RequestParam("regispassword") String regispassword,
                              Model model) {
        if (userRepository.findById(regisemail).isPresent()) {
            return "redirect:/login";
        }
        User newUser = new User(regisemail, regispassword);
        userRepository.save(newUser);
        model.addAttribute("message", "Registration successful!");
        return "redirect:/login";
    }

}
