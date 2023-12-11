package com.kaimuellercode.thecookbook.cookbook.security;

import com.kaimuellercode.thecookbook.cookbook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * A Custom UserDetailsService, which Uses the {@link UserService} to fetch a
 * {@link com.kaimuellercode.thecookbook.cookbook.entities.User} and build a
 * {@link UserDetails} through a  {@link UserPrinciple} from it.
 */

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;

    /**
     * Uses the Email as username
     * @param username the email
     * @return the user
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findByEmail(username).orElseThrow();
        return UserPrinciple.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .authorities(List.of(new SimpleGrantedAuthority(user.getUserRights().name())))
                .password(user.getPwHash())
                .build();
    }
}
