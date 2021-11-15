package com.hapvida.hapvidaapp.adapters.rest.controller.appointment;

import com.hapvida.hapvidaapp.adapters.rest.controller.appointment.request.AppointmentRequest;
import com.hapvida.hapvidaapp.adapters.rest.controller.appointment.request.AppointmentUpdateRequest;
import com.hapvida.hapvidaapp.adapters.rest.controller.appointment.response.AppointmentResponse;
import com.hapvida.hapvidaapp.application.usecase.appointment.CreateNewAppointment;
import com.hapvida.hapvidaapp.application.usecase.appointment.DeleteAppointment;
import com.hapvida.hapvidaapp.application.usecase.appointment.FindAllAppointment;
import com.hapvida.hapvidaapp.application.usecase.appointment.UpdateAppointment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final CreateNewAppointment createNewAppointment;
    private final FindAllAppointment findAllAppointment;
    private final DeleteAppointment deleteAppointment;
    private final UpdateAppointment updateAppointment;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AppointmentResponse createNewAppointment(@RequestBody AppointmentRequest appointmentRequest){
        var appointment = createNewAppointment.execute(
                appointmentRequest.getVeterinary(),
                appointmentRequest.getAnimal(),
                appointmentRequest.getDate(),
                appointmentRequest.getHour()
        );
        return AppointmentResponse.fromAppointment(appointment);
    }


    @PatchMapping("/{appointmentId}")
    public AppointmentResponse update(@RequestBody AppointmentUpdateRequest appointmentRequest,
                                      @PathVariable String appointmentId){
        var appointment = updateAppointment.execute(
                appointmentId,
                appointmentRequest.getVeterinary(),
                appointmentRequest.getAnimal(),
                appointmentRequest.getDate(),
                appointmentRequest.getHour(),
                appointmentRequest.getAppointmentStatus()
        );
        return AppointmentResponse.fromAppointment(appointment);
    }


    @GetMapping
    public List<AppointmentResponse> findAll(){
        return findAllAppointment.execute().stream().map(AppointmentResponse::fromAppointment).collect(Collectors.toList());
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{appointmentId}")
    public void delete(@PathVariable String appointmentId){
        deleteAppointment.execute(appointmentId);
    }

}
