package io.vizier.wp_challengeapi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository repository;

    public List<Pet> getAllPets() {
        return repository.findAll();
    }

    public void addPet(Pet pet) {
        repository.save(pet);
    }

    public void updatePetStatus(Long petId, boolean isAvailable) {
        var existingPet = repository.findById(petId).orElseThrow(NoSuchElementException::new);
        existingPet.setAvailable(isAvailable);
        repository.save(existingPet);
    }
}
