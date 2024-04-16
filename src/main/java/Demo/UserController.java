package Demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Handle user registration
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@ModelAttribute User newUser) {
        Optional<User> existingUser = userRepository.findByEmail(newUser.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        existingUser = userRepository.findByUsername(newUser.getUsername());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Error: Username is already in use!");
        }

        // Password is stored as plain text, which is not recommended for actual applications
        userRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully!");
    }

    // Handle user login
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (!existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Error: User not found!");
        }

        User user = existingUser.get();
        if (!user.getPassword().equals(password)) {
            return ResponseEntity.badRequest().body("Error: Invalid password!");
        }

        // Ideally, here you would create a session or a token for the user
        return ResponseEntity.ok("User logged in successfully!");
    }

    // Redirect to registration page
    @GetMapping("/register")
    public ModelAndView registerPage() {
        return new ModelAndView("register");
    }

    // Redirect to login page
    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @GetMapping("/all") public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

}