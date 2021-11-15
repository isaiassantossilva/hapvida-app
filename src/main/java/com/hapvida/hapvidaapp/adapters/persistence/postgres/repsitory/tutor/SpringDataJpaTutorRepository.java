package com.hapvida.hapvidaapp.adapters.persistence.postgres.repsitory.tutor;
import com.hapvida.hapvidaapp.adapters.persistence.postgres.entity.TutorPGEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpringDataJpaTutorRepository extends JpaRepository<TutorPGEntity, UUID> {

    List<TutorPGEntity> findByNameContains(String name);
    TutorPGEntity findByEmail(String email);

}
