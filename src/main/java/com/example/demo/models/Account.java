package com.example.demo.models;

// Importing necessary Java and JPA classes
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// JPA annotations for defining entity relationships and behaviors
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
// Lombok annotations for reducing boilerplate code
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Account class is an entity that represents the 'accounts' table in the database.
 */
@Entity // Marks this class as a JPA entity
@Getter // Lombok annotation to generate getter methods for all fields
@Setter // Lombok annotation to generate setter methods for all fields
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
public class Account {
    @Id // Marks 'id' as the primary key of the entity
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // Defines the primary key generation strategy
    private Long id; // Unique identifier for each Account

    // Basic fields for the account
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    /**
     * OneToMany relationship with Post entities.
     * Each Account can have multiple posts, but each Post is associated with one Account.
     */
    @OneToMany(mappedBy = "account") // 'mappedBy' indicates the field in 'Post' that owns the relationship
    private List<Post> posts;

    /**
     * ManyToMany relationship with Authority entities.
     * An Account can have multiple authorities, and an Authority can be associated with multiple accounts.
     * FetchType.EAGER means the authorities are loaded eagerly with the Account.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "account_authority", // Name of the join table
        joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")}, // Column for Account in join table
        inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")} // Column for Authority in join table
    )
    private Set<Authority> authorities = new HashSet<>();

    /**
     * Custom toString method for Account.
     * This provides a string representation of the Account, which is useful for logging and debugging.
     */
    @Override
    public String toString() {
        return "Account{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", authorities=" + authorities +
            '}';
    }
}


