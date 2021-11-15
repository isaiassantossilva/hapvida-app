package com.hapvida.hapvidaapp.adapters.persistence.postgres.repsitory.appointment;
import com.hapvida.hapvidaapp.adapters.persistence.postgres.entity.AppointmentPGEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface SpringDataJpaAppointmentRepository extends JpaRepository<AppointmentPGEntity, UUID> {

    AppointmentPGEntity findByDate(LocalDateTime dateTime);

}
