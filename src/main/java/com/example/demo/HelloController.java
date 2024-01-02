package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Corrected Model import
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.models.Post;
import com.example.demo.services.PostService;

@Controller
public class HelloController {
    
    @Autowired
    private PostService postService;

    /**
     * Handler for the root ("/") path.
     * 
     * @param model The Model object used to pass data to the view.
     * @return The name of the view to render.
     */
    @GetMapping("/")
    public String hello(Model model) {
        // Fetch all posts using the PostService
        List<Post> posts = postService.getAll();

        // Add the retrieved posts to the model under the attribute name "posts".
        model.addAttribute("posts", posts);

        // Return the name of the template (hello.html in src/main/resources/templates)
        return "hello";
    }
}

