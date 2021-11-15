package com.hapvida.hapvidaapp.adapters.persistence.postgres.entity;

import com.hapvida.hapvidaapp.application.entity.Animal;
import com.hapvida.hapvidaapp.application.entity.enums.Specie;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_ANIMAL")
@NoArgsConstructor
public class AnimalPGEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private Specie specie;
    private String breed;
    private LocalDate birthDate;
    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private TutorPGEntity tutor;
    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    private List<AppointmentPGEntity> appointments;

    public Animal toAnimal(){
        var entity = new Animal(name, specie, breed, birthDate);
        entity.setId(id);
        return entity;
    }

    public Animal toAnimalWithTutor(){
        var entity = new Animal(name, specie, breed, birthDate);
        entity.setId(id);
        entity.setTutor(tutor.toTutor());
        return entity;
    }

    public static AnimalPGEntity fromAnimal(Animal animal){
        var entity = new AnimalPGEntity();
        entity.setId(animal.getId());
        entity.setName(animal.getName());
        entity.setSpecie(animal.getSpecie());
        entity.setBreed(animal.getBreed());
        entity.setBirthDate(animal.getBirthDate());
        entity.setTutor(TutorPGEntity.fromTutor(animal.getTutor()));
        return entity;
    }
}
