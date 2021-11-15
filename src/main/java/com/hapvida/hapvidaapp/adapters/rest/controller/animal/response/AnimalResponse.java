package com.hapvida.hapvidaapp.adapters.rest.controller.animal.response;

import com.hapvida.hapvidaapp.application.entity.Animal;
import com.hapvida.hapvidaapp.application.entity.Tutor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class AnimalResponse {
    @ApiModelProperty(
            dataType = "String",
            example = "0")
    private String id;
    @ApiModelProperty(
            dataType = "String",
            example = "Nome")
    private String name;
    @ApiModelProperty(
            dataType = "String",
            example = "DOG")
    private String specie;
    @ApiModelProperty(
            dataType = "String",
            example = "Buldogue")
    private String breed;
    @ApiModelProperty(
            dataType = "String",
            example = "22/01/2021")
    private LocalDate birthDate;

    private TutorBriefResponse tutor;

    public static AnimalResponse fromAnimal(Animal animal){
        return AnimalResponse.builder()
                    .id(animal.getId().toString())
                    .name(animal.getName())
                    .specie(animal.getSpecie().toString())
                    .breed(animal.getBreed())
                    .birthDate(animal.getBirthDate())
                    .tutor(TutorBriefResponse.fromTutor(animal.getTutor()))
                .build();
    }


    @Getter
    @Builder
    private static class TutorBriefResponse {
        @ApiModelProperty(
                dataType = "String",
                example = "0")
        private String id;
        @ApiModelProperty(
                dataType = "String",
                example = "Nome")
        private String name;
        @ApiModelProperty(
                dataType = "String",
                example = "email@exemplo.com")
        private String email;
        @ApiModelProperty(
                dataType = "String",
                example = "12345678")
        private String phoneNumber;

        public static TutorBriefResponse fromTutor(Tutor tutor){
            return TutorBriefResponse.builder()
                        .id(tutor.getId().toString())
                        .name(tutor.getName())
                        .email(tutor.getEmail())
                        .phoneNumber(tutor.getPhoneNumber())
                    .build();
        }
    }
}
