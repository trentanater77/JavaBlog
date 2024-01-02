// Package declaration - defines the namespace in which the class resides.
package com.example.demo.services;

// Import statements bring in classes and interfaces from other packages or libraries.
import java.time.LocalDateTime; // For handling date and time.
import java.util.List; // To work with lists in Java.
import java.util.Optional; // Optional is a container object which may or may not contain a non-null value.

import org.springframework.beans.factory.annotation.Autowired; // Spring's annotation for automatic dependency injection.
import org.springframework.stereotype.Service; // Marks this class as a Spring service.

import com.example.demo.models.Post; // Importing the Post entity model.
import com.example.demo.repositories.PostRepository; // Importing the JPA repository for Post.

/**
 * PostService is marked with @Service, indicating it's a Spring-managed service class.
 * It contains business logic and calls methods defined in PostRepository.
 */
@Service
public class PostService {

    // Injecting PostRepository using Spring's dependency injection.
    @Autowired
    private PostRepository postRepository;

    /**
     * Method to retrieve a post by its ID.
     * 
     * @param id The ID of the post.
     * @return An Optional containing the post if found, or an empty Optional otherwise.
     */
    public Optional<Post> getById(Long id) {
        // Calls the findById method of PostRepository and returns the result.
        return postRepository.findById(id);
    }

    /**
     * Method to retrieve all posts.
     * 
     * @return A list of all posts.
     */
    public List<Post> getAll() {
        // Calls the findAll method of PostRepository to retrieve all posts.
        return postRepository.findAll();
    }

    /**
     * Method to save a post to the database.
     * 
     * @param post The post to be saved.
     * @return The saved post with updates (like generated ID).
     */
    public Post save(Post post) {
        // Checks if the post is new (i.e., no ID assigned).
        if (post.getId() == null) {
            // Sets the creation time for the new post.
            post.setCreatedAt(LocalDateTime.now());
        }
        post.setUpdatedAt(LocalDateTime.now()); // Sets the update time for the post.
        // Saves the post using PostRepository and returns the saved entity.
        return postRepository.save(post);
    }
public void delete(Post post) {
    postRepository.delete(post);


}}



