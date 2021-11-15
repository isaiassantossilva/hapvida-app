package com.hapvida.hapvidaapp.adapters.persistence.postgres.entity;

import com.hapvida.hapvidaapp.application.entity.Tutor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "TB_TUTOR")
@NoArgsConstructor
public class TutorPGEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String email;
    private String phoneNumber;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
    private List<AnimalPGEntity> animals;

    public Tutor toTutor(){
        var tutor = new Tutor(name, email, phoneNumber);
        tutor.setId(id);
        if(animals != null && !animals.isEmpty())
            tutor.addAllAnimals(animals.stream().map(AnimalPGEntity::toAnimal).collect(Collectors.toList()));
        return tutor;
    }

    public static TutorPGEntity fromTutor(Tutor tutor) {
        var entity = new TutorPGEntity();
        entity.setId(tutor.getId());
        entity.setName(tutor.getName());
        entity.setEmail(tutor.getEmail());
        entity.setPhoneNumber(tutor.getPhoneNumber());
        return entity;
    }
}
