package com.hapvida.hapvidaapp.adapters.rest.controller.tutor.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class TutorUpdateRequest {
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
}
