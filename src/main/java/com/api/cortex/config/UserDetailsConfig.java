package com.api.cortex.config;


import com.api.cortex.exceptions.user.UserNotFoundException;
import com.api.cortex.model.repository.user.UserRepository;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsConfig implements UserDetailsService {


    private final UserRepository userRepository;

    public UserDetailsConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @NonNull
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
        return (UserDetails) userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }


}
