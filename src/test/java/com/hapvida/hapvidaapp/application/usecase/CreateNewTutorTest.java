package com.hapvida.hapvidaapp.application.usecase;

import com.hapvida.hapvidaapp.adapters.persistence.memory.AnimalRepositoryMemory;
import com.hapvida.hapvidaapp.adapters.persistence.memory.TutorRepositoryMemory;
import com.hapvida.hapvidaapp.adapters.persistence.memory.db.DBMemory;
import com.hapvida.hapvidaapp.application.entity.Animal;
import com.hapvida.hapvidaapp.application.entity.enums.Specie;
import com.hapvida.hapvidaapp.application.repository.AnimalRepository;
import com.hapvida.hapvidaapp.application.repository.TutorRepository;
import com.hapvida.hapvidaapp.application.usecase.animal.CreateAnimalsByList;
import com.hapvida.hapvidaapp.application.usecase.tutor.CreateNewTutor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateNewTutorTest {

    DBMemory dbMemory;
    TutorRepository tutorRepository;
    AnimalRepository animalRepository;
    CreateAnimalsByList createAnimalsByList;

    @BeforeEach
    void setUp() {
        dbMemory = new DBMemory();
        tutorRepository = new TutorRepositoryMemory(dbMemory);
        animalRepository = new AnimalRepositoryMemory(dbMemory);
        createAnimalsByList = new CreateAnimalsByList(animalRepository);
    }

    @DisplayName("Should create new tutor")
    @Test
    void shouldCreateNewTutor(){
        var createNewTutor = new CreateNewTutor(tutorRepository, createAnimalsByList);
        var tutor = createNewTutor.execute("Isaias", "isaias@gmail.com", "12345678", List.of(
                new Animal("doguin", Specie.DOG, "chiuhauhaua", LocalDate.parse("01/01/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")))
        ));
        assertNotNull(tutor);
        assertEquals("Isaias", tutor.getName());
    }


    @DisplayName("Should throws new tutor")
    @Test
    void shouldThrowsException(){
        var createNewTutor = new CreateNewTutor(tutorRepository, createAnimalsByList);
        assertThrows(IllegalArgumentException.class, () -> createNewTutor.execute(null, "isaias@gmail.com", "12345678", List.of()));
    }


    @AfterEach
    void tearDown() {
    }
}