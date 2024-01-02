// Package declaration - defines the namespace in which the interface resides.
package com.example.demo.repositories;

// Import statements bring in classes and interfaces from other packages or libraries.
import org.springframework.data.jpa.repository.JpaRepository; // Spring Data JPA interface for generic CRUD operations.
import org.springframework.stereotype.Repository; // Indicates that the class is a repository.

import com.example.demo.models.Post; // Importing the Post entity model.

/**
 * PostRepository is an interface that extends JpaRepository.
 * It is marked with @Repository, indicating that it's a Spring-managed data repository.
 * Spring Data JPA will automatically provide the implementation of this interface.
 *
 * This interface enables CRUD (Create, Read, Update, Delete) operations and 
 * additional JPA-specific operations for the Post entity.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // Inherits methods like save, delete, findById, findAll, etc., from JpaRepository.

    // Additional custom methods for interacting with Post entities can be defined here.
    // For example, methods to find posts by title or content can be declared and
    // Spring Data JPA will automatically provide the implementation based on the method name.
}

