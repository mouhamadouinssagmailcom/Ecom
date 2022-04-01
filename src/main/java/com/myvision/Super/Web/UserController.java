package com.myvision.Super.Web;

import com.myvision.Super.Entity.User;
import com.myvision.Super.Repository.UserRepository;
import com.myvision.Super.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public String userPanel(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());

        if (user != null) {
            model.addAttribute("user", user);
        } else {
            return "error/404";
        }

        return "user";

    }

    @GetMapping("/customers")
    public String getAllcustomers() {
     List<User> userList=  userRepository.findAll();
        return "";
    }
}
