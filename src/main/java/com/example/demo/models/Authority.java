package com.example.demo.models;

// Importing Serializable interface for Java serialization
import java.io.Serializable;

// Importing JPA annotations for entity definition and column specification
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
// Importing Lombok annotations for automatic generation of getters, setters, and constructors
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Authority class is an entity that represents a set of permissions or roles in the application.
 * It is marked as Serializable to allow it to be serialized into different forms (like JSON, XML).
 */
@Entity // Marks this class as a JPA entity, representing a table in the database
@Getter // Lombok annotation to generate getter methods for all fields
@Setter // Lombok annotation to generate setter methods for all fields
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
public class Authority implements Serializable {

    @Id // Marks 'name' as the primary key of the entity
    @Column(length = 16) // Specifies the column details in the database. Here, the length of the 'name' column is set to 16
    private String name; // Unique identifier for each Authority

    /**
     * Custom toString method for Authority.
     * This provides a string representation of the Authority, which is useful for logging and debugging.
     */
    @Override
    public String toString() {
        return "Authority{" + "name='" + name + '\'' + "}";
    }
}


