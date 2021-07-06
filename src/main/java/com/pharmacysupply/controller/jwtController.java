package com.pharmacysupply.controller;

import com.pharmacysupply.helpers.JwtUtil;
import com.pharmacysupply.model.JwtRequest;
import com.pharmacysupply.model.jwtResponse;
import com.pharmacysupply.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class jwtController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customerUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @RequestMapping(value = "/token",method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest);
        try{
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));

        }catch (UsernameNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("Username not found");
        }catch(BadCredentialsException e){
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }
        UserDetails userDetails=this.customerUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token;
        token = this.jwtUtil.generateToken(userDetails);
        System.out.println("JWT"+token);
        return ResponseEntity.ok(new jwtResponse(token));
    }
}
