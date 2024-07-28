package store.mgmt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import store.mgmt.dto.UserDto;
import store.mgmt.dto.UserLoginDto;
import store.mgmt.service.UserService;

@RestController
@RequestMapping("v1.0/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String registerUser(@RequestBody UserDto userDto) {
        userService.registerUser(userDto);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody UserLoginDto userLoginDto) {
        // Authentication logic to be added
        return userService.loginUser(userLoginDto);
    }

}
