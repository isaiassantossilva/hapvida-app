package com.hapvida.hapvidaapp.application.usecase;

import com.hapvida.hapvidaapp.adapters.persistence.memory.TutorRepositoryMemory;
import com.hapvida.hapvidaapp.adapters.persistence.memory.db.DBMemory;
import com.hapvida.hapvidaapp.application.exception.ResourceNotFound;
import com.hapvida.hapvidaapp.application.repository.TutorRepository;
import com.hapvida.hapvidaapp.application.usecase.tutor.FindTutorById;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class FindTutorByIdTest {

    DBMemory dbMemory;
    TutorRepository tutorRepository;
    @MockBean
    BDDMockito mockito;

    @BeforeEach
    void setUp() {
        dbMemory = new DBMemory();
        dbMemory.populateDb();
        tutorRepository = new TutorRepositoryMemory(dbMemory);
    }

    @Test
    @DisplayName("Should get tutor by id")
    void findTutorById(){
        var findTutorById = new FindTutorById(tutorRepository);
        var tutor = findTutorById.execute("c3b6f2d7-1a39-46d5-83e4-43a549dfa297");
        assertNotNull(tutor);
    }

    @Test
    @DisplayName("Should throw exception")
    void findTutorByIdException(){
        var findTutorById = new FindTutorById(tutorRepository);
        assertThrows(ResourceNotFound.class, () -> findTutorById.execute("1000"));

    }

    @AfterEach
    void tearDown() {
    }
}