package io.vizier.wp_challengeapi.components.pets;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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
        if (pet.getBornDate() != null) {
            var age = calculateAge(pet);
            pet.setAge(age);
        }
        repository.save(pet);
    }

    public void updatePetStatus(Long petId, boolean isAvailable) {
        var existingPet = repository.findById(petId).orElseThrow(NoSuchElementException::new);
        existingPet.setAvailable(isAvailable);
        repository.save(existingPet);
    }

    public String calculateAge(Pet pet) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate born = LocalDate.parse(pet.getBornDate(), formatter);
        LocalDate today = LocalDate.now();
        Period period = Period.between(born, today);

        int years = period.getYears();
        int months = period.getMonths();

        if (months == 0 && years == 0) {
            return "Menos de um mês";
        }
        if (months == 0 && years == 1) {
            return "1 ano";
        }
        if (months == 1 && years == 0) {
            return "1 mes";
        }
        if (months == 0) {
            return years + " anos";
        }
        if (years == 0) {
            return months + " meses";
        }
        return years + " anos e " + months + " meses";
    }
}
