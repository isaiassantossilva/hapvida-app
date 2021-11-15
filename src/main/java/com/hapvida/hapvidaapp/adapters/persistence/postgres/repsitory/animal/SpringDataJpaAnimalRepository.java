package com.hapvida.hapvidaapp.adapters.persistence.postgres.repsitory.animal;
import com.hapvida.hapvidaapp.adapters.persistence.postgres.entity.AnimalPGEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataJpaAnimalRepository extends JpaRepository<AnimalPGEntity, UUID> {

}
