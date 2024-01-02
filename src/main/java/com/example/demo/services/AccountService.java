package com.example.demo.services;

// Importing necessary classes
import java.util.Optional; // Used for handling optional values

import org.springframework.beans.factory.annotation.Autowired; // Spring's annotation for dependency injection
import org.springframework.security.crypto.password.PasswordEncoder; // Class for password encoding
import org.springframework.stereotype.Service; // Annotation to indicate a service component

import com.example.demo.models.Account; // Importing the Account model
import com.example.demo.repositories.AccountRepository; // Importing the AccountRepository interface

/**
 * The AccountService class provides services related to Account entities.
 * It encapsulates the business logic for account operations.
 */
@Service // Marks this class as a service component for Spring's component scanning
public class AccountService {

    // Injecting the AccountRepository dependency
    // This repository will be used for data access operations related to Account entities
    @Autowired
    private AccountRepository accountRepository;

    // Injecting the PasswordEncoder dependency
    // This is used for encoding passwords before storing them in the database
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Saves an Account entity to the database.
     * 
     * @param account The Account entity to be saved.
     * @return The saved Account entity, with its ID generated or updated.
     * 
     * Before saving, this method encodes the account's password for security.
     */
    public Account save(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword())); // Encoding the password for security
        return accountRepository.save(account); // Saving the account entity to the database
    }

    /**
     * Finds an Account entity by its email.
     * 
     * @param email The email associated with the Account.
     * @return An Optional containing the Account if found, or empty otherwise.
     * 
     * This method uses a custom query method defined in AccountRepository.
     */
    public Optional<Account> findByEmail(String email) {
        return accountRepository.findOneByEmail(email); // Fetching the account by email using a repository method
    }
}


