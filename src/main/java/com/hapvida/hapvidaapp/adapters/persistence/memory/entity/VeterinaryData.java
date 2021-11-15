package com.hapvida.hapvidaapp.adapters.persistence.memory.entity;

import com.hapvida.hapvidaapp.application.entity.Veterinary;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;


@Getter
@Builder
public class VeterinaryData {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;

    public static VeterinaryData create(Veterinary veterinary){
        return VeterinaryData.builder()
                    .id(veterinary.getId().toString())
                    .name(veterinary.getName())
                    .email(veterinary.getEmail())
                    .phoneNumber(veterinary.getPhoneNumber())
                .build();
    }

    public Veterinary toVeterinary(){
        var veterinary = new Veterinary(name, email, phoneNumber);
        veterinary.setId(UUID.fromString(id));
        return veterinary;
    }
}
