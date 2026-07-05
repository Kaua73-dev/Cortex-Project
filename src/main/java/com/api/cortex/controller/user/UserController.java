package com.api.cortex.controller.user;


import com.api.cortex.model.dto.request.user.UserLoginRequest;
import com.api.cortex.model.dto.request.user.UserRegisterRequest;
import com.api.cortex.model.dto.response.user.UserLoginResponse;
import com.api.cortex.model.dto.response.user.UserRegisterResponse;
import com.api.cortex.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody UserLoginRequest request){
        return userService.login(request);
    }

    @PostMapping("/register")
    public UserRegisterResponse register(@RequestBody UserRegisterRequest request){
        return userService.register(request);
    }


}
