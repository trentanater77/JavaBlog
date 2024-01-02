package com.example.demo.repositories;

// Importing necessary Spring Data JPA and Spring framework classes
import org.springframework.data.jpa.repository.JpaRepository; // Base repository class in Spring Data JPA
import org.springframework.stereotype.Repository; // Annotation to indicate a repository component

import com.example.demo.models.Authority; // Importing the Authority model

/**
 * The AuthorityRepository interface extends JpaRepository to provide data access operations
 * for the Authority entity. JpaRepository comes with standard CRUD (Create, Read, Update, and Delete) 
 * operations and more.
 * 
 * This interface interacts with the database to perform operations on Authority entities.
 */
@Repository // Marks this interface as a repository component for Spring's component scanning
public interface AuthorityRepository extends JpaRepository<Authority, String> {
    // Currently, this interface does not have any custom methods. It inherits
    // standard data access methods like save, findById, findAll, deleteById, etc.,
    // from JpaRepository. These methods are sufficient for basic CRUD operations on 
    // Authority entities.

    // Custom methods specific to Authority entities can be added here as needed.
    // These methods can include additional query methods following Spring Data JPA naming conventions,
    // which automatically translate method names into SQL queries.
}
