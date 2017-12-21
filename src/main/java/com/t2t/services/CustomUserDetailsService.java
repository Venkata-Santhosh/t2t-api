/**
 * Custom User details services
 *  it is like middle layer which acts like delegate but not exactly
 */
package com.t2t.services;

import com.t2t.entities.AppUser;
import com.t2t.jpa.UserRepository;
import com.t2t.models.SpringSecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;


    /**
     *     //loads user information from database to UserDetails object
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username);
        return new SpringSecurityUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                null,
                null,
                AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities())
        );
    }
}

