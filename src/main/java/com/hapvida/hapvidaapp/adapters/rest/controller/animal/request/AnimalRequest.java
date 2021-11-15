package com.hapvida.hapvidaapp.adapters.rest.controller.animal.request;

import com.hapvida.hapvidaapp.application.entity.enums.Specie;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class AnimalRequest {
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
            example = "22/01/2021")
    private String birthDate;
    @ApiModelProperty(
            dataType = "String",
            example = "0")
    private String tutor;
}
