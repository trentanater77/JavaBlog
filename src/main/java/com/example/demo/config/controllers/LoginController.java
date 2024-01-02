package com.example.demo.config.controllers;

// Importing necessary Spring Framework annotation
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * LoginController is responsible for handling web requests related to the login page.
 */
@Controller // Marks this class as a Spring MVC Controller
public class LoginController {

    /**
     * Handles GET requests to "/login" and displays the login page.
     * @return The name of the view (login page) to be rendered.
     */
    @GetMapping("/login")
    public String getLoginPage() {
        // Returns the name of the HTML file (without the .html extension) that corresponds to the login view.
        // This view will be resolved by Spring's view resolver to display the login page to the user.
        return "login";
    }

}

