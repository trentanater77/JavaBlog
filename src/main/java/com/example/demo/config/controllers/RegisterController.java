package com.example.demo.config.controllers;

// Importing necessary Spring Framework annotations and classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

// Importing the Account model and AccountService
import com.example.demo.models.Account;
import com.example.demo.services.AccountService;

/**
 * RegisterController handles web requests related to user registration.
 */
@Controller // Marks this class as a Spring MVC Controller
public class RegisterController {
    
    // Injecting AccountService using Spring's dependency injection
    @Autowired
    private AccountService accountService;

    /**
     * Handles GET requests to "/register".
     * It returns the registration page.
     *
     * @param model The Model object used to pass data to the view.
     * @return The name of the view to be rendered.
     */
    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        // Create a new Account object to hold registration form data
        Account account = new Account();
        
        // Add the empty Account object to the model, to be filled in by the registration form
        model.addAttribute("account", account);
        
        // Returns the name of the HTML file (without the .html extension) to be rendered
        return "register";
    }

    /**
     * Handles POST requests to "/register", which is the form submission for new user registration.
     *
     * @param account The Account object that contains data from the registration form.
     * @return A redirection string, which redirects to the home page after successful registration.
     */
    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute Account account) {
        // Save the new account using the AccountService
        accountService.save(account);
        
        // Redirect to the home page after successful registration
        return "redirect:/";
    }

}
//comment this out above if you do not want a register page which ill probaly do as it still works when commented out just not register part