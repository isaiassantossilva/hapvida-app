package com.hapvida.hapvidaapp.adapters.rest.controller.appointment.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class AppointmentRequest {
    @ApiModelProperty(
            dataType = "String",
            example = "0"
    )
    private String veterinary;
    @ApiModelProperty(
            dataType = "String",
            example = "0"
    )
    private String animal;
    @ApiModelProperty(
            dataType = "String",
            example = "02/09/2021"
    )
    private String date;
    @ApiModelProperty(
            dataType = "String",
            example = "22:31"
    )
    private String hour;
}
