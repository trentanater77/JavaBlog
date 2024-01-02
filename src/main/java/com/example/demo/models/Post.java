package com.example.demo.models;

// Importing necessary classes
import java.time.LocalDateTime; // For handling date and time

// JPA annotations for defining entity and column behaviors
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// Bean validation annotation
import jakarta.validation.constraints.NotNull;
// Lombok annotations for automatic generation of getters and setters
import lombok.Getter;
import lombok.Setter;

/**
 * The Post class is an entity that represents posts in a blog or forum-like application.
 * Each post is related to an account and contains content and timestamps.
 */
@Entity // Marks this class as a JPA entity, representing a table in the database
@Getter // Lombok annotation to generate getter methods for all fields
@Setter // Lombok annotation to generate setter methods for all fields
public class Post {

    @Id // Marks 'id' as the primary key of the entity
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // Defines the primary key generation strategy
    private Long id; // Unique identifier for each Post

    private String title; // The title of the post

    @Column(columnDefinition = "TEXT") // Specifies the column details, 'TEXT' indicates a long string
    private String body; // The body content of the post

    @Column // Marks 'createdAt' as a column in the database
    private LocalDateTime createdAt; // Timestamp of when the post was created

    private LocalDateTime updatedAt; // Timestamp of when the post was last updated

    /**
     * ManyToOne relationship with the Account entity.
     * Each Post is associated with one Account. The @NotNull annotation ensures that
     * a Post cannot be saved without an associated Account.
     */
    @NotNull // Ensures that the 'account' field cannot be null
    @ManyToOne // Indicates a many-to-one relationship with the Account entity
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false) 
    private Account account; // Reference to the Account associated with this post

    /**
     * Custom toString method for Post.
     * Provides a string representation of the Post, useful for logging and debugging.
     */
    @Override
    public String toString() {
        return "Post{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", body='" + body + '\'' +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            ", account=" + (account != null ? account.getId() : null) + // Displaying account ID if account is not null
            '}';
    }
}

