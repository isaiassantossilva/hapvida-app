package com.hapvida.hapvidaapp.adapters.rest.controller.tutor.request;

import com.hapvida.hapvidaapp.application.entity.Animal;
import com.hapvida.hapvidaapp.application.entity.enums.Specie;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class TutorRequest {
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

    private List<AnimalBriefRequest> animals;

    @Getter
    public static class AnimalBriefRequest {
        @ApiModelProperty(
                dataType = "String",
                example = "Nome")
        private String name;
        @ApiModelProperty(
                dataType = "String",
                example = "DOG")
        private Specie specie;
        @ApiModelProperty(
                dataType = "String",
                example = "Buldogue")
        private String breed;
        @ApiModelProperty(
                dataType = "String",
                example = "09/01/2021")
        private String birthDate;

        public Animal toAnimal(){
            var formatDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return new Animal(name, specie, breed, formatDate);
        }
    }
}
