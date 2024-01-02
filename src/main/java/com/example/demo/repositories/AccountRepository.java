package com.example.demo.repositories;

// Importing necessary classes
import java.util.Optional; // Used for wrapping a potentially nullable Account object

import org.springframework.data.jpa.repository.JpaRepository; // Base repository class in Spring Data JPA
import org.springframework.stereotype.Repository; // Annotation to indicate that this is a repository component

import com.example.demo.models.Account; // Importing the Account model

/**
 * The AccountRepository interface extends JpaRepository to handle data access operations
 * for the Account entity in the database. It inherits standard CRUD operations from JpaRepository.
 */
@Repository // Marks this interface as a repository component for Spring's component scanning
public interface AccountRepository extends JpaRepository<Account, Long> {
    /**
     * Custom method to find an Account entity by its email.
     * 
     * @param email The email to search for in the database.
     * @return Optional<Account> - An Optional containing the Account if found, or empty otherwise.
     * 
     * This method leverages the query derivation mechanism of Spring Data JPA, where the method name
     * itself defines the query. Here, 'findOneByEmail' automatically translates to a query that
     * searches for an account by its 'email' field.
     */
    Optional<Account> findOneByEmail(String email);
}
