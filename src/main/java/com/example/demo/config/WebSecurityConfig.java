package com.example.demo.config;

// Import statements for various Spring framework classes
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Indicates that this class is a source of bean definitions
@EnableWebSecurity // Enables Spring Security's web security support
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true) // Enables method-level security
public class WebSecurityConfig implements WebMvcConfigurer {

    @Bean 
    public PasswordEncoder passwordEncoder() {
        // Defines a bean for encoding passwords using BCrypt
        return new BCryptPasswordEncoder();
    }

    // Array of RequestMatcher for URLs that should be publicly accessible
    private static final RequestMatcher[] PUBLIC_MATCHERS = {
        new AntPathRequestMatcher("/register"), // Register page
        new AntPathRequestMatcher("/h2-console/**"), // H2 Database console
        new AntPathRequestMatcher("/"), // Home page
        new AntPathRequestMatcher("/posts/*", HttpMethod.GET.name()), // GET requests to posts
        new AntPathRequestMatcher("/assets/**"), // Static assets like images, CSS, JS
        // Add other public matchers as needed
        new AntPathRequestMatcher("/test"), // Login page
        new AntPathRequestMatcher("/about"),
         new AntPathRequestMatcher("/api/messages/**"), // Allow access to /api/messages
          new AntPathRequestMatcher("/api/messages/hello"),
        new AntPathRequestMatcher("/static/**"), // Allow access to static content (Vue.js files) 
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")) // Disable CSRF protection for H2 console
            .headers(headers -> headers.frameOptions().disable()) // Disable frame options for H2 console
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers(PUBLIC_MATCHERS).permitAll(); // Allow public matchers without authentication
                auth.anyRequest().authenticated(); // Require authentication for all other requests
            })
            .formLogin()
                .loginPage("/login") // Custom login page
                .loginProcessingUrl("/login") // URL to submit the username and password
                .usernameParameter("email") // Custom username parameter
                .passwordParameter("password") // Custom password parameter
                .defaultSuccessUrl("/", true) // Redirect to home page on successful login
                .failureUrl("/login?error") // Redirect to login page on login failure
                .permitAll()
            .and()
                .logout()
                .logoutUrl("/logout") // Custom logout URL
                .logoutSuccessUrl("/login?logout") // Redirect after successful logout
            .and()
                .httpBasic(); // Enable basic authentication
            
        return http.build(); // Build the HttpSecurity configuration
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // Customizes web security to ignore certain request matchers
        return (web) -> web.ignoring().requestMatchers("/resources/**", "/static/**", "/assets/**", "/css/**", "/js/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configures resource handlers for serving static resources
        if (!registry.hasMappingForPattern("/assets/**")) {
            registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
        }
    }
}










// this works below with css just fix login issue
/*package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig implements WebMvcConfigurer {

    @Bean 
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static final RequestMatcher[] PUBLIC_MATCHERS = {
        new AntPathRequestMatcher("/register"),
        new AntPathRequestMatcher("/h2-console/**"),
        new AntPathRequestMatcher("/"),
        new AntPathRequestMatcher("/posts/*", HttpMethod.GET.name())
        // Add other public matchers as needed
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")) // Disable CSRF for H2 Console
            .headers(headers -> headers.frameOptions().disable()) // Disable X-Frame-Options for H2 Console
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers(PUBLIC_MATCHERS).permitAll();
                auth.anyRequest().authenticated();
            })
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error")
                .permitAll()
            .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
            .and()
                .httpBasic();
            
        return http.build();
    }

   @Bean
public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().requestMatchers("/resources/**", "/static/**", "/assets/**", "/css/**", "/js/**");
}


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/assets/**")) {
            registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
        }
    }
}
 */




