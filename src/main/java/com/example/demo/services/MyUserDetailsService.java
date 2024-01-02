package com.example.demo.services;

// Importing necessary classes
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired; // For dependency injection
import org.springframework.security.core.GrantedAuthority; // Represents an authority granted to an Authentication object
import org.springframework.security.core.authority.SimpleGrantedAuthority; // Concrete implementation of GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails; // Core interface in Spring Security
import org.springframework.security.core.userdetails.UserDetailsService; // Interface to load user-specific data
import org.springframework.security.core.userdetails.UsernameNotFoundException; // Exception for user not found cases
import org.springframework.stereotype.Component; // Annotation to mark this class as a Spring component

import com.example.demo.models.Account; // Importing the Account model

/**
 * Custom implementation of UserDetailsService for Spring Security.
 * This service is used for loading user details based on username (email in this case).
 */
@Component("userDetailsService") // Marks this class as a Spring component, specifying its name
public class MyUserDetailsService implements UserDetailsService {

    // Injecting the AccountService dependency
    @Autowired
    private AccountService accountService;

    /**
     * Loads the user details by username (email in this case).
     *
     * @param email The email of the user trying to log in.
     * @return UserDetails object that Spring Security can use for authentication and authorization.
     * @throws UsernameNotFoundException if the user with the given email cannot be found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Attempt to find the account by email
        Optional<Account> optionalAccount = accountService.findByEmail(email);

        // If account is not found, throw an exception
        if (!optionalAccount.isPresent()) {
            throw new UsernameNotFoundException("Account not found with email: " + email);
        }

        // Get the Account object from the optional
        Account account = optionalAccount.get();

        // Convert the authorities of the account to Spring Security's GrantedAuthority objects
        List<GrantedAuthority> grantedAuthorities = account.getAuthorities()
            .stream() // Stream the authorities
            .map(authority -> new SimpleGrantedAuthority(authority.getName())) // Convert each authority to SimpleGrantedAuthority
            .collect(Collectors.toList()); // Collect the results into a list

        // Return a User object (from Spring Security) with the details of the account
        return new org.springframework.security.core.userdetails.User(account.getEmail(), account.getPassword(), grantedAuthorities);
    }
}


