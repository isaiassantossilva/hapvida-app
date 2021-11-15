package com.hapvida.hapvidaapp.adapters.rest.controller.appointment.request;

import com.hapvida.hapvidaapp.application.entity.enums.AppointmentStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class AppointmentUpdateRequest {
    private String veterinary;
    private String animal;
    @ApiModelProperty(
            dataType = "String",
            example = "13/11/2021"
    )
    private String date;
    @ApiModelProperty(
            dataType = "String",
            example = "04:38"
    )
    private String hour;
    private AppointmentStatus appointmentStatus;
}
