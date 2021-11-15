package com.hapvida.hapvidaapp.adapters.persistence.postgres.entity;

import com.hapvida.hapvidaapp.application.entity.Veterinary;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_VETERINARY")
@NoArgsConstructor
public class VeterinaryPGEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String name;
    @Column(unique = true)
    private String email;
    private String phoneNumber;

    @OneToMany(mappedBy = "veterinary", cascade = CascadeType.ALL)
    private List<AppointmentPGEntity> appointments;

    public static VeterinaryPGEntity fromVeterinary(Veterinary veterinary) {
        var entity = new VeterinaryPGEntity();
        entity.setId(veterinary.getId());
        entity.setName(veterinary.getName());
        entity.setEmail(veterinary.getEmail());
        entity.setPhoneNumber(veterinary.getPhoneNumber());
        return entity;
    }

    public Veterinary toVeterinary() {
        var entity = new Veterinary();
        entity.setId(id);
        entity.setName(name);
        entity.setEmail(email);
        entity.setPhoneNumber(phoneNumber);
        return entity;
    }
}
