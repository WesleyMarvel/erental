package com.example.erental.api;

import com.example.erental.domain.Users;
import com.example.erental.service.UserService;
import com.example.erental.service.UsersDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String admin(Model model){
        List<Users> userInformation = userService.getAll();
        model.addAttribute("userInformation", userInformation);
        return "admin";
    }

    @GetMapping("/users")
    public String users(Model model){
        List<Users> userInformation = userService.getAll();
        model.addAttribute("userInformation", userInformation);
        return "users";
    }

    @GetMapping("/create-user")
    public String createUserForm(Model model){
        model.addAttribute("usersDto", new UsersDto());
        return "create-user";
    }
    @PostMapping("/create-new-user")
    public String createUser(@ModelAttribute("usersDto") UsersDto usersDto) throws IOException {
        userService.createUser(usersDto.getFirstName(),
                usersDto.getLastName(),
                usersDto.getPassword(),
                usersDto.getRole(),
                usersDto.getStatus());
        return "redirect:/admin";
    }
}
