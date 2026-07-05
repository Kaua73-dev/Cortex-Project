package com.api.cortex.auth;

import com.api.cortex.model.entity.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthVerifyService {

    public User getAuthenticate(){
        return (User) Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder
                        .getContext()
                        .getAuthentication())
                .getPrincipal()
        );
    }


}
