package io.vizier.wp_challengeapi.components.pets;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

    @GetMapping("/pets")
    public ResponseEntity<List<Pet>> getAllPets() {
        try {
            var petList = petService.getAllPets();
            return ResponseEntity.ok(petList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/pets")
    public ResponseEntity<Pet> addPet(@RequestBody Pet newPet) {
        try {
            petService.addPet(newPet);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/pets")
    public ResponseEntity<Pet> updatePet(@RequestParam Long petId, @RequestParam boolean newStatus) {
        try {
            petService.updatePetStatus(petId, newStatus);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
