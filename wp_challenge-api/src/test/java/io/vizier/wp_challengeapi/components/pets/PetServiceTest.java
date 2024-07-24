package io.vizier.wp_challengeapi.components.pets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PetServiceTest {

    @Mock
    private PetRepository repositoryMock;
    @InjectMocks
    private PetService petService;

    @Mock
    private PetService serviceMock;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPets() {
        // given
        Pet pet1 = new Pet(1L, "", "Meg", "Description1", "Cat", "", "", true);
        Pet pet2 = new Pet(2L, "", "Thor", "Description2", "Dog", "", "", true);
        List<Pet> pets = Arrays.asList(pet1, pet2);

        // when
        when(repositoryMock.findAll()).thenReturn(pets);
        List<Pet> result = petService.getAllPets();

        // then
        assertNotNull(result);
        assertEquals(pets.size(), result.size());
        assertTrue(result.containsAll(pets));
        verify(repositoryMock, times(1)).findAll();
    }

    @Test
    public void testUpdatePetStatus() {
        // given
        Long petId = 1L;
        boolean newStatus = true;
        Pet existingPet = new Pet(1L, "", "Meg", "Description1", "Cat", "", "", true);
        // when
        when(repositoryMock.findById(petId)).thenReturn(Optional.of(existingPet));
        petService.updatePetStatus(petId, newStatus);

        // then
        verify(repositoryMock, times(1)).save(existingPet);
        assertEquals(newStatus, existingPet.isAvailable());
    }

    @Test
    public void createPet_when_bornDate_is_null_then_just_use_jpa_repo() {
        // given
        var pet = new Pet(1L, "", "Meg", "Description1", "Cat", null, null, true);
        // when
//        when(repositoryMock.save(pet));
        petService.addPet(pet);

        // then
        verify(repositoryMock, times(1)).save(pet);
    }

    @Test
    public void createPet_when_bornDate_is_null_then_calc_age() {
        // given
        var pet = new Pet(1L, "", "Meg", "Description1", "Cat", "2022-01-01", null, true);
        // when
        petService.addPet(pet);
        // then
        var petWithExpectedAge = new Pet(1L, "", "Meg", "Description1", "Cat", "2022-01-01", "2 anos e 6 meses", true);
        verify(repositoryMock, times(1)).save(petWithExpectedAge);
    }



}
