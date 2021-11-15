package com.hapvida.hapvidaapp.adapters.rest.controller.appointment.response;

import com.hapvida.hapvidaapp.adapters.rest.controller.animal.response.AnimalResponse;
import com.hapvida.hapvidaapp.adapters.rest.controller.veterinary.response.VeterinaryResponse;
import com.hapvida.hapvidaapp.application.entity.Appointment;
import com.hapvida.hapvidaapp.application.entity.enums.AppointmentStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AppointmentResponse {
    @ApiModelProperty(
            dataType = "String",
            example = "0"
    )
    private String id;
    private LocalDateTime date;
    private AppointmentStatus status;
    private VeterinaryResponse veterinary;
    private AnimalResponse animal;

    public static AppointmentResponse fromAppointment(Appointment appointment){
        return AppointmentResponse.builder()
                    .id(appointment.getId().toString())
                    .date(appointment.getDate())
                    .status(appointment.getStatus())
                    .veterinary(VeterinaryResponse.fromVeterinary(appointment.getVeterinary()))
                    .animal(AnimalResponse.fromAnimal(appointment.getAnimal()))
                .build();
    }
}
