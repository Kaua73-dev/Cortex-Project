package com.api.cortex.service.user;


import com.api.cortex.config.TokenConfig;
import com.api.cortex.exceptions.user.UserAlreadyExistException;
import com.api.cortex.exceptions.user.UserNotFoundException;
import com.api.cortex.model.dto.request.user.UserLoginRequest;
import com.api.cortex.model.dto.request.user.UserRegisterRequest;
import com.api.cortex.model.dto.response.user.UserLoginResponse;
import com.api.cortex.model.dto.response.user.UserRegisterResponse;
import com.api.cortex.model.entity.user.User;
import com.api.cortex.model.repository.user.UserRepository;
import com.api.cortex.service.email.EmailService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TokenConfig tokenConfig;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;


    public UserService(UserRepository userRepository, TokenConfig tokenConfig, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.tokenConfig = tokenConfig;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }


    public UserRegisterResponse register(UserRegisterRequest request){
        if(userRepository.findByEmail(request.email()).isPresent()){
             throw new UserAlreadyExistException();
        }

        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        User userSave = userRepository.save(user);
        emailService.emailRegister(user.getEmail());

        return new UserRegisterResponse(userSave.getName(), user.getEmail());

    }

    public UserLoginResponse login(UserLoginRequest request){

        if(userRepository.findByEmail(request.email()).isEmpty()){
            throw new UserNotFoundException();
        }

        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);
        User user = (User) authentication.getPrincipal();

        String token = tokenConfig.generateToken(user);
        return new UserLoginResponse(token);
    }



}
