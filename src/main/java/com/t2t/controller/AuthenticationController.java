/**
 * AuthenticationController class provides
 * restful services to  authentication/authorize user
 */
package com.t2t.controller;

import com.t2t.constants.Constants;
import com.t2t.entities.AppUser;
import com.t2t.jpa.UserRepository;
import com.t2t.models.AuthenticationRequest;
import com.t2t.models.AuthenticationResponse;
import com.t2t.models.SpringSecurityUser;
import com.t2t.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

//@RestController annotation is used, so that spring know this as restful service.
@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    //is <host>:8080/signup
    //this restful URI is allows user to signup to our website
    @RequestMapping(method = RequestMethod.POST, value = "auth/signup")
    public ResponseEntity<?> createUser(@RequestBody AppUser user) {
        AppUser createdUser = userRepository.save(user);
        if(createdUser!=null) {
           return ResponseEntity.ok(createdUser);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    //this restful service is used to authenticate users
    // if user is authenticated then json web token is sent client application
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest)
            throws AuthenticationException {

        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String token = this.tokenUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    //this restful service is used to refresh json web token
    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    public ResponseEntity<?> authenticationRequest(HttpServletRequest request) {
        String token = request.getHeader(Constants.tokenHeader);
        String username = this.tokenUtils.getUsernameFromToken(token);
        SpringSecurityUser user = (SpringSecurityUser) this.userDetailsService.loadUserByUsername(username);
        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
            String refreshedToken = this.tokenUtils.refreshToken(token);
            return ResponseEntity.ok(new AuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
