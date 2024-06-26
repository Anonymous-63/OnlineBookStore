package com.anonymous63.onlinebookstore.security;

import com.anonymous63.onlinebookstore.exceptions.ResourceNotFoundException;
import com.anonymous63.onlinebookstore.models.User;
import com.anonymous63.onlinebookstore.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepo.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", username));
    }
}
