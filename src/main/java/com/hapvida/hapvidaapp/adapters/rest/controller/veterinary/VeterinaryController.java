package com.hapvida.hapvidaapp.adapters.rest.controller.veterinary;

import com.hapvida.hapvidaapp.adapters.rest.controller.veterinary.request.VeterinaryRequest;
import com.hapvida.hapvidaapp.adapters.rest.controller.veterinary.response.VeterinaryResponse;
import com.hapvida.hapvidaapp.application.usecase.veterinary.*;
import com.hapvida.hapvidaapp.application.valueobject.VeterinaryFilter;
import io.swagger.annotations.ApiModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/veterinaries")
@RequiredArgsConstructor
public class VeterinaryController {

    private final CreateNewVeterinary createNewVeterinary;
    private final SearchVeterinaryByFilter searchVeterinaryByFilter;
    private final UpdateVeterinary updateVeterinary;
    private final FindVeterinaryById findVeterinaryById;
    private final DeleteVeterinary deleteVeterinary;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public VeterinaryResponse saveNewVeterinary(@RequestBody VeterinaryRequest veterinaryRequest){
        var veterinary = createNewVeterinary.execute(
                veterinaryRequest.getName(),
                veterinaryRequest.getEmail(),
                veterinaryRequest.getPhoneNumber()
        );
        return VeterinaryResponse.fromVeterinary(veterinary);
    }


    @GetMapping("/{veterinaryId}")
    public VeterinaryResponse findById(@PathVariable String veterinaryId){
        var veterinary = findVeterinaryById.execute(veterinaryId);
        return VeterinaryResponse.fromVeterinary(veterinary);
    }


    @GetMapping
    public List<VeterinaryResponse> findByFilter(VeterinaryFilter veterinaryFilter){
        var veterinaries = searchVeterinaryByFilter.execute(veterinaryFilter);
        return veterinaries.stream().map(VeterinaryResponse::fromVeterinary).collect(Collectors.toList());
    }


    @PatchMapping("/{veterinaryId}")
    public VeterinaryResponse updateVeterinary(@PathVariable String veterinaryId,
                                               @RequestBody VeterinaryRequest veterinaryRequest){
        var veterinary = updateVeterinary.execute(
                veterinaryId,
                veterinaryRequest.getName(),
                veterinaryRequest.getPhoneNumber(),
                veterinaryRequest.getEmail()
        );
        return VeterinaryResponse.fromVeterinary(veterinary);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{veterinaryId}")
    public void deleteVeterinary(@PathVariable String veterinaryId){
        deleteVeterinary.execute(veterinaryId);
    }
}
