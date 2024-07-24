package io.vizier.wp_challengeapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(name = "image", columnDefinition="BLOB")
    private byte[] image;
    private String name;
    private String description;
    private String category;
    private String bornDate;
    private int age;
    @JsonProperty("isAvailable") // this avoids spring to convert "isAvailable" to "available"
    private boolean isAvailable;
}
