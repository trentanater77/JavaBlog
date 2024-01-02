package com.example.demo.config;

// Importing necessary Spring Framework annotations
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Importing the Authority model and AuthorityRepository
import com.example.demo.models.Authority;
import com.example.demo.repositories.AuthorityRepository;

/**
 * SeedData is a component that is executed at the start of the application.
 * It implements CommandLineRunner, allowing it to run code after the application starts.
 */
@Component // Marks this class as a Spring component to be managed by the Spring container
public class SeedData implements CommandLineRunner {

    // Injecting AuthorityRepository using Spring's dependency injection
    @Autowired
    private AuthorityRepository authorityRepository;

    /**
     * The run method is executed after the application starts.
     * It's used here to initialize the database with default authority roles.
     *
     * @param args Command line arguments, not used in this implementation.
     */
    @Override
    public void run(String... args) throws Exception {
        // Check if the ROLE_USER authority exists in the database
        if (!authorityRepository.findById("ROLE_USER").isPresent()) {
            // If ROLE_USER does not exist, create and save a new Authority with that name
            Authority user = new Authority();
            user.setName("ROLE_USER");
            authorityRepository.save(user); // Saving the new Authority to the database
        }

        // Check if the ROLE_ADMIN authority exists in the database
        if (!authorityRepository.findById("ROLE_ADMIN").isPresent()) {
            // If ROLE_ADMIN does not exist, create and save a new Authority with that name
            Authority admin = new Authority();
            admin.setName("ROLE_ADMIN");
            authorityRepository.save(admin); // Saving the new Authority to the database
        }

        // Commented out the creation of new accounts and posts
        /*
        List<Post> posts = postService.getAll();
        if (posts.isEmpty()) {
            // Account creation code...
            // Post creation code...
        }
        */
    }
}

/* 
            Account account1 = new Account();
            account1.setFirstName("user");
            account1.setLastName("user");
            account1.setEmail("user.user@domain.com");
            account1.setPassword("password");
            Set<Authority> authorities1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
            account1.setAuthorities(authorities1);

            Account account2 = new Account();
            account2.setFirstName("Trenton");
            account2.setLastName("Hammons");
            account2.setEmail("Trenton.Hammons777@domain.com");
            account2.setPassword("Trenton420$");
            Set<Authority> authorities2 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities2::add);
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
            account2.setAuthorities(authorities2);

            accountService.save(account1);
            accountService.save(account2);

            Post post1 = new Post();
            post1.setTitle("title of post 1");
            post1.setBody("This is the body of post 1");
            post1.setAccount(account1);

            Post post2 = new Post();
            post2.setTitle("title of post 2");
            post2.setBody("This is the body of post 2");
            post2.setAccount(account2);

            postService.save(post1);
            postService.save(post2);
        }
    }
}
*/



