package com.hapvida.hapvidaapp.adapters.persistence.postgres.repsitory.veterinary;
import com.hapvida.hapvidaapp.adapters.persistence.postgres.entity.VeterinaryPGEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpringDataJpaVeterinaryRepository extends JpaRepository<VeterinaryPGEntity, UUID> {

    VeterinaryPGEntity findByEmail(String email);
    List<VeterinaryPGEntity> findByNameContains(String name);

}
