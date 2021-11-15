package com.hapvida.hapvidaapp.adapters.persistence.memory.entity;

import com.hapvida.hapvidaapp.application.entity.Tutor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;


@Getter
@Builder
public class TutorData {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;

    public static TutorData create(Tutor tutor){
        return TutorData.builder()
                    .id(tutor.getId().toString())
                    .name(tutor.getName())
                    .email(tutor.getEmail())
                    .phoneNumber(tutor.getPhoneNumber())
                .build();
    }

    public Tutor toTutor(){
        var entity = new Tutor(name, email, phoneNumber);
        entity.setId(UUID.fromString(id));
        return entity;
    }
}
