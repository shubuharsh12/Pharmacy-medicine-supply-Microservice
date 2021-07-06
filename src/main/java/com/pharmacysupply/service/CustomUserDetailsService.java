package com.pharmacysupply.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if(userName.equals("pharmacy@gmail.com")){
            return  new User("pharmacy@gmail.com","root123456",new ArrayList<>());
        }
        else{
            throw new UsernameNotFoundException("User not found!!");
        }

    }
}
