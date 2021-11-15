package com.hapvida.hapvidaapp.adapters.rest.controller.tutor.response;

import com.hapvida.hapvidaapp.application.entity.Animal;
import com.hapvida.hapvidaapp.application.entity.Tutor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class TutorResponse {
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

    private List<AnimalBriefResponse> animals;

    public static TutorResponse fromTutor(Tutor tutor){
        return TutorResponse.builder()
                    .id(tutor.getId().toString())
                    .name(tutor.getName())
                    .email(tutor.getEmail())
                    .phoneNumber(tutor.getPhoneNumber())
                    .animals(tutor.getAnimals().stream().map(AnimalBriefResponse::fromAnimal).collect(Collectors.toList()))
                .build();
    }

    @Getter
    @Builder
    private static class AnimalBriefResponse {
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
                example = "2021-01-01")
        private LocalDate birthDate;

        public static AnimalBriefResponse fromAnimal(Animal animal){
            return AnimalBriefResponse.builder()
                        .id(animal.getId().toString())
                        .name(animal.getName())
                        .specie(animal.getSpecie().toString())
                        .breed(animal.getBreed())
                        .birthDate(animal.getBirthDate())
                    .build();
        }
    }
}
