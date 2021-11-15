package com.hapvida.hapvidaapp.adapters.rest.controller.veterinary.response;

import com.hapvida.hapvidaapp.application.entity.Veterinary;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VeterinaryResponse {
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

    public static VeterinaryResponse fromVeterinary(Veterinary veterinary){
        return VeterinaryResponse.builder()
                    .id(veterinary.getId().toString())
                    .name(veterinary.getName())
                    .email(veterinary.getEmail())
                    .phoneNumber(veterinary.getPhoneNumber())
                .build();
    }
}
