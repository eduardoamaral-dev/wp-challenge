package io.vizier.wp_challengeapi.components.pets;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1000000)
    private String imageUrl;
    private String name;
    private String description;
    private String category;
    private String bornDate;
    private String age;
    @JsonProperty("isAvailable") // this avoids spring to convert "isAvailable" to "available"
    private boolean isAvailable;
}
