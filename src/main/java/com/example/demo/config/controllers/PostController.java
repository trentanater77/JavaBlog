package com.example.demo.config.controllers;

// Importing necessary classes
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Account;
import com.example.demo.models.Post;
import com.example.demo.services.AccountService;
import com.example.demo.services.PostService;

/**
 * PostController handles web requests related to blog posts.
 */
@Controller // Marks this class as a Spring MVC Controller
public class PostController {

    // Injecting PostService and AccountService using Spring's dependency injection
    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    /**
     * Handles GET requests to "/posts/{id}" and displays a specific post.
     * @param id The ID of the post.
     * @param model The Model object used to pass data to the view.
     * @return The name of the view or a redirection.
     */
    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        // Retrieves the post by ID
        Optional<Post> optionalPost = postService.getById(id);
        if (optionalPost.isPresent()) {
            // If post is found, add it to the model and return the post view
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "post";
        } else {
            // If post is not found, return a 404 error page
            return "404";
        }
    }

    /**
     * Handles GET requests to "/posts/new" and displays the form to create a new post.
     * @param model The Model object used to pass data to the view.
     * @return The name of the view or a redirection.
     */
    @GetMapping("/posts/new")
    public String createNewPost(Model model) {
        // Retrieves the currently authenticated user's account
        Optional<Account> optionalAccount = accountService.findByEmail("user.user@domain.com");
        if (optionalAccount.isPresent()) {
            // Create a new post and set its account, then add to the model
            Post post = new Post();
            post.setAccount(optionalAccount.get());
            model.addAttribute("post", post);
            return "post_new";
        } else {
            // If account is not found, return a 404 error page
            return "404";
        }
    }

    /**
     * Handles POST requests to "/posts/new" and saves a new post.
     * @param post The Post object received from the form submission.
     * @return A redirection string to the new post.
     */
    @PostMapping("/posts/new")
    public String saveNewPost(@ModelAttribute Post post) {
        // Save the new post using PostService
        postService.save(post);
        return "redirect:/posts/" + post.getId();
    }

    /**
     * Handles GET requests to "/posts/{id}/edit" and displays a form to edit a post.
     * @param id The ID of the post to edit.
     * @param model The Model object used to pass data to the view.
     * @return The name of the view or a redirection.
     */
    @GetMapping("/posts/{id}/edit")
    @PreAuthorize("isAuthenticated()") // Ensures that only authenticated users can access this method
    public String getPostForEdit(@PathVariable Long id, Model model) {
        Optional<Post> optionalPost = postService.getById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "post_edit";
        } else {
            return "404";
        }
    }

    /**
     * Handles POST requests to "/posts/{id}" and updates an existing post.
     * @param id The ID of the post to update.
     * @param post The updated Post object received from the form submission.
     * @param result BindingResult object to hold validation results.
     * @param model The Model object used to pass data to the view.
     * @return A redirection string to the updated post.
     */
    @PostMapping("/posts/{id}")
    @PreAuthorize("isAuthenticated()") // Ensures that only authenticated users can access this method
    public String updatePost(@PathVariable Long id, Post post, BindingResult result, Model model) {
        Optional<Post> optionalPost = postService.getById(id);
        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();
            // Update the existing post with the new title and body
            existingPost.setTitle(post.getTitle());
            existingPost.setBody(post.getBody());
            postService.save(existingPost); // Save the updated post
        }
        return "redirect:/posts/" + post.getId();
    }

    /**
     * Handles GET requests to "/posts/{id}/delete" and deletes a post.
     * @param id The ID of the post to delete.
     * @return A redirection string to the home page.
     */
    @GetMapping("/posts/{id}/delete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // Ensures that only users with ROLE_ADMIN can access this method
    public String deletePost(@PathVariable Long id) {
        Optional<Post> optionalPost = postService.getById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            postService.delete(post); // Delete the post
            return "redirect:/";
        } else {
            return "404";
        }
    }
}
